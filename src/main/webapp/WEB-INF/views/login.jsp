<%@page language="java" contentType="text/html" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page session="true" %>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">
<head>
    <title>Login Page</title>
    <c:import url="header.jsp" charEncoding="UTF-8"/>
</head>

<body onload='document.loginForm.username.focus();'>
<h1>Login Form</h1>
<center>
    <div class="card-text-center">
        <form action="${pageContext.request.contextPath}/login" method="post">
            <div class="form-group">
                <label for="username">Username</label>
                <input type="text" class="form-control" id="username" name="username" required
                       aria-describedby="usernameHelp" placeholder="Enter username">
                <small id="usernameHelp" class="form-text text-muted">Latin alphabet and digits only.</small>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" class="form-control" id="password" name="password" required
                       placeholder="Password">
            </div>

            <c:choose>
                <c:when test="${not empty error}">
                    <div class="alert alert-danger" role="alert">
                        <c:out value="${error}"/>
                    </div>
                </c:when>
                <c:when test="${not empty msg}">
                    <div class="alert alert-primary" role="alert">
                        <c:out value="${msg}"/>
                    </div>
                </c:when>
            </c:choose>

            <input type="hidden" name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
    </div>
</center>
</body>
<footer id="footer">
    <c:import url="footer.jsp" charEncoding="UTF-8"/>
</footer>
</html>