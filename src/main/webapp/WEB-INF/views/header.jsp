<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<head>
    <title>Title</title>
</head>
<body>
<div class="header">
    <a href="${pageContext.request.contextPath}/user/articles">
        <spring:message code="header.logo"/>
    </a>
    <div class="header-right">
        <a href="?lang=en"><spring:message code="header.en"/></a>
        <a href="?lang=ru"><spring:message code="header.ru"/></a>
    </div>
    <br/>
</div>
</body>
</html>