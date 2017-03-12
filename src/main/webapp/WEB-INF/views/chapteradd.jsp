<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Denis
  Date: 10.03.2017
  Time: 9:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавить главу</title>
    <spring:url value="/resources/style.css" var="style" />
    <link href="${style}" rel="stylesheet" />
</head>
    <body>
        <fieldset>
            <legend>Добавить главу</legend>
            <form method="post" action="/kursovoy/chapters/add">
                <input type="text" name="title" placeholder="Заголовок" required/>
                <input type="text" name="description" placeholder="Описание главы"/>
                <input type="text" name="user_lvl" placeholder="Требуемый уровень пользователя" required/>
                <button type="submit">Создать главу</button>
                <c:if test = "${chadd_fail != null}">
                    <div name="">Не получилось добавить главу!</div>
                </c:if>
            </form>
        </fieldset>
    </body>
</html>
