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

@WebServlet(name = "VoteServlet", value = "/VoteServlet")
public class VoteServlet extends HttpServlet {

    Status status;
    Poll poll;
    String color;

    public void init() {
//        System.out.println("ManagerServlet init()");
//        this.poll = PollService.instance().get_poll();
//        System.out.println("status: " + status);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        this.status = PollManager.getPollStatus();
        this.poll = PollManager.getPoll();
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

        if (this.status == Status.running ) {
            request.setAttribute("poll", poll);
            request.getRequestDispatcher("poll_voting.jsp").forward(request, response);

            out.println("<html><body>");
            out.println("show poll here");  // TODO: create poll
            out.println("</body></html>");


        } else if (this.status == Status.created ) {

            out.println("<html><body>");
            out.println("<h1>Participant</h1>");
            out.println("keep refreshing this page until you see a poll");
            out.println("</body></html>");

        } else if (this.status == Status.released ) {

            out.println("<html><body><h3>Poll Ended </h3>");

            out.println("<form action=\"state_manager\" method=\"GET\">");

            out.println("<input id=\"blue\" type=\"radio\" name=\"status_change\" value=\"VIEW_PARTICIPANT\" />");
            out.println("<label for=\"blue\">View Results</label>");

            out.println("<br><br>");

            out.println("<input id=\"blue\" type=\"radio\" name=\"status_change\" value=\"DOWNLOAD\" />");
            out.println("<label for=\"blue\">Download Results</label>");

            out.println("<br><br>");
            out.println("<input type=\"submit\">");
            out.println("</form>");

            out.println("</body></html>");


        } else {

            out.println("<html><body>");
            out.println("no poll to see, go back.");
            out.println("</body></html>");

//            System.out.println("create poll");
//            request.getRequestDispatcher("create_poll.jsp").forward(request, response);
        }



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
