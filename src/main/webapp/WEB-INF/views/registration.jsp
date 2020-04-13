<%@page language="java" contentType="text/html" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">
<head>
    <title>Registration</title>
    <c:import url="header.jsp" charEncoding="UTF-8"/>
</head>

<body>
<h3>Reg Form. Checking body tag</h3>
<center>
    <div class="card-text-center">
        <form:form method="POST" modelAttribute="userForm" action="${pageContext.request.contextPath}/registration">
            <h2>Create your account</h2>
            <spring:bind path="username">
                <label for="username">Username</label>
                <form:input type="text" path="username" cssClass="form-control" id="username" name="username"
                            pattern="^[a-zA-Z0-9]+$" title="Digits and numbers only" required="required"/>
                <form:errors path="username" cssClass="error"/>
                <br>
            </spring:bind>

            <spring:bind path="password">
                <label for="password">Password</label>
                <form:input type="password" path="password" cssClass="form-control" id="password" name="password"
                            title="Digits and numbers only" pattern="^[a-zA-Z0-9]+$" required="required"/>
                <form:errors path="password" cssClass="error"/>
                <br>
            </spring:bind>

            <spring:bind path="confirmPassword">
                <div class="form-group">
                    <label for="confirmPassword">Confirm password</label>
                    <form:input type="password" path="confirmPassword" cssClass="form-control" id="confirmPassword"
                                name="confirmPassword" title="Digits and numbers only" pattern="^[a-zA-Z0-9]+$"
                                required="required"/>
                    <form:errors path="confirmPassword" cssClass="error"/>
                </div>
            </spring:bind>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form:form>
    </div>
</center>
</body>

<footer id="footer">
    <c:import url="footer.jsp" charEncoding="UTF-8"/>
</footer>
</html>