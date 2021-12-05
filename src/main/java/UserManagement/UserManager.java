package UserManagement;

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
        String token = "";
        boolean duplicateToken = false;
        // save token to DB
        do {
            // generate token
            token = generateToken();
            try {
                // save token to DB
                new DataConn().updateToken(email, token);
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
    public boolean sendEmail(String email, String link) {
        // transform message in here

        // gateway email

        return false;
    }

    private String generateToken() {
        Generex generex = new Generex("[a-zA-Z0-9]{10}");
        String randomStr = generex.random();
        System.out.println("Generating token: " + randomStr);
        return randomStr;
    }
}
