<%--
  Created by IntelliJ IDEA.
  User: F_Khan
  Date: 4/26/2022
  Time: 10:23 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Info</title>
</head>
<body>
    <jsp:include page="_header.jsp"></jsp:include>
    <jsp:include page="_menu.jsp"></jsp:include>

    <h3>Hello: ${user.userName}</h3>

    User Name: <b>${user.userName}</b>
    </br>
    Gender: ${user.gender} </br>

    <jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>
