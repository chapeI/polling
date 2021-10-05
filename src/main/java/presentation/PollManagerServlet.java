package presentation;

import business.Poll;
import business.PollService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

public class PollManagerServlet extends HttpServlet {

    String status;
    Poll poll;
    String color;

    public void init() {
        System.out.println("PollManagerServlet init()");
        this.poll = PollService.instance().get_poll();
        this.status = this.poll.get_status();
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


        this.status = this.poll.get_status();
        System.out.println("status doGet() " + this.status);

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

        if (this.status == "RUNNING" ) {


            // release, clear, update
//            request.setAttribute("status", this.status);

            out.println("<html><body><h1>Poll Manager</h1>");
            out.println("poll is running. START NEW TAB and be a participant.");

            out.println("<br><br>");

            out.println("<form action=\"state_manager\" method=\"GET\">");

            out.println("<input id=\"red\" type=\"radio\" name=\"status_change\" value=\"RELEASE\" />");
            out.println("<label for=\"red\">Release</label>");

            out.println("<br><br>");

            out.println("<input id=\"blue\" type=\"radio\" name=\"status_change\" value=\"CLEAR\" />");
            out.println("<label for=\"blue\">Clear</label>");

            out.println("<br><br>");

            out.println("<input id=\"blue\" type=\"radio\" name=\"status_change\" value=\"UPDATE\" />");
            out.println("<label for=\"blue\">Update</label>");

            out.println("<br><br>");
            out.println("<input type=\"submit\">");
            out.println("</form>");


            out.println("</body></html>");

        } else if (this.status == "CREATED" ) {
//            System.out.println("poll is created. show update and run buttons.");

            out.println("<html><body><h1>Poll Manager</h1>");
            out.println("poll should show here");

             out.println("<br><br>");

            out.println("<form action=\"state_manager\" method=\"GET\">");

            out.println("<input id=\"red\" type=\"radio\" name=\"status_change\" value=\"UPDATE\" />");
            out.println("<label for=\"red\">Update</label>");

            out.println("<br><br>");

            out.println("<input id=\"blue\" type=\"radio\" name=\"status_change\" value=\"RUNNING\" />");
            out.println("<label for=\"blue\">Run</label>");

            out.println("<br><br>");
            out.println("<input type=\"submit\">");
            out.println("</form>");

            out.println("</body></html>");


            // update, run

        } else if (this.status == "RELEASED" ) {

            // clear, unrelease, close, view, download

            out.println("<html><body><h1>Poll Manager</h1>");

            out.println("<form action=\"state_manager\" method=\"GET\">");

            out.println("<input id=\"red\" type=\"radio\" name=\"status_change\" value=\"CLEAR\" />");
            out.println("<label for=\"red\">Clear</label>");

            out.println("<br><br>");

            out.println("<input id=\"blue\" type=\"radio\" name=\"status_change\" value=\"UNRELEASE\" />");
            out.println("<label for=\"blue\">Unrelease</label>");

            out.println("<br><br>");

            out.println("<input id=\"blue\" type=\"radio\" name=\"status_change\" value=\"CLOSE\" />");
            out.println("<label for=\"blue\">Close</label>");

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

        // use parameters to set Poll to correct values

        // TODO: check for valid submission
//        this.poll.setFakePoll();
//        this.init();
//        System.out.println("managerServlet-doPost() status: " + status);
//        this.status = this.poll.get_status();
        this.poll.set_status_to_created();
        doGet(request, response);
    }
}
