/*
 * Jason Ta-min
 * Account creation screen 
 * Dec, 18, 2020
 */
package universityProgram;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.Scanner;
import javax.mail.PasswordAuthentication;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;


public class AccountCreation extends JFrame implements ActionListener {
	
	private JFrame AccountCreation;
	private JButton back, create;
	private JCheckBox show, cshow;
	private JLabel top, first, last, username, password, cpassword, strength, email;
	private JTextField enterfirst, enterlast, enteruser, enteremail;
	private JPasswordField enterpass, cEnterpass;
	private Color[] passStrengthColour = {Color.red, Color.yellow, Color.green};
	private int strongLength = 10;
	private JProgressBar passStrength;
	
	public AccountCreation() {
		
		//intialize all local variable 
		AccountCreation = new JFrame("University Assistant");
		top = new JLabel("Account Creation");
		first = new JLabel("First Name: ");
		last = new JLabel("Last Name: ");
		username = new JLabel("Username: ");
		password = new JLabel("Password: ");
		cpassword = new JLabel("Confirm Password: ");
		strength = new JLabel("Password Strength: ");
		email = new JLabel("Email: ");
		back = new JButton("Back");
		create = new JButton("Create Account");
		show = new JCheckBox();
		cshow = new JCheckBox();
		enterfirst = new JTextField();
		enterlast = new JTextField();
		enteruser = new JTextField();
		enterpass = new JPasswordField();
		cEnterpass = new JPasswordField();
		enteremail = new JTextField();
		passStrength = new JProgressBar();
		ImageIcon showp = new ImageIcon("show.png");

		
		
		//set locatian and asthtics of "back" button
		back.setBounds(1050, 35, 100, 50);
		back.setFocusable(false);
		back.setBackground(Color.red);
		back.setFont(new Font("Arial", Font.BOLD,20));
		back.setBorder(BorderFactory.createRaisedBevelBorder());
		back.addActionListener(this);
		
		//set locatian and asthtics of "create account" button
		create.setBounds(1000, 600, 200, 50);
		create.setFocusable(false);
		create.setBackground(Color.green);
		create.setFont(new Font("Arial", Font.BOLD,20));
		create.setBorder(BorderFactory.createRaisedBevelBorder());
		create.addActionListener(this);
		
		//set locatian and asthtics of "show password" button
		show.setBounds(520, 402, 40, 40);
		show.setFocusable(false);
		show.setIcon(showp);
		show.setHorizontalAlignment(JButton.CENTER);
		show.setBackground(Color.red);
		show.setBorder(BorderFactory.createEmptyBorder());
		show.addActionListener(this);
		
		//set locatian and asthtics of "show confirm password" button
		cshow.setBounds(650, 502, 40, 40);
		cshow.setFocusable(false);
		cshow.setIcon(showp);
		cshow.setHorizontalAlignment(JButton.CENTER);
		cshow.setBackground(Color.red);
		cshow.setBorder(BorderFactory.createEmptyBorder());
		cshow.addActionListener(this);
		
		//set locatian and asthtics of "page header" label
		top.setBounds(0, 0, 1280, 120);
		top.setFont(new Font("Tahoma", Font.BOLD,45));
		top.setOpaque(true);
		top.setBackground(Color.white);

		//set locatian and asthtics of "first name" label
		first.setBounds(20, 130, 300, 45);
		first.setFont(new Font("Tahoma", Font.BOLD,30));
		
		//set locatian and asthtics of "last name" label
		last.setBounds(20, 200, 300, 45);
		last.setFont(new Font("Tahoma", Font.BOLD,30));
		
		//set locatian and asthtics of "username" label
		username.setBounds(20, 300, 300, 45);
		username.setFont(new Font("Tahoma", Font.BOLD,30));
		
		//set locatian and asthtics of "password" label
		password.setBounds(20, 400, 300, 45);
		password.setFont(new Font("Tahoma", Font.BOLD,30));
		
		//set locatian and asthtics of "confrim password" label
		cpassword.setBounds(20, 500, 300, 45);
		cpassword.setFont(new Font("Tahoma", Font.BOLD,30));
		
		//set locatian and asthtics of "password strength" label
		strength.setBounds(575, 400, 300, 45);
		strength.setFont(new Font("Tahoma", Font.BOLD,15));
		
		//set locatian and asthtics of "email" label
		email.setBounds(20, 600, 300, 45);
		email.setFont(new Font("Tahoma", Font.BOLD,30));
		
		//set locatian of first name input text box
		enterfirst.setBounds(200, 130, 300, 45);
		enterfirst.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		//set locatian of last name input text box
		enterlast.setBounds(200, 200, 300, 45);
		enterlast.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		//set locatian of username input text box
		enteruser.setBounds(200, 300, 300, 45);
		enteruser.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		//set locatian of password input text box, and set the default character to be stars to allow oneness with the "show password" function
		enterpass.setBounds(200, 400, 300, 45);
		enterpass.setEchoChar('*');
		enterpass.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		//set locatian of confirm password input text box, and set the default character to be stars to allow oneness with the "show password" function
		cEnterpass.setBounds(325, 500, 300, 45);
		cEnterpass.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cEnterpass.setEchoChar('*');
		
		//set locatian of email input text box
		enteremail.setBounds(200, 600, 300, 45);
		enteremail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		//set location of password strength progress bar
		passStrength.setBounds(725, 413, 300, 20);
		
		//set what the progressbar will be focusing on
		enterpass.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				enterpassFocusLost(e);
		    }});

		//set up operations and asthtics of the "account creation" screen/frame
		AccountCreation.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		AccountCreation.setVisible(true);
		AccountCreation.setPreferredSize(new Dimension(1280, 720));
		AccountCreation.pack();
		AccountCreation.setVisible(true);

		//add everything to the "account creation" screen/frame so that they can be seen and used
		AccountCreation.setLayout(null);
		AccountCreation.add(back);
		AccountCreation.add(create);
		AccountCreation.add(show);
		AccountCreation.add(cshow);
		AccountCreation.add(top);
		AccountCreation.add(first);
		AccountCreation.add(last);
		AccountCreation.add(username);
		AccountCreation.add(password);
		AccountCreation.add(cpassword);
		AccountCreation.add(strength);
		AccountCreation.add(email);
		AccountCreation.add(enterfirst);
		AccountCreation.add(enterlast);
		AccountCreation.add(enteruser);
		AccountCreation.add(enterpass);
		AccountCreation.add(cEnterpass);
		AccountCreation.add(enteremail);
		AccountCreation.add(passStrength);
		
	}

	//method that controls what the progress bar will display
	private void enterpassFocusLost(FocusEvent e) {
		try {
            //checks length of password and changes colour based off the length and maximum length
            char[] pass = enterpass.getPassword();
            int value = (pass.length * 100) / strongLength;
            value = (value > 100) ? 100 : value;
            passStrength.setValue(value);

            //if the password is greater than 25 characters long then dispay an error to the user, and break out of the if statement and go to the "top" 
            if(pass.length > 10) {
                JOptionPane.showMessageDialog(null, "Password can only be 10 characters long\nPlease fix errors and try again!","ERROR!", JOptionPane.ERROR_MESSAGE);
            }
            //gives value to a colour
            int colour = (passStrengthColour.length * value) / 100;
            passStrength.setForeground(passStrengthColour[colour]);

        } catch (Exception exp){

        }
	   }
	
	//method to give buttons actions
	@Override
	public void actionPerformed(ActionEvent e) {

		//if "show password" button is pressed show the characters, otherwise display characters as a "*" 
		if (show.isSelected()) {
			 enterpass.setEchoChar((char) 0);  
			 show.setBackground(Color.gray);
	        }  else {
	        	enterpass.setEchoChar('*');
	        	 show.setBackground(Color.red);
	        }
		 
		//if "show confirm password" button is pressed show the characters, otherwise display characters as a "*"  
		if (cshow.isSelected()) {
			 cEnterpass.setEchoChar((char) 0);  
			 cshow.setBackground(Color.gray);
	        }  else {
	        	cEnterpass.setEchoChar('*');
	        	 cshow.setBackground(Color.red);
	        }
		
		//if "back" button is pressed close account creation screen/frame, and open login screen/frame
		 if (e.getSource() == back) {
			 AccountCreation.dispose();
			 new Login();
		 	}
		 
		//if "create account" button is pressed run though all validation
		 top: if(e.getSource() == create) { 		//if there is a break in an if statement come back to the "top" which is located on this line.
			 
			 //if the text boxes are empty then dispay an error to the user and break out of the if statement and go to the "top"
			 if(enterfirst.getText().isEmpty() || enterlast.getText().isEmpty() || enteruser.getText().isEmpty() || enteremail.getText().isEmpty() || enterpass.getPassword().length == 0 || cEnterpass.getPassword().length == 0) {
				 JOptionPane.showMessageDialog(null, "One or more fields are empty!\nPlease fill everything out and try again!","ERROR!", JOptionPane.ERROR_MESSAGE);
				 break top;
			 }
			 
			 //loop though all the characters of the input
			 for(int i = 0; i < enterfirst.getText().length(); i++) {
				 char ch =  enterfirst.getText().charAt(i);
				 
				 //if the first name input is not a lowercase, uppercase letter or a hyphen then dispay an error to the user and break out of the if statement and go to the "top"
				 if(!((ch >= 65 && ch <= 90) || (ch >= 97 && ch <= 122) || (ch == 126))) { 
					JOptionPane.showMessageDialog(null, "First name can only contain letters and hyphens!\nPlease fix errors and try again!","ERROR!", JOptionPane.ERROR_MESSAGE);
					break top;
				 }
			 }
			 //loop though all the characters of the input
			 for(int i = 0; i < enterlast.getText().length(); i++) {
				 char ch =  enterlast.getText().charAt(i);
				 
				 //if the last name input is not a lowercase, uppercase letter or a hyphen then dispay an error to the user and break out of the if statement and go to the "top"
				 if(!((ch >= 65 && ch <= 90) || (ch >= 97 && ch <= 122) || (ch == 45))) {
					JOptionPane.showMessageDialog(null, "Last name can only contain letters and hyphens!\nPlease fix errors and try again!","ERROR!", JOptionPane.ERROR_MESSAGE);
					break top;
				 }
			 }
			 
			 //gets a random whole number from 0 to 100
			 int a = (int) (Math.random()*100);
			 
			 //loop though all the characters of the input
			 for(int i = 0; i < enteruser.getText().length(); i++) {
				 char ch =  enteruser.getText().charAt(i);
				 
				 //if the username is not alphanumeric then dispay an error to the user, give the user a username sugesstion based on their first, last name and a random number that is generated 
				 //and break out of the if statement and go to the "top"
				 if(!((ch >= 48 && ch <= 57) || (ch >= 65 && ch <= 90) || (ch >= 97 && ch <= 122))) {
					JOptionPane.showMessageDialog(null, "Username can only be aplphanumeric" + "\n" + "Try: " + enterfirst.getText() + enterlast.getText() + a, "ERROR!", JOptionPane.ERROR_MESSAGE);
					break top;
				 }
			 }
			 
			 //loop though all the characters of the input
			 for(int i = 0; i < enterpass.getPassword().length; i++) {
				 char ch =  enterpass.getPassword()[i];
				 
				 //if the password is not alphanumeric then dispay an error to the user, and break out of the if statement and go to the "top"
				 if(!((ch >= 48 && ch <= 57) || (ch >= 65 && ch <= 90) || (ch >= 97 && ch <= 122))) {
					JOptionPane.showMessageDialog(null, "Password can only be alphanumeric\nPlease fix errors and try again!","ERROR!", JOptionPane.ERROR_MESSAGE);
					break top;
				 }
			 }
			 
			 //if the password and the confirm password inputs dont match, dispay an error to the user, and break out of the if statement and go to the "top"
			 if(!(new String(enterpass.getPassword()).equals (new String(cEnterpass.getPassword())))) {
				 JOptionPane.showMessageDialog(null, "Passwords don't match!\nPlease fix errors and try again!","ERROR!", JOptionPane.ERROR_MESSAGE);
					break top;
			 }
			 
			 Scanner input=new Scanner(System.in);
			 boolean found=false;
			 
			 try {
				input = new Scanner(new File("users.txt"));
				input.useDelimiter(",");
				
				while(input.hasNext() && !found) {			//loop that keeps going to the next line of the txt file if "found" is not switched to true
					
					//gives the temporary variables values 
					String tempFirst=input.next();			
					String tempLast=input.next();			
					String tempuser = input.next();
					String temppass = input.next();
					String tempmail=input.next();
					
					//if the temporary username and password from the save file is the same as the useres input, found is true
					if(tempuser.trim().equals(enteruser.getText().trim())) {
						
						found = true;	
					}
				}
				
			} catch (FileNotFoundException e1) {
				
				e1.printStackTrace();
			}	
			 
			 //if the usename is already in use then dispay an error to the user, give the user a username sugesstion based on their first, last name and a random number that is generated 
			 if(found) {
				 			 
				 JOptionPane.showMessageDialog(null, "This username is already used!" + "\n" + "Try: " + enterfirst.getText() + enterlast.getText() + a, "ERROR!", JOptionPane.ERROR_MESSAGE);
				 
			 }
			 else {
				//close account creation screen/frame
				 AccountCreation.dispose();
				 
				 	//creating and initalizing local variables 
				 	String firstNameE = enterfirst.getText().trim();			//stores the users first name input
					String lastNameE=enterlast.getText().trim();				//stores the users last name input
					String usernameE = enteruser.getText().trim();				//stores the users username input
					char[] passE = cEnterpass.getPassword();					//stores the users password input
					String passwordE = new String(passE);						//switch the char data type to a string data type
					String userEmail = enteremail.getText();					//get the users gmail from textbox
					String myEmail = "comsciisfun@gmail.com";					//the email that will send emails to users
				    String myPassword = "comsci11";								//the password for the email that will send emails to the users
					
					Properties prop = new Properties();
					
					//settng up the ability to reach the servers
					prop.put("mail.smtp.auth", "true");							
				    prop.put("mail.smtp.starttls.enable", "true");
				    prop.put("mail.smtp.host", "smtp.gmail.com");
				    prop.put("mail.smtp.port", "587");
					
				    //authentecation for the email that will do the sending. Logging on to the account to be able to send emails
				    Session session = Session.getDefaultInstance(prop, new Authenticator() {
			            @Override
			            protected PasswordAuthentication getPasswordAuthentication() {
			                return new PasswordAuthentication(myEmail, myPassword);
			            }
			        });
					
					try {
						MimeMessage message = new MimeMessage(session);
						
						//settig the details for the email such as recepient email, senders email, subject, and the text 
			            message.setFrom(new InternetAddress(myEmail));
			            message.setRecipient(Message.RecipientType.TO, new InternetAddress(userEmail));
			            message.setSubject("University Assistant Account Info");
			            message.setText("Hello " + firstNameE + lastNameE + ", " + "\n" + "Your username is: " + usernameE + "\n" + "Your password is: " + passwordE + "\n" + 
			            "Thank you for creating an account with University Assistant, good luck on your post-secondary journey!!");
			            Transport.send(message);
						
					} catch (Exception e1) {

						System.out.println("" + e1);
					}
				 
				 	//creating and initalizing local variables 
					String firstName = enterfirst.getText().trim();			//stores the users first name input
					String lastName=enterlast.getText().trim();				//stores the users last name input
					String username = enteruser.getText().trim();			//stores the users username input
					char[] pass = cEnterpass.getPassword();					//stores the users password input
					String password = new String(pass);						//switch the char data type to a string data type User currentUser = new User(tempFirst, tempLast, tempuser, password);
					
					
					User currentUser=new User(firstName, lastName, username, password, userEmail);
					new Initialize(currentUser);
					new HomeScreenGUI(currentUser);
					
					
					
					try {
						
						FileWriter fw = new FileWriter("users.txt", true); 													//use a file writer so that it doesnt erase the txt file each time it is used, and write in the users.txt file
						fw.write("\n");																						//create new line
						fw.write(firstName + "," + lastName +","+ username + "," + password + "," + userEmail + ",");		//write the first name, last name, username, password, email and a comma 
						
																							
						fw.close();																							//close file writer

						} catch (IOException ex) {
							ex.printStackTrace();
						}
			 }
			 
		 	 }
	}
	
}