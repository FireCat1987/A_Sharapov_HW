<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавить студента</title>
    <script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
    <script src="https://cdn.jsdelivr.net/tether/1.2.0/tether.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.5/css/bootstrap.min.css"
          crossorigin="anonymous">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.5/js/bootstrap.min.js"
            crossorigin="anonymous"></script>
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-colorpicker/2.3.6/css/bootstrap-colorpicker.css"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-colorpicker/2.3.6/js/bootstrap-colorpicker.min.js"></script>
    <style>
        body {
            background-color: #eceeef;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-light">
    <a href="/students" class="btn btn-outline-danger float-lg-right">Список студентов</a>
    <a href="/logout" class="btn btn-outline-danger float-lg-right">Выход</a>
</nav>
<div class="container">
    <h2>Добавление студента</h2>
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
</div>
</body>
</html>
