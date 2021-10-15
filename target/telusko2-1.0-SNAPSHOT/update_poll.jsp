<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manage Poll</title>
</head>
<body>
<h1>Update a Poll</h1>

<form action="pollManager" method="POST">
    Name: <input type="text" name="name"> <br>
    Question: <input type="text" name="question"> <br>
    Description: <input type="text" name="description"> <br>
    Choice: <input type="text" name="choice"> <br>
    <input type="submit" name="submit" value="Update">
</form>

<form action="state_manager" method="GET" >
    <button type="submit" value="HOME" name="status_change" >Home</button>
</form>

</body>
</html>
