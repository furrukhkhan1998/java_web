<%@ page import="com.example.jsp.models.UserAccount" %><%--
  Created by IntelliJ IDEA.
  User: F_Khan
  Date: 4/26/2022
  Time: 8:45 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div style = "background: #E0E0E0; height: 55px; padding: 5px;">
    <div style="float: left">
        <h1>My Site</h1>
    </div>
    <div style="float: right;padding: 10px;text-align: right;">
        <%
            UserAccount user = (UserAccount) session.getAttribute("loginedUser");
            String username = "";
            if(!(user == null)){
                username= user.getUserName();
            }

        %>
        Hello<b><%out.print(username);%></b>
        <br/>
        Search<input name = "search">
    </div>
</div>
