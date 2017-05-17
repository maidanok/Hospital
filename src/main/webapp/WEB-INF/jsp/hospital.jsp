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
    <div style="margin:10px 0 10px 0;"></div>
    <div class="easyui-tabs" style="width:95%;">
        <div title= <fmt:message key='department.name'/> style="padding:10px">
            <a href="controller?COMMAND=OpenDirectories" class="easyui-linkbutton" data-options="iconCls:'icon-add'"><fmt:message key='add'/></a>
            <form id="findsick" method="post" action="controller?COMMAND=FindSickListBy">
                <input type="search" name="firstname" class="easyui-searchbox" data-options="prompt:'<fmt:message key='surname'/>'"
                       style="width:25%" value="">
                <input class="easyui-datebox" name="dateIn" data-options="prompt:'<fmt:message key='datein'/>'"
                       style="width:25%;" value="">
                <a href="javascript:void(0)" data-options="iconCls:'icon-search'" class="easyui-linkbutton"
                   onclick="submitForm('findsick')"
                   style="width:80px"><fmt:message key='find'/></a>
            </form>
            <div style="margin:20px 0;"></div>
            <table class="my-table">
                <tr>
                    <th class="my-th"><fmt:message key='room'/></th>
                    <th class="my-th"><fmt:message key='fullname'/></th>
                    <th class="my-th"><fmt:message key='sex'/></th>
                    <th class="my-th"><fmt:message key='datein'/></th>
                    <th class="my-th"><fmt:message key='diagnosis'/></th>
                    <th class="my-th"><fmt:message key='action'/></th>
                </tr>
                <c:forEach items="${sickLists}" var="sickList">
                    <tr>
                        <td class="my-td">${sickList.getRoom()}</td>
                        <td class="my-td">${sickList.getPatient().getFullName()}</td>
                        <td class="my-td">${sickList.getPatient().getSex().getName()}</td>
                        <td class="my-td">
                            <fmt:formatDate pattern="dd/MM/yyyy" value="${sickList.getDateIN()}"/>
                        </td>
                        <td class="my-td">${sickList.getFinalDiagnose().getDiagnoseName()}</td>
                        <td class="my-td">
                            <a href="controller?COMMAND=EditSickList&id=${sickList.getPrimaryKey()}"
                               class="easyui-linkbutton" data-options="iconCls:'icon-edit'" style="float:left"></a>
                            <form id="delsl${sickList.getPrimaryKey()}" method="post"
                                  action="controller?COMMAND=DeleteSickList" style="float:left">
                                <input type="hidden" name="id" value="${sickList.getPrimaryKey()}">
                                <a href="javascript:void(0)"
                                   onclick="submitForm('delsl${sickList.getPrimaryKey()}')"
                                   class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"></a>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div title= <fmt:message key='prescriptions.name'/>  style="padding:10px">
            <form id="findpres" method="post" action="controller?COMMAND=FindPressriptionByPatientLastName">
                <input type="search" name="firstname" class="easyui-searchbox" data-options="prompt:'<fmt:message key='surname'/>'"
                       style="width:25%" value="">
                <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search'"
                   onclick="submitForm('findpres')"><fmt:message key='find'/></a>
            </form>
            <div style="margin:20px 0;"></div>
            <table class="my-table">
                <tr>
                    <th class="my-th"><fmt:message key='room'/></th>
                    <th class="my-th"><fmt:message key='fullname'/></th>
                    <th class="my-th"><fmt:message key='prescriptions.name'/></th>
                    <th class="my-th"><fmt:message key='quantity'/><br>
                        <fmt:message key='assign.perform'/>
                    </th>
                    <th class="my-th"><fmt:message key='description'/></th>
                    <th class="my-th"><fmt:message key='action'/></th>
                </tr>
                <c:forEach items="${prescriptionList}" var="prescription">
                    <tr>
                        <td class="my-td">${prescription.getSurveyHistory().getSickList().getRoom()}</td>
                        <td class="my-td">${prescription.getSurveyHistory().getSickList().getPatient().getFullName()}</td>
                        <td class="my-td">${prescription.getPrescriptionType().getName()}</td>
                        <td class="my-td">${prescription.getQuantity()}/${prescription.getCompleted()}</td>
                        <td class="my-td">${prescription.getDescription()}</td>
                        <td class="my-td">
                            <form id="runp${prescription.getPrimaryKey()}" method="post"
                                  action="controller?COMMAND=ExecutePrescriptionHosp">
                                <input type="hidden" name="id" value="${prescription.getPrimaryKey()}">
                                <a href=javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'"
                                   onclick="submitForm('runp${prescription.getPrimaryKey()}')"></a>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</t:html>