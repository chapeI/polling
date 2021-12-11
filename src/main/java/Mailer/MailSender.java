package Mailer;

import java.util.*;
import javax.mail.*;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.*;
import java.io.*;
import java.net.*;
import java.util.Properties;
//import java.smpt.*;

public class MailSender implements Mailer{

	// the IP/hostname of the host
	private String host;
	private Properties prop;
	private String sender = "soen387g15@gmail.com";;
	private String password = "Sog15en783";

	public MailSender(){
		this.prop = new Properties();
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.ssl.protocols", "TLSv1.2");

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
	public int sendMail (String recipient, String subject, String body){
		try {
			Session mailSession = Session.getDefaultInstance(this.prop,
					new javax.mail.Authenticator(){
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(
									sender, password);// Specify the Username and the PassWord
						}
					});
			Transport transport = mailSession.getTransport("smtp");

			MimeMessage message = new MimeMessage(mailSession);
			message.setSubject(subject);
			message.setContent(body, "text/plain");
			message.addRecipient(Message.RecipientType.TO,
					new InternetAddress(recipient));

			transport.connect();
			transport.sendMessage(message,
					message.getRecipients(Message.RecipientType.TO));
			transport.close();
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return 1;
	}
}
