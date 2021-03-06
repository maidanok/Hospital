<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib tagdir ="/WEB-INF/tags" prefix = "t"%>
<fmt:requestEncoding value="UTF-8"/>
<c:set var="locale" value="${not empty language ? language : pageContext.request.locale}" scope="session"/>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="language"/>
<t:html>
    <t:header/>
    <div class="easyui-layout" style="width:100%; height:600px;padding:10px;border:0;">
        <div data-options="region:'west'" style="width:50%;padding:10px;border:0; align:left">
            <form id="pres" method="post" action="controller?COMMAND=SavePrescription">
                <div class="easyui-panel" title="${prescription.getDescription()}"
                     style="width:100%;max-width:400px;padding:30px 60px;">
                    <div style="margin-bottom:20px">
                        <input type="hidden" name="id" value="${prescription.getPrimaryKey()}">
                        <input type="hidden" name="shid" value="${prescription.getSurveyHistory().getPrimaryKey()}">
                        <input class="easyui-textbox" name="patient" style="width:100%"
                               data-options="label:'<fmt:message key='patient'/>', editable:0"
                               value="${prescription.getSurveyHistory().getSickList().getPatient().getFullName()}">
                    </div>
                    <div style="margin-bottom:20px">
                        <input class="easyui-textbox" name="staff" style="width:100%"
                               data-options="label:'<fmt:message key='doctor'/>', editable:0"
                               value="${prescription.getSurveyHistory().getStaff().getFullName()}">
                    </div>
                    <div style="margin-bottom:20px">
                        <input class="easyui-datebox" name="surveydate"
                               data-options="label:'<fmt:message key='surveydate'/>', labelPosition:'top', disabled:1"
                               style="width:100%;" value="${prescription.getSurveyHistory().getSurveyDate()}">
                    </div>

                    <div style="margin-bottom:20px">
                        <c:set var="prestype" value="${prescription.getPrescriptionType()}"/>
                        <select class="easyui-combobox" name="prescriptiontype"
                                label="<fmt:message key='prescription'/>"
                                style="width:100%">
                            <c:if test="${not empty types}">
                                <c:forEach items="${types}" var="type">
                                    <option value="${type.toString()}"
                                    <c:if test="${prestype eq type}">
                                        <c:out value='selected="selected"'/>
                                    </c:if>
                                    >${type.getName()}</option>
                                </c:forEach>
                            </c:if>
                        </select>
                    </div>
                    <div style="margin-bottom:20px">
                        <input class="easyui-numberspinner" name="quantity" value="${prescription.getQuantity()}"
                               data-options="label:'<fmt:message key='quantity'/>:',min:${prescription.getCompleted()},max:100"
                               style="width:50%;">
                    </div>
                    <div style="margin-bottom:20px">
                        <input class="easyui-textbox" name="description" style="width:100%;height:80px"
                               data-options="label:'<fmt:message key='description'/>:', labelPosition:'top' ,multiline:true,required:true"
                               value="${prescription.getDescription()}">
                    </div>
                    <div style="text-align:left;padding:5px 0">
                        <a href="javascript:void(0)" data-options="iconCls:'icon-ok'" class="easyui-linkbutton"
                           onclick="submitForm('pres')"
                           style="width:80px">ОК</a>
                        <a href="javascript:history.back()" data-options="iconCls:'icon-cancel'"
                           class="easyui-linkbutton"
                           onclick=""
                           style="width:110px">
                            <fmt:message key='cancel'/>
                        </a>
                    </div>
                </div>
            </form>
        </div>
        <div data-options="region:'east'" style="width:50%;padding:10px;border:0;">
            <c:if test="${not empty executions}">
                <div class="easyui-panel" title="<fmt:message key='executingpres'/>"
                     style="width:100%;max-width:400px;padding:30px 60px;">
                    <table class="my-table">
                        <tr>
                            <th class="my-th">№</th>
                            <th class="my-th">
                                <fmt:message key='date'/>
                            </th>
                            <th class="my-th">
                                <fmt:message key='doctor'/>
                            </th>
                        </tr>
                        <c:forEach items="${executions}" var="execution">
                            <tr>
                                <td class="my-td" id={"execution.getPrimaryKey()}" class="my-td">
                                    ${execution.getPrimaryKey()}
                                </td>
                                <td class="my-td">${execution.getStaff().getFullName()}</td>
                                <td class="my-td">
                                    <fmt:formatDate pattern="dd/MM/yyyy"
                                                    value="${execution.getPrescriptionExecutionDate()}"/>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </c:if>
        </div>
    </div>
</t:html>
