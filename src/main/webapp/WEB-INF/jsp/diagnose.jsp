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
      <h3>Диагноз</h3>
      <h4>Название</h4>
      <input type="text" placeholder="Название"/>
      <h4>Терпия</h4>
        <textarea>Терапия</textarea>
      <button>
        Сохранить
      </button>
      <button>
        Отмена
      </button>
    </form>
</body>