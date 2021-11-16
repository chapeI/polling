package presentation;

import Exceptions.WrongStateException;
import business.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class PollManagerServlet extends HttpServlet {
    PollManager PM;
    String color;

    public void init() {
        System.out.println("PollManagerServlet init()");
	this.PM = new PollManager();
    }

    /**
     * handles all request redirections to jsp depending on poll Status
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String submitType = request.getParameter("submit");
        System.out.println("SubmitType: " + submitType);
        if (submitType!=null){
            if (submitType.equalsIgnoreCase("Create New Poll")){
                // create new poll here
                request.getRequestDispatcher("create_poll.jsp").forward(request, response);
            }
        }


        // manage poll here
        String pollID = "";
        if (request.getParameter("pollID") != null) {
            pollID = request.getParameter("pollID");
        } else {
            pollID = "";
            // retrieve pollID from cookie
            Cookie[] cookies = request.getCookies();
            if(cookies != null){
                for(Cookie cookie : cookies){
                    if(cookie.getName().equals("pollID")) pollID = cookie.getValue();
                }
            }
        }

        HashMap<String, HashMap<String,String>> pollMap = PM.getPoll(pollID);
        ArrayList<HashMap<String,String>> choicesList = PM.getChoices(pollID);
        Poll poll = new Poll(pollID, pollMap, choicesList);
        Status status = poll.getPollStatus();
        request.setAttribute("poll", poll);
        request.setAttribute("choiceSize", poll.getChoices().size());

        // attach pollID as cookie to request
        // not sure how to keep the pollID saved in the request when coming back - this is the only way
        // i can think of for now
        Cookie message = new Cookie("pollID", pollID);
        response.addCookie(message);

        if (status == Status.running) {
            request.getRequestDispatcher("poll_running.jsp").forward(request, response);

        } else if (status == Status.created) {
            System.out.println(request.getParameter("status_change"));

            if (request.getParameter("status_change") != null && (request.getParameter("status_change").equals("CREATED_UPDATE") || request.getParameter("status_change").equals("RUNNING_UPDATE"))) {
                // go back to create poll
                System.out.println("poll needs to be updated.");
                request.getRequestDispatcher("update_poll.jsp").forward(request, response);
            } else {
                System.out.println("poll is created. show update and run buttons.");
                request.getRequestDispatcher("poll_created.jsp").forward(request, response);
            }
        } else if (status == Status.released) {
            request.getRequestDispatcher("poll_released.jsp").forward(request, response);
        } else if (status == Status.closed) {
            // TODO: show archived poll data
            request.getRequestDispatcher("poll_released.jsp").forward(request, response);
        }

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        System.out.println("PollManager doGet()");
        //poll = PM.getPoll();
        //Status status = PM.getPollStatus();


        // at the beginning, when no poll is created
//        if (poll == null) {
//            if (request.getParameter("password") != null) {
//                if (authorize(request.getParameter("password"))) {
//                    Manager manager = (Manager) request.getSession().getAttribute("manager");
//                    manager.setAuthorized(true);
//                } else {
//                    request.setAttribute("loginError", "Incorrect Password");
//                    request.getRequestDispatcher("start.jsp").forward(request, response);
//                }
//            }
//            request.getRequestDispatcher("create_poll.jsp").forward(request, response);
//        }

        //out.println("<div style=\"background-color:" + this.color + ";\"> Poll Status: " + status +  "</div>");



    }

    /**
     * handles all operations relating to poll creation and update
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ManagerServlet doPost()");

        try {
            List<String> choices = Arrays.asList(request.getParameterValues("choice"));
            List<String> descriptions = Arrays.asList(request.getParameterValues("description"));
            String name = request.getParameter("name");
            String question = request.getParameter("question");
            String updateChoice = request.getParameter("update_choice");
	        String pollID = request.getParameter("pollID");

            // retrieve userID (managerID)
            Cookie[] cookies = request.getCookies();
            String userID = "";
            if(cookies != null){
                for(Cookie cookie : cookies){
                    if(cookie.getName().equals("username")) userID = cookie.getValue();
                }
            }


            if (request.getParameter("submit").equalsIgnoreCase("create")){
                String newPollID = PM.createPoll(userID, name, question, choices, descriptions);

                // replace the pollID in cookie
                Cookie message = new Cookie("pollID", newPollID);
                response.addCookie(message);
            }
            else if (request.getParameter("submit").equalsIgnoreCase("update")) {
                if (updateChoice == null){
                    PM.updatePoll(pollID, name, question, null, null, false);
                }
                else if (request.getParameter("update_choice").equalsIgnoreCase("AddMoreChoices")){
                    // add more choices here
                    PM.updatePoll(pollID, name, question, choices, descriptions, false);
                }
                else if (request.getParameter("update_choice").equalsIgnoreCase("ReplaceChoices")) {
                    // replace choices here
                    PM.updatePoll(pollID, name, question, choices, descriptions, true);
                }
            }
        } catch (WrongStateException e) {
            System.out.println(e.getMessage());
        }
        // TODO: check for valid submission

        doGet(request, response);
    }


}
