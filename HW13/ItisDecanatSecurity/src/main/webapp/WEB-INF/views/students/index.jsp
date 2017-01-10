<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Деканат итис</title>
    <meta name="_csrf" content="${_csrf.token}"/>
    <!-- default header name is X-CSRF-TOKEN -->
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
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
    <script type="text/javascript">
        function editandsave(divid)
        {
            console.log(divid);
            var div = document.getElementById(divid);
            var a = document.getElementById("esbut");


            if (div.contentEditable == "true")
            {
                div.contentEditable = "false";
                console.log(div.innerHTML);
                a.innerHTML = "Редактировать";
                /*$.ajax(url, {
                    method: 'GET',
                    success: function () {
                        $post.hide();
                    }
                });*/
            }

            else
            {
                div.contentEditable = "true";
                a.innerHTML = "Сохранить";
            }

        }
    </script>
</head>
<body>
<nav class="navbar navbar-light">


    <sec:authorize access="!isAuthenticated()">
        <a href="/login" class="btn btn-outline-danger float-lg-right">Вход</a>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
        <a href="/logout" class="btn btn-outline-danger float-lg-right">Выход</a>
    </sec:authorize>
</nav>
<div class="container">
<h2>Управление студентами итис! Привет, <sec:authentication property="principal.login"/>!</h2>

<form action="/students" method="get">
    <input type="text" name="group" placeholder="введите номер группы..." width="600px">
    <input type="submit" value="Search">
</form>

<a href="/students/add">Добавить нового студента</a>

<c:if test="${students.size() == 0}">
    <p>Пока ничего нету</p>
</c:if>

<c:forEach var="student" items="${students}">
    <h3><div contenteditable="false" id="${student.id}">${student.firstname} ${student.surname} ${student.lastname}</div></h3>
    <p>Группа:${student.studGroup}</p><p>Преподаватель: ${student.user.login}</p>
    <div class="btn-group" data-toggle="buttons">
    <a class="btn btn-primary btn-sm" href="/students/${student.id}">Смотреть оценки студента...</a>
    <sec:authentication property="principal.id" var="user_id"/>

    <sec:authorize access="${user_id eq student.user.id}">
       <%-- <a class="btn btn-warning btn-sm" href="/students/${student.id}/edit">Редактировать студента</a>--%>
        <a type="button" class="btn btn-warning btn-sm" id="esbut" onclick="editandsave(${student.id});" <%--href="/students/${student.id}/edit"--%>>Редактировать студента</a>
        <a class="btn btn-danger btn-sm" href="/students/${student.id}/delete">Удалить студента</a>
    </sec:authorize>
    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <a class="btn btn-danger btn-sm" href="/students/${student.id}/delete">Удалить студента</a>
    </sec:authorize>
    </div>
    <hr>
</c:forEach>
</div>
</body>
</html>
