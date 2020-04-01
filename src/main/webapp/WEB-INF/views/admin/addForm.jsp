<%@ page contentType="text/html; charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <c:import url="../header.jsp" charEncoding="utf8"/>
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
        <c:url value="/admin/articles/save" var="saveAction"/>
        <form:form action="${saveAction}" modelAttribute="article">
            <c:if test="${!empty article.header}">
                <form:hidden path="id"/>
            </c:if>
            <div class="grid-table table2">
                <div class="grid-item">
                    <form:label path="header" cssStyle="font-size: 14px">
                        <spring:message code="article.title"/>
                    </form:label>
                </div>
                <div class="grid-item">
                    <form:input path="header"/>
                    <form:errors path="header" cssStyle="color: red; font-size: 12px" />
                </div>
                <div class="grid-item">
                    <form:label path="releaseDate" cssStyle="font-size: 14px">
                        <spring:message code="article.date"/>
                    </form:label>
                </div>
                <div class="grid-item">
                    <form:input path="releaseDate" pattern="^\d{4}-\d{2}-\d{2}$" title="yyyy-MM-dd"/>
                    <form:errors path="releaseDate" cssStyle="color: red; font-size: 12px" />
                </div>
                <div class="grid-item">
                    <form:label path="text" cssStyle="font-size: 14px">
                        <spring:message code="article.text"/>
                    </form:label>
                </div>
                <div class="grid-item">
                    <form:textarea path="text" rows="5" cols="40"/>
                    <form:errors path="text" cssStyle="color: red; font-size: 12px" />
                </div>
                <br/>
            </div>
            <input type="submit" value="<spring:message code="button.save"/>"/>
        </form:form>
    </div>
</div>
</body>
</html>