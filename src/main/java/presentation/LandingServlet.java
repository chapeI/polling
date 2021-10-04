package presentation;

import business.Poll;
import business.PollService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet()
public class LandingServlet extends HttpServlet {
    String status;
    Poll poll;
    String color;

    public void init() {
        this.poll = PollService.instance().get_poll();
//        this.status = this.poll.get_status();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        this.status = this.poll.get_status();

        if (this.status == "RUNNING" ) {
            this.color = "lightgreen";
        } else if (this.status == "CREATED" ) {
            this.color = "yellow";
        } else if (this.status == "RELEASED" ) {
            this.color = "red";
        } else {
            this.color = "lightgrey";
        }

        out.println("<div style=\"background-color:" + this.color + ";\"> Poll Status: " + this.poll.get_status() +  "</div>");

        out.println("<html><body><h1></h1>");
        out.println("<p>Select one</p>");

        out.println("<form action='pollManager' >");
        out.println("<input type='submit' value='Poll Manager' />");
        out.println("</form></body></html>");

        out.println("<form action='vote' >");
        out.println("<input type='submit' value='Participant' />");
        out.println("</form></body></html>");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
