<%@page language="java" contentType="text/html" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <li><a href="<c:url value="/admin/add"/>">
                        <spring:message code="nav.add"/>
                    </a></li>
                </sec:authorize>
            </ul>
        </div>
    </div>

    <div class="grid-item item2">
        <c:if test="${!empty requestScope.error}">
            <c:out value="${requestScope.error}"/>
        </c:if>
        <c:if test="${!empty listArticles}">
            <form:form method="post" action="${pageContext.request.contextPath}/admin/remove">
                <c:forEach items="${listArticles}" var="article">
                    <div class="grid-table">
                        <div class="grid-item">
                            <div class="article">
                                <p>${article.header}</p>
                                <c:set var="articleText" value="${article.text}"/>
                                <c:choose>
                                <c:when test="${articleText.length() > 10}">
                                    ${fn:substring(articleText, 0, 10).concat("...")}
                                </c:when>
                                <c:otherwise>
                                    ${article.text}
                                </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                        <div class="grid-item">
                            <div class="date-cell">
                                <fmt:formatDate pattern="yyyy-MM-dd"
                                                value="${article.releaseDate}"/>
                            </div>
                        </div>

                        <div class="grid-item">
                        </div>
                        <div class="grid-item">
                            <div class="modify-cell">
                                <sec:authorize access="hasRole('ROLE_ADMIN')">
                                    <a href="<c:url value="/admin/edit/${article.id}"/>">
                                        <spring:message code="article.edit"/>
                                    </a>
                                </sec:authorize>
                                <a href="/user/articleInfo/${article.id}">
                                    <spring:message code="article.view"/>
                                </a>
                                <td>
                                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                                        <input type="checkbox" value="${article.id}" name="articleId">
                                    </sec:authorize>
                                </td>
                            </div>
                        </div>
                        <br/>
                    </div>
                </c:forEach>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                <input type="submit" value="<spring:message code="article.delete"/>"
                       onclick="return confirm('<spring:message code="onclick.delete"/>')"/>
                </sec:authorize>
            </form:form>
        </c:if>
    </div>
</div>

<c:import url="../footer.jsp" charEncoding="UTF-8"/>
</body>
</html>