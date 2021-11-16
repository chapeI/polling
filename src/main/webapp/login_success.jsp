<%--
  Created by IntelliJ IDEA.
  User: khoatrinh
  Date: 2021-11-15
  Time: 9:11 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>LogIn Success</title>
</head>
<body>
<h1>LOGIN SUCCESS</h1>
<%
    Cookie[] cookies = request.getCookies();
    String userID = "";
    if(cookies != null){
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("username")) userID = cookie.getValue();
        }
    }
%>
<p>User ID: <%=userID%></p>

<form id='form' action='login' >
    <input type='submit' name="submit" value='View Polls' />
    <input type='submit' name="submit" value='Vote' />
    <input type='submit' name="submit" value='Log out' />
</form>
</body>
</html>
