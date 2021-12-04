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
                // save info to DB -> set status to NON-VALIDATED
                new DataConn().insertUser(userID, FName, LName, email, password, token);
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
        // generate token

        // save token to DB

        // return token to Servlet
        return null;
    }

    @Override
    public boolean emailVerification(String email, String link) {
        // send email
        return false;
    }

    @Override
    public boolean changePassword(String password, String userID, String newPassword) {
        // save info to DB
        return false;
    }

    @Override
    public boolean verifyEmail(String token) {
        // set Validated in DB
        return false;
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
