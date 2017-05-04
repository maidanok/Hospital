<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<head>
<link rel="stylesheet" href="css/style.css">
<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<form>
      <h3>Осмотр пациента больничный лист</h3>
      <h4>Пациент</h4>
      <input type="text" placeholder="Фамилия имя отчество"/>
      <h4>Врач</h4>
      <input type="text" placeholder="Фамилия имя отчество"/>
      <h4>Дата осмотра</h4>
      <input type="text" id="_463" placeholder="Дата осмотра"/>
      <h4>Диагноз</h4>
              <select size="1">
                  <option disabled>Диагноз</option>
              </select>
      <h4>Примечания</h4>
      <textarea>Анамнез жалобы и прочая ерунда</textarea>
      <label id="_699">
                <input type="checkbox" />Пациент готов к выписке
              </label>
      <button>Ok</button>
      <button>Отмена</button>
</form>
<form>
 <h3>Назначеия</h3>
 <table >
       <tr>
           <th> </th>
           <th>Кол-во. План.факт</th>
           <th>Назначение</th>
           <th>Описание</th>
           <th>Действие</th>
       </tr>
       </table>
         <button>Добавить</button>
         <button>Удалить выбраные</button>
</form>
  <script type="text/javascript" src="../common-files/js/require.min.js" data-main="../scripts/startup"></script>
</body>