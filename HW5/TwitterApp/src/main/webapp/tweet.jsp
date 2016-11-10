<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tweet № <c:out value="${tweet.id}"/></title>
</head>
<body>

<h1>Tweet № <c:out value="${tweet.id}"/></h1>
<h2></h2><p>${tweet.message}</p>
<p>${tweet.createdAt}</p></h2>
<hr>
<p>--------------------Комментарии--------------------</p>
<form action="/tweet/<c:out value="${tweet.id}"/>" method="post">
    <label>Ваше сообщение: </label>
    <input type="text" name="message">
    <input type="submit">
</form>
<c:forEach items="${comments}" var="comment">
    <p>${comment.message} <small>(${comment.createdAt})</small></p>
    <p>----------------------------------------</p>
</c:forEach>

</body>
</html>
