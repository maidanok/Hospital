<%@tag language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    <div class="easyui-layout" style="width:100%; height:120px;padding:10px;border:0;">
        <div data-options="region:'west'" style="width:20%;padding:10px;border:0; align:left">
        <c:url var="logoPath" value="/img"/>
        <img src="${logoPath}/logo.png" height="50" alt="альтернативный текст">
            </div>

        <div data-options="region:'center'" style="width:50%;padding:10px;border:0;halign:top">
            <h1 align ="center">Hospital</h1>
        </div>

        <div data-options="region:'east'" style="width:30%;padding:10px;border:0;">
        <p align ="right"><a href="">Врач Фамилия имя отчество</a></p>
        <p align ="right"><a href="">Выход</a></p>
        </div>
    </div>