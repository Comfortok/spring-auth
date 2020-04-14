<%@page language="java" contentType="text/html" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">
<head>
    <title><spring:message code="changeRole.title"/></title>
    <c:import url="../header.jsp" charEncoding="UTF-8"/>
</head>

<body>
<center>
    <div class="card-text-center">
        <form:form method="POST" modelAttribute="user" action="${pageContext.request.contextPath}/admin/addRole">
            <h2><spring:message code="changeRole.header"/></h2>
            <spring:bind path="username">
                <label for="username"><spring:message code="changeRole.username"/></label>
                <form:input type="text" path="username" cssClass="form-control" id="username" name="username"
                            pattern="^[a-zA-Z0-9]+$" title="Digits and numbers only" required="required"/>
                <form:errors path="username" cssClass="badge badge-danger"/>
                <form:errors path="roles" cssClass="badge badge-danger"/>
                <META http-equiv="refresh" content="3; URL=showUsers">
                <br>
            </spring:bind>
            <button type="submit" class="btn btn-primary">
                <spring:message code="changeRole.button.submit"/>
            </button>
        </form:form>

        <c:if test="${not empty alert}">
            <div class="alert alert-primary" role="alert">
                <spring:message code="changeRole.alert"/>
            </div>
            <META http-equiv="refresh" content="3; URL=showUsers">
        </c:if>
    </div>
</center>
</body>

<footer id="footer">
    <c:import url="../footer.jsp" charEncoding="UTF-8"/>
</footer>
</html>