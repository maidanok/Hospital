<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib tagdir ="/WEB-INF/tags" prefix = "t"%>
<t:html>
    <t:header/>

    <div style="margin:10px 0 10px 0;"></div>
    <div class="easyui-tabs" style="width:95%;">
        <div title="Стационар" style="padding:10px">
            <a href="controller?COMMAND=OpenDirectories" class="easyui-linkbutton" data-options="iconCls:'icon-add'">Добавить</a>
            <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">Удалить выбранные</a><br>
            <input type="search" class="easyui-searchbox" data-options="prompt:'Фамилия'" style="width:25%">
            <input type="search" class="easyui-datebox" data-options="prompt:'Дата поступления'" style="width:25%">
            <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">Найти</a>
            <table>
                <tr>
                    <th>Отметить</th>
                    <th>Номер палаты</th>
                    <th>Фамилия Имя Отчество</th>
                    <th>Пол</th>
                    <th>Дата поступления</th>
                    <th>Текущий диагноз</th>
                    <th>Действие</th>
                </tr>
                <c:forEach items="${sickLists}" var="sickList">
                    <tr>
                        <td><input id="${sickList.getPrimaryKey()}" type="checkbox"/></td>
                        <td>${sickList.getRoom()}</td>
                        <td>${sickList.getPatient().getFullName()}</td>
                        <td>${sickList.getPatient().getSex().getName()}</td>
                        <td>${sickList.getDateIN()}</td>
                        <td>${sickList.getFinalDiagnose().getDiagnoseName()}</td>
                        <td><a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'"></a>
                            <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"></a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div title="Назначения" style="padding:10px">
            <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'">Добавить</a>
            <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">Удалить выбранные</a><br>
            <input type="search" class="easyui-searchbox" data-options="prompt:'Фамилия'" style="width:25%">
            <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">Найти</a>
            <table>
                <tr>
                    <th>Отметить</th>
                    <th>Номер палаты</th>
                    <th>Фамилия Имя Отчество</th>
                    <th>Вид назначения</th>
                    <th>Количество</th>
                    <th>Описание</th>
                    <th>Действие</th>
                </tr>
                <c:forEach items="${prescriptionList}" var="prescription">
                    <tr>
                        <td><input id="${prescription.getPrimaryKey()}" type="checkbox"/></td>
                        <td>${prescription.getSurveyHistory().getSickList().getRoom()}</td>
                        <td>${prescription.getSurveyHistory().getSickList().getPatient().getFullName()}</td>
                        <td>${prescription.getPrescriptionType().getName()}</td>
                        <td>${prescription.getQuantity()}</td>
                        <td>${prescription.getDescription()}</td>
                        <td><a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'"></a>
                            <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-no'"></a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</t:html>