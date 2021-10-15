<%--
  Created by IntelliJ IDEA.
  User: khoatrinh
  Date: 2021-10-14
  Time: 10:48 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Poll Released</title>
</head>
<body>
<h1>Poll Manager</h1>

<form action="state_manager" method="GET">

<input id="red" type="radio" name="status_change" value="RELEASED_CLEAR" />
<label for="red">Clear (RELEASED->CREATED)</label>

<br><br>

<input id="blue" type="radio" name="status_change" value="UNRELEASE" />
<label for="blue">Unrelease (RELEASED->RUNNING)</label>

<br><br>

<input id="blue" type="radio" name="status_change" value="CLOSE" />
<label for="blue">Close (RELEASED->NULL)</label>

<br><br>

<input id="blue" type="radio" name="status_change" value="VIEW" />
<label for="blue">View Results</label>

<br><br>

<input id="blue" type="radio" name="status_change" value="DOWNLOAD" />
<label for="blue">Download Results</label>
<br><br>
<input type="submit">
</form>

<form action="state_manager" method="GET" >
    <button type="submit" value="HOME" name="status_change" >Home</button>
</form>
</body>
</html>
