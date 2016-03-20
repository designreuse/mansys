<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@page import="com.wicky.biz.web.layouts.PipelineHolder"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="w" %>
<!DOCTYPE html>
<html>
<c:set var="showSkinConfig" value="false" />
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

	<sitemesh:write property='meta' />
	
    <title><sitemesh:write property='title' /></title>
    
	<meta name="controller" content="${controllerName}" />
	<meta name="action" content="${controllerAction}" />
	<%
	PipelineHolder holder = PipelineHolder.getInstance();
	
	java.util.Map<String, java.util.List<String>> styles = holder.getStyles();
	String controllerName = (String)request.getAttribute("controllerName");
	java.util.List<String> controllerCssList = styles.get(controllerName);
	if(controllerCssList != null && !controllerCssList.isEmpty()){
		String scriptTags = "";
		for(String cssPath: controllerCssList){
			scriptTags += ("<link href=\""+cssPath+"\" rel=\"stylesheet\" />\n	");
		}%>
	<!-- Include style per-controller - vendor plugins -->
	<%
		out.println(scriptTags);
	}
 
	java.util.List<String> mainCssList = styles.get("*");
	if(mainCssList != null && !mainCssList.isEmpty()){
		String scriptTags = "";
		for(String cssPath: mainCssList){
			scriptTags += ("<link href=\""+cssPath+"\" rel=\"stylesheet\" />\n	");
		}%>
	<!-- Main css styles -->
	<%
		out.println(scriptTags);
	}
	
	java.util.Map<String, java.util.List<String>> javascripts = holder.getJavascripts();
	java.util.List<String> mainJSList = javascripts.get("*");
	if(mainJSList != null && !mainJSList.isEmpty()){
		String scriptTags = "";
		for(String jsPath: mainJSList){
			scriptTags += ("<script src=\""+jsPath+"\"></script>\n	");
		}%>
	<!-- Main javascript files -->
	<%
		out.println(scriptTags);
	}
	%>
	<sitemesh:write property='head' />
	
</head>

<body>
	<c:if test="${showSkinConfig}">
	<!-- Skin configuration box -->
	<jsp:include page="skin-config.jsp" />
	</c:if>
	
	<!-- Wrapper-->
    <div id="wrapper" class="${controllerName}.${controllerAction}">
    
    	<!-- Navigation -->
    	<jsp:include page="left-menu.jsp" >
    		<jsp:param value="myVar" name="myVar"/>
    	</jsp:include>
    	
    	<!-- Chat box -->
    	<jsp:include page="small-chat-box.jsp" >
    		<jsp:param value="myVar" name="myVar"/>
    	</jsp:include>
    	
    	<!-- Right side bar -->
    	<jsp:include page="right-sidebar.jsp" >
    		<jsp:param value="myVar" name="myVar"/>
    	</jsp:include>

    	<!-- Page wrapper -->
        <div id="page-wrapper" class="gray-bg ${extraClass}">
        
        	<!-- Top navigation bar -->
			<jsp:include page="navibar-top.jsp" >
	    		<jsp:param value="myVar" name="myVar"/>
	    	</jsp:include>
	        
	        <!-- Main view  -->
    		<sitemesh:write property='body' />
    		
    		<!-- Footer -->
			<jsp:include page="footer.jsp" >
	    		<jsp:param value="myVar" name="myVar"/>
	    	</jsp:include>
    		
        </div>
        <!-- End page wrapper-->
        
    </div>
	<!-- End wrapper-->
	<% 
	java.util.List<String> contollerJSList = javascripts.get(controllerName);
	if(contollerJSList != null && !contollerJSList.isEmpty()){
		String scriptTags = "";
		for(String jsPath: contollerJSList){
			scriptTags += ("<script src=\""+jsPath+"\"></script>\n	");
		}%>
	<!-- Include javascript per-controller - vendor plugins -->
	<%
		out.println(scriptTags);
	}
	%>
	<% 
	String actionName = (String)request.getAttribute("controllerAction");
	java.util.List<String> ctlActionList = javascripts.get(controllerName + "." + actionName);
	if(ctlActionList != null && !ctlActionList.isEmpty()){
		String scriptTags = "";
		for(String jsPath: ctlActionList){
			scriptTags += ("<script src=\""+jsPath+"\"></script>\n	");
		}%>
	<!-- Include javascript per-action -->
	<%
		out.println(scriptTags);
	}
	%>
	<!-- Include javascript per-view -->
	<sitemesh:write property='page.script' />
	
	<c:if test="${showSkinConfig}">
	<!-- Skin configuration box -->
	<jsp:include page="skin-config.script.jsp" />
	</c:if>
</body>
</html>
