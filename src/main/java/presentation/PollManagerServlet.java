package presentation;

import Exceptions.WrongStateException;
import business.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class PollManagerServlet extends HttpServlet {
    Poll poll;
    String color;

    public void init() {
        System.out.println("PollManagerServlet init()");
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
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        System.out.println("PollManager doGet()");
        poll = PollManager.getPoll();
        Status status = PollManager.getPollStatus();


        // at the beginning, when no poll is created
        if (poll==null) {
            if (request.getParameter("password") != null){
                if (authorize(request.getParameter("password"))) {
                    Manager manager = (Manager) request.getSession().getAttribute("manager");
                    manager.setAuthorized(true);
                }
                else {
                    request.setAttribute("loginError", "Incorrect Password");
                    request.getRequestDispatcher("start.jsp").forward(request,response);
                }
            }
            request.getRequestDispatcher("create_poll.jsp").forward(request, response);
        }

	// Will change the bar's colour at the top based on status
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

            if (request.getParameter("submit").equalsIgnoreCase("create")){
                PollManager.createPoll(name, question, choices, descriptions);
            }
            else if (request.getParameter("submit").equalsIgnoreCase("update")) {
                if (updateChoice == null){
                    PollManager.updatePoll(name, question, null, null, false);
                }
                else if (request.getParameter("update_choice").equalsIgnoreCase("AddMoreChoices")){
                    // add more choices here
                    PollManager.updatePoll(name, question, choices, descriptions, false);
                }
                else if (request.getParameter("update_choice").equalsIgnoreCase("ReplaceChoices")) {
                    // replace choices here
                    PollManager.updatePoll(name, question, choices, descriptions, true);
                }
            }
        } catch (WrongStateException e) {
            System.out.println(e.getMessage());
        }
        // TODO: check for valid submission

        doGet(request, response);
    }

    /**
     * authorize manager
     * @param userInput
     * @return
     */
    private boolean authorize(String userInput) {
        String hash = "2f5daf52c54ac06a7e86b6d5659828f3";
        String hashedInput = hash(userInput);

        return hash.equals(hashedInput);
    }

    /**
     * hash function to hash password
     * @param toHash
     * @return
     */
    private String hash (String toHash){
//        String toHash = "SOEN387";
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(toHash.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return (generatedPassword);
    }
}
