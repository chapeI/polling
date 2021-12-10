package Mailer;

import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;


public class MailSender implements Mailer {

    // the IP/hostname of the host
    private String host;

    public MailSender(){
	this.host = "127.0.0.1";
    }

    public MailSender(String hostname){
	this.host = hostname;
    }
    
    public void connectServive(){
	

    }

    public boolean checkConnection(){
	
	try{
	    InetAddress hostAddress = InetAddress.getByName(this.host);
	    return hostAddress.isReachable(5000);
	}catch(UnknownHostException e){
	    return false;
	}catch(IOException e){
	    return false;
	}

	
    }

    /**
     * Returns the response code of sending.
     */
    public int sendMail(String recipient, String sender, String subject, String body){

	// recieves system properties
	Properties properties = System.getProperties();

	properties.setProperty("mail.smtp.host", this.host);
	
	Session session = Session.getDefaultInstance(properties);
	System.out.println("Attempting to set parts of the message.");	
	try{
	    MimeMessage message = new MimeMessage(session);

	    message.setFrom(new InternetAddress(sender));

	    message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
	    System.out.println("Done setting the To and From.");
	    
	    message.setSubject(subject);

	    message.setText(body);
	    System.out.println("Message parts set.");
	    
	    Transport.send(message);
	    System.out.println("Message sent to "+recipient+" with the Subject being "+subject+".");
	    
	}catch(MessagingException e){
	    System.out.println("Message Failed to Send");
	}
	return 1;
    }

    

}
