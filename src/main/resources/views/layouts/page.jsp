<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<c:set var="showSkinConfig" value="false" />
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

	<sitemesh:write property='meta' />
	
    <title><sitemesh:write property='title' /></title>
    
	<!-- Include style per-controller - vendor plugins -->
	<!-- include-controller-css -->
	
	<!-- Main css styles -->
	<% 
	java.util.Map<String, java.util.List<String>> styles = com.wicky.biz.web.layouts.PipelineHolder.getInstance().getStyles();
	java.util.List<String> mainCssList = styles.get("*");
	if(mainCssList != null && !mainCssList.isEmpty()){
		String mainScriptTags = "";
		for(String mainCssPath: mainCssList){
			mainScriptTags += ("<link href=\""+mainCssPath+"\" rel=\"stylesheet\" />\n	");
		}
		out.println(mainScriptTags);
	}
	%>

	<!-- Main javascript files -->
	<!-- include-main-js -->
	
<%--     <script src="js/jquery-2.1.1.js"></script> --%>
<%--     <script src="js/bootstrap.min.js"></script> --%>
<%--     <script src="js/plugins/metisMenu/jquery.metisMenu.js"></script> --%>
<%--     <script src="js/plugins/slimscroll/jquery.slimscroll.min.js"></script> --%>
    
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
	
	<!-- Include javascript per-controller - vendor plugins -->
	<!-- include-controller-js -->

	<!-- Include javascript per-view -->
	<sitemesh:write property='page.script' />
	
	<c:if test="${showSkinConfig}">
	<!-- Skin configuration box -->
	<jsp:include page="skin-config.script.jsp" />
	</c:if>
</body>
</html>
