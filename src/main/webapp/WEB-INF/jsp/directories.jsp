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
    <div style="margin:10px 0 10px 0;"></div>
    <div class="easyui-tabs" style="width:95%;">
        <div title=<fmt:message key='patients'/> style="padding:10px">
            <h3><fmt:message key='patients'/></h3>
            <a href="controller?COMMAND=EditPatient&id=0" class="easyui-linkbutton" data-options="iconCls:'icon-add'"><fmt:message key='add'/></a>
            <form id="find" method="post" action="controller?COMMAND=FindPatientByLastName">
                <input type="search" name="firstname" class="easyui-searchbox" data-options="prompt:'<fmt:message key='surname'/>'"
                       style="width:25%" value="">
                <a href="javascript:void(0)" data-options="iconCls:'icon-search'" class="easyui-linkbutton"
                   onclick="submitForm('find')"
                   style="width:80px"><fmt:message key='find'/></a>
            </form>
            <div style="margin:20px 0;"></div>
            <table class="my-table">
                <tr>
                    <th class="my-th">№</th>
                    <th class="my-th"><fmt:message key='fullname'/></th>
                    <th class="my-th"><fmt:message key='birthday'/></th>
                    <th class="my-th"><fmt:message key='sex'/></th>
                    <th class="my-th"><fmt:message key='address'/></th>
                    <th class="my-th"><fmt:message key='passport'/></th>
                    <th class="my-th"><fmt:message key='action'/></th>
                </tr>
                <c:forEach items="${allPatient}" var="patient">
                    <tr>
                        <td class="my-td" id={"$patient.getPrimaryKey()}"  class="my-td">${patient.getPrimaryKey()}</td>
                        <td class="my-td">${patient.getFullName()}</td>
                        <td class="my-td">
                            <fmt:formatDate pattern="dd/MM/yyyy" value="${patient.getBirthday()}"/>
                        </td>
                        <td class="my-td">${patient.getSex().getName()}</td>
                        <td class="my-td">${patient.getAddress()}</td>
                        <td class="my-td">${patient.getPassportNumber()}</td>
                        <td class="my-td">
                            <a href="controller?COMMAND=NewSickList&id=${patient.getPrimaryKey()}"
                               class="easyui-linkbutton" data-options="iconCls:'icon-ok'" style="float:left"></a>
                            <a href="controller?COMMAND=EditPatient&id=${patient.getPrimaryKey()}"
                               class="easyui-linkbutton" data-options="iconCls:'icon-edit'" style="float:left"></a>
                            <form id="delp${patient.getPrimaryKey()}" method="post" style="float:left"
                                  action="controller?COMMAND=DeletePatient">
                                <input type="hidden" name="id" value="${patient.getPrimaryKey()}">
                                <a href="javascript:void(0)" onclick="submitForm('delp${patient.getPrimaryKey()}')"
                                   class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"></a>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>

<c:set var="userrole" value="${user.getPost()}"/>
    <c:if test="${userrole eq 'ADMINISTRATOR'}">
        <div title=<fmt:message key='staff'/> style="padding:10px">
            <h3><fmt:message key='staff'/></h3>
            <a href="controller?COMMAND=EditStaff&id=0" class="easyui-linkbutton" data-options="iconCls:'icon-add'"><fmt:message key='add'/></a>
            <div style="margin:20px 0;"></div>
            <table class="my-table">
                <tr>
                    <th class="my-th">№</th>
                    <th class="my-th"><fmt:message key='fullname'/></th>
                    <th class="my-th"><fmt:message key='birthday'/></th>
                    <th class="my-th"><fmt:message key='post'/></th>
                    <th class="my-th"><fmt:message key='sex'/></th>
                    <th class="my-th"><fmt:message key='address'/></th>
                    <th class="my-th"><fmt:message key='passport'/></th>
                    <th class="my-th"><fmt:message key='action'/></th>
                </tr>
                <c:forEach items="${allStaff}" var="staff">
                    <tr>
                        <td class="my-td" id={"$staff.getPrimaryKey()}">${staff.getPrimaryKey()}</td>
                        <td class="my-td">${staff.getFullName()}</td>
                        <td class="my-td">
                            <fmt:formatDate pattern="dd/MM/yyyy" value="${staff.getBirthday()}"/>
                        </td>
                        <td class="my-td">${staff.getPost().getName()}</td>
                        <td class="my-td">${staff.getSex().getName()}</td>
                        <td class="my-td">${staff.getAddress()}</td>
                        <td class="my-td">${staff.getPassportNumber()}</td>
                        <td class="my-td">
                            <a href="controller?COMMAND=EditStaff&id=${staff.getPrimaryKey()}" class="easyui-linkbutton"
                               data-options="iconCls:'icon-edit'" style="float:left"></a>
                            <form id="dels${staff.getPrimaryKey()}" method="post"
                                  action="controller?COMMAND=DeleteStaff" style="float:left">
                                <input type="hidden" name="id" value="${staff.getPrimaryKey()}">
                                <a href="javascript:void(0)" onclick="submitForm('dels${staff.getPrimaryKey()}')"
                                   class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"></a>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </c:if>
        <div title=<fmt:message key='diagnosis'/> style="padding:10px">
            <h3><fmt:message key='diagnosis'/></h3>
            <a href="controller?COMMAND=EditDiagnose&id=0" class="easyui-linkbutton" data-options="iconCls:'icon-add'"><fmt:message key='add'/></a>
            <div style="margin:20px 0;"></div>
            <table class="my-table">
                <tr>
                    <th class="my-th">№</th>
                    <th class="my-th"><fmt:message key='title'/></th>
                    <th class="my-th"><fmt:message key='therapy'/></th>
                    <th class="my-th"><fmt:message key='action'/></th>
                </tr>
                <c:forEach items="${allDiagnose}" var="diagnose">
                    <tr>
                        <td class="my-td" id={"$diagnose.getPrimaryKey()}">${diagnose.getPrimaryKey()}</td>
                        <td class="my-td">${diagnose.getDiagnoseName()}</td>
                        <td class="my-td">${diagnose.getTherapy()}</td>
                        <td class="my-td">
                            <a href="controller?COMMAND=EditDiagnose&id=${diagnose.getPrimaryKey()}"
                               class="easyui-linkbutton" data-options="iconCls:'icon-edit'" style="float:left"></a>
                            <form id="deld${diagnose.getPrimaryKey()}" method="post"
                                  action="controller?COMMAND=DeleteDiagnose" style="float:left">
                                <input type="hidden" name="id" value="${diagnose.getPrimaryKey()}">
                                <a href="javascript:void(0)" onclick="submitForm('deld${diagnose.getPrimaryKey()}')"
                                   class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"></a>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</t:html>