<%--
  Created by IntelliJ IDEA.
  User: F_Khan
  Date: 4/26/2022
  Time: 3:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
    <title>Delete Product</title>
</head>
<body>
  <jsp:include page="_header.jsp"></jsp:include>
  <jsp:include page="_menu.jsp"></jsp:include>

  <h3>Delete Product</h3>
  <p style="color:red";>${errorString}</p>
  <a href="productList">Product List</a>

  <jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>
