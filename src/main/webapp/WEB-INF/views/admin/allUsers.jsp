<%@page language="java" contentType="text/html" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">
<head>
    <title><spring:message code="allUsers.title"/></title>
    <c:import url="../header.jsp" charEncoding="UTF-8"/>
</head>

<body>
<center>
    <div class="card-text-center">
        <h2><spring:message code="allUsers.header"/></h2>
        <table class="table">
            <thead>
            <tr>
                <th scope="col"><spring:message code="allUsers.username"/></th>
                <th scope="col"><spring:message code="allUsers.enabled"/></th>
                <th scope="col"><spring:message code="allUsers.admin"/></th>
                <th scope="col"><spring:message code="allUsers.modify"/></th>
            </tr>
            </thead>
            <tbody>

            <c:forEach items="${userList}" var="user">
                <tr>
                    <td>${user.username}</td>
                    <td>
                        <c:choose>
                            <c:when test="${user.enabled}">
                                <spring:message code="allUsers.enable.true"/>
                            </c:when>
                            <c:otherwise>
                                <spring:message code="allUsers.enable.false"/>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <c:if test="${not empty adminRights}">
                            <spring:message code="allUsers.adminRights.true"/>
                        </c:if>
                    </td>
                    <td>
                        <a href="/admin/disable/${user.username}"><spring:message code="allUsers.disable"/></a><br/>
                        <a href="/admin/enable/${user.username}"><spring:message code="allUsers.enable"/></a><br/>
                        <a href="${pageContext.request.contextPath}/admin/roleModifier/${user.username}">
                            <spring:message code="allUsers.addRole"/>
                        </a>
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