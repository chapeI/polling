<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Manage Poll</title>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
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
        <div style="border: 1px solid lightgray; border-radius: 30px; padding: 20px 50px 50px 50px;  width: 25%; margin: 10% auto 0px auto; ">

            <h1 id="created" style="text-align: center; color: black; padding: 10px;">Create Poll</h1>

            <br>

            <form  action="pollManager" method="POST">
                <div id = "form">
                    <input type="text" required class="form-control" placeholder="Enter Poll Name" name="name">
                    <input type="text" required class="form-control" placeholder="Enter Poll Question" name="question">

                    <input type="text" class="form-control" required name="choice" placeholder="Option 1">
                    <input type="text" class="form-control" required name="description" placeholder="Option 1 Description">

                    <input type="text" class="form-control" required name="choice" placeholder="Option 2">
                    <input type="text" class="form-control" required name="description" placeholder="Option 2 Description">
                </div>

                <br>

                <button class="btn btn-primary" type="button" onclick="addMoreChoices()">Add choice</button>
                <input class="btn btn-primary" type="submit" name= "submit" value="Create">
            </form>


            <form action="state_manager" method="GET" >
                <button class="btn btn-primary" id="home" type="submit" value="HOME" name="status_change" >Home</button>
            </form>
        </div>
    </body>
</html>
