/*
 * Contributors: Simran
 * Description: This class creates the grades screen and allows the user to modify and save their 6 grade 12 grades using the Jsliders.
 */

package universityProgram;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JSlider;

public class GradesGUI implements ActionListener, ChangeListener {

	private JFrame frame; //frame to hold components
	private JPanel panel = new JPanel(); //panel for the top header
	private JLabel yourGrades = new JLabel("Your Grades"); //main header label
	private JLabel englishLabel = new JLabel("English*:"); //label for mandatory english grade
	private JLabel calcLabel = new JLabel("Calculus*:");  //label for mandatory calc grade
	private JLabel physicsLabel = new JLabel("Physics*:"); //label for mandatory physics grade
	private JLabel advancedFuncLabel = new JLabel("Advanced Functions*:");  //label for mandatory advanced func grade
	private JLabel chemLabel = new JLabel("Chemistry*:");  //label for mandatory chem grade
	private JComboBox electiveSubject = new JComboBox(); //label to let the user choose last subject (elective or biology)
	private JSlider[] sliders = new JSlider[6]; //array of sliders to let the user change their grades
	private JLabel[] showGrade = new JLabel[6]; //array of labels that display the grade for each subject
	private JLabel chooseSubject = new JLabel("Choose Subject: "); //label to show the user they can choose subject from combobox
	private JButton btnSave = new JButton("SAVE"); //save button
	private JButton returnHome = new JButton("RETURN TO HOME SCREEN"); //button to return to home screen
	private JLabel requireGradesLabel = new JLabel("*Grades for these courses are required for your program."); //label to tell the user why some of the grades are mandatory
	//instruction label to tell the user about the screen
	private JLabel instructionLabel = new JLabel("<html>Use the sliders to enter in your top 6 grade 12 grades (0 - 100%). <br>Anything that displays -1 means there is no grade entered for that subject.<br> You must enter in all your grades to get your average.<html>");
	private JLabel averageLabel = new JLabel(); //label to show the user's average
	private int[] userGrades; //array to hold user grades
	private String[] userSubjects; //array to hold user subjects
	private User newUser; //user object
	
	
	/*
	 * constructor to set user and user grades and subjects and to call createScreen method
	 * @param currentUser
	 */
	public GradesGUI(User currentUser) {
		newUser = currentUser;
		userGrades = newUser.getGrade();
		userSubjects = newUser.getSubject();
		createScreen();
	}

	
	/*
	 * creates the frame and creates and adds everything in the frame
	 */
	private void createScreen() {
		//create frame
		frame = new JFrame();
		frame.setSize(1280, 720);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setTitle("University Assistant");
		
		//create header panel
		panel.setBounds(0, 0, 1266, 69);
		panel.setBackground(Color.WHITE);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		//label for header panel of screen
		yourGrades.setBounds(25, 11, 239, 54);
		yourGrades.setFont(new Font("Tahoma", Font.BOLD, 35));
		panel.add(yourGrades);
		
		//button to return home
		returnHome.addActionListener(this);
		returnHome.setFont(new Font("Tahoma", Font.BOLD, 18));
		returnHome.setBounds(924, 26, 300, 32);
		returnHome.setFocusable(false);
		panel.add(returnHome);
		
		//english label and text box
		englishLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		englishLabel.setBounds(133, 171, 114, 45);
		frame.getContentPane().add(englishLabel);
		
		//calculus label and text box
		calcLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		calcLabel.setBounds(125, 331, 122, 45);
		frame.getContentPane().add(calcLabel);
		
		//physics label
		physicsLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		physicsLabel.setBounds(133, 488, 122, 45);
		frame.getContentPane().add(physicsLabel);
		
		//advanced function label
		advancedFuncLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		advancedFuncLabel.setBounds(665, 175, 300, 38);
		frame.getContentPane().add(advancedFuncLabel);
		
		//chemistry label
		chemLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		chemLabel.setBounds(665, 335, 208, 38);
		frame.getContentPane().add(chemLabel);
		
		//check if all grades entered, if they have been entered, then display the average
		if (allGradesEntered()) {
			averageLabel.setText("Your Average: " + newUser.getGradeAverage() + "%");
		}
		else {
			averageLabel.setText("Your Average: N/A");
		}
		averageLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		averageLabel.setBounds(900, 50, 300, 100);
		frame.getContentPane().add(averageLabel);
		
		
		//combo box for the last subject
		electiveSubject.setFont(new Font("Tahoma", Font.PLAIN, 18));
		electiveSubject.setModel(new DefaultComboBoxModel(new String[] {"Elective", "Biology"}));
		electiveSubject.setBounds(830, 492, 100, 38);
		frame.getContentPane().add(electiveSubject);
		
		//choose subject label for combo box
		chooseSubject.setBounds(665, 492, 208, 38);
		chooseSubject.setFont(new Font("Tahoma", Font.PLAIN, 22));
		frame.getContentPane().add(chooseSubject);
		
		//button to save the user's marks
		btnSave.addActionListener(this);
		btnSave.setBackground(new Color(144, 238, 144));
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnSave.setBounds(1099, 617, 114, 33);
		btnSave.setFocusable(false);
		frame.getContentPane().add(btnSave);
		
		//labels inform the user the required grades
		requireGradesLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		requireGradesLabel.setBounds(50, 622, 464, 25);
		frame.getContentPane().add(requireGradesLabel);
		
		//instruction label to tell user what to do
		instructionLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		instructionLabel.setBounds(38, 75, 1079, 78);
		frame.getContentPane().add(instructionLabel);
		
		addSlidersAndGradeLabel();
		setGradesAndSubjectText();
	}

	
	/*
	 * method to set and add sliders and their corresponding grade label
	 */
	//for setting the sliders
	private void addSlidersAndGradeLabel() {
		//for the sliders
		for (int i = 0; i < 6; i++) {
			sliders[i] = new JSlider(JSlider.HORIZONTAL, -1, 100, -1);
			sliders[i].setMajorTickSpacing(10);
			sliders[i].setMinorTickSpacing(1);
			sliders[i].setPaintTicks(true);
			sliders[i].setPaintLabels(true);
			sliders[i].addChangeListener(this);
		}
		//set the sliders positions
		sliders[0].setBounds(125, 218, 370, 57);
		sliders[1].setBounds(125, 375, 370, 57);
		sliders[2].setBounds(125, 532, 370, 57);
		sliders[3].setBounds(665, 218, 370, 57);		
		sliders[4].setBounds(665, 375, 370, 57);		
		sliders[5].setBounds(665, 532, 370, 57);
		//add the sliders to the frame
		for (int i = 0; i < 6; i++) {
			frame.getContentPane().add(sliders[i]);
		}
		
		//for the labels
		for (int i = 0; i < 6; i++) {
			showGrade[i] = new JLabel("-1");
			showGrade[i].setFont(new Font("Tahoma", Font.PLAIN, 25));
		}
		//set the labels positions
		showGrade[0].setBounds(260, 171, 114, 45);
        showGrade[1].setBounds(260, 331, 122, 45);
        showGrade[2].setBounds(260, 488, 114, 45);
        showGrade[3].setBounds(933, 172, 114, 45);
        showGrade[4].setBounds(823, 332, 114, 45);
        showGrade[5].setBounds(950, 486, 114, 45);
        //add the labels to the frame
        for (int i = 0; i < 6; i++) {
            frame.getContentPane().add(showGrade[i]);
		}
	}
	
	
	/*
	 * setting the grade for each slider and subject for the combobox
	 * so if the user has already entered in their grades or subject, it will show up on the screen
	 */
	private void setGradesAndSubjectText() {
		for (int i = 0; i < 6; i++) { 
			sliders[i].setValue(userGrades[i]); //set the value of each slider to the person's grade
			if (userGrades[i] == -1) { //if the grade is -1, don't add a % 
				showGrade[i].setText("" + userGrades[i]);
			}
			else { //if the grade not -1, add a %
				showGrade[i].setText("" + userGrades[i] + "%");
			}
		}

		if (userSubjects[5].equals("Biology")) { //if the user has selected biology, then set the default in the combobox to biology
			electiveSubject.setSelectedIndex(1);
		}
	}
	
	
	/*
	 * method that saves the data in the sliders and combobox to the user object's grades and subjects
	 */
	private void saveData() {
		for (int i = 0; i < 6; i++) {
			userGrades[i] = sliders[i].getValue();
		}
		userSubjects[0] = "English";
		userSubjects[1] = "Calculus";
		userSubjects[2] = "Physics";
		userSubjects[3] = "Adv. Function";
		userSubjects[4] = "Chemistry";		
		userSubjects[5] = (String)electiveSubject.getSelectedItem();
	}
	
	
	/*
	 * method to check if all grades have been entered, if all grades have been entered, the average can be calculated
	 * returns true if all grades have been entered, returns false if not
	 */
	private boolean allGradesEntered() {
		for (int i = 0; i < 6; i++) {
			if (userGrades[i] == -1) {
				return false;
			}
		}
		return true;
	}

	
	/*
	 * method to handle the actions of each button
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSave) { //for saving data
			saveData();
			newUser.setSubject(userSubjects); //sets the new subjects
			newUser.setGrade(userGrades);  //sets the new grades
			if (allGradesEntered()) { //if all the grades have been entered, show their average
				averageLabel.setText("Your Average: " + newUser.getGradeAverage() + "%");
			}
			else { //if all grades have not been entered yet, do not show average
				averageLabel.setText("Your Average: N/A");
			}
			//confirmation message for user
			JOptionPane.showMessageDialog(null, "Your grades have been saved." , "Confirmation: " + "Grades Saved", JOptionPane.INFORMATION_MESSAGE);
		}
		else if (e.getSource() == returnHome) { //for returning to home screen
			//show a JoptionPane to confirm with the user if they wanna leave the Your grades screen
			int exit = JOptionPane.showConfirmDialog(null, "<html>Are you sure you want to return to home screen? <br>Anything not saved will be lost.<html>", "Exit Your Grades screen", JOptionPane.YES_NO_OPTION);
			if (exit == JOptionPane.YES_OPTION) { //if the user says yes, exit screen and return to home
				new HomeScreenGUI(newUser);
				frame.dispose();
			} 
		}
	}

	
	/*
	 * method to handle the slider's actions
	 */
	@Override
	public void stateChanged(ChangeEvent e) {
		for (int i = 0; i < 6; i++) {
			if (e.getSource() == sliders[i]) { //if slider has been moved, change the value of their corresponding grade label
				JSlider source = (JSlider) e.getSource();
				if (source.getValue() == -1) { //if slider is at -1, it is not a valid grade so don't add %
					showGrade[i].setText("" + source.getValue());
				}
				else { //if slider is not at -1, it is a valid grade so add %
					showGrade[i].setText("" + source.getValue() + "%");
				}	
			}
		}
	}
	
}
