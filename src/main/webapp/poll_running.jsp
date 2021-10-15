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
</head>
<body>
<h1>Poll Manager</h1>
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

</body>
</html>
