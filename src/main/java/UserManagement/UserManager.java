package UserManagement;

import business.UserManagerInterface;

public class UserManager implements UserManagerInterface {
    /**
     * A new user may be signed-up to the system by providing name, email address,
     * and other necessary felds, used in the previous assignments. Upon sign-up, a random
     * verifcation token is generated and attached to the record. The verifcation token is
     * then plugged into a verifcation link which is emailed to the user for email verifcation.
     * The password is then entered during the verifcation
     * @param userID
     * @param FName
     * @param LName
     * @param email
     * @param password
     */
    @Override
    public void signUp(String userID, String FName, String LName, String email, String password) {
        // generate token

        // generate verification link

        // email verification link

        // email verification
    }

    /**
     * This function is reserved to generate a change-password token. The
     * change-password token is similarly emailed to the user. Only one change password
     * token is allowed per user. Requesting another password change, overwrites the previous
     * requests. There is no expiry on the password change. Once the record is marked for
     * change, the previous password must be invalidated (i.e account is temporarily disabled).
     * @param email
     */
    @Override
    public void forgotPassword(String email) {
        // generate token

        // email token to user

        //

    }

    @Override
    public void emailVerification(String email) {
        //
    }

    @Override
    public void changePassword(String password, String userID, String newPassword) {

    }
}
