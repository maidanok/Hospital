<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<head>
  <link rel="stylesheet" href="css/style.css">
<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<h1>Hospital</h1>
<div class="tab">
  <button class="tablinks" onclick="openTab(event, 'hospital')">Стационар</button>
  <button class="tablinks" onclick="openTab(event, 'prescription')">Назначения</button>
</div>
<div id="hospital" class="tabcontent">
  <h3>Стационар</h3>
    <button>Добавить</button>
    <button>Удалить выбраные</button>
    <input type="text" placeholder="Фамилия"/>
    <input type="text" placeholder="Дата постпуления"/>
        <button>Найти</button>
    <table >
    <tr>
        <th>Отметить</th>
        <th>Номер палаты</th>
        <th>Фамилия Имя Отчество</th>
        <th>Пол</th>
        <th>Дата поступления</th>
        <th>Текущий диагноз</th>
        <th>Действие</th>
    </tr>
        <c:forEach items="${sickLists}" var="sickList">
            <tr>
                <td><input id="${sickList.getPrimaryKey()}" type="checkbox"/></td>
                <td>${sickList.getRoom()}</td>
                <td>${sickList.getPatient().getFullName()}</td>
                <td>${sickList.getPatient().getSex().getName()}</td>
                <td>${sickList.getDateIN()}</td>
                <td>${sickList.getFinalDiagnose().getDiagnoseName()}</td>
                <td></td>
            </tr>
        </c:forEach>
    </table>
</div>

<div id="prescription" class="tabcontent">
  <h3>Назначения</h3>
  <button>Добавить</button>
  <button>Удалить выбраные</button>
  <input type="text" placeholder="Найти"/>
  <button>Search</button>
  <table >
      <tr>
          <th>Отметить</th>
          <th>Номер палаты</th>
          <th>Фамилия Имя Отчество</th>
          <th>Вид назначения</th>
          <th>Количество</th>
          <th>Описание</th>
          <th>Действие</th>
      </tr>
      <c:forEach items="${prescriptionList}" var="prescription">
                  <tr>
                      <td><input id="${prescription.getPrimaryKey()}" type="checkbox"/></td>
                      <td>${prescription.getSurveyHistory().getSickList().getRoom()}</td>
                      <td>${prescription.getSurveyHistory().getSickList().getPatient().getFullName()}</td>
                      <td>${prescription.getPrescriptionType().getName()}</td>
                      <td>${prescription.getQuantity()}</td>
                      <td>${prescription.getDescription()}</td>
                      <td></td>
                  </tr>
      </c:forEach>
      </table>
</div>
<script src="js/tabsHospital.js"></script>
</body>
</html>