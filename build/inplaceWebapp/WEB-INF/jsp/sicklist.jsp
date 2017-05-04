<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<head>
<link rel="stylesheet" href="css/style.css">
<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<div class="tab">
  <button class="tablinks" onclick="openTab(event, 'sicklist')">Больничный Лист</button>
  <button class="tablinks" onclick="openTab(event, 'survey')">Осмотры</button>
  <button class="tablinks" onclick="openTab(event, 'prescription')">Назначения</button>
</div>
<div id="sicklist" class="tabcontent">
  <h3>Больничный Лист</h3>
        <h4>Фамилия Имя Отчество</h4>
        <input type="text" placeholder="Фамилия Имя Отчество"/>
        <h4>Дата поступления</h4>
        <input type="text" placeholder="Дата поступления"/>
        <h4>Дата выписки</h4>
        <input type="text" placeholder="Дата выписки"/>
        <h4>Палата</h4>
        <input type="text" placeholder="Палата"/>
        <h4>Диагноз</h4>
        <select size="1">
            <option disabled>Диагноз</option>
        </select>
        <h4>Анамнез</h4>
        <textarea>Анамнез жалобы и прочая ерунда</textarea>
          <button>Ok</button>
          <button>Отмена</button>
</div>
<div id="survey" class="tabcontent">
  <h3>Осмотры</h3>
  <button>Добавить</button>
  <table >
      <tr>
          <th>№</th>
          <th>Врач</th>
          <th>Дата</th>
          <th>Действие</th>
      </tr>
      </table>
</div>
<div id="prescription" class="tabcontent">
  <h3>Назначения</h3>
  <button>Добавить</button>
  <button>Удалить выбраные</button>
  <table >
      <tr>
          <th> </th>
          <th>Дата</th>
          <th>Кол-во. План.факт</th>
          <th>Назначение</th>
          <th>Описание</th>
          <th>Врач</th>
          <th>Действие</th>
      </tr>
      </table>
</div>

<script src="js/tabsHospital.js"></script>
</body>