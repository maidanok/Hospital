<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib tagdir ="/WEB-INF/tags" prefix = "t"%>
<fmt:requestEncoding value="UTF-8"/>

<t:html>
    <t:header/>
    <div style="margin:20px 0 10px 0;"></div>
    <div class="easyui-tabs" style="width:95%;">
        <div title="Осмотр пациента №${surveyHistory.getPrimaryKey()}" style="padding:10px">
            <div class="easyui-layout" style="width:700px;height:350px;">
                <form name="survey" method="post" action="">
                    <div style="margin-bottom:20px">
                        <input type="hidden" name="id" value="${surveyHistory.getPrimaryKey()}">
                        <input class="easyui-textbox" name="patient" style="width:50%" data-options="label:'Пациент'"
                               value="${surveyHistory.getSickList().getPatient().getFullName()}">
                    </div>
                    <div style="margin-bottom:20px">
                        <input class="easyui-textbox" name="staff" style="width:50%" data-options="label:'Врач'"
                               value="${surveyHistory.getStaff().getFullName()}">
                    </div>
                    <div style="margin-bottom:20px">
                        <input class="easyui-datebox" name="surveydate"
                               data-options="label:'Дата осмотра', labelPosition:'top'" style="width:50%;"
                               value="${surveyHistory.getSurveyDate()}">
                    </div>
                    <div style="margin-bottom:20px">
                        <input class="easyui-textbox" name="description" style="width:50%;height:80px"
                               data-options="label:'Примечания:', labelPosition:'top',multiline:true"
                               value="${surveyHistory.getDescription()}">
                    </div>
                    <div style="margin-bottom:20px">
                        <input type="checkbox" name="isdischarge" value="">Пациент готов к выписке
                    </div>
                    <div style="text-align:left;padding:5px 0">
                        <input type="submit" data-options="iconCls:'icon-ok'" class="easyui-linkbutton" value="OK"
                               style="width:110px; height:27px">
                        <a href="javascript:history.back()" data-options="iconCls:'icon-cancel'"
                           class="easyui-linkbutton"
                           onclick=" " style="width:110px">Отменить</a>
                    </div>
                </form>
            </div>
        </div>

        <div title="Назначения" style="padding:10px">
            <form>
                <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'">Добавить</a>
                <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">Удалить выбранные</a><br>
                <table>
                    <tr>
                        <th></th>
                        <th>Дата</th>
                        <th>Кол-во.<br> План.факт</th>
                        <th>Назначение</th>
                        <th>Описание</th>
                        <th>Действие</th>
                    </tr>
                    <c:forEach items="${prescriptionList}" var="prescription">
                        <tr>
                            <td><input id="${prescription.getPrimaryKey()}" type="checkbox"/></td>
                            <td><fmt:formatDate pattern="dd/MM/yyyy" value="${prescription.getSurveyHistory().getSurveyDate()}"/></td>
                            <td>${prescription.getQuantity()}/${prescription.getCompleted()}</td>
                            <td>${prescription.getPrescriptionType().getName()}</td>
                            <td>${prescription.getDescription()}</td>
                            <td><a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'"></a>
                                <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-no'"></a></td>
                        </tr>
                    </c:forEach>
                </table>
                <div style="text-align:left;padding:5px 0">
                    <a href="javascript:void(0)" data-options="iconCls:'icon-ok'" class="easyui-linkbutton" onclick=" "
                       style="width:80px">ОК</a>
                </div>
            </form>
        </div>
    </div>
</t:html>