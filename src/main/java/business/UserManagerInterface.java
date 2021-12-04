package business;

public interface UserManagerInterface {
    void signUp(String userID, String FName, String LName, String email, String password);
    void forgotPassword(String email);
    void emailVerification(String email);
    void changePassword(String password, String userID, String newPassword);
}
