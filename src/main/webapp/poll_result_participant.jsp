<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: khoatrinh
  Date: 2021-10-16
  Time: 10:06 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Result View for Participant</title>
</head>
<body>
<h1>Here are the results</h1>
<%    HashMap<String, Integer> results = (HashMap<String, Integer>) request.getAttribute("results");%>
<% for (Map.Entry<String, Integer> entry : results.entrySet()) { %>
<p> <%= entry.getKey()%></p>
&emsp; <i><b>  Count: </b></i> <%= entry.getValue()%><br>
<%} %>

<form action="state_manager" method="GET" >
    <button type="submit" value="HOME" name="status_change" >Home</button>
</body>
</html>
