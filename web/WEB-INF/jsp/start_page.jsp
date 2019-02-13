<%--
  Created by IntelliJ IDEA.
  User: LAS
  Date: 25.01.2019
  Time: 19:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <title>Start page</title>
    <style>
        body {
            background-size: cover;
            background-image: url("https://www.imgbase.info/images/safe-wallpapers/photography/indoor/40465_indoor_library.jpg");
        }
    </style>
</head>
<body>
<fmt:setLocale value="${sessionScope.local}"/>
<c:set var="www" value="12" scope="session"/>
<fmt:setBundle basename="resources.locale.locale" var="loc"/>
<fmt:message bundle="${loc}" key="locale.default.locale_button.eng" var="locale_button_eng"/>
<fmt:message bundle="${loc}" key="locale.default.locale_button.ru" var="locale_button_ru"/>
<fmt:message bundle="${loc}" key="locale.default.enter_login_message" var="locale_login"/>
<fmt:message bundle="${loc}" key="locale.default.enter_password_message" var="locale_password"/>
<fmt:message bundle="${loc}" key="locale.default.login_button" var="locale_log_in"/>
<fmt:message bundle="${loc}" key="locale.default.registration_button" var="locale_registration"/>
<div style="width: 100%;" align="right">
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
<div style="height: 500px; color: antiquewhite;" align="center">
    <form action="controller" method="get" style="margin-top: 10%;">
        <input type="hidden" name="command" value="authorization">
        ${locale_login}:
        <div style="margin-bottom: 10px;margin-top: 10px">
            <input type="text" name="login" value=""/>
        </div>
        ${locale_password}:
        <div style="margin-bottom: 10px;margin-top: 10px">
            <input type="password" name="password" value=""/>
        </div>
        <input type="submit" name="submit" value="${locale_log_in}"/>
    </form>
    <a href="controller?command=goToRegistrationPage" style="color: bisque;">${locale_registration}</a>
    <h1 style="color: bisque">
        <c:out value="${requestScope.error}"/>
    </h1>
</div>
</body>
</html>