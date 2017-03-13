<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
        <c:forEach items="${lessonList}" var="lesson">
            <title>${lesson.title}</title>
        </c:forEach>

        <spring:url value="/resources/style.css" var="style" />
        <spring:url value="/resources/ReadingTimer.js" var="script" />
        <script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
        <script src="${script}" type="text/javascript" rel="script"></script>
        <link href="${style}" rel="stylesheet" />
    </head>
    <body>
        <fieldset>
            <legend>Лекция</legend>
            <c:forEach items="${lessonList}" var="lesson">
                <h3><c:out value="${lesson.title}"/></h3>
                <div><c:out value="${lesson.path}"/></div>
            </c:forEach>
        </fieldset>
        <a href="/" id="read_link">Прочитать</a>
        <form action="/kursovoy/chapters/lessons/read" method="post">
            <button type="submit" name="read" id="readbtn">Лекция прочитана</button>
            <button type="submit" name="back">Назад к лекциям</button>
        </form>
    </body>
</html>
