<%@page language="java" contentType="text/html" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="sec"
          uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <c:import url="../header.jsp" charEncoding="utf8"/>
</head>
<body>

<div class="jumbotron">
    <h1 class="display-4"><spring:message code="404.header"/></h1>
    <p class="lead"><h2><spring:message code="404.message"/></h2></p>
</div>
</body>

<footer id="footer">
    <c:import url="../footer.jsp" charEncoding="UTF-8"/>
</footer>
</html>