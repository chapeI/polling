<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %><%--
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
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<h1 id="released">Poll Manager</h1>

<form action="state_manager" method="GET">
    <input id="blue" type="radio" name="status_change" value="DOWNLOAD" />
    <label for="blue">Download Results</label>
    <br><br>
    <input type="submit">
</form>

<br><br><br>

<% if (true) { %>
<h1 id="released">Here are the results</h1>
<%
    HashMap<String, Integer> results = (HashMap<String, Integer>) request.getAttribute("results");
    if (results != null) {
%>
<% for (Map.Entry<String, Integer> entry : results.entrySet()) { %>
<p> <%= entry.getKey()%></p>
&emsp; <i><b>  Count: </b></i> <%= entry.getValue()%><br>
<%}
}
else {
    out.println("No one has voted yet");
}


}%>

<form action="state_manager" method="GET" >
    <button id="home" type="submit" value="HOME" name="status_change" >Home</button>
</form>
</body>
</html>
