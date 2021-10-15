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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class PollManagerServlet extends HttpServlet {
    Poll poll;
    String color;

    public void init() {
        System.out.println("PollManagerServlet init()");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        System.out.println("PollManager doGet()");
        poll = PollManager.getPoll();
        Status status = PollManager.getPollStatus();

        // at the beginning
        if (poll==null) {
            request.getRequestDispatcher("create_poll.jsp").forward(request, response);
        }

        System.out.println("status doGet() " + status);
        System.out.println(poll);
        if (status == Status.running ) {
            this.color = "lightgreen";
        } else if (status == Status.created ) {
            this.color = "yellow";
        } else if (status == Status.released ) {
            this.color = "red";
        } else {
            this.color = "lightgrey";
        }

        out.println("<div style=\"background-color:" + this.color + ";\"> Poll Status: " + status +  "</div>");

        if (status == Status.running ) {
            request.getRequestDispatcher("poll_running.jsp").forward(request, response);


        } else if (status == Status.created) {
            System.out.println(request.getParameter("status_change"));
            request.setAttribute("poll", poll);
            request.setAttribute("choiceSize", poll.getChoices().size());
            if (request.getParameter("status_change")!= null && (request.getParameter("status_change").equals("CREATED_UPDATE") || request.getParameter("status_change").equals("RUNNING_UPDATE"))) {
                // go back to create poll
                System.out.println("poll needs to be updated.");
                request.getRequestDispatcher("update_poll.jsp").forward(request, response);
            }
            else {
                System.out.println("poll is created. show update and run buttons.");
                request.getRequestDispatcher("poll_created.jsp").forward(request, response);
            }
        } else if (status == Status.released ) {
            request.getRequestDispatcher("poll_released.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("ManagerServlet doPost()");
        try {
            if (request.getParameter("submit").equalsIgnoreCase("create")){
                List<String> choices = Arrays.asList(request.getParameterValues("choice"));
                List<String> descriptions = Arrays.asList(request.getParameterValues("description"));
                PollManager.createPoll(request.getParameter("name"), request.getParameter("question"), choices, descriptions);
            }
            else if (request.getParameter("submit").equalsIgnoreCase("update")) {
                PollManager.updatePoll(request.getParameter("name"), request.getParameter("question"), null);
            }
        } catch (WrongStateException e) {
            System.out.println(e.getMessage());
        }
        // TODO: check for valid submission

        doGet(request, response);
    }
}
