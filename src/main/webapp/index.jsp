<%@page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<head>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/fonts-v3.css">
    <link rel="stylesheet" href="css/base-v4.css">
    <link rel="stylesheet" href="css/properties-79c76308-f359-4b06-866a-a6a2b435e248.css">
    <META http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<c:set var="context" value="${contextPath}"/>


<c:if test="${not empty user}">
    <c:set var="role" value="${user.getPost()}"/>
    <c:if test="${role eq 'ADMINISTRATOR'}">
        <jsp:forward page="controller?COMMAND=OpenDirectories"></jsp:forward>
    </c:if>
    <c:if test="${role eq 'DOCTOR' or role eq 'NURSE'}">
        <jsp:forward page="controller?COMMAND=OpenHospital"></jsp:forward>
    </c:if>
</c:if>
<jsp:forward page="${context }/WEB-INF/jsp/login.jsp"></jsp:forward>
</body>
</html>