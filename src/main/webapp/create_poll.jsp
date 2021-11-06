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



    <div style="border: 1px solid lightgray; border-radius: 30px; padding: 20px 50px 50px 50px;  width: 50%; margin: 10% auto 0px auto; ">

        <h1 class="display-1" style="text-align: center">
            Create a Poll
            <button class="btn" style="float: right; margin: 20px">
                <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor" class="bi bi-house" viewBox="0 0 16 16">
                    <path fill-rule="evenodd" d="M2 13.5V7h1v6.5a.5.5 0 0 0 .5.5h9a.5.5 0 0 0 .5-.5V7h1v6.5a1.5 1.5 0 0 1-1.5 1.5h-9A1.5 1.5 0 0 1 2 13.5zm11-11V6l-2-2V2.5a.5.5 0 0 1 .5-.5h1a.5.5 0 0 1 .5.5z"/>
                    <path fill-rule="evenodd" d="M7.293 1.5a1 1 0 0 1 1.414 0l6.647 6.646a.5.5 0 0 1-.708.708L8 2.207 1.354 8.854a.5.5 0 1 1-.708-.708L7.293 1.5z"/>
                </svg>
            </button>
        </h1>

    <form action="pollManager" method="POST">

            <div class="form-group">
                <input type="text" required class="form-control" placeholder="Enter Poll Name">
    <%--            <small id="emailHelp" class="form-text text-muted">Help</small>--%>
            </div>
            <div class="form-group">
                <input type="text" required class="form-control" placeholder="Enter Poll Question" name="question">
            </div>
            <div class="form-group">
                <input type="text" class="form-control" required name="choice" placeholder="Option 1">
                <input type="text" class="form-control" required name="description" placeholder="Description">
            </div>
            <div class="form-group">
                <input type="text" class="form-control" required name="choice" placeholder="Option 2">
                <input type="text" class="form-control" required name="description" placeholder="Description">
            </div>

        <div style="border: 1px solid white;  float: right">
            <button type="submit" class="btn btn-warning" name="submit">Add Option</button>
            <button type="" class="btn btn-primary" name=>Submit</button>
        </div>

<%--        <div>--%>
<%--            Name: <input type="text" required="required" name="name"> <br>--%>
<%--            Question: <input type="text" required="required" name="question"> <br>--%>
<%--            Choice: <input type="text" required="required" name="choice"> Description: <input type="text" name="description"><br>--%>
<%--            Choice: <input type="text" required="required" name="choice"> Description: <input type="text" name="description"><br>--%>
<%--        </div>--%>
<%--        <button type="button" onclick="addMoreChoices()">Add choice</button>--%>
<%--        <input type="submit" value="Create">--%>
    </form>

<%--    <form action="state_manager" method="GET" >--%>
<%--    	<button type="submit" value="HOME" name="status_change" >Home</button>--%>
<%--    </form>--%>

    </div>


    </body>
</html>
