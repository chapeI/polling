<%--
  Created by IntelliJ IDEA.
  User: khoatrinh
  Date: 2021-10-14
  Time: 10:35 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Poll Running</title>
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<h1 id="running">Poll Manager</h1>
<br><br>

<form action="state_manager" method="GET">
    <input id="red" type="radio" name="status_change" value="RELEASE" />
    <label for="red">Release (RUNNING->RELEASED)</label>
<br><br>
    <input id="blue" type="radio" name="status_change" value="RUNNING_CLEAR" />
    <label for="blue">Clear</label>
<br><br>
    <input id="blue" type="radio" name="status_change" value="RUNNING_UPDATE" />
    <label for="blue">Update (RUNNING->CREATED)</label>
    <br><br>
    <input type="submit">
</form>

<form action="state_manager" method="GET" >
    <button id="home" type="submit" value="HOME" name="status_change" >Home</button>
</form>

</body>
</html>
