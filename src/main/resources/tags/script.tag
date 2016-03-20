<%@tag import="java.util.HashMap"%><%@ tag dynamic-attributes="attrs" %><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%@ attribute name="src" required="true" %><script src="${pageContext.request.contextPath}/static/js/${src}" <% 
	HashMap<String, String> attrs = (HashMap<String, String>)jspContext.getAttribute("attrs");
	for(HashMap.Entry<String, String> entry: attrs.entrySet()){
		out.print(entry.getKey() + "=\""+entry.getValue()+"\" ");
	}
%>></script>