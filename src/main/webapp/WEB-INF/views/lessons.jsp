<%--
  Created by IntelliJ IDEA.
  User: Denis
  Date: 26.02.2017
  Time: 15:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
    <head>
        <title>Лекции главы</title>
        <spring:url value="/resources/style.css" var="style" />
        <link href="${style}" rel="stylesheet" />
    </head>
    <body>
        <div class="lesson_list">
            <fieldset>
                <legend>Лекции</legend>
                <h3>Выберите лекцию для чтения</h3>
                <form action="/kursovoy/chapters/lessons" method="post">
                    <select name="selectLesson" size="10">
                        <c:forEach items="${lessonsList}" var="lessons">
                        <option value="<c:out value="${lessons.id}"/>"><c:out value="${lessons.title}"></c:out></option>
                        </c:forEach>
                    </select>
                    <c:out value="${lessons.description}"></c:out>
                    <input type="submit" name="lessconfirm" formmethod="post" value="Читать лекцию">
                    <button name="lesson_create" type="submit">Добавить лекцию</button>
                </form>
            </fieldset>
        </div>
    </body>
</html>
