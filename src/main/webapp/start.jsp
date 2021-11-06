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
        <!-- CSS only -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <script>
            // function logIn() {
            //     var form = document.getElementById("form");
            //
            //     var password = document.createElement("input");
            //     password.type = "text";
            //     password.name = "password";
            //     password.required = true;
            //     password.placeholder = "Enter password";
            //
            //     var logInButton = document.createElement("input");
            //     logInButton.type = "submit";
            //     logInButton.name = "submit";
            //     logInButton.value = "Log In";
            //
            //     form.appendChild(password);
            //     form.appendChild(logInButton);
            // }
        </script>
    </head>
    <body >
        <jsp:useBean id='manager' class='business.Manager' scope="session">
            <jsp:setProperty name='manager' property='password' />
        </jsp:useBean>


        <div style="border: 1px solid white; width: 17%; border-radius: 30px; padding: 30px; text-align: center; margin-left: auto; margin-right: auto; margin-top: 15%">
            <h1 class="display-2" style="text-align: center; margin-bottom: 30px">Welcome</h1>

            <form id='form' action='pollManager' class="input-group mb-3">
                <% if (manager.isAuthorized()) { %>
                <input class="btn btn-primary" type="submit" value="Poll Manager Already Logged In. Go to Poll Manager" />
                <% } else { %>
                <input class="form-control"type="text" name="password" required placeholder="Password">
                <button class="btn btn-primary" type="submit">Poll Manager</button>
                <%--                <button type="button" class="btn btn-primary" onclick="logIn()">Poll Manager</button>--%>
                <% } %>
            </form>

            <% if (request.getAttribute("loginError") != null) { %>
            <p style="color: red"> <%= request.getAttribute("loginError")%></p>
            <% } %>

            <form action='vote' >
                <button class="btn btn-primary"  type='submit'>Participate</button>
            </form>
        </div>


    </body>
</html>
