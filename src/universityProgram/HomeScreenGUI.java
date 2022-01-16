/* 
 * Contributors: Simran
 * Description: This class creates the home screen for our program and allows the user access to the other screens of our program using
 * the buttons in this screen. Also when the user presses logout, their information is automatically saved.
 */

package universityProgram;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class HomeScreenGUI implements ActionListener{

	private JFrame frame; //frame to hold all components
	private JPanel panel = new JPanel(); //panel for the top header
	private JLabel HomeScreenlabel = new JLabel("Home Screen"); //label for home screen
	private JButton btnLogout = new JButton("LOGOUT"); //logout button
	private JButton btnYourAccount = new JButton("Your Account"); //button that leads to your account information
	private JButton btnYourGrades = new JButton("Your Grades"); //button that leads to your grades screen
	private JButton btnPersonalUnis = new JButton("<html>Your personal <br>University choices<html>"); //button that leads to uni preferences
	private JButton btnUniInfo = new JButton("<html>Information on <br>Universities/Programs<html>"); //button that leads to uni information
	private JButton btnRecommendedUnis = new JButton("<html>Recommended <br>Universities for you<html>"); //button that leads to the results screen for the user's recommended unis
	private JLabel gradesLabel = new JLabel("Enter in or edit your top 6 grade 12 grades"); //Label that describes the grades screen
	private JLabel personalUnisLabel = new JLabel("List your preferred universities"); //label that describes the personal unis screen
	private JLabel uniInfoLabel1 = new JLabel("Find information on 14 of Ontario's universities,"); //label that describes the info on unis screen part 1
	private JLabel uniInfoLabel2 = new JLabel("and their engineering program."); //label that describes the info on unis screen part 2
	private JLabel viewAccountLabel = new JLabel("View your account information and edit it"); //label that describes account info screen
	private JLabel recommendUniLabel = new JLabel("Find out what universities we recommend for you."); //label that describes the uni recommendation screen
	private JLabel NoteLabel = new JLabel("*Note: You must enter in your grades and university preferences first to recieve results."); //label that informs user what they need in order to recieve recommended uni results
	private User user; //user object
	
	
	/*
	 * Constructor to create the screen
	 */
	public HomeScreenGUI(User currentUser) {
		user = currentUser;
		createScreen();
		addButtons();
		addLabels();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/*
	 * Creates the basic frame with the top panel and the home screen label
	 */
	private void createScreen() {
		frame = new JFrame();
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setSize(1280, 720);
		frame.setTitle("University Assistant");

		// panel for the top white part of the screen
		panel.setBorder(null);
		panel.setBounds(0, 0, 1266, 69);
		panel.setBackground(Color.WHITE);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		//add the main top label
		HomeScreenlabel.setBounds(10, 11, 260, 54);
		HomeScreenlabel.setFont(new Font("Tahoma", Font.BOLD, 35));
		panel.add(HomeScreenlabel);
	}
	
		
	/*
	 * method to add the labels onto the screen
	 */
	private void addLabels() {
		viewAccountLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		viewAccountLabel.setBounds(138, 289, 260, 17);
		frame.getContentPane().add(viewAccountLabel);

		// the label for the welcome part calls the user object FirstName variable to greet user by first name
		JLabel welcomeLabel = new JLabel("Welcome " + user.getFirstName() + "!");
		welcomeLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		welcomeLabel.setBounds(34, 107, 800, 27);
		frame.getContentPane().add(welcomeLabel);

		gradesLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		gradesLabel.setBounds(492, 289, 279, 17);
		frame.getContentPane().add(gradesLabel);

		personalUnisLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		personalUnisLabel.setBounds(873, 289, 232, 17);
		frame.getContentPane().add(personalUnisLabel);

		uniInfoLabel1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		uniInfoLabel1.setBounds(235, 499, 303, 17);
		frame.getContentPane().add(uniInfoLabel1);

		uniInfoLabel2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		uniInfoLabel2.setBounds(288, 519, 202, 17);
		frame.getContentPane().add(uniInfoLabel2);

		recommendUniLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		recommendUniLabel.setBounds(696, 499, 322, 17);
		frame.getContentPane().add(recommendUniLabel);

		NoteLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		NoteLabel.setBounds(610, 519, 550, 17);
		frame.getContentPane().add(NoteLabel);
	}


	/*
	 * method to add the buttons onto the screen
	 */
	private void addButtons() {
		//logout button
		btnLogout.setBounds(1085, 29, 128, 29);
		panel.add(btnLogout);
		btnLogout.addActionListener(this); 
		btnLogout.setForeground(Color.BLACK);
		btnLogout.setBackground(new Color(250, 128, 114));
		btnLogout.setFocusable(false);
		btnLogout.setFont(new Font("Tahoma", Font.BOLD, 18));
			
		//account button
		btnYourAccount.setBackground(SystemColor.activeCaptionBorder);
		btnYourAccount.addActionListener(this); 
		btnYourAccount.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnYourAccount.setBounds(175, 208, 180, 70);
		btnYourAccount.setFocusable(false);
		frame.getContentPane().add(btnYourAccount);
		
		//grades button
		btnYourGrades.setBackground(SystemColor.activeCaptionBorder);
		btnYourGrades.addActionListener(this); 
		btnYourGrades.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnYourGrades.setBounds(532, 208, 180, 70);
		btnYourGrades.setFocusable(false);
		frame.getContentPane().add(btnYourGrades);
				
		//personal unis button
		btnPersonalUnis.addActionListener(this); 
		btnPersonalUnis.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnPersonalUnis.setBackground(SystemColor.activeCaptionBorder);
		btnPersonalUnis.setBounds(891, 208, 180, 70);
		btnPersonalUnis.setFocusable(false);
		frame.getContentPane().add(btnPersonalUnis);
				
		//info on unis button
		btnUniInfo.addActionListener(this); 
		btnUniInfo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnUniInfo.setBackground(SystemColor.activeCaptionBorder);
		btnUniInfo.setBounds(288, 418, 202, 70);
		btnUniInfo.setFocusable(false);
		frame.getContentPane().add(btnUniInfo);
				
		//recommended unis button
		btnRecommendedUnis.addActionListener(this); 
		btnRecommendedUnis.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnRecommendedUnis.setBackground(SystemColor.activeCaptionBorder);
		btnRecommendedUnis.setBounds(744, 418, 202, 70);
		btnRecommendedUnis.setFocusable(false);
		frame.getContentPane().add(btnRecommendedUnis);		
	}


	/*
	 * method to handle the actions of each button
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnYourAccount) { //this button will take them to their account info screen
			new AccountInfoGUI(user);
			frame.dispose();
		}
		else if (e.getSource() == btnYourGrades) { //this button will take them to their grades screen 
			new GradesGUI(user);
			frame.dispose();
		}
		else if (e.getSource() == btnPersonalUnis) { //this button will take them to their personal uni preferences screen
			new UniversityPreferenceGUI(user);
			frame.dispose();
		}
		else if (e.getSource() == btnUniInfo) { //this button will take them to the info on ontario unis screen
			new UniversityInformationGUI(user);
			frame.dispose();
		}
		else if (e.getSource() == btnRecommendedUnis){ //this button will take them to their recommended unis screen
			if (allGradesEntered()) { //make sure they have all their grades entered first
				if (allUniPreferencesEntered()) { //make sure they also have all their uni preferences entered first
					new QuizGUI(user);
					frame.dispose();
				}
				else { //show message if they dont have all uni preferences entered
					JOptionPane.showMessageDialog(null, "You must enter in your preferred universities to get university recommendations." , "Enter in univeristies preferences first.", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			else { //show message if they dont have all grades entered
				JOptionPane.showMessageDialog(null, "You must enter in all your grade 12 grades before getting university recommendations." , "Enter in grades first.", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		else { //for logout button
			int exit = JOptionPane.showConfirmDialog(null, "<html>Are you sure you want to logout and close application? <br>Your data will be saved.<html>", "Logout and close application", JOptionPane.YES_NO_OPTION);
			if (exit == JOptionPane.YES_OPTION) { //if the user says yes, save and close application
				new SaveUserData(user);
				System.exit(0); //closes application if someone logs out
			} 
		}
	}
	
	
	/*
	 * method to check if all grades have been entered
	 * returns true if all grades been entered, returns false if not
	 */
	private boolean allGradesEntered() {
		int[] grades = user.getGrade();
		
		for (int i = 0; i < grades.length; i++) {
			if (grades[i] == -1) {
				return false;
			}
		}
		return true;
	}
	
	
	/*
	 * method to check if all uni preferences have been entered in
	 * returns true if all uni preferences have been entered, returns false if not
	 */
	private boolean allUniPreferencesEntered() {
		University[] preferences = user.getUniversityPreference();
			
		for (int i = 0; i < preferences.length; i++) {
			if (preferences[i] == null) {
				return false;
			}
		}
		return true;
	}

}
