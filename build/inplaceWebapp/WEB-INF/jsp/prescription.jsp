<%@page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib tagdir ="/WEB-INF/tags" prefix = "t"%>
<fmt:requestEncoding value="UTF-8" />

<t:html>
    <t:header/>
    <form >
        <div class="easyui-panel" title="Новое назначение" style="width:100%;max-width:400px;padding:30px 60px;">
            <div style="margin-bottom:20px">
                <input type="hidden" name="id" value="${prescription.getPrimaryKey()}">
                <input type="hidden" name="shid" value="${prescription.getSurveyHistory().getPrimaryKey()}">
                <input class="easyui-textbox" name="patient" style="width:100%" data-options="label:'Пациент'">
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-textbox" name="staff" style="width:100%" data-options="label:'Врач'"
                value="${prescription.getSurveyHistory().getStaff().getFullName()}">
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-datebox" name="surveydate" data-options="label:'Дата осмотра', labelPosition:'top'"
                       style="width:100%;" value="${prescription.getSurveyHistory().getSurveyDate()}">
            </div>
            <c:set var="prestype" value="${prescription.getPrescriptionType()}"/>
            <div style="margin-bottom:20px">
                <select class="easyui-combobox" name="prescriptiontype" label="Тип назначения" style="width:100%">
                    <c:if test="${not empty prescriptionType}">
                        <c:forEach items="${prescriptionType}" var="type">
                            <option value="${type.getName()}"
                            <c:if test="${prestype eq type}"> <c:out value='selected="selected"'/>
                            </c:if>${type.getName()}</option>
                        </c:forEach>
                    </c:if>
                </select>
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-numberspinner" name="quantity" value="${prescription.getQuantity()}"
                       data-options="label:'Количество:',min:1,max:100" style="width:50%;">
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-textbox" name="description" style="width:100%;height:80px"
                       data-options="label:'Примечания:', labelPosition:'top' ,multiline:true" value="${prescription.getDescription()}">
            </div>
            <div style="text-align:left;padding:5px 0">
                <a href="javascript:void(0)" data-options="iconCls:'icon-ok'" class="easyui-linkbutton" onclick=""
                   style="width:80px">ОК</a>
                <a href="javascript:void(0)" data-options="iconCls:'icon-cancel'" class="easyui-linkbutton" onclick=" "
                   style="width:110px">Отменить</a>
            </div>
        </div>
    </form>
</t:html>
