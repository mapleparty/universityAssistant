/*
 * Contributors: Ankit
 * Class is a JPanel that is used by the ResultsGUI to display the information relating to a single university.
 */
package universityProgram;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Scrollable;

public class UniversityPanel extends JPanel implements ActionListener {

	User currentUser;
	String currentUniversityName;			//Used solely for displaying the proper University from the UniversityInformation Class
	
	/**
	 * Constructor creates the Panel for a university and sets the currentUser. 
	 * @param rank
	 * @param uni
	 * @param currentUser
	 */
	public UniversityPanel(int rank, University uni, User currentUser) {

		initialize(rank, uni);
		this.currentUser = currentUser;
		this.currentUniversityName = uni.getName();
	}

	/**
	 * Method is used to create the Panel for a university. 
	 * Code created using WindowBuilder
	 * @param rank
	 * @param uni
	 */
	private void initialize(int rank, University uni) {

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{28, 277, 0, 63, 21, 36, 0, 59, 35, 60, 25, 0};
		gridBagLayout.rowHeights = new int[]{50, 50, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		// Create rank label
		JLabel lblNewLabel = new JLabel(rank + ". ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		add(lblNewLabel, gbc_lblNewLabel);
		
		// Create button to link to university info page
		JButton btnNewButton = new JButton("<HTML><U>" + uni.getName() + "</U></HTML>");
		btnNewButton.setOpaque(false);
		btnNewButton.setForeground(new Color(0, 0, 255));
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setBorderPainted(false);
		btnNewButton.addActionListener(this);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.WEST;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 0;
		add(btnNewButton, gbc_btnNewButton);
		
		// Creates label for program name
		JLabel lblNewLabel_1 = new JLabel(uni.getPrograms().getName());
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 1;
		add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		// Creates Blank space label, used for formating
		JLabel lblNewLabel_8 = new JLabel("               ");
		GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
		gbc_lblNewLabel_8.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_8.gridx = 2;
		gbc_lblNewLabel_8.gridy = 1;
		add(lblNewLabel_8, gbc_lblNewLabel_8);
		
		// Creates eligibility label.
		JLabel lblNewLabel_2 = new JLabel("Eligibility: ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 3;
		gbc_lblNewLabel_2.gridy = 1;
		add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		// Shows user is eligible, For current version, where user is always eligible, yes is always displayed
		JLabel lblNewLabel_3 = new JLabel("Yes");
		lblNewLabel_3.setForeground(new Color(0, 204, 0));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 4;
		gbc_lblNewLabel_3.gridy = 1;
		add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		// Shows the courses user are missing through tooltipText. However since users are always eligible this is not used
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		//lblNewLabel_6.setToolTipText("Courses required: " + currentUser.missingCourses);
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.gridx = 5;
		gbc_lblNewLabel_6.gridy = 1;
		add(lblNewLabel_6, gbc_lblNewLabel_6);
		
		// Creates Blank space label, used for formating
		JLabel lblNewLabel_9 = new JLabel("               ");
		GridBagConstraints gbc_lblNewLabel_9 = new GridBagConstraints();
		gbc_lblNewLabel_9.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_9.gridx = 6;
		gbc_lblNewLabel_9.gridy = 1;
		add(lblNewLabel_9, gbc_lblNewLabel_9);
		
		//Creates label to show user chances of getting into university
		JLabel lblNewLabel_4 = new JLabel("Chances: ");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 7;
		gbc_lblNewLabel_4.gridy = 1;
		add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel(uni.getUserChance() + "%");
		
		//Sets colour of chance based on value, >= 80% is green, >= 40% is yellow, < 40% is red
		if (uni.getUserChance() >= 80) {
			lblNewLabel_5.setForeground(new Color(0, 204, 0));	// Green
			//System.out.println("Setting " + uni.getName() + " chance " + uni.getUserChance() +"% to " + lblNewLabel_5.getForeground() + "Green");

		}else if (uni.getUserChance() >= 40) {
			lblNewLabel_5.setForeground(new Color(255, 215, 0));	// Yellow
			//System.out.println("Setting " + uni.getName() + " chance " + uni.getUserChance() +"% to " + lblNewLabel_5.getForeground() + "Yellow");

		}else {
			lblNewLabel_5.setForeground(new Color(220, 20, 60));	// Red
			//System.out.println("Setting " + uni.getName() + " chance " + uni.getUserChance() +"% to " + lblNewLabel_5.getForeground() + "Red");

		}
		
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 8;
		gbc_lblNewLabel_5.gridy = 1;
		add(lblNewLabel_5, gbc_lblNewLabel_5);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 2;
		gbc_panel.gridheight = 2;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 9;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		JLabel lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setIcon(new ImageIcon("UniversityIcons/" + uni.getName() +".png"));
		panel.add(lblNewLabel_7);
		// Creates Blank space label, used for formating
		JLabel lblNewLabel_10 = new JLabel("<HTML><br><br></HTML>");
		GridBagConstraints gbc_lblNewLabel_10 = new GridBagConstraints();
		gbc_lblNewLabel_10.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_10.gridx = 1;
		gbc_lblNewLabel_10.gridy = 2;
		add(lblNewLabel_10, gbc_lblNewLabel_10);
	}
	
	/**
	 * Calls UniversityInformationGUI and 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		//System.out.println("Button clicked");
		new UniversityInformationGUI(currentUser, currentUniversityName);
		

	}

}
