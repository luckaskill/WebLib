<%--
  Created by IntelliJ IDEA.
  User: LAS
  Date: 02.02.2019
  Time: 3:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>registration</title>
    <style>
        body{
            background-image: url("https://images.bigissue.com/2017/03/gladstones_library_hero-pic.jpg");
            background-size: cover;

        }
        div{
            position: fixed;
            background-size: cover;
            top: 18%;
            left: 40%;
            width: 20%;
            color: #ffefd0;
        }
    </style>
</head>
<body>
<div align="center">
    <form action="controller" method="get" style="font-size: 17px;width: 70%">
        <input type="hidden" name="command" value="registration">
        Enter login:
        <input type="text" name="login" value=""/>
        <br/>
        Enter password:
        <input type="password" name="password" value=""/>
        <br/>
        Repeat password:
        <input type="password" name="passwordRepeat" value=""/>
        <br/>
        <input type="submit" name="doRegistrationButton" value="Registration" style="margin-top: 10px;"/>
    </form>
    <h1>
        <c:out value="${requestScope.error}"/>
    </h1>
</div>
</body>
</html>
