<%@page language="java" contentType="text/html" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page session="true" %>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">
<head>
    <title><spring:message code="login.title"/></title>
    <c:import url="header.jsp" charEncoding="UTF-8"/>
</head>

<body onload='document.loginForm.username.focus();'>
<center>
    <h1><spring:message code="login.form"/></h1>
    <div class="card-text-center">
        <form action="${pageContext.request.contextPath}/login" method="post">
            <div class="form-group">
                <label for="username"><spring:message code="login.username"/></label>
                <input type="text" class="form-control" id="username" name="username" required
                       aria-describedby="usernameHelp">
                <small id="usernameHelp" class="form-text text-muted">
                    <spring:message code="login.username.help"/></small>
            </div>
            <div class="form-group">
                <label for="password"><spring:message code="login.password"/></label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>

            <c:choose>
                <c:when test="${not empty error}">
                    <div class="alert alert-danger" role="alert">
                        <spring:message code="login.message.error"/>
                    </div>
                </c:when>
                <c:when test="${not empty logout}">
                    <div class="alert alert-primary" role="alert">
                        <spring:message code="login.message.logout"/>
                    </div>
                </c:when>
            </c:choose>

            <input type="hidden" name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
            <button type="submit" class="btn btn-primary">
                <spring:message code="login.button.submit"/></button>
        </form>
    </div>
</center>
</body>
<footer id="footer">
    <c:import url="footer.jsp" charEncoding="UTF-8"/>
</footer>
</html>