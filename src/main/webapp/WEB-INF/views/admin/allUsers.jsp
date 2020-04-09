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
            <h2>User settings</h2>
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">Username</th>
                    <th scope="col">Enabled</th>
                    <th scope="col">Admin</th>
                    <th scope="col">Modify</th>
                </tr>
                </thead>
                <tbody>

                <c:forEach items="${userList}" var="user">
                <tr>
                    <td>${user.username}</td>
                    <td>${user.enabled}</td>
                    <td>${user.roles}</td>
                    <td>
                        <a href="/admin/disable/${user.username}">Disable</a>
                        <a href="/admin/enable/${user.username}">Enable</a>
                        <a href="${pageContext.request.contextPath}/admin/roleModifier/${user.username}">Add Role</a>
                    </td>
                </tr>
                </c:forEach>

                </tbody>
            </table>
            <br/>
    </div>
</center>
</body>

<footer id="footer">
    <c:import url="../footer.jsp" charEncoding="UTF-8"/>
</footer>
</html>