<%--
  Created by IntelliJ IDEA.
  User: khoatrinh
  Date: 2021-10-15
  Time: 11:22 a.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Voted</title>
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<h2>You have voted</h2>
Thank you for participating

<form action="state_manager" method="GET" >
    <button id="home" type="submit" value="HOME" name="status_change" >Home</button>
</form>

</body>
</html>
