
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
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page session="true" %>
<html>
  <head>
      <title>ИС обучения программированию</title>
      <spring:url value="/resources/style.css" var="style" />
      <link href="${style}" rel="stylesheet" />
  </head>
  <body>
      <div class="header">
          <jsp:include page="header.jsp"/>
      </div>
      <div class="chapter_list">
          <fieldset>
              <legend>Доступные главы для чтения</legend>
              <h3>Выберите доступную главу</h3>
              <form action="/kursovoy/chapters" method="post">
                  <select name="selectChapter" id="selectChapter" size="10" required>
                      <c:forEach items="${chapterList}" var="chapter">
                      <option value="<c:out value="${chapter.id}"></c:out>"><c:out value="${chapter.title}"></c:out></option>
                      <c:out value="${chapter.description}"></c:out>
                      </c:forEach>
                  </select>
                  <input type="submit" id="chapconfirm" name="chapconfirm" formmethod="post" value="Просмотреть список лекций">
                  <sec:authorize access="hasRole('ROLE_EDITOR') OR hasRole('ROLE_ADMIN')">
                      <button name="chapter_create" type="submit">Добавить главу</button>
                  </sec:authorize>
              </form>
          </fieldset>
      </div>
  </body>
</html>
