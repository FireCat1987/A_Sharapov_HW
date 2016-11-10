<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Twitter</title>
</head>
<body>

<h1>Tweeter</h1>

<form action="/tweeter" method="post">
    <label>Ваше сообщение: </label>
    <input type="text" name="message">
    <input type="submit">
</form>
<br>
<c:if test="${tweets.isEmpty()}">
    <p>Ничего пока нету :(</p>
</c:if>


<c:forEach items="${tweets}" var="tweet">
    <a href="/tweet/<c:out value="${tweet.id}"/>">${tweet.message}</a>
    <p>${tweet.createdAt}</p>
    <hr>
</c:forEach>

</body>
</html>