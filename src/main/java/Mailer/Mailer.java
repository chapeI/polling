package Mailer;

public interface Mailer{

    public void connectServive();

    public boolean checkConnection();

    /**
     * Returns the response code of sending.
     */
    public int sendMail(String recipient, String sender, String topic, String body);

    
}
