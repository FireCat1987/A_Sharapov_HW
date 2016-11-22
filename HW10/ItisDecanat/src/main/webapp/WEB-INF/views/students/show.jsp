<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${student.id}</title>
</head>
<body>

<h1>${student.firstname} ${student.surname} ${student.lastname}</h1>
<p>Номер группы: ${student.studgroup}</p>

<h2>Оценки: </h2>
<form:form action="/students/${student.id}" method="post" modelAttribute="score">
<table>
    <tr>
        <td><form:label path="subjectType">Предмет</form:label></td>
        <td><form:select path="subjectType" >
            <form:option value="0" label="Chose subject" />
            <form:options items="${subjects}" itemValue="description" />
        </form:select></td>
        <td><form:errors path="subjectType"/></td>
    </tr>
    <tr>
        <td><form:label path="score">Оценка</form:label></td>
        <td><form:input path="score"/></td>
        <td><form:errors path="score"/></td>
    </tr>
</table>

    <input type="submit" value="Добавить оценку">

</form:form>
<hr>
<c:forEach var="score" items="${student.scores}">
    <p>${score.subjectType} - ${score.score}</p>
    <hr>
</c:forEach>


<a href="/students">Вернуться на главную</a>
</body>
</html>
