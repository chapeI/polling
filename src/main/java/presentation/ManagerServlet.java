package presentation;

import business.Poll;
import business.PollService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

public class ManagerServlet extends HttpServlet {

    String status;
    Poll poll;

    public void init() {
        System.out.println("ManagerServlet init()");
        this.poll = PollService.instance().get_poll();
        this.status = this.poll.get_status();
        System.out.println("status: " + status);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        this.status = this.poll.get_status();
        out.println("<div style=\"background-color:yellow;\"> Poll Status: " + this.poll.get_status() +  "</div>");

        if (this.status == "RUNNING" ) {

            // release, clear, update
//            request.setAttribute("status", this.status);



            out.println("<html><body><h1>Poll Manager</h1>");

            out.println("<form action='/debug.html' >");
            out.println("<input type='submit' value='Release' />");

            out.println("<form action='vote' >");
            out.println("<input type='submit' value='Clear' /> ");
            out.println("</form>");

            out.println("<form action='vote' >");
            out.println("<input type='submit' value='Update' /> ");
            out.println("</form>");

            out.println("</body></html>");

        } else if (this.status == "CREATED" ) {
//            System.out.println("poll is created. show update and run buttons.");

            out.println("<html><body><h1>Poll Manager</h1>");

            out.println("show poll here");

            out.println("<form action='/debug.html' >");
            out.println("<input type='submit' value='Update' />");

            out.println("<form action='vote' >");
            out.println("<input type='submit' value='Run' /> ");
            out.println("</form>");

            out.println("</body></html>");


            // update, run

        } else if (this.status == "RELEASED" ) {

            // clear, unrelease, close, view, download

            out.println("<html><body><h1>Poll Manager</h1>");

            out.println("<form action='/debug.html' >");
            out.println("<input type='submit' value='Clear' />");

            out.println("<form action='vote' >");
            out.println("<input type='submit' value='Unrelease' /> ");
            out.println("</form>");

            out.println("<form action='vote' >");
            out.println("<input type='submit' value='Close' /> ");
            out.println("</form>");

            out.println("<form action='vote' >");
            out.println("<input type='submit' value='View Results' /> ");
            out.println("</form>");

            out.println("<form action='vote' >");
            out.println("<input type='submit' value='Download Results' /> ");
            out.println("</form>");


            out.println("</body></html>");


        } else {

            System.out.println("create poll");
            request.getRequestDispatcher("create_poll.jsp").forward(request, response);
        }

//        request.getRequestDispatcher("create_poll.jsp").forward(request, response); // landingPage
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ManagerServlet doPost()");

        // use parameters to set Poll to correct values

        // TODO: check for valid submission
        this.poll.setFakePoll();
//        this.init();
        this.status = this.poll.get_status();
        System.out.println("managerServlet-doPost() status: " + status);
        doGet(request, response);


    }
}
