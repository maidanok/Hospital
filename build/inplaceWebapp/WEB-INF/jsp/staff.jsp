<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib tagdir ="/WEB-INF/tags" prefix = "t"%>
<t:html>
    <t:header/>
    <form>
        <div class="easyui-panel" title="Новый работник" style="width:100%;max-width:400px;padding:30px 60px;">
            <form id="ff" method="post">
                <div style="margin-bottom:20px">
                    <input class="easyui-textbox" name="firstname" style="width:100%"
                           data-options="label:'Фамилия:',required:true">
                </div>
                <div style="margin-bottom:20px">
                    <input class="easyui-textbox" name="lastname" style="width:100%"
                           data-options="label:'Имя:',required:true">
                </div>
                <div style="margin-bottom:20px">
                    <input class="easyui-textbox" name="middlename" style="width:100%" data-options="label:'Отчество:'">
                </div>
                <div style="margin-bottom:20px">
                    <input class="easyui-datebox" name="birthday" data-options="label:'Дата рождения'"
                           style="width:100%;">
                </div>
                <div style="margin-bottom:20px">
                    <input class="easyui-textbox" name="address" style="width:100%;height:60px"
                           data-options="label:'Адрес:', multiline:true">
                </div>
                <div style="margin-bottom:20px">
                    <select class="easyui-combobox" name="gender" label="Пол" style="width:100%">
                        <option value="MALE">мужчина</option>
                        <option value="FEMALE">женщина</option>
                    </select>
                </div>
                <div style="margin-bottom:20px">
                    <span>Установить права доступа</span><br>
                    <c:if test="${not empty posts}">
                        <c:forEach items="${posts}" var="post">
                            <input type="checkbox" name="${post.toString()}" value="${post.toString()}">${post.getName()}<br>
                        </c:forEach>
                    </c:if>
                </div>
                <div style="margin-bottom:20px">
                    <input class="easyui-textbox" name="passport" style="width:100%" data-options="label:'Паспорт:'">
                </div>
                <div style="margin-bottom:20px">
                    <input class="easyui-textbox" name="login" style="width:100%"
                           data-options="label:'Логин:',required:true">
                </div>
                <div style="margin-bottom:20px">
                    <input class="easyui-passwordbox" name="password" style="width:100%"
                           data-options="label:'Пароль:',required:true">
                </div>
                <div style="text-align:center;padding:5px 0">
                    <a href="javascript:void(0)" data-options="iconCls:'icon-ok'" class="easyui-linkbutton" onclick=" "
                       style="width:80px">ОК</a>
                    <a href="javascript:void(0)" data-options="iconCls:'icon-cancel'" class="easyui-linkbutton"
                       onclick=" " style="width:110px">Отменить</a>
                </div>
            </form>

</t:html>
