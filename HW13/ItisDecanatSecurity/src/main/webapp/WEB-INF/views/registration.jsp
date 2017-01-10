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
        .form-signin {
            max-width: 530px;
            padding: 15px;
            margin: 0 auto;
        }
        .form-signin .form-signin-heading{
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-light">

    <a href="/students" class="btn btn-outline-danger float-lg-right">Список студентов</a>
    <a href="/login" class="btn btn-outline-danger float-lg-right">Вход</a>

</nav>
<div class="container">
    <form:form action="/registration" method="post" modelAttribute="userForm" cssClass="form-signin">
        <h2 class="form-signin-heading">Введите логин и пароль для регистрации!</h2>

        <table>
            <tr>
                <td><form:label path="login">Логин</form:label></td>
                <td><form:input path="login"/></td>
                <td><form:errors path="login"/></td>
            </tr>
            <tr>
                <td><form:label path="password">Пароль</form:label></td>
                <td><form:password path="password" /></td>
                <td><form:errors path="password"/></td>
            </tr>
            <tr>
                <td><form:label path="repassword">Повтор пароля</form:label></td>
                <td><form:password path="repassword" /></td>
                <td><form:errors path="repassword"/></td>
            </tr>
        </table>
        <input type="submit" value="save"/>
    </form:form>
</div>
</body>
</html>
