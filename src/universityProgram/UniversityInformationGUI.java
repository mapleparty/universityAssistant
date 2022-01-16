
package universityProgram;

/**
 * Contributor: Carlos Wong
 * The class UniversityInformationGUI creates a screen that displays information about the university and their engineering program
 */


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UniversityInformationGUI extends JFrame implements ActionListener {

	//Labels
	private JLabel universities;
	private JLabel title = new JLabel();

	//Panel
	private JPanel panel = new JPanel();

	// Buttons
	private JButton buttonLeft = new JButton();
	private JButton buttonReturn = new JButton();
	private JButton buttonRight = new JButton();
	private JButton buttonCompare = new JButton();
	private JButton buttonExit=new JButton();

	//Combo Box
	private JComboBox UniversityList = new JComboBox();;

	//University Images
	private ImageIcon image;

	//Variables tract index of university images and current user and holds the names of universities for combo box
	private int index = 0;
	private User currentUser;
	private String[] universitySelection = { "Select University", "Carleton University", "University of Guelph",
			"Lakehead University", "Laurentian University", "McMaster University", "University of Ottawa",
			"Ontario Tech University", "Queen's University", "Ryerson University", "Toronto University",
			"University of Waterloo", "Western Main Campus", "University of Windsor", "York University" };

	/**
	 * Constructor to create the GUI window
	 * @param currentUser
	 */
	public UniversityInformationGUI(User currentUser) {
		this.currentUser = currentUser;
		frameDesign();
		setImage();
		addButtons();
		addComboBox();
		panelDesign();
	}

	/**
	 * Constructor to redirect the user from ResultsGUI
	 * @param currentUser
	 * @param university
	 */
	public UniversityInformationGUI(User currebtUser, String university) {
		this.currentUser = currebtUser;
		frameDesign();
		addSelectedButtons();
		panelDesign();

		// add image of user's choice;
		image = new ImageIcon("UniversityImages/" + university + ".jpg");
		universities = new JLabel(image);
		universities.setBounds(155, 0, 1000, 800);
		panel.add(universities);
	}

	/**
	 * Constructor to return to the GUI window from CompareProgram
	 * @param index
	 */
	public UniversityInformationGUI(int index) {
		this.index = index;
	}

	/**
	 * Method sets up the frame design
	 */
	private void frameDesign() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1300, 750);
		setLayout(null);
		setVisible(true);
		setResizable(false);
		setContentPane(panel);
		setTitle("University Assistant");

	}

	/**
	 * Method sets the initial university image
	 */
	private void setImage() {
		image = new ImageIcon("UniversityImages/Carleton University.jpg");
		universities = new JLabel(image);
		universities.setBounds(155, 0, 1000, 800);
		panel.add(universities);
	}

	/**
	 * Method creates buttons and adds it to the frame
	 */
	private void addButtons() {

		//Set compare program button
		buttonCompare.setText("Compare Programs");
		buttonCompare.setFont(new Font("Tahoma", Font.BOLD, 12));
		buttonCompare.addActionListener(this);
		buttonCompare.setBounds(1125, 100, 155, 40);
		buttonCompare.setFocusable(false);
		panel.add(buttonCompare);

		//Set return to main screen button
		buttonReturn.setText("BACK");
		buttonReturn.setFont(new Font("Tahoma", Font.BOLD, 24));
		buttonReturn.setForeground(Color.RED);
		buttonReturn.addActionListener(this);
		buttonReturn.setBounds(1155, 10, 120, 50);
		buttonReturn.setFocusable(false);
		panel.add(buttonReturn);

		//Set rotate image left button
		buttonLeft = new JButton(new ImageIcon("UniversityImages/LeftButton.jpg"));
		buttonLeft.addActionListener(this);
		buttonLeft.setBounds(50, 300, 96, 96);
		buttonLeft.setFocusable(false);
		panel.add(buttonLeft);

		//Set rotate image right button
		buttonRight = new JButton(new ImageIcon("UniversityImages/RightButton.jpg"));
		buttonRight.addActionListener(this);
		buttonRight.setBounds(1150, 300, 96, 96);
		buttonRight.setFocusable(false);
		panel.add(buttonRight);
	}
	
	private void addSelectedButtons() {
		buttonExit.setText("EXIT");
		buttonExit.setFont(new Font("Tahoma", Font.BOLD, 24));
		buttonExit.setForeground(Color.RED);
		buttonExit.addActionListener(this);
		buttonExit.setBounds(1155, 10, 120, 50);
		buttonExit.setFocusable(false);
		panel.add(buttonExit);
	}

	/**
	 * Method creates a combo box
	 * 
	 */
	private void addComboBox() {
		
		//Set combo box for universities names
		UniversityList = new JComboBox(universitySelection);
		UniversityList.setFont(new Font("Tahoma", Font.BOLD, 13));
		UniversityList.addActionListener(this);
		UniversityList.setBounds(10, 100, 175, 40);
		panel.add(UniversityList);
	}

	/**
	 * Method creates a panel and creates the title of the window and is added to
	 * the panel
	 */
	private void panelDesign() {

		//Create panel for frame
		panel.setBorder(null);
		panel.setBounds(0, 0, 1300, 750);
		panel.setLayout(null);

		//Set title for the GUI and add it to the panel
		title.setText("  University Information Centre");
		title.setBackground(Color.WHITE);
		title.setFont(new Font("Tahoma", Font.BOLD, 30));
		title.setBounds(0, 0, 1300, 80);
		title.setOpaque(true);
		title.setVisible(true);
		panel.add(title);
	}

	/**
	 * Method changes the university image based on user selection with combo box
	 */
	private void changeImage() {
		if (UniversityList.getSelectedIndex() != 0) {
			index = UniversityList.getSelectedIndex() - 1;
			universities.setIcon(null);
			universities.setIcon(new ImageIcon("UniversityImages/" + UniversityList.getSelectedItem() + ".jpg"));
			universities.setBounds(155, 0, 1000, 800);
			panel.add(universities);
		}
	}

	/**
	 * Method rotates university image to the left
	 * 
	 * @param index
	 */
	private void rotateLeft(int index) {
		
		//Decrease index by one
		index -= 1;
		this.index = index;

		//Set index to 13 if the index is less than 0
		if (index == -1) {
			index = 13;
			this.index = index;
		}

		//Matches index with the index of the university and displays the information image
		for (int x = 0; x < 14; x++) {
			if (index == x) {
				universities.setIcon(null);
				universities
						.setIcon(new ImageIcon("UniversityImages/" + Initialize.universities[x].getName() + ".jpg"));
				universities.setBounds(155, 0, 1000, 800);
				panel.add(universities);
			}
		}
	}

	/**
	 * Method rotates university image to the right
	 * 
	 * @param index
	 */
	private void rotateRight(int index) {
		//Increase index by one
		index += 1;
		this.index = index;

		//Set index to 0 if the index is greater than 13
		if (index == 14) {
			index = 0;
			this.index = index;
		}

		//Matches index with the index of the university and displays the information image
		for (int x = 0; x < 14; x++) {
			if (index == x) {
				universities.setIcon(null);
				universities
						.setIcon(new ImageIcon("UniversityImages/" + Initialize.universities[x].getName() + ".jpg"));
				universities.setBounds(155, 0, 1000, 800);
				panel.add(universities);
			}
		}
	}

	/**
	 * Method creations action for buttons and combo box
	 */
	@Override
	public void actionPerformed(ActionEvent event) {

		//Return to the main screen and dispose the UniversityInformationGUI window
		if (event.getSource() == buttonReturn) {
			this.dispose();
			new HomeScreenGUI(currentUser);
		}

		//Calls the rotateLeft method to rotate the university image left
		if (event.getSource() == buttonLeft) {
			rotateLeft(index);
		}

		//Calls the rotateRight method to rotate the university image right
		if (event.getSource() == buttonRight) {
			rotateRight(index);
		}

		//Calls the changeImage method to change university image based on user selection
		if (event.getSource() == UniversityList) {
			changeImage();
		}

		// Directs the user to the CompareProgramGUI
		if (event.getSource() == buttonCompare) {
			new CompareProgramGUI(index);
		}
		
		if(event.getSource()==buttonExit) {
			this.dispose();
		}

	}

}