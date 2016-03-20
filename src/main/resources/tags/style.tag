<%@tag import="java.util.HashMap"%><%@ tag dynamic-attributes="attrs" %><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%@ taglib tagdir="/WEB-INF/tags" prefix="w" %><%@ attribute name="src" required="true" %><link href="<w:asset src="${src}" folder="css"/>" <% 
	HashMap<String, String> attrs = (HashMap<String, String>)jspContext.getAttribute("attrs");
	for(HashMap.Entry<String, String> entry: attrs.entrySet()){
		out.print(entry.getKey() + "=\""+entry.getValue()+"\" ");
	}
%>rel="stylesheet" />