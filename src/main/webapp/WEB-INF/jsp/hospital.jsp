<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib tagdir ="/WEB-INF/tags" prefix = "t"%>
<fmt:requestEncoding value="UTF-8"/>

<t:html>
    <t:header/>
    <div style="margin:10px 0 10px 0;"></div>
    <div class="easyui-tabs" style="width:95%;">
        <div title="Отделение" style="padding:10px">
            <a href="controller?COMMAND=OpenDirectories" class="easyui-linkbutton" data-options="iconCls:'icon-add'">Добавить</a>
            <form id="findsick" method="post" action="controller?COMMAND=FindSickListBy">
                <input type="search" name="firstname" class="easyui-searchbox" data-options="prompt:'Фамилия'"
                       style="width:25%" value="">
                <input class="easyui-datebox" name="dateIn" data-options="prompt:'Дата поступления'"
                       style="width:25%;" value="">
                <a href="javascript:void(0)" data-options="iconCls:'icon-search'" class="easyui-linkbutton"
                   onclick="submitForm('findsick')"
                   style="width:80px">Найти</a>
            </form>
            <div style="margin:20px 0;"></div>
            <table>
                <tr>
                    <th>Номер палаты</th>
                    <th>Фамилия Имя Отчество</th>
                    <th>Пол</th>
                    <th>Дата поступления</th>
                    <th>Текущий диагноз</th>
                    <th>Действие</th>
                </tr>
                <c:forEach items="${sickLists}" var="sickList">
                    <tr>
                        <td>${sickList.getRoom()}</td>
                        <td>${sickList.getPatient().getFullName()}</td>
                        <td>${sickList.getPatient().getSex().getName()}</td>
                        <td>
                            <fmt:formatDate pattern="dd/MM/yyyy" value="${sickList.getDateIN()}"/>
                        </td>
                        <td>${sickList.getFinalDiagnose().getDiagnoseName()}</td>
                        <td>
                            <a href="controller?COMMAND=EditSickList&id=${sickList.getPrimaryKey()}"
                               class="easyui-linkbutton" data-options="iconCls:'icon-edit'" style="float:left"></a>
                            <form id="delsl${sickList.getPrimaryKey()}" method="post"
                                  action="controller?COMMAND=DeleteSickList" style="float:left">
                                <input type="hidden" name="id" value="${sickList.getPrimaryKey()}">
                                <a href="javascript:void(0)"
                                   onclick="submitForm('delsl${sickList.getPrimaryKey()}')"
                                   class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"></a>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div title="Назначения" style="padding:10px">
            <form id="findpres" method="post" action="controller?COMMAND=FindPressriptionByPatientLastName">
                <input type="search" name="firstname" class="easyui-searchbox" data-options="prompt:'Фамилия'"
                       style="width:25%" value="">
                <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search'"
                   onclick="submitForm('findpres')">Найти</a>
            </form>
            <div style="margin:20px 0;"></div>
            <table>
                <tr>
                    <th>Номер палаты</th>
                    <th>Фамилия Имя Отчество</th>
                    <th>Вид назначения</th>
                    <th>Количество<br>
                        План/Факт
                    </th>
                    <th>Описание</th>
                    <th>Действие</th>
                </tr>
                <c:forEach items="${prescriptionList}" var="prescription">
                    <tr>
                        <td>${prescription.getSurveyHistory().getSickList().getRoom()}</td>
                        <td>${prescription.getSurveyHistory().getSickList().getPatient().getFullName()}</td>
                        <td>${prescription.getPrescriptionType().getName()}</td>
                        <td>${prescription.getQuantity()}/${prescription.getCompleted()}</td>
                        <td>${prescription.getDescription()}</td>
                        <td>
                            <form id="runp${prescription.getPrimaryKey()}" method="post"
                                  action="controller?COMMAND=ExecutePrescriptionHosp">
                                <input type="hidden" name="id" value="${prescription.getPrimaryKey()}">
                                <a href=javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'"
                                   onclick="submitForm('runp${prescription.getPrimaryKey()}')"></a>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</t:html>