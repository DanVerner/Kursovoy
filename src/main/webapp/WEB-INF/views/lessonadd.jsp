<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Denis
  Date: 10.03.2017
  Time: 10:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавить лекцию</title>
</head>
    <body>
        <fieldset>
            <legend>Добавить лекцию</legend>
            <form method="post" action="/kursovoy/chapters/lessons/add">
                <input type="text" placeholder="Название лекции" name="title"/>
                <input type="text" placeholder="Описание лекции" name="description"/>
                <input type="text" placeholder="Путь к лекции" name="path" />
                <button type="submit">Добавить лекцию</button>
                <c:if test = "${lesadd_fail != null}">
                    <div name="">Не получилось добавить урок!</div>
                </c:if>
            </form>
        </fieldset>
    </body>
</html>
