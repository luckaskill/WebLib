<%--
  Created by IntelliJ IDEA.
  User: LAS
  Date: 07.02.2019
  Time: 2:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Librarian panel</title>
    <style>
        div {
            width:500px;
            margin: auto;
            border: 3px solid #73AD21;
        }
    </style>
</head>
<body>
<div align="center">

<form action="controller" method="post">
    <input type="hidden" name="command" value="openAddBookPanel">
    <input type="submit" name="addBookButton" value="Add book panel"/>
</form>

<form action="controller" method="post">
    <input type="hidden" name="command" value="openDeleteBookPanel">
    <input type="submit" name="addBookButton" value="Delete book panel"/>
</form>
</div>

<c:if test="${sessionScope.bookPanelView}">
    <div>
    <form action="controller" method="post">
        <input type="hidden" name="command" value="addBook">
        Enter title:
        <input type="text" name="title" value=""/>
        <br/>
        Enter author:
        <input type="text" name="author" value=""/>
        <br/>
        Enter issue:
        <input type="text" name="issue" value="0"/>
        <br/>
        Enter cost:
        <input type="text" name="cost" value="0"/>
        <br/>
        Enter rating:
        <input type="text" name="rating" value="0"/>
        <br/>
        Enter rent cost:
        <input type="text" name="rent_cost" value="0"/>
        <br/>
        <input type="submit" name="addBookButton" value="Add book"/>
    </form>
    </div>
    <c:out value="${requestScope.addingError}"/>
    <c:out value="${requestScope.addingSuccess}"/>
</c:if>

</body>
</html>
