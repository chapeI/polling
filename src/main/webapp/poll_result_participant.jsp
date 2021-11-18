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
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<h1>Here are the results</h1>
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
    }%>

<form action="state_manager" method="GET" >
    <button type="submit" value="DOWNLOAD" name="status_change" >Download</button>
    <button id="home" type="submit" value="HOME_PARTICIPANT" name="status_change" >Home</button>
</form>
</body>
</html>
