/*
 * Contributors: Jason, Ankit
 * Class is used to send user an email of their results
 */
package universityProgram;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class SendEmail {

	/**
	 * Method sends an email to the user containing their rankings
	 * @param currentUser
	 */
	public static void sendResults(User currentUser) {
		
		// Set up to and from email and host. Sourced from Jason's Account Creation Code
		String to = currentUser.getEmail();
		String from = "comsciisfun@gmail.com";
		String host = "localhost";

		Properties prop = new Properties();

		// settng up the ability to reach the servers. Sourced from Jason's Account Creation Code
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");

		// Authentication for the email that will do the sending. Logging on to the
		// account to be able to send emails. Sourced from Jason's Account Creation Code
		Session session = Session.getDefaultInstance(prop, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, "comsci11");
			}
		});
		
		// compose the message
		try {
			
			// Set up the message. Sourced from Jason's Account Creation Code
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			
			// Set up the actual subject and contents of the message. Made by Ankit
			message.setSubject("Your Ranking Results");
			
			String text ="";
			
			text += "Here are your personalized University Rankings!\n\n";
			
			// For each uni add their information to the email
			for (int uni = 0; uni < 10; uni++) {
				
				University tempUni = ResultsGUI.rankedUnis[uni];
				
				text += (uni + 1) + ". " + tempUni.getName() + "\n";
				text += tempUni.getPrograms().getName() + "\t" + "Eligibility: Yes" + "\tChances: " + tempUni.getUserChance() + "%\n\n";
				
			}
			
			text += "\nThank you for using our services!";
			
			message.setText(text);

			
			// Send message. Sourced from Jason's Account Creation Code
			Transport.send(message);
			//System.out.println("message sent successfully....");

		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
		
	}
	
}