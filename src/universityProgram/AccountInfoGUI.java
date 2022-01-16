/**
 * Contributor: Ryker
 * 
 * The class AccountInfoGUI creates a screen that shows the user's information
 */
package universityProgram;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import java.awt.Color;


public class AccountInfoGUI extends JFrame implements ActionListener {

	User currentUser; // The current user
	
	// Panel
	JPanel screen = new JPanel();

	// Buttons
	JButton toGradesGUI = new JButton(); //The button directs the user to Grade screen
	JButton toPreferenceGUI = new JButton(); //The button directs the user to the preference screen
	JButton toHomeGUI = new JButton(); //The button directs the user to home screen
	JButton toLocation=new JButton(); //The button directs the user to the preference screen

	// Labels
	// Titles
	JLabel title = new JLabel(); //Account Information
	JLabel logInInfoTitle = new JLabel(); //Login Info
	JLabel preferenceTitle = new JLabel(); //University Preference
	JLabel gradeTitle = new JLabel(); //Grades
	JLabel locationTitle=new JLabel(); //Location

	// Log In Info
	JLabel firstName = new JLabel(); //The label of the user's first name
	JLabel lastName = new JLabel(); //The label of the user's last name
	JLabel username = new JLabel(); //The label of the user's user name
	JLabel password = new JLabel(); //The label of the user's password
	JLabel email=new JLabel(); //The label of the user's email
	
	//Location
	JLabel latitude=new JLabel(); //The label of the user's latitude
	JLabel longitude=new JLabel(); //The label of the user's longitude
	JLabel locationStatus=new JLabel(); //The label notifies the user whether they have entered
	

	// Grades
	JLabel[] grade = new JLabel[6]; //The label of the user's grades
	JLabel gradeAverage=new JLabel(); //The label of the user's average grade

	// University Preferences
	JLabel[] preference = new JLabel[5]; //The label of the user's university preference

	/**
	 * The constructor that creates a new AccountInfoGUI screen
	 * @param currentUser
	 */
	
	public AccountInfoGUI(User currentUser) {

		//Initialization
		for (int x = 0; x < 6; x++) {
			grade[x] = new JLabel();
		}

		for (int x = 0; x < 5; x++) {
			preference[x] = new JLabel();
		}

		this.currentUser = currentUser;

		//The methods that creates the screen
		frameSetup();
		panelDesign();

	}

	/**
	 * The method that set up the frame
	 */
	
	private void frameSetup() {

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(1280, 720);
		setTitle("University Assistant");
		setLayout(null);
		setVisible(true);
		setResizable(false);
		add(screen);

	}

	/**
	 * The method that setup the panel/labels/buttons
	 */
	private void panelDesign() {

		//JPanel
		screen.setBorder(null);
		screen.setBounds(0, 0, 1280, 720);
		screen.setLayout(null);

		//JButtons
		toHomeGUI.addActionListener(this);
		toHomeGUI.setText("BACK");
		toHomeGUI.setFont(new Font("Tahoma", Font.BOLD, 24));
		toHomeGUI.setBounds(1100, 20, 120, 50);
		toHomeGUI.setForeground(Color.RED);
		toHomeGUI.setFocusable(false);
		screen.add(toHomeGUI);
		
		toPreferenceGUI.addActionListener(this);
		toPreferenceGUI.setText("CHANGE");
		toPreferenceGUI.setFont(new Font("Tahoma", Font.BOLD, 24));
		toPreferenceGUI.setBounds(900, 85, 150, 50);
		toPreferenceGUI.setFocusable(false);
		screen.add(toPreferenceGUI);

		toGradesGUI.addActionListener(this);
		toGradesGUI.setText("CHANGE");
		toGradesGUI.setFont(new Font("Tahoma", Font.BOLD, 24));
		toGradesGUI.setBounds(260, 405, 150, 50);
		toGradesGUI.setFocusable(false);
		screen.add(toGradesGUI);
		
		toLocation.addActionListener(this);
		toLocation.setText("CHANGE");
		toLocation.setFont(new Font("Tahoma", Font.BOLD, 24));
		toLocation.setBounds(900, 405, 150, 50);
		toLocation.setFocusable(false);
		screen.add(toLocation);


		//JLabel
		title.setText("  Account Information");
		title.setFont(new Font("Tahoma", Font.BOLD, 30));
		title.setBounds(0, 0, 1280, 80);
		title.setOpaque(true);
		title.setVisible(true);
		title.setBackground(Color.WHITE);
		screen.add(title);

		logInInfoTitle.setText("Login Info");
		logInInfoTitle.setFont(new Font("Tahoma", Font.BOLD, 24));
		logInInfoTitle.setBounds(80, 80, 150, 60);
		logInInfoTitle.setVisible(true);
		screen.add(logInInfoTitle);

		preferenceTitle.setText("University Preference");
		preferenceTitle.setFont(new Font("Tahoma", Font.BOLD, 24));
		preferenceTitle.setBounds(600, 80, 300, 60);
		preferenceTitle.setVisible(true);
		screen.add(preferenceTitle);

		gradeTitle.setText("Grades");
		gradeTitle.setFont(new Font("Tahoma", Font.BOLD, 24));
		gradeTitle.setBounds(80, 400, 220, 60);
		gradeTitle.setVisible(true);
		screen.add(gradeTitle);
		
		locationTitle.setText("Location");
		locationTitle.setFont(new Font("Tahoma", Font.BOLD, 24));
		locationTitle.setBounds(600, 400, 220, 60);
		locationTitle.setVisible(true);
		screen.add(locationTitle);
		
		latitude.setText("Latitude: " + currentUser.getLatitude());
		latitude.setFont(new Font("Tahoma", Font.PLAIN, 22));
		latitude.setBounds(610, 470, 300, 60);
		latitude.setVisible(true);
		screen.add(latitude);

		longitude.setText("Longitude: " + currentUser.getLongitude());
		longitude.setFont(new Font("Tahoma", Font.PLAIN, 22));
		longitude.setBounds(610, 540, 300, 60);
		longitude.setVisible(true);
		screen.add(longitude);
		
		//Whether the user has entered their location
		if(currentUser.getLatitude()==User.BUR_OAK_LATITUDE && currentUser.getLongitude()==User.BUR_OAK_LONGITUDE) {
			locationStatus.setText("Bur Oak Secondary School is default location.");
		}else {
			locationStatus.setText("You have entered your location.");
		}
		
		locationStatus.setFont(new Font("Tahoma", Font.PLAIN, 22));
		locationStatus.setBounds(610, 610, 550, 60);
		locationStatus.setVisible(true);
		screen.add(locationStatus);

		username.setText("Username: " + currentUser.getUserName());
		username.setFont(new Font("Tahoma", Font.PLAIN, 22));
		username.setBounds(90, 140, 300, 60);
		username.setVisible(true);
		screen.add(username);
		
		password.setText("Password: " + currentUser.getPassword());
		password.setFont(new Font("Tahoma", Font.PLAIN, 22));
		password.setBounds(90, 190, 300, 60);
		password.setVisible(true);
		screen.add(password);

		firstName.setText("First Name: " + currentUser.getFirstName());
		firstName.setFont(new Font("Tahoma", Font.PLAIN, 22));
		firstName.setBounds(90, 240, 300, 60);
		firstName.setVisible(true);
		screen.add(firstName);

		lastName.setText("Last Name: " + currentUser.getLastName());
		lastName.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lastName.setBounds(90, 290, 300, 60);
		lastName.setVisible(true);
		screen.add(lastName);
		
		email.setText("Email: " + currentUser.getEmail());
		email.setFont(new Font("Tahoma", Font.PLAIN, 22));
		email.setBounds(90, 340, 500, 60);
		email.setVisible(true);
		screen.add(email);

		//User's information
		
		//If the user has entered any grade
		if (currentUser.getGradeAverage()!=-1) {
			
			//The first to the third grades
			for (int x = 0; x < 3; x++) {
				if(currentUser.getGrade()[x]!=-1) {
					grade[x].setText(currentUser.getSubject()[x] + ": " + currentUser.getGrade()[x]+"%");
				}else {
					grade[x].setText(currentUser.getSubject()[x] + ": N/A ");
				}
				grade[x].setFont(new Font("Tahoma", Font.PLAIN, 22));
				grade[x].setBounds(90, 460 + 50 * x, 220, 60);
				grade[x].setVisible(true);
				screen.add(grade[x]);
			}
	
			//The fourth to the sixth grades
			for (int x = 3; x < 6; x++) {
				if(currentUser.getGrade()[x]!=-1) {
					grade[x].setText(currentUser.getSubject()[x] + ": " + currentUser.getGrade()[x]+"%");
				}else {
					grade[x].setText(currentUser.getSubject()[x] + ": N/A");
				}
				grade[x].setFont(new Font("Tahoma", Font.PLAIN, 22));
				grade[x].setBounds(260, 460 + 50 * (x - 3), 220, 60);
				grade[x].setVisible(true);
				screen.add(grade[x]);
			}
		
			//Whether the user has entered all their grade
			boolean checkAllEntered=true;
			
			//Run through each grade
			for(int x=0; x<6; x++) {
				//If the grade is not entered
				if(currentUser.getGrade()[x]==-1) {
					//The enter has not entered all the grades
					checkAllEntered=false;
			}
		}
		
		//The user has entered all the grades
		if(checkAllEntered) {
			//Show the average
			gradeAverage.setText("Your average is "+currentUser.getGradeAverage()+"%");
		
		//The user has not entered all the grades
		}else {
			//Don't show the average
			gradeAverage.setText("Your average is N/A");
		}
		
		gradeAverage.setFont(new Font("Tahoma", Font.PLAIN, 22));
		gradeAverage.setBounds(90, 610, 220, 60);
		gradeAverage.setVisible(true);
		screen.add(gradeAverage);
		
		//If the user has not entered any grade
		}else {
			
			//Notify the user that no grade has been entered
			grade[0].setFont(new Font("Tahoma", Font.PLAIN, 22));
			grade[0].setText("No grade has been entered...");
			grade[0].setBounds(90, 530, 500, 60);
			grade[0].setVisible(true);
			screen.add(grade[0]);
		}

		//If the user has selected the preferences
		if (currentUser.getUniversityPreference()[0] != null) {
			
			//The first to the fifth preferences
			for (int x = 0; x < 5; x++) {
				preference[x].setText("No. " + (x + 1) + " Choice: " + currentUser.getUniversityPreference()[x].getName());
				preference[x].setFont(new Font("Tahoma", Font.PLAIN, 22));
				preference[x].setBounds(610, 140 + 50 * x, 400, 60);
				preference[x].setVisible(true);
				screen.add(preference[x]);

			}
		
		//If the user has not selected the preferences
		}else {
			
			//Notify the user that no preference has been entered
			preference[0].setFont(new Font("Tahoma", Font.PLAIN, 22));
			preference[0].setText("No university preference has been entered...");
			preference[0].setBounds(610, 230, 500, 60);
			screen.add(preference[0]);
		}
	}

	/**
	 * The method used whenever any JButton is clicked/modified
	 */
	public void actionPerformed(ActionEvent event) {

		//If the preference change button / location change button is clicked
		if (event.getSource() == toPreferenceGUI ||event.getSource() == toLocation) {
			
			//Direct the user's to UniversityPreferenceGUI page
			new UniversityPreferenceGUI(currentUser);
			
			//Close the screen
			this.dispose();
		}

		//If the BACK button is clicked
		if (event.getSource() == toHomeGUI) {
			
			//Direct the user's to the home screen
			new HomeScreenGUI(currentUser);
			
			//Close the screen
			this.dispose();
		}

		//If the grade change button is clicked
		if (event.getSource() == toGradesGUI) {
			
			//Direct the user's to the GradeGUI page
			new GradesGUI(currentUser);
			
			//Close the screen
			this.dispose();
		}
	}

}