<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавить студента</title>
</head>
<body>

<form:form action="/students/add" method="post" modelAttribute="student">
    <table>
        <tr>
            <td><form:label path="firstname">Фамилия</form:label></td>
            <td><form:input path="firstname"/></td>
            <td><form:errors path="firstname"/></td>
        </tr>
        <tr>
            <td><form:label path="surname">Имя</form:label></td>
            <td><form:input path="surname"/></td>
            <td><form:errors path="surname"/></td>
        </tr>
        <tr>
            <td><form:label path="lastname">Отчество</form:label></td>
            <td><form:input path="lastname"/></td>
            <td><form:errors path="lastname"/></td>
        </tr>
        <tr>
            <td><form:label path="studgroup">Номер группы</form:label></td>
            <td><form:input path="studgroup"/></td>
            <td><form:errors path="studgroup"/></td>
        </tr>
    </table>
    <input type="submit" value="save"/>
</form:form>

</body>
</html>
