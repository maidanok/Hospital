<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib tagdir ="/WEB-INF/tags" prefix = "t"%>
<fmt:requestEncoding value="UTF-8"/>

<t:html>
    <t:header/>
    <div style="margin:20px 0 10px 0;"></div>
    <div class="easyui-tabs" style="width:95%;">
        <div title="Больничный лист" style="padding:10px">
            <div class="easyui-layout" style="width:700px;height:450px;">
                <form id="ff" method="post" action="controller?COMMAND=SaveSickList">
                    <div style="margin-bottom:20px">
                        <input type="hidden" name="id" value="${sickList.getPrimaryKey()}">
                        <input type="hidden" name="patientid" value="${sickList.getPatient().getPrimaryKey()}">
                        <input class="easyui-textbox" name="fullname" style="width:50%"
                               data-options="label:'Фамилия Имя Отчество',labelPosition:'top', editable:0"
                               value="${sickList.getPatient().getFullName()}">
                    </div>
                    <div style="margin-bottom:20px">
                        <input class="easyui-datebox" name="datein"
                               data-options="label:'Дата поступления', labelPosition:'top'" style="width:50%;"
                               value="${sickList.getDateIN()}">
                    </div>
                    <div style="margin-bottom:20px">
                        <input class="easyui-datebox" name="dateout"
                               data-options="label:'Дата выписки', labelPosition:'top', disabled:1" style="width:50%;"
                               value="${sickList.getDateOUT()}">
                    </div>
                    <div style="margin-bottom:20px">
                        <input class="easyui-textbox" name="room" style="width:50%" data-options="label:'Палата:'"
                               value="${sickList.getRoom()}">
                    </div>

                    <div style="margin-bottom:20px">
                        <c:set var="finalDiagnose" value="${sickList.getFinalDiagnose().getPrimaryKey()}"/>
                        <c:if test="${not empty alldiagnose}">
                            <select class="easyui-combobox" name="diagnose" label="Диагноз" style="width:50%">
                                <c:forEach items="${alldiagnose}" var="diagnose">
                                    <c:set var="diagnoseid" value="${diagnose.getPrimaryKey()}"></c:set>
                                    <option value="${diagnose.getPrimaryKey()}"
                                    <c:if test="${diagnoseid eq finalDiagnose}">
                                        <c:out value='selected="selected"'/>
                                    </c:if>
                                    >${diagnose.getDiagnoseName()}</option>
                                </c:forEach>
                            </select>
                        </c:if>
                    </div>

                    <div style="margin-bottom:20px">
                        <input class="easyui-textbox" name="symptoms" style="width:50%;height:80px"
                               data-options="label:'Анамнез:', labelPosition:'top',multiline:true"
                               value="${sickList.getSymptoms()}">
                    </div>

                    <div style="text-align:left;padding:5px 0">
                        <a href="javascript:void(0)" data-options="iconCls:'icon-ok'" class="easyui-linkbutton" onclick="submitForm('ff')"
                           style="width:80px">ОК</a>
                        <a href="javascript:history.back()" data-options="iconCls:'icon-cancel'"
                           class="easyui-linkbutton"
                           onclick=" " style="width:110px">Отменить</a>
                    </div>
                </form>
            </div>
        </div>
        <div title="Осмотры" style="padding:10px">
            <h3>Осмотры</h3>
            <form id="add" method="post" action="controller?COMMAND=NewSurveyHistory">
                <input type="hidden" name="sickListid" value="${sickList.getPrimaryKey()}">
                <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add'"onclick="submitForm('add')">Добавить</a>
            </form>
            <table>
                <tr>
                    <th>№</th>
                    <th>Врач</th>
                    <th>Дата</th>
                    <th>Действие</th>
                </tr>
                <c:forEach items="${surveyHistoryList}" var="surveyHistory">
                    <tr>
                        <td id={"surveyHistory.getPrimaryKey()}">${surveyHistory.getPrimaryKey()}</td>
                        <td>${surveyHistory.getStaff().getFullName()}</td>
                        <td>
                            <fmt:formatDate pattern="dd/MM/yyyy" value="${surveyHistory.getSurveyDate()}"/>
                        </td>
                        <td>
                            <a href="controller?COMMAND=EditSurveyHistory&id=${surveyHistory.getPrimaryKey()}"
                               class="easyui-linkbutton" data-options="iconCls:'icon-edit'"></a>
                            <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"></a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <div style="text-align:left;padding:5px 0">
                <a href="" data-options="iconCls:'icon-ok'" class="easyui-linkbutton" onclick=" "
                   style="width:80px">ОК</a>
            </div>
        </div>
        <div title="Назначения" style="padding:10px">
            <h3>Назначения</h3>
            <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'">Добавить</a>
            <table>
                <tr>
                    <th></th>
                    <th>Дата</th>
                    <th>Кол-во.<br> План.факт</th>
                    <th>Назначение</th>
                    <th>Описание</th>
                    <th>Врач</th>
                    <th>Действие</th>
                </tr>
                <c:forEach items="${prescriptionList}" var="prescription">
                    <tr>
                        <td id="${prescription.getPrimaryKey()}">${prescription.getPrimaryKey()}</td>
                        <td>
                            <fmt:formatDate pattern="dd/MM/yyyy"
                                            value="${prescription.getSurveyHistory().getSurveyDate()}"/>
                        </td>
                        <td>${prescription.getQuantity()}/${prescription.getCompleted()}</td>
                        <td>${prescription.getPrescriptionType().getName()}</td>
                        <td>${prescription.getDescription()}</td>
                        <td>${prescription.getSurveyHistory().getStaff().getFullName()}</td>
                        <td>
                            <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'"></a>
                            <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"></a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <div style="text-align:left;padding:5px 0">
                <a href="javascript:void(0)" data-options="iconCls:'icon-ok'" class="easyui-linkbutton" onclick=" "
                   style="width:80px">ОК</a>
            </div>
        </div>
    </div>
</t:html>