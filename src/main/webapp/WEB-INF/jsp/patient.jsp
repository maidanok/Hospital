<%@page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib tagdir ="/WEB-INF/tags" prefix = "t"%>
<fmt:requestEncoding value="UTF-8"/>

<t:html>
    <t:header/>
    <div class="easyui-panel" title="${patient.getFullName()}" style="width:100%;max-width:400px;padding:30px 60px;">
        <form id="ff" method="post" action="controller?COMMAND=SavePatient">
            <input type="hidden" name="id" value="${patient.getPrimaryKey()}">
            <div style="margin-bottom:20px">
                <input class="easyui-textbox" name="firstname" style="width:100%"
                       data-options="label:'Фамилия:',required:true" value="${patient.getFirstName()}">
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-textbox" name="lastname" style="width:100%"
                       data-options="label:'Имя:',required:true" value="${patient.getLastName()}">
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-textbox" name="middlename" style="width:100%"
                       data-options="label:'Отчество:',required:true" value="${patient.getMiddleName()}">
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-datebox" name="birthday" data-options="label:'Дата рождения'" style="width:100%;"
                       value="${patient.getBirthday()}">
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-textbox" name="address" style="width:100%;height:60px"
                       data-options="label:'Адресс:',multiline:true" value="${patient.getAddress()}">
            </div>
            <div style="margin-bottom:20px">
                <span>Пол</span><br>
                <c:set var="Sex" value="${patient.getSex()}"/>
                <input type="radio" name="gender" value="MALE"
                <c:if test="${Sex eq 'MALE'}"> <c:out value='checked'/> </c:if> >мужчина<br>
                <input type="radio" name="gender" value="FEMALE"
                <c:if test="${Sex eq 'FEMALE'}"> <c:out value='checked'/> </c:if> >женщина<br>
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-textbox" name="passport" style="width:100%"
                       data-options="label:'Паспорт:',required:true" value="${patient.getPassportNumber()}">
            </div>
            <div style="text-align:center;padding:5px 0">
                <a href="javascript:void(0)" data-options="iconCls:'icon-ok'" class="easyui-linkbutton"
                   onclick="submitForm('ff')"
                   style="width:80px">ОК</a>
                <a href="javascript:history.back()" data-options="iconCls:'icon-cancel'" class="easyui-linkbutton"
                   onclick=" "
                   style="width:110px">Отменить</a>
            </div>
        </form>
</t:html>


