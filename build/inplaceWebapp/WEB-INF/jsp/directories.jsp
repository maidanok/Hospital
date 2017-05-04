<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib tagdir ="/WEB-INF/tags" prefix = "t"%>
<t:html>
<h1>Hospital</h1>
<div style="margin:20px 0 10px 0;"></div>
    <div class="easyui-tabs" style="width:95%;">
      <div title="Пациенты" style="padding:10px">
              <h3>Пациенты</h3>
                    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'">Добавить</a>
                    <input type="search" class="easyui-searchbox" data-options="prompt:'Фамилия'" style="width:25%">
                        <table >
                        <tr>
                            <th>№</th>
                            <th>Фамилия Имя Отчество</th>
                            <th>Дата рождения</th>
                            <th>Пол</th>
                            <th>Адрес</th>
                            <th>Паспорт</th>
                            <th>Действие</th>
                        </tr>
                        <c:forEach items="${allPatient}" var="patient">
                            <tr>
                                <td id = {"$patient.getPrimaryKey()}">${patient.getPrimaryKey()}</td>
                                <td>${patient.getFullName()}</td>
                                <td>${patient.getBirthday()}</td>
                                <td>${patient.getSex().getName()}</td>
                                <td>${patient.getAddress()}</td>
                                <td>${patient.getPassportNumber()}</td>
                                <td></td>
                            </tr>
                        </c:forEach>
                        </table>
      </div>


            <div title="Сотрудники" style="padding:10px">
              <h3>Персонал</h3>
              <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'">Добавить</a>
                        <table >
                        <tr>
                            <th>№</th>
                            <th>Фамилия Имя Отчество</th>
                            <th>Дата рождения</th>
                            <th>Пол</th>
                            <th>Адрес</th>
                            <th>Паспорт</th>
                            <th>Действие</th>
                        </tr>
                            <c:forEach items="${allStaff}" var="staff">
                            <tr>
                                <td id = {"$staff.getPrimaryKey()}">${staff.getPrimaryKey()}</td>
                                <td>${staff.getFullName()}</td>
                                <td>${staff.getBirthday()}</td>
                                <td>${staff.getSex().getName()}</td>
                                <td>${staff.getAddress()}</td>
                                <td>${staff.getPassportNumber()}</td>
                                <td></td>
                            </tr>
                        </c:forEach>
                        </table>
            </div>
            <div title="Диагнозы" style="padding:10px">
              <h3>Диагнозы</h3>
              <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'">Добавить</a>
              <table >
                  <tr>
                      <th>№</th>
                      <th>Название</th>
                      <th>Терапия</th>
                      <th>Действие</th>
                  </tr>
                            <c:forEach items="${allDiagnose}" var="diagnose">
                            <tr>
                                <td id = {"$diagnose.getPrimaryKey()}">${diagnose.getPrimaryKey()}</td>
                                <td>${diagnose.getDiagnoseName()}</td>
                                <td>${diagnose.getTherapy()}</td>
                                <td></td>
                            </tr>
                        </c:forEach>
                  </table>
            </div>
    </div>
</t:html>