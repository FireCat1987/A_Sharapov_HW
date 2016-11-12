<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tweet WebApp</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
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
        $(document).ready(function () {
            $('#cp2').colorpicker().on('changeColor', function (e) {
                btn = $('button.editbackcolor').get(0);
                $(btn).removeClass('disabled');
            });
            $('button.editbackcolor').click(function () {
                input = $("input.inputbackcolor").get(0);
                document.cookie = "backcolor=" + $(input).val() + "; path=/;";
                location.reload();
            });
        });
    </script>
</head>
<body>
<header class="header">
    <div class="container">
        <div class="row">
            <div class="col-md-8">
                <div id="cp2" class="input-group colorpicker-component">
                    <input id="inputbackcolor" type="text" value="${backcolor}" class="form-control inputbackcolor"/>
                    <span class="input-group-addon"><i></i></span>
                </div>
            </div>
            <div class="col-md-4">
                <button id="editbackcolor" type="button" class="btn btn-danger editbackcolor disabled">Применить</button>
            </div>
        </div>
    </div>
</header>
<div class="container">
    <div class="mt-1">
        <h1>Twitter WebAPP</h1>
    </div>
    <p class="lead">Упрощённая версия всем известного сервиса Twitter. Приложение позволяет писать анонимные твиты и
        оставлять к ним комментарии.</p>
    <p>Для начала использования перейдите по <a href="<c:url value="/twitter" />">ссылке</a></p>
</div>

</body>
</html>
