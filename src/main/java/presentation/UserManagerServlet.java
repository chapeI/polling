package presentation;

import business.Factory;
import business.UserManagerInterface;
import db.DataConn;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class UserManagerServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("submit");

        // user accesses servlet from sign-up page
        if (type!=null) {
            if (type.equalsIgnoreCase("Sign Up")){
                request.getRequestDispatcher("sign_up.jsp").forward(request, response);
            }
        }

        // user accesses servlet directly from link sent in email
        else {
            String token = request.getParameter("token");
            String message = "";
            String username = "";
            // Validate account
            boolean success = Factory.getUMObj().verifyEmail(token);

            if (success) {
                //get the old session and invalidate
                HttpSession oldSession = request.getSession(false);
                if (oldSession != null) {
                    oldSession.invalidate();
                }
                //generate a new session
                HttpSession newSession = request.getSession(true);
                try {
                    username = new DataConn().getUserIDByToken(token);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                // save userID in session
                newSession.setAttribute("username", username);

                // set success message
                message = "Your email has been verified.";
                request.setAttribute("verified", true);
            }
            else {
                message = "We cannot verify your email";
                request.setAttribute("verified", false);
            }
            request.setAttribute("message", message);
            request.getRequestDispatcher("email_verified.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("submit");

        if (type.equalsIgnoreCase("Sign Up")){
            String fname = request.getParameter("firstname");
            String lname = request.getParameter("lastname");
            String uid = request.getParameter("userid");
            String email = request.getParameter("email");
            List<String> password = Arrays.asList(request.getParameterValues("password"));

            UserManagerInterface UM = Factory.getUMObj();
            String token = UM.signUp(uid, fname, lname, email, password.get(0));

            if (token.contains("Error")){
                request.setAttribute("error", "Email provided already exists.");
                request.getRequestDispatcher("sign_up.jsp").forward(request,response);
            }

            // generate link with token
            String link = request.getRequestURL().toString()+"?token="+token;
            System.out.println(link);

            // send link to user's email
            UM.sendEmail(email, link);
            request.setAttribute("email", email);
            request.getRequestDispatcher("email_sent.jsp").forward(request, response);
        }
    }
}
