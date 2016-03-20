<%@ tag %><%@ attribute name="src" required="true" %><%@ attribute name="folder" required="true" %><%
	String folder = (String)jspContext.getAttribute("folder");
	if(!folder.endsWith("/"))folder = folder + "/";
	String src = (String)jspContext.getAttribute("src");
	String prefix = request.getContextPath() + "/static/";
	String result = "";
	if(src.startsWith("//")
			||src.startsWith("http://")
			||src.startsWith("https://")){
		result = src;
	}else if(src.startsWith(folder)){
		result = prefix + src;
	}else if(src.startsWith("/")){
		result = prefix + folder + src.replaceFirst("/", "");
	}else{
		result = prefix + folder + src;
	}
	out.print(result);
%>