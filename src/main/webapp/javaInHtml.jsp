<%--
  Created by IntelliJ IDEA.
  User: F_Khan
  Date: 4/25/2022
  Time: 10:25 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import = "java.util.Random, java.text.*"%>
<%!
    public int add(int a, int b){
        return a+b;
    }
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    Random random = new Random();
    // Returns a random number (0, 1 or 2)
    int randomInt = random.nextInt(3);
    if (randomInt == 0) {
%>
<h1>Random value =<%=randomInt%></h1>
<%
} else if (randomInt == 1) {
%>
<h2>Random value =<%=randomInt%></h2>
<%
} else {
%>
<h3>Random value =<%=randomInt%></h3>
<%
    }
%>
<h1>The sum of 3 + 8 is = <%out.println(add(3,8));%></h1>
<a href="<%= request.getRequestURI() %>">Try Again</a>
</body>
</html>
