<%@ page import="business.Poll" %>
<%@ page import="business.Choice" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: khoatrinh
  Date: 2021-10-13
  Time: 9:33 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Test</title>
</head>
<body>

<h1> This is the poll</h1>
<%
    Poll poll = (Poll) request.getAttribute("poll");%>
    <b>Name: </b>
<%    out.println(poll.getName()); %>
<br>
    <b>Question: </b>
<%    out.println(poll.getQuestion()); %>
<br><br>
<%    List<Choice> choices = poll.getChoices(); %>
<%    for (Choice c : choices) { %>
    <b>Choice: </b>
<%        out.println(c.getText()); %>
<br>
&emsp; <i><b>  Description: </b></i>
<%       out.println(c.getDescription()); %>
<br>
<%    }
%>


<h1>Poll Manager</h1>
<br>

<br><br>


<form action="state_manager" method="GET">
<input id="red" type="radio" name="status_change" value="CREATED_UPDATE" />
    <label for="red">Update (clear results)</label>
<br><br>
<input id="blue" type="radio" name="status_change" value="RUNNING" />
<label for="blue">Run (CREATED->RUNNING)</label>
<br><br>
<input type="submit">
</form>

<form action="state_manager" method="GET" >
    <button type="submit" value="HOME" name="status_change" >Home</button>
</form>


</body>
</html>
