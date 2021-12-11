package UserManagement;

import Mailer.MailSender;
import Transform.Message;
import Transform.Transformer;
import business.UserManagerInterface;
import com.mifmif.common.regex.Generex;
import db.DataConn;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class UserManager implements UserManagerInterface {

    @Override
    public String signUp(String userID, String FName, String LName, String email, String password) {
        String token = "";
        boolean duplicateToken = false;
        do {
            // generate token
            token = generateToken();
            try {
                DataConn dc = new DataConn();
                // see if email already exists
                String user = dc.getUserIDByEmail(email);
                String account = dc.getAccountStatusByUserID(userID);
                if (account!=null){
                    return "Error: userID already exists";
                }
                if (user!=null){
                    return "Error: email already exists";
                }
                // save info to DB -> set status to NON-VALIDATED
                dc.insertUser(userID, FName, LName, email, password, token);
            } catch (SQLException throwables) {
                // if token is already given to someone else
                if (throwables instanceof SQLIntegrityConstraintViolationException) {
                    duplicateToken = true;
                }
                throwables.printStackTrace();
            }
        } while (duplicateToken);


        // return token to servlet
        return token;
    }

    @Override
    public String forgotPassword(String email) {
        DataConn dc = new DataConn();
        String token = "";
        boolean duplicateToken = false;
        // save token to DB
        do {
            // generate token
            token = generateToken();
            try {
                // look for user
                String user = dc.getUserIDByEmail(email);
                if (user ==null) {
                    return token;
                }
                // save token to DB
                dc.updateToken(email, token);

                // temporarily disable account
                dc.updateValidate(false, token);

            } catch (SQLException throwables) {
                // if token is already given to someone else
                if (throwables instanceof SQLIntegrityConstraintViolationException) {
                    duplicateToken = true;
                }
                throwables.printStackTrace();
            }
        } while (duplicateToken);

        // return token to Servlet
        return token;
    }

    @Override
    public boolean changePassword(String password, String userID, String newPassword) {
        // save info to DB
        String oldpw = "";
        try {
            DataConn dc = new DataConn();
            oldpw = dc.getPassword(userID);
            if (oldpw.equals(password)){
                dc.updatePassword(userID, newPassword);
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean resetPassword(String email, String token, String newPassword) {
        DataConn dc = new DataConn();
        try {
            String emailFromDB = dc.getEmailByToken(token);

            if (email.equalsIgnoreCase(emailFromDB)){
                String userID = dc.getUserIDByEmail(email);
                boolean success = dc.updatePassword(userID, newPassword);

                if (success) {
                    // set Account status to Validated
                    dc.updateValidate(true, token);
                }

                return success;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean verifyEmail(String token) {
        DataConn dc = new DataConn();
        boolean success = false;
        // set Validated in DB
        try {
            String status = dc.getAccountStatusByToken(token);

            // if account status is already validated or if can't find token -> return false
            if (status==null || status.equalsIgnoreCase("VALIDATED")){
                return false;
            }
            success = dc.updateValidate(true, token);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return success;
    }

    @Override
    public boolean signIn(String userID, String password) {
        DataConn dc = new DataConn();

        try{
            String accountStatus = dc.getAccountStatusByUserID(userID);

            // only allow sign in if account has been validated
            if (accountStatus != null && accountStatus.equalsIgnoreCase("VALIDATED")){
                String passwordFromDB = dc.getPassword(userID);

                if (passwordFromDB!= null && passwordFromDB.equals(password)){
                    return true;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean sendEmailSignUp(String email, String link, String templatePath) {
        String message = "";
        DataConn dc = new DataConn();

        // transform message
        try {
            String userid = dc.getUserIDByEmail(email);
            Message m = new Message(userid, email);
            m.setLink(link);

            message = Transformer.transform(m, templatePath);
            System.out.println(message);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        MailSender mailSender = new MailSender();
        mailSender.sendMail(email, "Poll System 2.0 Sign Up", message);

        return false;
    }

    @Override
    public boolean sendEmailForgotPassword(String email, String token, String templatePath) {
        String message = "";
        DataConn dc = new DataConn();

        // transform message
        try {
            String userid = dc.getUserIDByEmail(email);
            Message m = new Message(userid, email);
            m.setToken(token);

            message = Transformer.transform(m, templatePath);
            System.out.println(message);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        MailSender mailSender = new MailSender();
        mailSender.sendMail(email, "Poll System 2.0 Forgot Password", message);

        return false;
    }

    private String generateToken() {
        Generex generex = new Generex("[a-zA-Z0-9]{10}");
        String randomStr = generex.random();
        System.out.println("Generating token: " + randomStr);
        return randomStr;
    }
}
