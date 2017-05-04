<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<head>
<link rel="stylesheet" href="css/style.css">
<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<h3>Осмотр пациента больничный лист</h3>
      <h4>Пациент</h4>
      <input type="text" placeholder="Фамилия имя отчество"/>
      <h4>Врач</h4>
      <input type="text" placeholder="Фамилия имя отчество"/>
      <h4>Дата осмотра</h4>
      <input type="text" placeholder="Дата осмотра"/>
      <h4>Тип назначения</h4>
              <select size="1">
                  <option disabled>Тип назначения</option>
              </select>
            <h4>Количество</h4>
            <input type="text" placeholder="количество"/>
      <h4>Примечания</h4>
      <textarea>Анамнез жалобы и прочая ерунда</textarea>
      <button>
        Сохранить
      </button>
      <button>
        Отмена
      </button>
</body>
