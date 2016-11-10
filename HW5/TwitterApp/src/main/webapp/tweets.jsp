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
        $(document).ready(function () {
            $('button.delete').click(function () {
                $.ajax({
                    url: 'twitter',
                    type: 'DELETE',
                    data: this.getAttribute('data-id'),
                    success: function (response) {
                        console.log(response);
                        document.location.href = response;
                    }
                });
            });
        });
    </script>
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
        <h1>Tweeter</h1>
    </div>
    <form action="/twitter" method="post">
        <label>Ваше сообщение: </label>
        <input type="text" name="message">
        <input type="submit">
    </form>
    <br>
    <ul class="list-group">
        <c:if test="${tweets.isEmpty()}">
            <p>Ничего пока нету :(</p>
        </c:if>


        <c:forEach items="${tweets}" var="tweet">
            <li class="list-group-item">
                <button type="button" class="close delete" aria-label="delete" data-id="${tweet.id}">
                    <span aria-hidden="true">&times;</span>
                </button>

                <a href="/tweet/<c:out value="${tweet.id}"/>">${tweet.message}</a>
                <p>${tweet.createdAt}</p>

            </li>
        </c:forEach>
    </ul>
</div>
</body>
</html>