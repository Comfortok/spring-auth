<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">
<head>
    <c:import url="../header.jsp" charEncoding="UTF-8"/>
</head>
<body>

<div class="grid-container">
    <div class="grid-item item1">
        <div class="nav">
            <ul>
                <li><a href="<c:url value="/user/articles"/>">
                    <spring:message code="nav.list"/>
                </a></li>
                <li><a href="<c:url value="/admin/add"/>">
                    <spring:message code="nav.add"/>
                </a></li>
            </ul>
        </div>
    </div>

    <div class="grid-item item2">
        <form:form action="${pageContext.request.contextPath}/admin/edit/${article.id}">
            <div class="grid-table table2">
                <div class="grid-item item3">
                    <spring:message code="article.title"/>
                </div>
                <div class="grid-item item3">
                    ${article.header}
                </div>
                <div class="grid-item item3">
                    <spring:message code="article.date"/>
                </div>
                <div class="grid-item item3">
                    <fmt:formatDate type="date" value="${article.releaseDate}" pattern="yyyy-MM-dd"/>
                </div>
                <div class="grid-item item3">
                    <spring:message code="article.text"/>
                </div>
                <div class="grid-item item3">
                    ${article.text}
                </div>
                <br/>
            </div>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <input type="submit" value="<spring:message code="article.edit"/>"/>
            </sec:authorize>
        </form:form>

        <form:form method="post" action="${pageContext.request.contextPath}/admin/remove/${article.id}">
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <input type="submit" value="<spring:message code="article.delete"/>"
                       onclick="return confirm('<spring:message code="onclick.delete"/>')"/>
            </sec:authorize>
        </form:form>
        <br/>
        <br/>
        <br/>
        Add comment:
        <form:form action="${pageContext.request.contextPath}/user/articleInfo/${article.id}" modelAttribute="comment"
        method="post">
            <div class="grid-table table2">
                <div class="grid-item">
                    <form:label path="text" cssStyle="font-size: 14px">
                        <spring:message code="article.text"/>
                    </form:label>
                </div>
                <div class="grid-item">
                    <form:textarea path="text" rows="5" cols="40"/>
                    <form:errors path="text"/>
                </div>
                <br/>
            </div>
            <input type="submit" value="<spring:message code="button.save"/>"/>
        </form:form>
        Comments:
        <br/>
        <c:forEach items="${listComments}" var="comment">
            <div class="grid-table table2">
                <div class="grid-item">
                    <spring:message code="comment.text"/>
                </div>
                <div class="grid-item">
                        ${comment.text}
                </div>
                <br/>
            </div>
        </c:forEach>
    </div>
</div>

<c:import url="../footer.jsp" charEncoding="UTF-8"/>
</body>
</html>