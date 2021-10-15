package presentation;

import business.Poll;
import business.PollManager;
import business.PollService;
import business.Status;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet()
public class StartingServlet extends HttpServlet {
    Status status;
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

        this.status = PollManager.getPollStatus();

        if (this.status == Status.running ) {
            this.color = "lightgreen";
        } else if (this.status == Status.created ) {
            this.color = "yellow";
        } else if (this.status == Status.released ) {
            this.color = "red";
        } else {
            this.color = "lightgrey";
        }

        out.println("<div style=\"background-color:" + this.color + ";\"> Poll Status: " + status +  "</div>");

        out.println("<html><body><h1>Start Page</h1>");

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
