<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Деканат итис</title>
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
    <script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
    <style>
        body {
            background-color: #eceeef;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-light">
    <c:if test='<%= session.getAttribute("user") != null %>'>
        <a href="/logout" class="btn btn-outline-danger float-lg-right">Выход</a>
    </c:if>
    <c:if test='<%= session.getAttribute("user") == null %>'>
        <a href="/login" class="btn btn-outline-danger float-lg-right">Вход</a>
    </c:if>
</nav>
<div class="container">
    <div class="jumbotron jumbotron-fluid">
        <div class="container">
            <h1 class="display-3">Управление студентами итис!</h1>
            <p class="lead">Здесь вы можете увидеть список всех студентов с возможностью добавления и удаления студентам оценок.</p>
            <p class="lead">
                <a class="btn btn-primary btn-lg" href="/students" role="button">К списку стеднтов.</a>
            </p>
        </div>
    </div>

</div>
</body>
</html>
