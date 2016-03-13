package com.wicky.biz.web.layouts;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PipelineHolder {
	public static String appContext = "/mansys";

	private Map<String, List<String>> styles = new HashMap<>();
	private Map<String, String> specialCssPathMap = new HashMap<>();
	
	private Map<String, List<String>> javascripts = new HashMap<>();
	private Map<String, String> specialJavascriptPathMap = new HashMap<>();
	
	public static void main(String[] args) {
		new PipelineHolder();
	}

	private static PipelineHolder instance;
	public static PipelineHolder getInstance(){
		if(instance == null){
			instance = new PipelineHolder();
		}
		return instance;
	}
	
	private PipelineHolder() {
		// init special path for css
		specialCssPathMap.put("font-awesome", appContext + "/static/font-awesome/css/font-awesome.css");
		initCssHolder();
		
		// init special path for script
		specialJavascriptPathMap.put("", "");
		//initJavascriptHolder();
	}

	private void initCssHolder() {
		System.out.println("Init CSS Holder now!");
		
		String styleSheetFolder = "assets/stylesheets/";
		try {
			Files.list(Paths.get(getClass().getClassLoader().getResource(styleSheetFolder).toURI())).forEach(path -> {
				if (!path.toFile().isDirectory()) {
					String fileNameAsController = path.toFile().getName();
					System.out.println( path.toFile());
					System.out.println("FileName: " + fileNameAsController);
					
					String content = readFileContent(path);
					
					Matcher matcher = Pattern.compile("\\/\\*([^\\*^\\/]*|[\\*^\\/*]*|[^\\**\\/]*)*\\*\\/").matcher(content);
					if(matcher.find()){
						String firstComment = matcher.group();
//						System.out.println(firstComment);
						
						Matcher matcher2 = Pattern.compile("\\*( )?=( )?require.*").matcher(firstComment);
						while(matcher2.find()){
							String requireDescLine = matcher2.group();
							System.out.println(requireDescLine);
							
							
							List<String> cssList = null;
							if(fileNameAsController.equals("application.scss")){
								cssList = styles.get("*");
								if(cssList == null){
									cssList = new ArrayList<>();
									styles.put("*", cssList);
								}
							}else{
								String controller = fileNameAsController.replace(".scss", "");
								cssList = styles.get(controller);
								if(cssList == null){
									cssList = new ArrayList<>();
									styles.put(controller, cssList);
								}
							}
							
							if(requireDescLine.contains("require_self")){
								String cssContent = content.replaceAll("\\/\\*([^\\*^\\/]*|[\\*^\\/*]*|[^\\**\\/]*)*\\*\\/", "").replaceAll("\\s*", "");
								if(!cssContent.isEmpty()){
									cssList.add(appContext + "/static/css/" + fileNameAsController.replace(".scss", ".css"));
								}
							}else{
								String targetCssName = requireDescLine.replaceFirst("( )*\\*( )?=( )?require( )*", "").trim();
								if(!targetCssName.isEmpty()){
									String filePath = appContext + "/static/css/" + targetCssName;
									// for special css file mapping
									if(specialCssPathMap.containsKey(targetCssName)){
										filePath = specialCssPathMap.get(targetCssName);
									}
									if(!filePath.contains(".") || !filePath.endsWith(".css")){
										filePath += ".css";
									}
									cssList.add(filePath);
								}
							}
						}
					}else{
						System.out.println("No Comments Found!");
					}
					System.out.println("---------------------");
				}
			});
		} catch (IOException | URISyntaxException e1) {
			e1.printStackTrace();
		}
		
		System.out.println("Styles: " + styles);
	}
	
	private void initJavascriptHolder() {
		System.out.println("Init Javascript Holder now!");

		String javascriptFolder = "assets/javascripts/";
		try {
			Files.list(Paths.get(getClass().getClassLoader().getResource(javascriptFolder).toURI())).forEach(path -> {
				if (!path.toFile().isDirectory()) {
					String fileNameAsController = path.toFile().getName();
					System.out.println( path.toFile());
					System.out.println("FileName: " + fileNameAsController);
					
					String content = readFileContent(path);
					
					Matcher matcher = Pattern.compile("\\/\\*([^\\*^\\/]*|[\\*^\\/*]*|[^\\**\\/]*)*\\*\\/").matcher(content);
					if(matcher.find()){
						String firstComment = matcher.group();
//						System.out.println(firstComment);
						
						Matcher matcher2 = Pattern.compile("\\*=( )?require.*").matcher(firstComment);
						while(matcher2.find()){
							String requireDescLine = matcher2.group();
							System.out.println(requireDescLine);
							
							
							List<String> cssList = null;
							if(fileNameAsController.equals("application.scss")){
								cssList = styles.get("*");
								if(cssList == null){
									cssList = new ArrayList<>();
									styles.put("*", cssList);
								}
							}else{
								String controller = fileNameAsController.replace(".scss", "");
								cssList = styles.get(controller);
								if(cssList == null){
									cssList = new ArrayList<>();
									styles.put(controller, cssList);
								}
							}
							
							if(requireDescLine.contains("require_self")){
								String cssContent = content.replaceAll("\\/\\*([^\\*^\\/]*|[\\*^\\/*]*|[^\\**\\/]*)*\\*\\/", "").replaceAll("\\s*", "");
								if(!cssContent.isEmpty()){
									cssList.add(appContext + "/static/css/" + fileNameAsController.replace(".scss", ".css"));
								}
							}else{
								String targetCssName = requireDescLine.replaceFirst("( )*\\*=( )?require( )*", "");
								String filePath = appContext + "/static/css/" + targetCssName;
								// for special css file mapping
								if(specialCssPathMap.containsKey(targetCssName)){
									filePath = specialCssPathMap.get(targetCssName);
								}
								if(!filePath.contains(".") || !filePath.endsWith(".css")){
									filePath += ".css";
								}
								cssList.add(filePath);
							}
						}
					}else{
						System.out.println("No Comments Found!");
					}
					System.out.println("---------------------");
				}
			});
		} catch (IOException | URISyntaxException e1) {
			e1.printStackTrace();
		}

		System.out.println("Styles: " + styles);
	}

	private String readFileContent(Path path) {
		String content = null;
		try {
			content = readFileFromClasspath(path);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content;
	}

	public String readFileFromClasspath(final String fileName) throws IOException, URISyntaxException {
		return new String(Files.readAllBytes(Paths.get(getClass().getClassLoader().getResource(fileName).toURI())));
	}

	public String readFileFromClasspath(final Path path) throws IOException {
		return new String(Files.readAllBytes(path));
	}

	public Map<String, List<String>> getStyles() {
		return styles;
	}

	public void setStyles(Map<String, List<String>> styles) {
		this.styles = styles;
	}

	public Map<String, List<String>> getJavascripts() {
		return javascripts;
	}

	public void setJavascripts(Map<String, List<String>> javascripts) {
		this.javascripts = javascripts;
	}

}
