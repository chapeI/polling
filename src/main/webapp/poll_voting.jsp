<%@ page import="business.Poll" %>
<%@ page import="business.Choice" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: khoatrinh
  Date: 2021-10-14
  Time: 11:37 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Poll voting</title>
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
    <h1>Poll</h1>

<%
    Poll poll = (Poll) request.getAttribute("poll");%>
    <b>Poll ID: </b>
    <%    out.println(poll.getId()); %>
    <br>
<b>Name: </b>
<%    out.println(poll.getName()); %>
<br>
<b>Question: </b>
<%    out.println(poll.getQuestion()); %>
<br><br>
<form action="vote" method="GET">
    <label>PIN #: </label>
    <input name="pin"><br/>
<%    List<Choice> choices = poll.getChoices();%>
<%    for (int i = 0 ; i < choices.size() ; i++) { %>
<input type="radio" name="choice" value="<%= choices.get(i).getText() %>" required/>
    <label><%= choices.get(i).getText() %></label>
<br>
&emsp; <i><b>  Description: </b></i>
    <%= choices.get(i).getDescription() %>
<br>
    <input type="hidden" name="pollID" value="<%= poll.getId() %>"/>
<%    }
%>
    <input type="submit">
</form>


<form action="state_manager" method="GET" >
    <button id="home" type="submit" value="HOME_PARTICIPANT" name="status_change" >Home</button>
</form>

</body>
</html>
