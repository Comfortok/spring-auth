<%@page language="java" contentType="text/html" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="sec"
          uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <c:import url="header.jsp" charEncoding="utf8"/>
</head>
<body>
Some Welcome Article would be here later...
<a class="nav-link" href="${pageContext.request.contextPath}/user/articles">Articles</a>
</body>

<footer id="footer">
	<c:import url="footer.jsp" charEncoding="UTF-8"/>
</footer>
</html>