<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${student.id}</title>
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
    <script>
        $(document).ready(function () {
            $('button.delete').click(function () {
                $.ajax({
                    url: '/students/'+${student.id},
                    type: 'DELETE',
                    data: this.getAttribute('data-id'),
                    success: function (response) {
                        console.log("okey");
                        console.log(response);
                        document.location.reload();
                    }
                });
            });
        });
    </script>
</head>
<body>
    <div class="container">

<h1>${student.firstname} ${student.surname} ${student.lastname}</h1>
<p>Номер группы: ${student.studgroup}</p>

<h2>Оценки: </h2>
<form:form action="/students/${student.id}" method="post" modelAttribute="score">
    <table>
        <tr>
            <td><form:label path="subjectType">Предмет</form:label></td>
            <td><form:select path="subjectType" >
                <form:options items="${subjects}" itemLabel="description"/>
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
<ul class="list-group">
    <c:if test="${student.scores.isEmpty()}">
        <p>Оценки пока отсутствуют!</p>
    </c:if>


    <c:forEach items="${student.scores}" var="score">
        <li class="list-group-item">
            <button type="button" class="close delete" aria-label="delete" data-id="${score.id}">
                <span aria-hidden="true">&times;</span>
            </button>
            <p>${score.subjectType.description} - ${score.score}</p>
        </li>
    </c:forEach>
</ul>



<a href="/students">Вернуться на главную</a>
</div>
</body>
</html>
