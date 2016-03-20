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

import javax.servlet.ServletContext;

import org.springframework.web.context.ServletContextAware;

public class PipelineHolder implements ServletContextAware{

	private Map<String, List<String>> styles = new HashMap<>();
	
	private Map<String, List<String>> javascripts = new HashMap<>();

	private ServletContext servletContext;

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	
	private static PipelineHolder instance;
	public static PipelineHolder getInstance(){
		if(instance == null){
			instance = new PipelineHolder();
		}
		return instance;
	}
	private PipelineHolder(){}
	
	public void init() {
		String appContext = servletContext.getContextPath();
		initCssHolder(appContext);
		initJavascriptHolder(appContext);
	}
	
	public void reload(){
		String appContext = servletContext.getContextPath();
		initCssHolder(appContext);
		initJavascriptHolder(appContext);
	}

	private void initCssHolder(String appContext) {
		System.out.println("Init CSS Holder now!");
		styles.clear();
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
						
						Matcher matcher2 = Pattern.compile("\\*( )*=( )*((require .*)|(require_self))").matcher(firstComment);
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
								cssList.add(appContext + "/static/css/" + fileNameAsController.replace(".scss", ".css"));
							}else{
								String targetCssName = requireDescLine.replaceFirst("( )*\\*( )*=( )*require ( )*", "").trim();
								if(!targetCssName.isEmpty()){
									String filePath = appContext + "/static/css/" + targetCssName;
//									// for special css file mapping
//									if(specialCssPathMap.containsKey(targetCssName)){
//										filePath = specialCssPathMap.get(targetCssName);
//									}
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
	
	private void initJavascriptHolder(String appContext) {
		System.out.println("Init Javascript Holder now!");
		javascripts.clear();
		String javascriptFolder = "assets/javascripts/";
		try {
			Files.list(Paths.get(getClass().getClassLoader().getResource(javascriptFolder).toURI())).forEach(path -> {
				if (!path.toFile().isDirectory()) {
					String fileNameAsController = path.toFile().getName();
					System.out.println( path.toFile());
					System.out.println("FileName: " + fileNameAsController);
					
					String content = readFileContent(path);
					
					Matcher matcher = Pattern.compile("( )*//( )?=( )?((require .*)|(require_self))").matcher(content);
					boolean found = false;
					while(matcher.find()){
						found = true;
						String requireDescLine = matcher.group();
						System.out.println(requireDescLine);
							
						List<String> jsList = null;
						if(fileNameAsController.equals("application.js")){
							jsList = javascripts.get("*");
							if(jsList == null){
								jsList = new ArrayList<>();
								javascripts.put("*", jsList);
							}
						}else{
							String controller = fileNameAsController.replace(".js", "");
							jsList = javascripts.get(controller);
							if(jsList == null){
								jsList = new ArrayList<>();
								javascripts.put(controller, jsList);
							}
						}
						
						if(requireDescLine.contains("require_self")){
							jsList.add(appContext + "/static/js/" + fileNameAsController);
						}else{
							String targetJSName = requireDescLine.replaceFirst("( )*//( )*=( )*require ( )*", "");
							String filePath = appContext + "/static/js/" + targetJSName;
							// for special js file mapping
//							if(specialJavascriptPathMap.containsKey(targetJSName)){
//								filePath = specialJavascriptPathMap.get(targetJSName);
//							}
							if(!filePath.contains(".") || !filePath.endsWith(".js")){
								filePath += ".js";
							}
							jsList.add(filePath);
						}
					}
					
					if(!found){
						System.out.println("No Comments Found!");
					}
					System.out.println("---------------------");
				}
			});
		} catch (IOException | URISyntaxException e1) {
			e1.printStackTrace();
		}

		System.out.println("Javascripts: " + javascripts);
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
