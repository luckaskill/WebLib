<%--
  Created by IntelliJ IDEA.
  User: LAS
  Date: 01.02.2019
  Time: 23:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>hello page</title>
    <style>
        div {
            position: fixed;
        }
        .resultMessage {
            position: absolute;
            top: 25%;
            left: 75%;
            width: 20%;
            color: bisque;
        }
        form {
            margin: 2px;
        }
        .tableButtonSize {
            width: 95%;
            height: 40%;
            margin: 2px;
        }
        table {
            background-color: #311f3cbf;;
            color: white;
            text-align: center;
            width: 70%;
            margin-left: 4%;
            margin-top: 25px;
            font-family: cursive;
        }
        body {
            background-size: cover;
            background-image: url("http://wallpapersmug.com/download/1920x1080/f12332/books.jpg");
            color: aliceblue;
        }
        .commandButtonSize {
            width: 200px;
            height: 25px;
        }
    </style>
</head>
<body>
<h1>
    Halo, book lover <c:out value="${sessionScope.user.login}"/>
    <br/>
    <c:out value="${sessionScope.user.cashValue}$"/>
</h1>

<div align="center" style="color: white; width: 100%;position: static">
    <form style="margin-bottom: 1%">
        <input type="hidden" name="command" value="viewUserBooks">
        <input type="submit" name="viewUserBooksButton" value="View your books" class="commandButtonSize"/>
    </form>

    <form style="margin-bottom: 1%">
        <input type="hidden" name="command" value="showLibrary">
        <input type="submit" name="showLibraryButton" value="View our library" class="commandButtonSize"/>
    </form>
</div>

<div style="right: 2%; width: 20%; top: 10%">
    <form>
        <input type="hidden" name="command" value="librarySearch">
        Enter search criteria:
        <br/>
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
            <td>Rent</td>
            <td>Buy</td>
        </tr>
        <c:forEach items="${sessionScope.books}" var="book">
            <tr>
                <td>${book.title}</td>
                <td>${book.author}</td>
                <td>${book.rating}</td>
                <td>${book.coast}</td>
                <td>${book.rentCoast}</td>
                <td>${book.issue}</td>
                <td>
                    <form>
                        <input type="hidden" name="command" value="rentBook">
                        <input type="hidden" name="title" value="${book.title}"/>
                        <input type="hidden" name="author" value="${book.author}"/>
                        <input type="hidden" name="rating" value="${book.rating}"/>
                        <input type="hidden" name="coast" value="${book.coast}"/>
                        <input type="hidden" name="issue" value="${book.issue}"/>
                        <input type="hidden" name="rentCoast" value="${book.rentCoast}"/>

                        <input type="submit" name="rentButton" value="Rent" class="tableButtonSize"/>
                    </form>
                </td>
                <td>
                    <form>
                        <input type="hidden" name="command" value="buyBook">
                        <input type="hidden" name="title" value="${book.title}"/>
                        <input type="hidden" name="author" value="${book.author}"/>
                        <input type="hidden" name="rating" value="${book.rating}"/>
                        <input type="hidden" name="coast" value="${book.coast}"/>
                        <input type="hidden" name="issue" value="${book.issue}"/>
                        <input type="hidden" name="rentCoast" value="${book.rentCoast}"/>

                        <input type="submit" name="rentButton" value="Buy" class="tableButtonSize"/>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<c:if test="${userBooks!=null}">
    <table border="1">
        <tr>
            <td>Title</td>
            <td>Author</td>
            <td>Rating</td>
            <td>Issue</td>
            <td>Deadline</td>
        </tr>
        <c:forEach items="${sessionScope.userBooks}" var="book">
            <tr>
                <td>${book.title}</td>
                <td>${book.author}</td>
                <td>${book.rating}</td>
                <td>${book.issue}</td>
                <td>
                    <c:choose>
                        <c:when test="${book.deadline != null}">
                            ${book.deadline}
                        </c:when>
                        <c:otherwise>
                            Purchased
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<h1 class="resultMessage">
    <c:out value="${requestScope.error}"/>
    <c:out value="${requestScope.nothing}"/>
    <c:out value="${requestScope.rentError}"/>
</h1>

<div style="right: 5%; top: 3%; width: 50px; height: 27px">
    <form>
        <input type="hidden" name="command" value="goToStartPage">
        <input type="submit" name="logOutButton" value="Log out">
    </form>
</div>
</body>
</html>
