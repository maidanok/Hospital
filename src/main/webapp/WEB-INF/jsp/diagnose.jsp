<%@page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib tagdir ="/WEB-INF/tags" prefix = "t"%>
<fmt:requestEncoding value="UTF-8" />
<c:set var="locale" value="${not empty language ? language : pageContext.request.locale}" scope="session"/>
<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="language"/>
<t:html>
    <t:header/>

    <div class="easyui-panel" title="${diagnose.getDiagnoseName()}" style="width:100%;max-width:500px;padding:30px 60px;">
        <form id="ff" method="post" action = "controller?COMMAND=SaveDiagnose">
            <input type="hidden" name="id" value="${diagnose.getPrimaryKey()}">
            <div style="margin-bottom:20px">
                <input type="text" class="easyui-textbox" name="diagnosename" style="width:100%"
                       data-options="label:'<fmt:message key='title'/>:',required:true" maxlength="10" title="WTF" value="${diagnose.getDiagnoseName()}">
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-textbox" name="therapy" style="width:100%;height:200px"
                       data-options="label:'<fmt:message key='therapy'/>:',multiline:true, labelPosition:'top'" value="${diagnose.getTherapy()}">
            </div>
            <div style="text-align:center;padding:5px 0">
                <a href="javascript:void(0)" data-options="iconCls:'icon-ok'" class="easyui-linkbutton" onclick="submitForm('ff')"
                   style="width:80px">ОК</a>
                <a href="javascript:history.back()" data-options="iconCls:'icon-cancel'" class="easyui-linkbutton" onclick=" "
                   style="width:110px"><fmt:message key='cancel'/></a>
            </div>
        </form>
</t:html>