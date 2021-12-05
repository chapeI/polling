<%--
  Created by IntelliJ IDEA.
  User: khoatrinh
  Date: 2021-12-04
  Time: 8:27 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign Up</title>
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script>
    var check = function() {
        if (document.getElementById('new_password').value ==
            document.getElementById('confirm_password').value) {
            document.getElementById('message').style.color = 'green';
            document.getElementById('message').innerHTML = 'matching';
            document.getElementById('signup').disabled = false;
        } else {
            document.getElementById('message').style.color = 'red';
            document.getElementById('message').innerHTML = 'not matching';
            document.getElementById('signup').disabled = true;
        }
    }
</script>


</head>
<body>
<div style="border: 1px solid lightgray; border-radius: 30px; padding: 20px 50px 50px 50px;  width: fit-content; margin: 10% auto 0px auto; ">

    <h1 id="created" style="text-align: center; color: black; padding: 10px;">Reset Password</h1>

    <br>

    <form  action="umanager" method="POST">
        <p>We have sent an email containing a token to <%=request.getAttribute("email")%></p>
        <p>Please enter your token below to reset your password</p>
        <div>
            <input hidden type="text" required class="form-control" placeholder="Email" name="email" value = "<%=request.getAttribute("email")%>">
            <input type="text" id="token" required class="form-control" placeholder="Token" name="token">
            <input type="password" id="new_password" required class="form-control" placeholder="New password" name="password" onkeyup='check();'>
            <input type="password" id="confirm_password" required class="form-control" placeholder="Re-enter password" name="password" onkeyup='check();'>
            <span id='message'></span>
        </div>

        <br>

        <input disabled class="btn btn-primary" id = "signup" type="submit" name= "submit" value="Reset Password">
        <%
            if (request.getAttribute("error") != null) {
        %>
        <p style="color: red"> <%= request.getAttribute("error")%></p>
        <%
            }
        %>
    </form>


    <form action="state_manager" method="GET" >
        <button class="btn btn-primary" id="home" type="submit" value="HOME_PARTICIPANT" name="status_change" >Home</button>
    </form>
</div>
</body>
</html>
