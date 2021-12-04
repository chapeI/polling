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
<%
    String error = (String) request.getAttribute("error");
    String replace = (String) request.getAttribute("replace");
    String newPIN = (String) request.getAttribute("newPIN");
    String pollID = (String) request.getAttribute("pollID");
    if (error != null){
        %>
        <p> PIN # cannot be found </p>
<%
    } else if (replace != null) {
        %>
<p><%=replace%></p>
        <%
    } else if (newPIN != null) {
        %>
<P>Thanks for voting. Here is the PIN associated to your vote: <%=newPIN%></P>
<%
    }
%>
<form action="state_manager" method="GET" >
    <button id="home" type="submit" value="HOME_PARTICIPANT" name="status_change" >Home</button>
</form>

<form action="polls" method="GET" >
    <button type="submit" value="vote" name="submit" >Back to poll</button>
    <input type="hidden" name="pollID" value="<%=pollID%>">
</form>

</body>
</html>
