<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib tagdir ="/WEB-INF/tags" prefix = "t"%>
<fmt:requestEncoding value="UTF-8"/>

<t:html>
    <t:header/>

    <div class="easyui-panel" title="${staff.getFullName()}" style="width:100%;max-width:400px;padding:30px 60px;">
        <form id="stafform" method="post" action="controller?COMMAND=SaveStaff">
            <input type="hidden" name="id" value="${staff.getPrimaryKey()}">
            <div style="margin-bottom:20px">
                <input class="easyui-textbox" name="firstname" style="width:100%"
                       data-options="label:'Фамилия:',required:true" value="${staff.getFirstName()}">
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-textbox" name="lastname" style="width:100%"
                       data-options="label:'Имя:',required:true" value="${staff.getLastName()}">
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-textbox" name="middlename" style="width:100%"
                       data-options="label:'Отчество:'" value="${staff.getMiddleName()}">
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-datebox" name="birthday" data-options="label:'Дата рождения'"
                       style="width:100%;" value="${staff.getBirthday()}">
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-textbox" name="address" style="width:100%;height:60px"
                       data-options="label:'Адрес:', multiline:true" value="${staff.getAddress()}">
            </div>
            <c:set var="Sex" value="${staff.getSex()}"/>
            <div style="margin-bottom:20px">
                <select class="easyui-combobox" name="gender" label="Пол" style="width:100%">
                    <option value="MALE"
                    <c:if test="${Sex eq 'MALE'}">
                        <c:out value='selected="selected"'/>
                    </c:if>
                    >мужчина</option>
                    <option value="FEMALE"
                    <c:if test="${Sex eq 'FEMALE'}">
                        <c:out value='selected="selected"'/>
                    </c:if>
                    >женщина</option>
                </select>
            </div>
            <div style="margin-bottom:20px">

                    <span>Установить права доступа</span><br>
                    <c:set var="Role" value="${staff.getPost().toString()}"/>
                    <c:if test="${not empty posts}">
                        <c:forEach items="${posts}" var="post">
                            <input type="radio" name="post" value="${post.toString()}"
                            <c:set var="e" value="${post.toString()}"/>
                                <c:if test="${Role eq e}">
                                    <c:out value='checked'/>
                                </c:if>
                             >${post.getName()} ${post.toString()}<br>
                        </c:forEach>
                    </c:if>

            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-textbox" name="passport" style="width:100%" data-options="label:'Паспорт:'"
                       value="${staff.getPassportNumber()}">
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-textbox" name="login" style="width:100%"
                       data-options="label:'Логин:',required:true" value="${staff.getLogin()}">
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-passwordbox" name="password" style="width:100%"
                       data-options="label:'Пароль:',required:true" value="${staff.getPassword()}">
            </div>
            <div style="margin-bottom:20px">
                <input type="checkbox" name="fired"
                <c:if test="{$staff.isFired()}">
                    <c:out value='checked="checked"'/>
                </c:if>
                >уволен<br>
            </div>
            <div style="text-align:center;padding:5px 0">
                <a href="javascript:void(0)" data-options="iconCls:'icon-ok'" class="easyui-linkbutton" onclick="submitForm('stafform')"
                   style="width:80px">ОК</a>
                <a href="javascript:history.back()" data-options="iconCls:'icon-cancel'" class="easyui-linkbutton"
                   onclick="" style="width:110px">Отменить</a>
            </div>
        </form>

</t:html>
