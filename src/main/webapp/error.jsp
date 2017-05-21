<%@page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib tagdir ="/WEB-INF/tags" prefix = "t"%>
<fmt:requestEncoding value="UTF-8" />
<c:set var="locale" value="${not empty language ? language : pageContext.request.locale}" scope="session"/>
<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="language"/>
<t:html>
    <c:choose>
        <c:when test="${not empty error}">
            <H2>${error}</H2>
            <br>
            <c:url value="/index.html" var="mainUrl"/>
            <a href="${mainUrl}"><fmt:message key="returnindex"/></a>
        </c:when>
        <c:when test="${not empty message}">
            <h2>
                <fmt:message key='${message}'/>
            </h2>
            <c:if test="${not empty user}">
                <a href="controller?COMMAND=Logout">
                    <fmt:message key="login.exit"/>
                </a>
            </c:if>
        </c:when>
        <c:when test="${not empty pageContext.errorData.requestURI}">
            <H2><fmt:message key="pagenotfound"/> ${pageContext.errorData.requestURI}</H2>
            <br>
            <c:url value="/index.html" var="mainUrl"/>
            <a href="${mainUrl}"><fmt:message key="returnindex"/></a>
        </c:when>
        <c:otherwise><fmt:message key="unexpected"/></c:otherwise>
    </c:choose>
</t:html>