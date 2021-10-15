package presentation;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "ValidateServlet", value = "/ValidateServlet")
public class ValidateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        List<String> errors = new ArrayList<String>();

        String name = request.getParameter("name");
        String question = request.getParameter("question");
        List<String> choices = Arrays.asList(request.getParameterValues("choice"));
        List<String> descriptions = Arrays.asList(request.getParameterValues("description"));

//        System.out.println("choices: " + choices);
//        System.out.println("descriptions: " + descriptions);


        if (name.equals("") || null == name) {
            errors.add("Name can't be empty.");
        }

        if (question.equals("") || null == question) {
            errors.add("Question can't be empty.");
        }


        for(String c : choices) {
            if(c.equals("") || null == c) {
                errors.add(c + " choice can't be empty");

            }
        }

        for(String d : descriptions) {
            if(d.equals("") || null == d) {
                errors.add(d + " description can't be empty");
            }
        }



        if (!errors.isEmpty()) {
            String errorList = "<ul>";
            for (String error : errors) {
                errorList += "<li>" + error + "</li>";
            }
            errorList += "</ul>";

            System.out.println(errorList);
            out.println("Form Validation");
            out.println(errorList);

            out.println("go back to previous page");

        } else {
            System.out.println("go to statemanager?");
            // No errors! Send the new HTML.
            request.getRequestDispatcher("pollManager").forward(request, response);
        }
    }
}
