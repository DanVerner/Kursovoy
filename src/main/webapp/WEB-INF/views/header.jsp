<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Denis
  Date: 15.03.2017
  Time: 11:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>header</title>
</head>
<body>
    <div class="inner_header">
        <c:if test="${pageContext.request.userPrincipal.name != null}">
            <h2>
                Добро пожаловать, ${pageContext.request.userPrincipal.name}!
            </h2>
            <a class="logouturl" href="<c:url value="/logout" />" >Выйти</a>
        </c:if>
    </div>
</body>
</html>
