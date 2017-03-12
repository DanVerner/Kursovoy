<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Denis
  Date: 23.02.2017
  Time: 17:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>ListPage</title>
    </head>
    <body>
        <fieldset>
            <legend>Лекция</legend>
            <c:forEach items="${lessonList}" var="lesson">
                <h3><c:out value="${lesson.title}"/></h3>
                <div><c:out value="${lesson.path}"/></div>
            </c:forEach>
        </fieldset>
        <form action="/kursovoy/chapters/lessons/read" method="post">
            <button type="submit" name="read" disabled>Лекция прочитана</button>
            <button type="submit" name="back">Назад к лекциям</button>
        </form>
    </body>
</html>
