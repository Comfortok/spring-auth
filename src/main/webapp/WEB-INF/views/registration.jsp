<%@page language="java" contentType="text/html" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<head>
    <title>Registration</title>
</head>
<body>

<form:form method="POST" modelAttribute="userForm" action="${pageContext.request.contextPath}/registration">
    <h2>Create your account</h2>
    <spring:bind path="username">
        <form:input type="text" path="username" placeholder="Username"
                    autofocus="true" pattern="^[a-zA-Z0-9]+$" title="Digits and numbers only"/>
        <form:errors path="username"/>
    </spring:bind>

    <spring:bind path="password">
        <form:input type="password" path="password" placeholder="Password"
                    title="Digits and numbers only" pattern="^[a-zA-Z0-9]+$"/>
        <form:errors path="password"/>
    </spring:bind>

    <spring:bind path="confirmPassword">
        <form:input type="password" path="confirmPassword" placeholder="Confirm your password"
                    title="Digits and numbers only" pattern="^[a-zA-Z0-9]+$"/>
        <form:errors path="confirmPassword"/>
    </spring:bind>

    <button type="submit">Submit</button>
</form:form>

</body>
</html>