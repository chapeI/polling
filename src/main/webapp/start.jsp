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
<div id="poll_status"><% request.getParameter("PollInfo"); %></div>
<form id='form' action='login' >
    <input type="submit" name="submit" value="Poll Manager" />
</form>
<form action='polls' >
    <input type='submit' value='Participant' />
</form>

</body>
</html>
