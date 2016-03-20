<%@ tag %><%@ attribute name="src" required="true" %><%
	String folder = "img/";
	String src = (String)jspContext.getAttribute("src");
	String prefix = request.getContextPath() + "/static/";
	String result = "";
	if(src.startsWith("//")
			||src.startsWith("http://")
			||src.startsWith("https://")){
		result = src;
	}else if(src.startsWith("/")){
		result = prefix + folder + src.replaceFirst("/", "");
	}else if(src.startsWith(folder)){
		result = prefix + src;
	}
	out.print(result);
%>