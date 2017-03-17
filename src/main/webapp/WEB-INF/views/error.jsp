<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Denis
  Date: 25.02.2017
  Time: 16:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Error page</title>
    </head>
    <body>
        <h1>Произошла ошибка!</h1>
        <c:forEach items="${errMsg}" var="ste">
            ${ste}
        </c:forEach>
        <c:forEach items="${SQLError}" var="ste2">
            ${ste2}
        </c:forEach>
        <c:forEach items="${CNFE}" var="ste3">
            ${ste3}
        </c:forEach>
    </body>
</html>
