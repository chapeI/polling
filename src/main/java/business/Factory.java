package business;

import UserManagement.UserManager;

public class Factory {
    private static UserManagerInterface UM = new UserManager();
    public static UserManagerInterface getUMObj(){
        return UM;
    }
}
