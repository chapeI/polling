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
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
<h1>Poll System 2.0</h1>
<%
    String userID = (String) session.getAttribute("username");
%>
<p>User ID: <%=userID%></p>

<form id='form' action='login' >
    <input type='submit' name="submit" value='View Polls' />
    <input type='submit' name="submit" value='Vote' />
    <input type='submit' name="submit" value='Log out' />
</form>
</body>
</html>
