<%--
  Created by IntelliJ IDEA.
  User: LAS
  Date: 07.02.2019
  Time: 2:20
  To change this template use File | Settings | File Templates.
--%>
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
            background-color: #311f3cbf;
            color: #faffd0;
            text-align: center;
            width: 70%;
            margin-left: 4%;
            margin-top: 25px;
            font-family: cursive;
        }

        .usersTable {
            background-color: #973f4761;
            width: 40%;
            margin-left: 10%;
            font-style: inherit;
            font-family: sans-serif;
        }

        .rankEditCP {
            position: fixed;
            right: 14%;
            top: 38%;
            width: 300px;
            background-color: #5d29548a;
        }

        body {
            background-size: cover;
            background-image: url("http://wallpapersmug.com/download/1920x1080/f12332/books.jpg");
            color: bisque;
        }

        .editBookFields {
            position: relative;
            right: 20%;
            margin-bottom: 3.5%;
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
<fmt:message bundle="${loc}" key="locale.default.cost" var="locale_cost"/>
<fmt:message bundle="${loc}" key="locale.default.rent_cost" var="locale_rent_cost"/>
<fmt:message bundle="${loc}" key="locale.default.rating" var="locale_raring"/>
<fmt:message bundle="${loc}" key="locale.default.enter" var="locale_enter"/>

<h1>
    ${locale_welcome_message} <c:out value="${sessionScope.user.login}"/>
    <br/>
    <c:out value="${sessionScope.user.cashValue}$"/>
</h1>

<div align="center" style="color: white; width: 100%;position: static">

    <form action="controller" method="post" style="margin-bottom: 1%">
        <input type="hidden" name="command" value="openAddBookPanel">
        <input type="submit" name="addBookButton" value="${locale_add_book_panel_button}" class="commandButtonSize"/>
    </form>

    <form style="margin-bottom: 1%">
        <input type="hidden" name="command" value="viewUserBooks">
        <input type="submit" name="viewUserBooksButton" value="${locale_your_lib_button}" class="commandButtonSize"/>
    </form>

    <form style="margin-bottom: 1%">
        <input type="hidden" name="command" value="usersView">
        <input type="submit" name="openUsersControlPanelButton" value="${locale_users_control_panel_button}"
               class="commandButtonSize"/>
    </form>

    <form style="margin-bottom: 1%">
        <input type="hidden" name="command" value="showLibrary">
        <input type="submit" name="showLibraryButton" value="${locale_our_lib_button}" class="commandButtonSize"/>
    </form>
</div>

<div style="right: 2%; width: 20%; top: 10%">
    <form>
        <input type="hidden" name="command" value="librarySearch">
        ${locale_enter_search_criteria}:
        <br/>
        <input type="text" name="criteria" value=""/>
        <input type="submit" name="searchButton" value="${locale_search_button}"/>
    </form>
    <h2>
        <c:out value="${requestScope.searchError}"/>
    </h2>
</div>
<c:if test="${requestScope.booksControllerView}">
    <div align="center" style="width: 100%">
        <form action="controller" method="post" style="background-color: #000000bd; width: 22%;">
            <input type="hidden" name="command" value="addBook">
            <div align="right" style="padding-top: 4%" class="editBookFields">
                    ${locale_title}:
                <input type="text" name="title" value=""/>
            </div>
            <div align="right" class="editBookFields">
                    ${locale_author}:
                <input type="text" name="author" value=""/>
            </div>
            <div align="right" class="editBookFields">
                    ${locale_issue}:
                <input type="text" name="issue" value="2019"/>
            </div>
            <div align="right" class="editBookFields">
                    ${locale_cost}:
                <input type="text" name="cost" value="10.0"/>
            </div>
            <div align="right" class="editBookFields">
                    ${locale_raring}:
                <input type="text" name="rating" value="0"/>
            </div>
            <div align="right" class="editBookFields">
                    ${locale_rent_cost}:
                <input type="text" name="rent_cost" value="5"/>
            </div>
            <input type="submit" name="addBookButton" value="Add book"
                   style="margin-bottom: 15px"/>
        </form>
    </div>
</c:if>

<c:if test="${requestScope.editBookPanelView}">
    <div align="center" style="width: 100%">
        <form action="controller" method="post" style="background-color: #000000bd; width: 22%;">
            <input type="hidden" name="command" value="editBook">
            <input type="hidden" name="bookID" value="${requestScope.bookID}"/>
            <div align="right" class="editBookFields" style="padding-top: 4%">
                    ${locale_title}:
                <input type="text" name="title" value="${requestScope.title}"/>
            </div>
            <div align="right" class="editBookFields">
                    ${locale_author}:
                <input type="text" name="author" value="${requestScope.author}"/>
            </div>
            <div align="right" class="editBookFields">
                    ${locale_issue}:
                <input type="text" name="issue" value="${requestScope.issue}"/>
            </div>
            <div align="right" class="editBookFields">
                    ${locale_cost}
                <input type="text" name="cost" value="${requestScope.cost}"/>
            </div>
            <div align="right" class="editBookFields">
                    ${locale_raring}:
                <input type="text" name="rating" value="${requestScope.rating}"/>
            </div>
            <div align="right" class="editBookFields">
                    ${locale_rent_cost}:
                <input type="text" name="rentCost" value="${requestScope.rentCost}"/>
            </div>
            <input type="submit" name="editBookButton" value="Edit Commit"
                   style="margin-bottom: 15px"/>
        </form>
    </div>
</c:if>

<c:if test="${books != null}">
    <table border="1">
        <tr>
            <td>${locale_title}</td>
            <td>${locale_author}</td>
            <td>${locale_raring}</td>
            <td>${locale_cost}</td>
            <td>${locale_rent_cost}</td>
            <td>${locale_issue}</td>
        </tr>
        <c:forEach items="${sessionScope.books}" var="rentBook">
            <tr>
                <td>${rentBook.title}</td>
                <td>${rentBook.author}</td>
                <td>${rentBook.rating}</td>
                <td>${rentBook.cost}</td>
                <td>${rentBook.rentCost}</td>
                <td>${rentBook.issue}</td>
                <td>
                    <form>
                        <input type="hidden" name="command" value="rentBook">
                        <input type="hidden" name="rentCost" value="${rentBook.rentCost}"/>
                        <input type="hidden" name="bookID" value="${rentBook.id}"/>

                        <input type="submit" name="rentButton" value="Rent" class="tableButtonSize"/>
                    </form>
                </td>
                <td>
                    <form>
                        <input type="hidden" name="command" value="buyBook">
                        <input type="hidden" name="cost" value="${rentBook.cost}"/>
                        <input type="hidden" name="bookID" value="${rentBook.id}"/>

                        <input type="submit" name="rentButton" value="Buy" class="tableButtonSize"/>
                    </form>
                </td>
                <td>
                    <form>
                        <input type="hidden" name="command" value="openEditBookPanel">
                        <input type="hidden" name="title" value="${rentBook.title}"/>
                        <input type="hidden" name="author" value="${rentBook.author}"/>
                        <input type="hidden" name="rating" value="${rentBook.rating}"/>
                        <input type="hidden" name="cost" value="${rentBook.cost}"/>
                        <input type="hidden" name="issue" value="${rentBook.issue}"/>
                        <input type="hidden" name="rentCost" value="${rentBook.rentCost}"/>
                        <input type="hidden" name="bookID" value="${rentBook.id}"/>

                        <input type="submit" name="openEditBookPanelButton" value="Edit" class="tableButtonSize"/>
                    </form>
                </td>
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
            <td>Action</td>
        </tr>
        <c:forEach items="${sessionScope.rentBooks}" var="rentBook">
            <tr>
                <td>${rentBook.book.title}</td>
                <td>${rentBook.book.author}</td>
                <td>${rentBook.book.rating}</td>
                <td>${rentBook.book.issue}</td>
                <td>${rentBook.returnDeadline}</td>
                <td>
                    <form>
                        <input type="hidden" name="command" value="returnBook">
                        <input type="hidden" name="rentID" value="${rentBook.id}">

                        <input type="submit" name="returnBookButton" value="Return">
                    </form>
                </td>
            </tr>
        </c:forEach>
        <c:forEach items="${sessionScope.purchasedBooks}" var="purchasedBooks">
            <tr>
                <td>${purchasedBooks.book.title}</td>
                <td>${purchasedBooks.book.author}</td>
                <td>${purchasedBooks.book.rating}</td>
                <td>${purchasedBooks.book.issue}</td>
                <td>Purchased</td>
                <td>
                    <form>
                        <input type="hidden" name="command" value="removePurchase">
                        <input type="hidden" name="purchaseID" value="${purchasedBooks.id}">

                        <input type="submit" name="returnBookButton" value="Clean">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<c:if test="${sessionScope.users != null}">
    <table border="1" class="usersTable">
        <tr>
            <td>Login</td>
            <td>Present rank</td>
            <td>Cash</td>
            <td>Rank control</td>
        </tr>
        <c:forEach items="${sessionScope.users}" var="user">
            <tr>
                <td>${user.login}</td>
                <td>${user.accessLevel}</td>
                <td>${user.cashValue}</td>
                <td>
                    <c:if test="${user.accessLevel==1}">
                        <form>
                            <input type="hidden" name="command" value="openChangeRankPanel">
                            <input type="hidden" name="login" value="${user.login}">
                            <input type="hidden" name="changingUserID" value="${user.id}">
                            <input type="hidden" name="actionName" value="Promote">
                            <input type="submit" name="promoteUserButton" value="Promote" class="tableButtonSize"/>
                        </form>
                        <form>
                            <input type="hidden" name="command" value="openChangeRankPanel">
                            <input type="hidden" name="login" value="${user.login}">
                            <input type="hidden" name="changingUserID" value="${user.id}">
                            <input type="hidden" name="actionName" value="Block">
                            <input type="submit" name="blockUserButton" value="Block" class="tableButtonSize"/>
                        </form>
                    </c:if>

                    <c:if test="${user.accessLevel == 2}">
                        <form>
                            <input type="hidden" name="command" value="openChangeRankPanel">
                            <input type="hidden" name="login" value="${user.login}">
                            <input type="hidden" name="changingUserID" value="${user.id}">
                            <input type="hidden" name="actionName" value="Demotion">
                            <input type="submit" name="demotionUserButton" value="Demotion"
                                   class="tableButtonSize"/>
                        </form>
                    </c:if>
                    <c:if test="${user.accessLevel==0}">
                        <form>
                            <input type="hidden" name="command" value="openChangeRankPanel">
                            <input type="hidden" name="login" value="${user.login}">
                            <input type="hidden" name="changingUserID" value="${user.id}">
                            <input type="hidden" name="actionName" value="Unblock">
                            <input type="submit" name="unblockUserButton" value="Unblock" class="tableButtonSize"/>
                        </form>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<div class="resultMessage">
    <h1>
        <c:out value="${requestScope.rankChangedSuccess}"/>
    </h1>
</div>

<c:if test="${requestScope.actionName != null}">
    <div class="rankEditCP">
        <form style="font-size: 20px; font-family: cursive;">
            <input type="hidden" name="command" value="changeUserAccessLevel">
            If you sure that you want to
            <br>
            <b style="font-size: 25px;"><c:out
                    value="${requestScope.actionName}  \"${requestScope.userLogin}\""/></b>
            <br>
            enter admin-<c:out value="${requestScope.actionName}"/> password
            <input type="password" name="adminPassword" value="" style="margin-bottom: 7px;">
            <input type="hidden" name="actionName" value="${requestScope.actionName}">
            <input type="hidden" name="changingUserID" value="${requestScope.changingUserID}">

            <input type="submit" name="submit" value="${requestScope.actionName}" class="tableButtonSize">
        </form>
    </div>
</c:if>

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

<h1 class="resultMessage">
    <c:out value="${requestScope.error}"/>
    <c:out value="${requestScope.nothing}"/>
    <c:out value="${requestScope.rentError}"/>
    <c:out value="${requestScope.addingError}"/>
    <c:out value="${param.addingSuccess}"/>
</h1>
<div style="right: 5%; top: 3%; width: 50px; height: 27px">
    <form>
        <input type="hidden" name="command" value="logOut">
        <input type="submit" name="logOutButton" value="${locale_log_out}">
    </form>
</div>

</body>
</html>
