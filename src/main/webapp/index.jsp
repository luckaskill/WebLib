<%--
  Created by IntelliJ IDEA.
  User: LAS
  Date: 25.01.2019
  Time: 19:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<body>
<c:set value="eng" var="local" scope="session"/>
<c:redirect url="controller?command=goToStartPage"/>
</body>
</html>
