<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Denis
  Date: 23.02.2017
  Time: 17:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Регистрация</title>
        <spring:url value="/resources/style.css" var="style" />
        <link href="${style}" rel="stylesheet" />
    </head>
    <body>
        <div class="reg_form">
            <h2 class="reg_title">ИС обучения программированию</h2>
            <fieldset>
                <legend>Регистрация</legend>
                <form action="/kursovoy/registration" method="post">
                    <input type="text" name="login" placeholder="Введите логин..." value="" alt="Должен содержать только латинские символы!" required><br>
                    <input type="email" name="email" placeholder="Введите электронную почту..." value="" alt="Ваш адрес электронной почты. Например example@mail.com" required><br>
                    <input type="password" name="password" placeholder="Введите Ваш пароль..." value="" required><br>
                    <a href="/kursovoy/login" class="reg_login">Войти</a>
                    <input type="submit" id="regbtn" value="Регистрация" formmethod="post">
                </form>
            </fieldset>
        </div>
    </body>
</html>
