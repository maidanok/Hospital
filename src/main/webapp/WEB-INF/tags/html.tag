<!doctype html>
<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ attribute name="css" required="false" rtexprvalue="true" type="java.lang.String"%>
<%@ attribute name="javascript" required="false" rtexprvalue="true" type="java.lang.String"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="locale" value="${not empty language ? language : pageContext.request.locale}" scope="session"/>
<html>
<head>
<c:url var="cssPath" value="/jquery-easyui-1.5.2"/>
<link type="text/css" rel="stylesheet" href="${cssPath}/themes/default/easyui.css"/>
<link type="text/css" rel="stylesheet" href="${cssPath}/themes/icon.css"/>
<link type="text/css" rel="stylesheet" href="${cssPath}/demo/demo.css"/>
<c:if test="${not empty css}">
<link type="text/css" rel="stylesheet" href="${cssPath}/${css}"/>
</c:if>
<c:url var="javascriptPath" value = "/jquery-easyui-1.5.2"/>
<c:url var="myScriptPart" value = "/js"/>
<script type="text/javascript" src="${javascriptPath}/jquery.min.js"></script>
<script type="text/javascript" src="${myScriptPart}/main.js"></script>
<script type="text/javascript" src="${javascriptPath}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${javascriptPath}/locale/easyui-lang-${locale}.js"></script>
<c:if test="${not empty javascript}">
<script type="text/javascript" src="${scriptPath}/${javascript}"></script>
</c:if>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>
<jsp:doBody/>
</body>
</html>
