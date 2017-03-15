<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Denis
  Date: 22.02.2017
  Time: 19:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<html>
<head>
    <title>Вход в систему</title>
    <spring:url value="/resources/style.css" var="style" />
    <link href="${style}" rel="stylesheet" />
</head>
<body>
       <div class="form_login">
            <h2 class="log_title">ИС обучения программированию</h2>
            <fieldset>
                <legend>Форма входа</legend>
                <spring:url value="/j_spring_security_check" var="loginUrl"/>
                <form action="${loginUrl}" method="POST">
                    <input type="text" id="login" name="j_username" placeholder="Логин или электронная почта..." value="" required><br>
                    <input type="password" id="password" name="j_password" placeholder="Пароль..." value="" required><br>
                    <input type="submit" id="loginbtn" value="Авторизоваться" formmethod="post">
                </form>
                <a href="/kursovoy/registration" class="reg_link">Зарегистрироваться</a>
            </fieldset>
    </div>
</body>
</html>
