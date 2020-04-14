<%@page language="java" contentType="text/html" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<body>
	<h1><spring:message code="403.header"/></h1>

	<c:choose>
		<c:when test="${not empty username}">
			<h2><spring:message code="403.username"/> ${username}
				<br/><spring:message code="403.message"/></h2>
		</c:when>
	</c:choose>

</body>
</html>