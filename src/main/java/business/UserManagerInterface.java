package business;

public interface UserManagerInterface {
    String signUp(String userID, String FName, String LName, String email, String password);
    String forgotPassword(String email);
    boolean sendEmail(String email, String link);
    boolean changePassword(String password, String userID, String newPassword);
    boolean resetPassword(String email, String token, String newPassword);
    boolean verifyEmail(String token);
    boolean signIn(String userID, String password);
}
