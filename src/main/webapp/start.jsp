<%--
  Created by IntelliJ IDEA.
  User: khoatrinh
  Date: 2021-10-18
  Time: 4:32 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Start Page</title>
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
    <script>
        function logIn() {
            var form = document.getElementById("form");

            var password = document.createElement("input");
            password.type = "text";
            password.name = "password";
            password.required = true;
            password.placeholder = "Enter password";

            var logInButton = document.createElement("input");
            logInButton.type = "submit";
            logInButton.name = "submit";
            logInButton.value = "Log In";

            form.appendChild(password);
            form.appendChild(logInButton);
        }
    </script>
</head>
<body>
<jsp:useBean id='manager' class='business.Manager' scope="session">
    <jsp:setProperty name='manager' property='password' />
</jsp:useBean>




<h1 id="start"> Start Page</h1>
<form id='form' action='pollManager' >
    <%
        if (manager.isAuthorized()) {
    %>
    <input type="submit" value="Poll Manager" />
    <%
    } else {
    %>
    <button type="button" onclick="logIn()">Poll Manager</button>
    <%
        }
    %>
</form>
<%
    if (request.getAttribute("loginError") != null) {
%>
    <p style="color: red"> <%= request.getAttribute("loginError")%></p>
<%
    }
%>
<form action='vote' >
    <input type='submit' value='Participant' />
</form>

</body>
</html>
