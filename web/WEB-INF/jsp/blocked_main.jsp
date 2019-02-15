<%--
  Created by IntelliJ IDEA.
  User: LAS
  Date: 09.02.2019
  Time: 7:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>hello page</title>
    <style>
        div {
            position: fixed;
        }

        form {
            margin: 2px;
        }
    </style>
</head>
<body>
<h1>
    Hi, no one
</h1>
<form>
    <input type="hidden" name="command" value="showLibrary">
    <input type="submit" name="showLibraryButton" value="View our library" class="buttonSizes"/>
</form>

<div style="left: 40%; width: 15%; top: 7%">
    <form>
        <input type="hidden" name="command" value="librarySearch">
        Enter search criteria:
        <input type="text" name="criteria" value=""/>
        <input type="submit" name="searchButton" value="Search"/>
    </form>
    <h2>
        <c:out value="${requestScope.searchError}"/>
    </h2>
</div>

<c:if test="${books != null}">
    <table border="1">
        <tr>
            <td>Title</td>
            <td>Author</td>
            <td>Rating</td>
            <td>Coast</td>
            <td>Rent coast</td>
            <td>Issue</td>
        </tr>
        <c:forEach items="${sessionScope.books}" var="book">
            <tr>
                <td>${book.title}</td>
                <td>${book.author}</td>
                <td>${book.rating}</td>
                <td>${book.coast}</td>
                <td>${book.rentCoast}</td>
                <td>${book.issue}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<h1 class="error">
    <c:out value="${requestScope.error}"/>
    <c:out value="${requestScope.nothing}"/>
</h1>
<div style="right: 5%; top: 3%; width: 50px; height: 27px">
    <form>
        <input type="hidden" name="command" value="goToStartPage">
        <input type="submit" name="logOutButton" value="Log out">
    </form>
</div>
</body>
</html>
