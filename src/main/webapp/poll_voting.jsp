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
</head>
<body>
<h1>Current poll</h1>

<%
    Poll poll = (Poll) request.getAttribute("poll");%>
<b>Name: </b>
<%    out.println(poll.getName()); %>
<br>
<b>Question: </b>
<%    out.println(poll.getQuestion()); %>
<br><br>
<form action="state_manager" method="GET">
    <input name="participant">
<%    List<Choice> choices = poll.getChoices();%>
<%    for (int i = 0 ; i < choices.size() ; i++) { %>
<input type="radio" name="choice" value="<%= i %>" />
    <label><%= choices.get(i).getText() %></label>
<br>
&emsp; <i><b>  Description: </b></i>
    <%= choices.get(i).getDescription() %>
<br>
<%    }
%>
    <input type="submit">
</form>
<%--extract poll choices here--%>
<%--<form action="state_manager" method="GET">--%>
<%--    <input id="red" type="radio" name="status_change" value="RELEASE" />--%>
<%--    <label for="red">Release (RUNNING->RELEASED)</label>--%>
<%--    <br><br>--%>
<%--    <input id="blue" type="radio" name="status_change" value="RUNNING_CLEAR" />--%>
<%--    <label for="blue">Clear</label>--%>
<%--    <br><br>--%>
<%--    <input id="blue" type="radio" name="status_change" value="RUNNING_UPDATE" />--%>
<%--    <label for="blue">Update (RUNNING->CREATED)</label>--%>
<%--    <br><br>--%>
<%--    <input type="submit">--%>
<%--</form>--%>

<form action="state_manager" method="GET" >
    <button type="submit" value="HOME" name="status_change" >Home</button>
</form>

</body>
</html>
