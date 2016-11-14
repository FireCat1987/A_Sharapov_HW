<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавить пост</title>
</head>
<body>

<form:form action="/posts/add" method="post" modelAttribute="post">
    <table>
        <tr>
            <td><form:label path="title">Заголовок</form:label></td>
            <td><form:input path="title"/></td>
            <td><form:errors path="title"/> </td>
        </tr>
        <tr>
            <td><form:label path="text">Текст</form:label></td>
            <td><form:input path="text"/></td>
            <td><form:errors path="text"/> </td>
        </tr>

    </table>
    <input type="submit" value="save"/>
</form:form>


</body>
</html>
