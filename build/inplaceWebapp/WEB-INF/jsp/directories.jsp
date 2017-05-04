<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<head>
    <link rel="stylesheet" href="css/style.css">
<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<h1>Hospital</h1>
<div class="tab">
  <button class="tablinks" onclick="openTab(event, 'patients')">Пациенты</button>
  <button class="tablinks" onclick="openTab(event, 'staff')">Персонал</button>
  <button class="tablinks" onclick="openTab(event, 'diagnoses')">Диагнозы</button>
</div>
<div id="patients" class="tabcontent">
  <h3>Пациенты</h3>
        <button>Добавить</button>
            <input type="text" placeholder="Найти"/>
                <button>Найти</button>
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
<div id="staff" class="tabcontent">
  <h3>Персонал</h3>
  <button>Добавить</button>
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
<div id="diagnoses" class="tabcontent">
  <h3>Диагнозы</h3>
  <button>Добавить</button>
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
  <script src="js/tabsHospital.js"></script>
</body>