<%--
  Created by IntelliJ IDEA.
  User: LAS
  Date: 01.02.2019
  Time: 23:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="resources.locale.locale" var="loc"/>
<fmt:message bundle="${loc}" key="locale.default.welcome_admin_message" var="locale_welcome_message"/>
<fmt:message bundle="${loc}" key="locale.default.locale_button.eng" var="locale_button_eng"/>
<fmt:message bundle="${loc}" key="locale.default.locale_button.ru" var="locale_button_ru"/>
<fmt:message bundle="${loc}" key="locale.default.view_our_library_button" var="locale_our_lib_button"/>
<fmt:message bundle="${loc}" key="locale.default.view_your_books_button" var="locale_your_lib_button"/>
<fmt:message bundle="${loc}" key="locale.default.add_book_panel_button" var="locale_add_book_panel_button"/>
<fmt:message bundle="${loc}" key="locale.default.users_control_panel" var="locale_users_control_panel_button"/>
<fmt:message bundle="${loc}" key="locale.default.search_button" var="locale_search_button"/>
<fmt:message bundle="${loc}" key="locale.default.enter_search_criteria" var="locale_enter_search_criteria"/>
<fmt:message bundle="${loc}" key="locale.default.log_out_button" var="locale_log_out"/>
<fmt:message bundle="${loc}" key="locale.default.title" var="locale_title"/>
<fmt:message bundle="${loc}" key="locale.default.author" var="locale_author"/>
<fmt:message bundle="${loc}" key="locale.default.issue" var="locale_issue"/>
<fmt:message bundle="${loc}" key="locale.default.coast" var="locale_coast"/>
<fmt:message bundle="${loc}" key="locale.default.rent_coast" var="locale_rent_coast"/>
<fmt:message bundle="${loc}" key="locale.default.rating" var="locale_raring"/>
<fmt:message bundle="${loc}" key="locale.default.enter" var="locale_enter"/>

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
        <c:forEach items="${sessionScope.books}" var="rentBook">
            <tr>
                <td>${rentBook.title}</td>
                <td>${rentBook.author}</td>
                <td>${rentBook.rating}</td>
                <td>${rentBook.coast}</td>
                <td>${rentBook.rentCoast}</td>
                <td>${rentBook.issue}</td>
                <td>
                    <form>
                        <input type="hidden" name="command" value="rentBook">
                        <input type="hidden" name="rentCoast" value="${rentBook.rentCoast}"/>
                        <input type="hidden" name="bookID" value="${rentBook.id}"/>

                        <input type="submit" name="rentButton" value="Rent" class="tableButtonSize"/>
                    </form>
                </td>
                <td>
                    <form>
                        <input type="hidden" name="command" value="buyBook">
                        <input type="hidden" name="coast" value="${rentBook.coast}"/>
                        <input type="hidden" name="bookID" value="${rentBook.id}"/>

                        <input type="submit" name="rentButton" value="Buy" class="tableButtonSize"/>
                    </form>
                </td>
                <td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<c:if test="${sessionScope.rentBooks != null}">
    <table border="1">
        <tr>
            <td>${locale_title}</td>
            <td>${locale_author}</td>
            <td>${locale_raring}</td>
            <td>${locale_issue}</td>
            <td>Deadline</td>
        </tr>
        <c:forEach items="${sessionScope.rentBooks}" var="rentBook">
            <tr>
                <td>${rentBook.book.title}</td>
                <td>${rentBook.book.author}</td>
                <td>${rentBook.book.rating}</td>
                <td>${rentBook.book.issue}</td>
                <td>${rentBook.returnDeadline}</td>
            </tr>
        </c:forEach>
        <c:forEach items="${sessionScope.purchasedBooks}" var="purchasedBooks">
            <tr>
                <td>${purchasedBooks.book.title}</td>
                <td>${purchasedBooks.book.author}</td>
                <td>${purchasedBooks.book.rating}</td>
                <td>${purchasedBooks.book.issue}</td>
                <td>Purchased</td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<h1 class="resultMessage">
    <c:out value="${requestScope.error}"/>
    <c:out value="${requestScope.nothing}"/>
    <c:out value="${requestScope.rentError}"/>
</h1>

<div style="right: 4%; bottom: 5%;">
    <form action="controller" method="post">
        <input type="hidden" name="command" value="changeLocale">
        <input type="hidden" name="locale" value="ru">
        <input type="submit" name="${locale_button_ru}" value="${locale_button_ru}"/>
    </form>
    <form action="controller" method="post">
        <input type="hidden" name="command" value="changeLocale">
        <input type="hidden" name="locale" value="eng">
        <input type="submit" name="${locale_button_eng}" value="${locale_button_eng}"/>
    </form>
</div>

<div style="right: 5%; top: 3%; width: 50px; height: 27px">
    <form>
        <input type="hidden" name="command" value="goToStartPage">
        <input type="submit" name="logOutButton" value="Log out">
    </form>
</div>
</body>
</html>
