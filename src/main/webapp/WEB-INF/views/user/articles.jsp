<%@page language="java" contentType="text/html" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">
<head>
    <c:import url="../header.jsp" charEncoding="UTF-8"/>
</head>

<body>
<center>
    <c:if test="${!empty listArticles}">
        <a class="btn btn-info btn-sm" href="${pageContext.request.contextPath}/user/articles/byHeader"
           role="button"><spring:message code="hello.button.articles.sort.byHeader"/></a>
        <a class="btn btn-info btn-sm" href="${pageContext.request.contextPath}/user/articles/byText"
           role="button"><spring:message code="hello.button.articles.sort.byText"/></a>
        <form:form method="post" action="${pageContext.request.contextPath}/admin/remove">
            <div id="accordion">
                <c:forEach items="${listArticles}" var="article" varStatus="loop">
                    <div class="card-text-center">
                        <div class="card-header" id="heading_${loop.index}">
                            <h5 class="mb-0">
                                <div class="row">
                                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                                        <div class="col-auto">
                                            <div class="form-check">
                                                <input class="form-check-input position-static" type="checkbox"
                                                       id="articleId"
                                                       name="articleId" value="${article.id}" aria-label="...">
                                            </div>
                                        </div>
                                    </sec:authorize>

                                    <div class="col-auto">
                                        <a class="nav link" data-toggle="collapse" data-target="#collapse_${loop.index}"
                                           aria-expanded="true" aria-controls="collapse_${loop.index}">
                                                ${article.header}
                                        </a>
                                    </div>
                                </div>
                            </h5>
                        </div>

                        <div id="collapse_${loop.index}" class="collapse hide" aria-labelledby="heading_${loop.index}"
                             data-parent="#accordion">
                            <div class="card-body">
                                <h6 class="card-subtitle mb-2 text-muted">
                                    <fmt:formatDate type="both" dateStyle="short" timeStyle="short"
                                                    value="${article.releaseDate}"/>
                                </h6>
                                <p class="card-text block">
                                        ${article.text}
                                </p>
                                <a href="#" class="button">More</a><br/>
                                <a href="/user/articleInfo/${article.id}" class="card-link">
                                    <spring:message code="articles.view"/></a>
                                <sec:authorize access="hasRole('ROLE_ADMIN')">
                                    <a href="<c:url value="/admin/edit/${article.id}"/>" class="card-link">
                                        <spring:message code="articles.edit"/></a>
                                </sec:authorize>
                            </div>
                        </div>
                    </div>
                </c:forEach>
                <br/>

                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <input type="submit" class="btn btn-primary" value="<spring:message code="articles.delete"/>"
                           onclick="return confirm('<spring:message code="articles.onclick.delete"/>')"/>
                    <br/>
                </sec:authorize>
            </div>
        </form:form>
    </c:if>
</center>
</body>

<footer id="footer">
    <c:import url="../footer.jsp" charEncoding="UTF-8"/>
</footer>

<script type="text/javascript">
    $(document).ready(function(){

        $('.button').click(function(){
            $('.block').toggleClass('opener');
            if (!$(this).data('status')) {
                $(this).data('status', true).html('Hide');
            } else {
                $(this).data('status', false).html('More');
            }
        });

    });
</script>
</html>