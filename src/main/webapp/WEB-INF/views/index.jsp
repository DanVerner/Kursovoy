
<%--
  Created by IntelliJ IDEA.
  User: Denis
  Date: 22.02.2017
  Time: 19:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
  <head>
    <title>ИС обучения программированию</title>
      <spring:url value="/resources/style.css" var="style" />
      <link href="${style}" rel="stylesheet" />
  </head>
  <body>
      <div class="chapter_list">
          <fieldset>
              <legend>Доступные главы для чтения</legend>
              <h3>Выберите доступную главу</h3>
              <form action="/kursovoy/chapters" method="post">
                  <select name="selectChapter" size="10">
                      <c:forEach items="${chapterList}" var="chapter">
                      <option value="<c:out value="${chapter.id}"></c:out>"><c:out value="${chapter.title}"></c:out></option>
                      <c:out value="${chapter.description}"></c:out>
                      </c:forEach>
                  </select>
                  <input type="submit" name="chapconfirm" formmethod="post" value="Просмотреть список лекций">
                  <button name="chapter_create" type="submit">Добавить главу</button>
              </form>
          </fieldset>
      </div>
  </body>
</html>
