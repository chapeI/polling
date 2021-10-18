package business;

public class Manager {
    String name;
    String password;
    boolean authorized;

    public Manager() {
    }

    public Manager(String password, boolean authorized) {
        this.password = password;
        this.authorized = authorized;
    }

    public Manager(String name, String password, boolean authorized) {
        this.name = name;
        this.password = password;
        this.authorized = authorized;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAuthorized() {
        return authorized;
    }

    public void setAuthorized(boolean authorized) {
        this.authorized = authorized;
    }
}
