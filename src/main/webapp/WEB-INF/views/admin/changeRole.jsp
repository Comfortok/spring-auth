<%@page language="java" contentType="text/html" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">
<head>
    <title>Role Adding</title>
    <c:import url="../header.jsp" charEncoding="UTF-8"/>
</head>

<body>
<center>
    <div class="card-text-center">
        <form:form method="POST" modelAttribute="user" action="${pageContext.request.contextPath}/admin/addRole">
            <h2>Add admin role to user</h2>
            <spring:bind path="username">
                <label for="username">Username</label>
                <form:input type="text" path="username" cssClass="form-control" id="username" name="username"
                            pattern="^[a-zA-Z0-9]+$" title="Digits and numbers only" required="required"/>
                    <c:if test="${status.error != null}">
                        <div class="alert alert-primary" role="alert">
                            <form:errors path="username"/>
                            <form:errors path="roles"/>
                            <META http-equiv="refresh" content="3; URL=showUsers">
                        </div>
                    </c:if>
                <br>
            </spring:bind>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form:form>

        <c:if test="${not empty alert}">
            <div class="alert alert-primary" role="alert">
                <c:out value="${alert}"/>
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