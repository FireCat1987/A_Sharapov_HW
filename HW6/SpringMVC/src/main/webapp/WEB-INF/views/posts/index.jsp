<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Бложик</title>
</head>
<body>
<h1>Добро пожаловать в мой бложик!</h1>
<a href="/posts/add">добавить новый пост</a>
<form action="/posts" method="get">
    <input type="text" name="phrase" width="600px">
    <input type="submit" name="search">
</form>

<c:if test="${posts.size() == 0 }">
    <p>Пока ничего нету!</p>
</c:if>


<c:forEach var="post" items="${posts}">
    <h3>${post.title}</h3>
    <p>${post.text.substring(0,post.text.length() > 127 ? 127 : post.text.length())}</p>
    <small>${post.date}</small>
    <a href="/posts/${post.id}">далее</a>
    <a href="/posts/${post.id}/delete">удалить</a>
    <hr>
</c:forEach>
</body>
</html>
