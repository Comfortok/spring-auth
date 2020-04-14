<%@page language="java" contentType="text/html" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">
<head>
    <title><spring:message code="registration.title"/></title>
    <c:import url="header.jsp" charEncoding="UTF-8"/>
</head>

<body>
<center>
    <div class="card-text-center">
        <form:form method="POST" modelAttribute="userForm" action="${pageContext.request.contextPath}/registration">
            <h2><spring:message code="registration.form"/></h2>
            <spring:bind path="username">
                <label for="username"><spring:message code="registration.username"/></label>
                <form:input type="text" path="username" cssClass="form-control" id="username" name="username"
                            required="required"/>
                <form:errors path="username" cssClass="badge badge-danger"/>
                <br>
            </spring:bind>

            <spring:bind path="password">
                <label for="password"><spring:message code="registration.password"/></label>
                <form:input type="password" path="password" cssClass="form-control" id="password" name="password"
                            required="required"/>
                <form:errors path="password" cssClass="badge badge-danger"/>
                <br>
            </spring:bind>

            <spring:bind path="confirmPassword">
                <div class="form-group">
                    <label for="confirmPassword"><spring:message code="registration.confirmPassword"/></label>
                    <form:input type="password" path="confirmPassword" cssClass="form-control" id="confirmPassword"
                                name="confirmPassword" required="required"/>
                    <form:errors path="confirmPassword" cssClass="badge badge-danger"/>
                </div>
            </spring:bind>
            <button type="submit" class="btn btn-primary"><spring:message code="registration.button.send"/></button>
        </form:form>
    </div>
</center>
</body>

<footer id="footer">
    <c:import url="footer.jsp" charEncoding="UTF-8"/>
</footer>
</html>