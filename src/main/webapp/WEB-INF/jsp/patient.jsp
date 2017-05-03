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
      <h3>Пациент</h3>
      <h4>Фамилия</h4>
      <input type="text" placeholder="Фамилия"/>
      <h4>Имя</h4>
      <input type="text" placeholder="Имя"/>
      <h4>Отчество</h4>
      <input type="text" placeholder="Отчество"/>
      <h4>Дата рождения</h4>
      <input type="text" placeholder="Дата рождения"/>
      <h4>Адрес</h4>
      <input type="text" placeholder="Адрес"/>
      <h4>Пол</h4>
        <label>
        <input type="radio" name=c54956e6-e863-44e1-0e0e-4e186a312c47 />Мужчина
        </label>
        <label>
        <input type="radio" checked name=c54956e6-e863-44e1-0e0e-4e186a312c47 />Женщина
        </label>
        <h4>Паспорт</h4>
      <input type="text" placeholder="Паспорт"/>
      <button>
        Сохранить
      </button>
      <button>
        Отмена
      </button>
    </form>

  <script type="text/javascript" src="../common-files/js/require.min.js" data-main="../scripts/startup"></script>
</body>

