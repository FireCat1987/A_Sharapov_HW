<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tweet № <c:out value="${tweet.id}"/></title>
    <script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
    <script src="https://cdn.jsdelivr.net/tether/1.2.0/tether.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.5/css/bootstrap.min.css"
          crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.5/js/bootstrap.min.js"
            crossorigin="anonymous"></script>
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-colorpicker/2.3.6/css/bootstrap-colorpicker.css"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-colorpicker/2.3.6/js/bootstrap-colorpicker.min.js"></script>
    <style>

        html {
            position: relative;
            min-height: 100%;
            background-color: ${backcolor};
        }

        body {
            margin-top: 60px;
            margin-bottom: 60px;
            background-color: ${backcolor};
        }

        .header {
            position: absolute;
            top: 0;
            width: 100%;

            height: 60px;
            line-height: 60px;
            background-color: #f5f5f5;
        }

        .container {
            width: auto;
            max-width: 680px;
            padding: 15px;
        }
    </style>
    <script>
        $(function () {
            $('#cp2').colorpicker().on('changeColor', function (e) {
                document.cookie = "backcolor=" + e.color.toHex() + "; path=/;";
                location.reload();
            });
        });
    </script>
</head>
<body>
<header class="header">
    <div class="container">

        <div id="cp2" class="input-group colorpicker-component">
            <input type="text" value="${backcolor}" class="form-control"/>
            <span class="input-group-addon"><i></i></span>
        </div>
    </div>
</header>
<div class="container">
    <div class="mt-1">
        <h1>Tweet № <c:out value="${tweet.id}"/></h1>
    </div>
    <p class="lead">Сообщение: ${tweet.message}</p>
    <p>дата создания: ${tweet.createdAt}</p>
    <a href="<c:url value="/twitter" />">Назад</a>
    <hr>
    <p>--------------------Комментарии--------------------</p>
    <form action="/tweet/<c:out value="${tweet.id}"/>" method="post">
        <label>Ваш комментарий: </label>
        <input type="text" name="message">
        <input type="submit">
    </form>
    <c:forEach items="${comments}" var="comment">
        <p>(${comment.id}) ${comment.comment}
        </p>

    </c:forEach>
</div>

</body>
</html>
