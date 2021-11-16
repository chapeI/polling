<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: khoatrinh
  Date: 2021-11-15
  Time: 9:53 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manager View Polls</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
<h1> Manager view poll </h1>
<%
    Cookie[] cookies = request.getCookies();
    String userID = "";
    if(cookies != null){
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("username")) userID = cookie.getValue();
        }
    }

    HashMap<String, HashMap<String, String>> listOfPolls = (HashMap<String, HashMap<String, String>>) request.getAttribute("listOfPolls");
%>
<p>User ID: <%=userID%></p>

<%

%>

<table class="table table-striped">
    <thead>
    <tr>
        <th scope="col">Poll ID</th>
        <th scope="col">Poll Name</th>
        <th scope="col"></th>
    </tr>
    </thead>
    <tbody>
    <%
        for (Map.Entry<String, HashMap<String, String>> poll: listOfPolls.entrySet()){
            HashMap<String, String> pollDataMap = poll.getValue();
            String pollName = pollDataMap.get("PollName");
            %>
    <tr>
        <th scope="row"><%=poll.getKey()%></th>
        <td><%=pollName%></td>
        <td>
            <form action="pollManager">
                <input class="btn btn-primary" name="submit" type="submit" value="Manage">
                <input type="hidden" name="pollID" value="<%=poll.getKey()%>">
            </form>

        </td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>

<form action="pollManager">
    <input class="btn btn-success" name="submit"  type="submit" value="Create New Poll">
</form>

</body>
</html>
