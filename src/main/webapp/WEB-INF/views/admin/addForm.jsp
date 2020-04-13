<%@ page contentType="text/html; charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="currentDate" class="java.util.Date"/>

<html>
<head>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <c:import url="../header.jsp" charEncoding="utf8"/>
</head>
<body>
<center>
    <div class="card-text-center">
        <c:url value="/admin/articles/save" var="saveAction"/>
        <form:form action="${saveAction}" modelAttribute="article">
            <c:if test="${!empty article.header}">
                <form:hidden path="id"/>
            </c:if>

            <spring:bind path="header">
                <label for="header">Header</label>
                <form:input type="text" path="header" cssClass="form-control" id="header" name="header"
                            required="required"/>
                <form:errors path="header" cssClass="error"/>
                <br>
            </spring:bind>

            <spring:bind path="releaseDate">
                <form:hidden path="releaseDate"/>
<%--                <input id="releaseDate" name="releaseDate" type="hidden"--%>
<%--                       value="<fmt:formatDate type="both" pattern="yyyy-MM-dd HH:mm"--%>
<%--                                value="${currentDate}"/>"/>--%>
<%--                <fmt:formatDate type="both" pattern="yyyy-MM-dd HH:mm"--%>
<%--                                value="${currentDate}"/>--%>

<%--                <label for="releaseDate">Date</label>--%>
<%--                <form:input type="text" path="releaseDate" cssClass="form-control" id="releaseDate" name="releaseDate"--%>
<%--                            required="required" pattern="^\d{4}-\d{2}-\d{2}$" title="yyyy-MM-dd"/>--%>
<%--                <form:errors path="releaseDate" cssClass="error"/>--%>
<%--                <br>--%>
            </spring:bind>

            <spring:bind path="text">
                <label for="text">Text</label>
                <form:textarea path="text" rows="5" cols="40" cssClass="form-control" id="text" name="text"
                            required="required"/>
                <form:errors path="text" cssClass="error"/>
                <br>
            </spring:bind>

            <button type="submit" class="btn btn-primary">
                <spring:message code="button.save"/>
            </button>
        </form:form>
    </div>
</center>
</body>
<footer id="footer">
    <c:import url="../footer.jsp" charEncoding="UTF-8"/>
</footer>
</html>