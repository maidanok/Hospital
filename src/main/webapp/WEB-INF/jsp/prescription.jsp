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
                <input class="easyui-textbox" name="patient" style="width:100%" data-options="label:'Пациент'">
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-textbox" name="staff" style="width:100%" data-options="label:'Врач'">
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-datebox" name="surveydate" data-options="label:'Дата осмотра', labelPosition:'top'"
                       style="width:100%;">
            </div>
            <div style="margin-bottom:20px">
                <select class="easyui-combobox" name="prescriptiontype" label="Тип назначения" style="width:100%">
                    <c:if test="${not empty PrescriptionType}">
                        <c:forEach items="${PrescriptionType}" var="type">
                            <option value="${type.getName()}">${type.getName()}</option>
                        </c:forEach>
                    </c:if>
                </select>
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-numberspinner" value="0" data-options="label:'Количество:',min:1,max:100"
                       style="width:50%;">
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-textbox" name="description" style="width:100%;height:80px"
                       data-options="label:'Примечания:', labelPosition:'top' ,multiline:true">
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
