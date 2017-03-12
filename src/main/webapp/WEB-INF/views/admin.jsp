<%--
  Created by IntelliJ IDEA.
  User: Denis
  Date: 28.02.2017
  Time: 15:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Administrator Page</title>
    </head>
    <body>
        <h1>Админка</h1>
        <div>
            <form action="/administrator" method="post">
                <input type="checkbox" name="adminEmailNotifications" value="Включить оповещения о входе в систему других админов">
                <input type="submit" formmethod="post" name="savesettings" value="Сохранить изменения">
            </form>
        </div>
    </body>
</html>
