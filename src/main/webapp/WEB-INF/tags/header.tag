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
        <p align ="right"><a href="controller?COMMAND=EditUser">${user.getPost().getName()} ${user.getFullName()}</a></p>
        <p align ="right"><a href="controller?COMMAND=Logout">Выход</a></p>
        </div>
    </div>
    <div class="easyui-panel" style="padding:5px;">
            <a href="controller?COMMAND=OpenHospital" class="easyui-linkbutton" data-options="toggle:true,group:'g2',plain:true">Стационар</a>
            <a href="controller?COMMAND=OpenDirectories" class="easyui-linkbutton" data-options="toggle:true,group:'g2',plain:true">Справочники</a>
    </div>