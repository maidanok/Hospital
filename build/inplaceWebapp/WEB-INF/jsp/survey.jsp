<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib tagdir ="/WEB-INF/tags" prefix = "t"%>
<fmt:requestEncoding value="UTF-8"/>
<c:set var="locale" value="${not empty language ? language : pageContext.request.locale}" scope="session"/>
<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="language"/>
<t:html>
    <t:header/>
    <div style="margin:20px 0 10px 0;"></div>
    <div class="easyui-tabs" style="width:95%;">
        <div title="<fmt:message key='survey'/> №${surveyHistory.getPrimaryKey()}" style="padding:10px">
            <div class="easyui-layout" style="width:700px;height:350px;">
                <form id="ff" name="survey" method="post" action="controller?COMMAND=SaveSurveyHistory">
                    <div style="margin-bottom:20px">
                        <input type="hidden" name="id" value="${surveyHistory.getPrimaryKey()}">
                        <input type="hidden" name="sickListid" value="${surveyHistory.getSickList().getPrimaryKey()}">
                        <input class="easyui-textbox" name="patient" style="width:50%"
                               data-options="label:'<fmt:message key='patient'/>',disabled:1"
                               value="${surveyHistory.getSickList().getPatient().getFullName()}">
                    </div>
                    <div style="margin-bottom:20px">
                        <input type="hidden" name="staffid" value="${surveyHistory.getStaff().getPrimaryKey()}">
                        <input class="easyui-textbox" name="staff" style="width:50%"
                               data-options="label:'<fmt:message key='doctor'/>',disabled:1"
                               value="${surveyHistory.getStaff().getFullName()}">
                    </div>
                    <div style="margin-bottom:20px">
                        <input class="easyui-datebox" name="surveydate"
                               data-options="label:'<fmt:message key='surveydate'/>', labelPosition:'top'" style="width:50%;"
                               value="${surveyHistory.getSurveyDate()}">
                    </div>
                    <c:set var="myDiagnose" value="${surveyHistory.getDiagnose().getPrimaryKey()}"/>
                    <c:if test="${not empty alldiagnose}">
                        <select class="easyui-combobox" name="diagnose" label="<fmt:message key='diagnosis'/>" style="width:50%">
                            <c:forEach items="${alldiagnose}" var="diagnose">
                                <c:set var="diagnoseid" value="${diagnose.getPrimaryKey()}"></c:set>
                                <option value="${diagnose.getPrimaryKey()}"
                                <c:if test="${diagnoseid eq myDiagnose}">
                                    <c:out value='selected="selected"'/>
                                </c:if>
                                >${diagnose.getDiagnoseName()}</option>
                            </c:forEach>
                        </select>
                    </c:if>
                    <div style="margin-bottom:20px">
                        <input class="easyui-textbox" name="description" style="width:50%;height:80px"
                               data-options="label:'<fmt:message key='description'/>:', labelPosition:'top',multiline:true"
                               value="${surveyHistory.getDescription()}">
                    </div>
                    <div style="margin-bottom:20px">
                        <input type="checkbox" name="isdischarge" value="true"><fmt:message key='ishealthy'/>
                    </div>
                    <div style="text-align:left;padding:5px 0">
                        <a href="javascript:void(0)" data-options="iconCls:'icon-ok'" class="easyui-linkbutton"
                           onclick="submitForm('ff')"
                           style="width:80px">ОК</a>
                        <a href="controller?COMMAND=EditSickList&id=${surveyHistory.getSickList().getPrimaryKey()}"
                           data-options="iconCls:'icon-cancel'"
                           class="easyui-linkbutton"
                           onclick="" style="width:110px"><fmt:message key='cancel'/></a>
                    </div>
                </form>
            </div>
        </div>

        <div title=<fmt:message key='prescriptions.name'/> style="padding:10px">
                <a href="controller?COMMAND=EditPrescription&shid=${surveyHistory.getPrimaryKey()}&id=0"
                   class="easyui-linkbutton" data-options="iconCls:'icon-add'"><fmt:message key='add'/></a>
                <div style="margin:20px 0;"></div>
                <table class="my-table">
                    <tr>
                        <th class="my-th"><fmt:message key='date'/></th>
                        <th class="my-th"><fmt:message key='quantity'/><br><fmt:message key='assign.perform'/></th>
                        <th class="my-th"><fmt:message key='prescription'/></th>
                        <th class="my-th"><fmt:message key='description'/></th>
                        <th class="my-th"><fmt:message key='action'/></th>
                    </tr>
                    <c:forEach items="${prescriptionList}" var="prescription">
                        <c:set var="quantity" value="${prescription.getQuantity()}"/>
                        <c:set var="completed" value="${prescription.getCompleted()}"/>
                        <tr>
                            <td class="my-td">
                                <fmt:formatDate pattern="dd/MM/yyyy"
                                                value="${prescription.getSurveyHistory().getSurveyDate()}"/>
                            </td>
                            <td class="my-td">${prescription.getQuantity()}/${prescription.getCompleted()}</td>
                            <td class="my-td">${prescription.getPrescriptionType().getName()}</td>
                            <td class="my-td">${prescription.getDescription()}</td>
                            <td class="my-td">
                                <c:if test="${quantity >completed}">
                                    <form style="float:left" id="exp${prescription.getPrimaryKey()}" method="post" action="controller?COMMAND=ExecutePrescriptionSurvey">
                                        <input type="hidden" name="id" value="${prescription.getPrimaryKey()}">
                                        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm('exp${prescription.getPrimaryKey()}')" data-options="iconCls:'icon-ok'"></a>
                                    </form>
                                </c:if>
                                <a style="float:left" href="controller?COMMAND=EditPrescription&id=${prescription.getPrimaryKey()}"
                                   class="easyui-linkbutton" data-options="iconCls:'icon-edit'"></a>
                                <form style="float:left" id="delp${prescription.getPrimaryKey()}" method="post"
                                      action="controller?COMMAND=DeletePrescription">
                                    <input type="hidden" name="id" value="${prescription.getPrimaryKey()}">
                                    <a href="javascript:void(0)" class="easyui-linkbutton"
                                       onclick="submitForm('delp${prescription.getPrimaryKey()}')"
                                       data-options="iconCls:'icon-cancel'"></a>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
                <div style="text-align:left;padding:5px 0">
                    <a href="controller?COMMAND=EditSickList&id=${surveyHistory.getSickList().getPrimaryKey()}"
                       data-options="iconCls:'icon-ok'" class="easyui-linkbutton" onclick=" "
                       style="width:80px">ОК</a>
                </div>
        </div>
    </div>
</t:html>