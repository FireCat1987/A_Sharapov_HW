<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Деканат итис</title>
</head>
<body>

<h1>Добро пожаловать управление стадентами!</h1>

<form action="/students" method="get">
    <input type="text" name="group" width="600px">
    <input type="submit" value="Search">
</form>

<a href="/students/add">Добавить нового студента</a>

<c:if test="${students.size() == 0}">
    <p>Пока ничего нету</p>
</c:if>

<c:forEach var="student" items="${students}">
    <h3>${student.firstname} ${student.surname} ${student.lastname}</h3>
    <p>Группа:${student.studgroup}</p>
    <a href="/students/${student.id}">Смотреть оценки студента...</a><br>
    <a href="/students/${student.id}/delete">Удалить студента</a>
    <a href="/students/${student.id}/edit">Редактировать студента</a>
    <hr>
</c:forEach>

</body>
</html>
