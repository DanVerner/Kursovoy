<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
c<%--
  Created by IntelliJ IDEA.
  User: Denis
  Date: 04.03.2017
  Time: 16:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Лекция</title>
    </head>
    <body>
    <c:forEach items="${contentPage}" var="content">
        <h2><c:out value="${lessons.title}"></c:out></h2>
        <div><c:out value="${lessons.description}"></c:out></div>
        <div><c:out value="${lessons.path}"></c:out></div>
    </c:forEach>
    </body>
</html>
