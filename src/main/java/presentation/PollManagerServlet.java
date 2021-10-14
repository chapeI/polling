package presentation;

import Exceptions.WrongStateException;
import business.Poll;
import business.PollManager;
import business.PollService;
import business.Status;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class PollManagerServlet extends HttpServlet {

    Status status;
    PollManager pollManager;
    String color;

    public void init() {
        System.out.println("PollManagerServlet init()");
        pollManager = new PollManager();
//        this.pollManager = PollService.instance().get_poll();
//        this.status = this.pollManager.get_status();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

//        String status_change = request.getParameter("status_change");
//        System.out.println("status change to " + status_change);
//
//        this.status = status_change;
        System.out.println("PollManager doGet()");


        this.status = this.pollManager.getPollStatus();
        System.out.println("status doGet() " + this.status);

        if (this.status == Status.running ) {
            this.color = "lightgreen";
        } else if (this.status == Status.created ) {
            this.color = "yellow";
        } else if (this.status == Status.released ) {
            this.color = "red";
        } else {
            this.color = "lightgrey";
        }

        out.println("<div style=\"background-color:" + this.color + ";\"> Poll Status: " + this.pollManager.getPollStatus() +  "</div>");

        if (this.status == Status.running ) {


            // release, clear, update
//            request.setAttribute("status", this.status);

            out.println("<html><body><h1>Poll Manager</h1>");
            out.println("The poll is running. Participants may vote among the available choices. Open new tab to be a participant.");

            out.println("<br><br>");

            out.println("<form action=\"state_manager\" method=\"GET\">");

            out.println("<input id=\"red\" type=\"radio\" name=\"status_change\" value=\"RELEASE\" />");
            out.println("<label for=\"red\">Release (RUNNING->RELEASED)</label>");

            out.println("<br><br>");

            out.println("<input id=\"blue\" type=\"radio\" name=\"status_change\" value=\"RUNNING_CLEAR\" />");
            out.println("<label for=\"blue\">Clear</label>");

            out.println("<br><br>");

            out.println("<input id=\"blue\" type=\"radio\" name=\"status_change\" value=\"RUNNING_UPDATE\" />");
            out.println("<label for=\"blue\">Update (RUNNING->CREATED)</label>");

            out.println("<br><br>");
            out.println("<input type=\"submit\">");
            out.println("</form>");


            out.println("</body></html>");

        } else if (this.status == Status.created) {
            System.out.println("poll is created. show update and run buttons.");
            request.setAttribute("name", pollManager.getPoll().getName());
            request.setAttribute("question", pollManager.getPoll().getQuestion());
            request.setAttribute("status", status);
            request.getRequestDispatcher("poll_created.jsp").forward(request, response);

            // update, run

        } else if (this.status == Status.released ) {

            // clear, unrelease, close, view, download

            out.println("<html><body><h1>Poll Manager</h1>");

            out.println("<form action=\"state_manager\" method=\"GET\">");

            out.println("<input id=\"red\" type=\"radio\" name=\"status_change\" value=\"RELEASED_CLEAR\" />");  // note: CREATE, not CLEAR
            out.println("<label for=\"red\">Clear (RELEASED->CREATED)</label>");

            out.println("<br><br>");

            out.println("<input id=\"blue\" type=\"radio\" name=\"status_change\" value=\"UNRELEASE\" />");
            out.println("<label for=\"blue\">Unrelease (RELEASED->RUNNING)</label>");

            out.println("<br><br>");

            out.println("<input id=\"blue\" type=\"radio\" name=\"status_change\" value=\"CLOSE\" />");
            out.println("<label for=\"blue\">Close (RELEASED->NULL)</label>");

            out.println("<br><br>");

            out.println("<input id=\"blue\" type=\"radio\" name=\"status_change\" value=\"VIEW\" />");
            out.println("<label for=\"blue\">View Results</label>");

            out.println("<br><br>");

            out.println("<input id=\"blue\" type=\"radio\" name=\"status_change\" value=\"DOWNLOAD\" />");
            out.println("<label for=\"blue\">Download Results</label>");

            out.println("<br><br>");
            out.println("<input type=\"submit\">");
            out.println("</form>");


            out.println("</body></html>");


        } else {

            request.getRequestDispatcher("create_poll.jsp").forward(request, response);
        }

//        request.getRequestDispatcher("create_poll.jsp").forward(request, response); // landingPage
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ManagerServlet doPost()");
        try {
            pollManager.createPoll(request.getParameter("name"), request.getParameter("question"), null);
        } catch (WrongStateException e) {
            System.out.println(e.getMessage());
        }

        // use parameters to set Poll to correct values

        // TODO: check for valid submission
//        this.poll.setFakePoll();
//        this.init();
//        System.out.println("managerServlet-doPost() status: " + status);
//        this.status = this.poll.get_status();
//        this.pollManager.set_status_to_created();
        doGet(request, response);
    }
}
