<%@page language="java" contentType="text/html" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="sec"
          uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <c:import url="header.jsp" charEncoding="utf8"/>
</head>
<body>

<%--<security:authorize access="isAuthenticated()">--%>
<%--    <security:authentication property="principal.enabled" var="status"/>--%>
<%--    <c:if test="${status == false}">--%>
<%--        <c:redirect url="/403"/>--%>
<%--        <c:out value="False"/>--%>
<%--    </c:if>--%>
<%--</security:authorize>--%>

<div class="jumbotron">
    <h1 class="display-4">Hello, world!</h1>
    <p class="lead">This is a simple hero unit, a simple jumbotron-style component for calling extra attention to featured content or information.</p>
    <hr class="my-4">
    <p>It uses utility classes for typography and spacing to space content out within the larger container.</p>
    <p class="lead">
        <a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/user/articles"
           role="button">Articles</a>
    </p>
</div>
</body>

<footer id="footer">
	<c:import url="footer.jsp" charEncoding="UTF-8"/>
</footer>
</html>