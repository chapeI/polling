<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Manage Poll</title>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <script>
            function addMoreChoices() {
                var text = document.createElement("span");
                text.innerText = "Choice: "
                var textfield = document.createElement("input");
                textfield.type = "text";
                textfield.name = "choice";
                var br = document.createElement("br");
                var form = document.getElementById('form');
                form.appendChild(text);
                form.appendChild(textfield );

                var textDescription = document.createElement("span");
                textDescription.innerText = "Description: "
                var textfield = document.createElement("input");
                textfield.type = "text";
                textfield.name = "description";
                var br = document.createElement("br");
                var form = document.getElementById('form');
                form.appendChild(textDescription);
                form.appendChild(textfield );
                form.appendChild(br);
            }
        </script>
    </head>
    <body>

    <h1 id="created">Create a Poll</h1>

    <form  action="pollManager" method="POST">
        <div id = "form">
            Name: <input type="text" required="required" name="name"> <br>
            Question: <input type="text" required="required" name="question"> <br>
            Choice: <input type="text" required="required" name="choice"> Description: <input type="text" name="description"><br>
            Choice: <input type="text" required="required" name="choice"> Description: <input type="text" name="description"><br>
        </div>
        <button type="button" onclick="addMoreChoices()">Add choice</button>
        <input type="submit" name= "submit" value="Create">
    </form>

    
    <form action="state_manager" method="GET" >
    	<button id="home" type="submit" value="HOME" name="status_change" >Home</button>
    </form>  	  

    </body>
</html>
