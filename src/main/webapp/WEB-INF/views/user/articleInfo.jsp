<%@page language="java" contentType="text/html" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">
<head>
    <c:import url="../header.jsp" charEncoding="UTF-8"/>
</head>
<body>

<div class="card text-center">
    <form:form action="${pageContext.request.contextPath}/admin/edit/${article.id}">
        <div class="card-header">
                ${article.header}
        </div>
        <div class="card-body">
            <p class="card-text">
                    ${article.text}
            </p>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <input type="submit" class="btn btn-warning" value="<spring:message code="article.edit"/>"/>
            </sec:authorize>
        </div>
        <div class="card-footer text-muted">
            <fmt:formatDate type="both" dateStyle="short" timeStyle="short"
                            value="${article.releaseDate}"/>
        </div>
    </form:form>

    <form:form method="post" action="${pageContext.request.contextPath}/admin/remove/${article.id}">
        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <input type="submit" class="btn btn-danger" value="<spring:message code="article.delete"/>"
                   onclick="return confirm('<spring:message code="onclick.delete"/>')"/>
        </sec:authorize>
    </form:form>
</div>

<center>
    <div class="card-text-center">
        <br/>
        <form:form action="${pageContext.request.contextPath}/user/articleInfo/${article.id}" modelAttribute="comment"
                   method="post">
            <div class="form-group">
                <label for="userInfo">${pageContext.request.userPrincipal.name}</label>
                <form:textarea path="text" class="form-control" id="userInfo" rows="3"
                               placeholder="Enter your text"/>
                <form:errors path="text"/>
            </div>
            <input type="submit" class="btn btn-primary" value="<spring:message code="button.save"/>"/>
        </form:form>

        <br/>
        <c:forEach items="${listComments}" var="comment">
            <div class="card">
                <div class="card-header">
                        ${comment.user.username}
                </div>
                <div class="card-body">
                    <p class="card-text">${comment.text}</p>
                        <%--        <a href="#" class="btn btn-primary">Reply</a>--%>
                </div>
            </div>
            <br/>
        </c:forEach>
    </div>
</center>
</body>

<footer id="footer">
    <c:import url="../footer.jsp" charEncoding="UTF-8"/>
</footer>
</html>