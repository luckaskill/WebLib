<%--
  Created by IntelliJ IDEA.
  User: LAS
  Date: 25.01.2019
  Time: 19:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<body>
<h1>hhh</h1>
<form>
    <input type="hidden" name="command" value="goToRegistrationPage">
    <input type="submit" name="l" value="submit">
</form>
<c:set value="eng" var="local" scope="session"/>
<c:redirect url="controller?command=goToStartPage"/>
</body>
</html>
