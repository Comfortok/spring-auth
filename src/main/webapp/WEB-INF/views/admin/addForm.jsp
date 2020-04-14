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
                <label for="header"><spring:message code="addForm.header"/></label>
                <form:input type="text" path="header" cssClass="form-control" id="header" name="header"
                            required="required"/>
                <form:errors path="header" cssClass="badge badge-danger"/>
                <br>
            </spring:bind>

            <spring:bind path="releaseDate">
                <form:hidden path="releaseDate"/>
            </spring:bind>

            <spring:bind path="text">
                <label for="text"><spring:message code="addForm.text"/></label>
                <form:textarea path="text" rows="5" cols="40" cssClass="form-control" id="text" name="text"
                               required="required"/>
                <form:errors path="text" cssClass="badge badge-danger"/>
                <br>
            </spring:bind>

            <button type="submit" class="btn btn-primary">
                <spring:message code="addForm.save"/>
            </button>
        </form:form>
    </div>
</center>
</body>
<footer id="footer">
    <c:import url="../footer.jsp" charEncoding="UTF-8"/>
</footer>
</html>