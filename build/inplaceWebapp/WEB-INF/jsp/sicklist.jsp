<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib tagdir ="/WEB-INF/tags" prefix = "t"%>
<fmt:requestEncoding value="UTF-8"/>
<c:set var="locale" value="${not empty language ? language : pageContext.request.locale}" scope="session"/>
<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="language"/>
<t:html>
    <t:header/>
    <div style="margin:20px 0 10px 0;"></div>
    <div class="easyui-tabs" style="width:95%;">
        <div title="<fmt:message key='sicklist'/>" style="padding:10px">
            <div class="easyui-layout" style="width:700px;height:450px;">
                <form id="ff" method="post" action="controller?COMMAND=SaveSickList">
                    <div style="margin-bottom:20px">
                        <input type="hidden" name="id" value="${sickList.getPrimaryKey()}">
                        <input type="hidden" name="patientid" value="${sickList.getPatient().getPrimaryKey()}">
                        <input class="easyui-textbox" name="fullname" style="width:50%"
                               data-options="label:'<fmt:message key='fullname'/>',labelPosition:'top', editable:0"
                               value="${sickList.getPatient().getFullName()}">
                    </div>
                    <div style="margin-bottom:20px">
                        <input class="easyui-datebox" name="datein"
                               data-options="label:'<fmt:message key='datein'/>', labelPosition:'top'" style="width:50%;"
                               value="${sickList.getDateIN()}">
                    </div>
                    <div style="margin-bottom:20px">
                        <input class="easyui-datebox" name="dateout"
                               data-options="label:'<fmt:message key='dateout'/>', labelPosition:'top', disabled:1" style="width:50%;"
                        <fmt:formatDate pattern="dd/MM/yyyy" value="${sickList.getDateOUT()}"/>
                        >
                    </div>
                    <div style="margin-bottom:20px">
                        <input class="easyui-textbox" name="room" style="width:50%" data-options="label:'<fmt:message key='room'/>'"
                               value="${sickList.getRoom()}">
                    </div>

                    <div style="margin-bottom:20px">
                        <c:set var="finalDiagnose" value="${sickList.getFinalDiagnose().getPrimaryKey()}"/>
                        <c:if test="${not empty alldiagnose}">
                            <select class="easyui-combobox" name="diagnose" label="<fmt:message key='diagnosis'/>" style="width:50%">
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
                               data-options="label:'<fmt:message key='anamnesis'/>:', labelPosition:'top',multiline:true"
                               value="${sickList.getSymptoms()}">
                    </div>

                    <div style="text-align:left;padding:5px 0">
                        <a href="javascript:void(0)" data-options="iconCls:'icon-ok'" class="easyui-linkbutton"
                           onclick="submitForm('ff')"
                           style="width:80px">ОК</a>
                        <a href="javascript:history.back()" data-options="iconCls:'icon-cancel'"
                           class="easyui-linkbutton"
                           onclick=" " style="width:110px"><fmt:message key='cancel'/></a>
                    </div>
                </form>
            </div>
        </div>
        <div title="<fmt:message key='surveys'/>" style="padding:10px">
            <h3><fmt:message key='surveys'/></h3>
            <form id="add" method="post" action="controller?COMMAND=NewSurveyHistory">
                <input type="hidden" name="sickListid" value="${sickList.getPrimaryKey()}">
                <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add'"
                   onclick="submitForm('add')"><fmt:message key='add'/></a>
            </form>
            <div style="margin:20px 0;"></div>
            <table class="my-table">
                <tr>
                    <th class="my-th">№</th>
                    <th class="my-th"><fmt:message key='doctor'/></th>
                    <th class="my-th"><fmt:message key='date'/></th>
                    <th class="my-th"><fmt:message key='action'/></th>
                </tr>
                <c:forEach items="${surveyHistoryList}" var="surveyHistory">
                    <tr>
                        <td class="my-td" id={"surveyHistory.getPrimaryKey()}">${surveyHistory.getPrimaryKey()}</td>
                        <td class="my-td">${surveyHistory.getStaff().getFullName()}</td>
                        <td class="my-td">
                            <fmt:formatDate pattern="dd/MM/yyyy" value="${surveyHistory.getSurveyDate()}"/>
                        </td>
                        <td class="my-td">
                            <a href="controller?COMMAND=EditSurveyHistory&id=${surveyHistory.getPrimaryKey()}"
                               class="easyui-linkbutton" data-options="iconCls:'icon-edit'" style="float:left"></a>
                            <form id="delsh${surveyHistory.getPrimaryKey()}" method="post" style="float:left"
                                  action="controller?COMMAND=DeleteSurveyHistory">
                                <input type="hidden" name="id" value="${surveyHistory.getPrimaryKey()}">
                                <a href="javascript:void(0)"
                                   onclick="submitForm('delsh${surveyHistory.getPrimaryKey()}')"
                                   class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"></a>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <div style="text-align:left;padding:5px 0">
                <a href="controller?COMMAND=OpenHospital" data-options="iconCls:'icon-ok'" class="easyui-linkbutton"
                   onclick=" "
                   style="width:80px">ОК</a>
            </div>
        </div>
        <div title=<fmt:message key='prescriptions.name'/> style="padding:10px">
            <h3><fmt:message key='prescriptions.name'/></h3>
            <div style="margin:20px 0;"></div>
            <table class="my-table">
                <tr>
                    <th class="my-th"></th>
                    <th class="my-th"><fmt:message key='date'/></th>
                    <th class="my-th"><fmt:message key='quantity'/><br><fmt:message key='assign.perform'/></th>
                    <th class="my-th"><fmt:message key='prescription'/></th>
                    <th class="my-th"><fmt:message key='description'/></th>
                    <th class="my-th"><fmt:message key='doctor'/></th>
                    <th class="my-th"><fmt:message key='action'/></th>
                </tr>
                <c:forEach items="${prescriptionList}" var="prescription">
                    <tr>
                        <td  class="my-td" id="${prescription.getPrimaryKey()}">${prescription.getPrimaryKey()}</td>
                        <td class="my-td">
                            <fmt:formatDate pattern="dd/MM/yyyy"
                                            value="${prescription.getSurveyHistory().getSurveyDate()}"/>
                        </td>
                        <td class="my-td">${prescription.getQuantity()}/${prescription.getCompleted()}</td>
                        <td class="my-td">${prescription.getPrescriptionType().getName()}</td>
                        <td class="my-td">${prescription.getDescription()}</td>
                        <td class="my-td">${prescription.getSurveyHistory().getStaff().getFullName()}</td>
                        <td class="my-td">
                            <c:set var="quantity" value="${prescription.getQuantity()}"/>
                            <c:set var="completed" value="${prescription.getCompleted()}"/>
                            <c:if test="${quantity >completed}">
                                <form id="exp${prescription.getPrimaryKey()}" method="post"
                                      action="controller?COMMAND=ExecutePrescriptionList">
                                    <input type="hidden" name="id" value="${prescription.getPrimaryKey()}">
                                    <a href="javascript:void(0)" class="easyui-linkbutton"
                                       onclick="submitForm('exp${prescription.getPrimaryKey()}')"
                                       data-options="iconCls:'icon-ok'"></a>
                                </form>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <div style="text-align:left;padding:5px 0">
                <a href="controller?COMMAND=OpenHospital" data-options="iconCls:'icon-ok'" class="easyui-linkbutton"
                   onclick=" "
                   style="width:80px">ОК</a>
            </div>
        </div>
    </div>
</t:html>