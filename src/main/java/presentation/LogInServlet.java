package presentation;

import business.PollManager;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
public class LogInServlet extends HttpServlet {
    PollManager PM;

    public void init() {
        System.out.println("PollManagerServlet init()");
        this.PM = new PollManager();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String submitType = request.getParameter("submit");

        switch (submitType) {
            case "Poll Manager":
                request.getRequestDispatcher("login.jsp").forward(request,response);
                break;
            case "View Polls":
                Cookie[] cookies = request.getCookies();
                String userID = "";
                if(cookies != null){
                    for(Cookie cookie : cookies){
                        if(cookie.getName().equals("username")) userID = cookie.getValue();
                    }
                }
                HashMap<String, HashMap<String, String>> listOfPolls = PM.getListOfPollsCreatedBySelf(userID);
                request.setAttribute("listOfPolls", listOfPolls);
                request.getRequestDispatcher("manager_view_polls.jsp").forward(request,response);
                break;
        }
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        // get request parameters for username and password
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (authorize(password)) {
            //get the old session and invalidate
            HttpSession oldSession = request.getSession(false);
            if (oldSession != null) {
                oldSession.invalidate();
            }
            //generate a new session
            HttpSession newSession = request.getSession(true);

            Cookie message = new Cookie("username", username);
            response.addCookie(message);
            response.sendRedirect("login_success.jsp");
        } else {
            // TODO: handle if wrong pw
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/loginPage.html");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>Either username or password is wrong.</font>");
            rd.include(request, response);
        }
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
