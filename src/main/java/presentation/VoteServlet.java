package presentation;

import business.Poll;
import business.PollService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "VoteServlet", value = "/VoteServlet")
public class VoteServlet extends HttpServlet {

    String status;
    Poll poll;

    public void init() {
//        System.out.println("ManagerServlet init()");
        this.poll = PollService.instance().get_poll();
        this.status = this.poll.get_status();
//        System.out.println("status: " + status);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        this.status = this.poll.get_status();
        out.println("<div style=\"background-color:yellow;\"> Poll Status: " + this.poll.get_status() +  "</div>");

        if (this.status == "RUNNING" ) {
            out.println("show poll here");  // TODO: create poll

            out.println("<html><body>");
            out.println("CREATE POLL HERE");
            out.println("</body></html>");


        } else if (this.status == "CREATED" ) {

            out.println("<html><body>");
            out.println("keep refreshing page until you see a poll");
            out.println("</body></html>");

        } else if (this.status == "RELEASED" ) {

            out.println("<html><body><h3>Poll Ended </h3>");

            out.println("<form action='/debug.html' >");
            out.println("<input type='submit' value='GetPollResults()' />");

            out.println("<form action='vote' >");
            out.println("<input type='submit' value='DownloadPollResults()' /> ");
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
