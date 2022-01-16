/**
 * Contributor: Ryker
 * The class UniversityPreferenceGUI creates a GUI screen for the user to enter their university preferences
 */

package universityProgram;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class UniversityPreferenceGUI extends JFrame implements ActionListener {

	User currentUser; // The user variable currentUser stores the user that is manipulating the screen
	int previousPreference[] = new int[5]; // Stores the user's previous selection
	int newSelectedUni; // Stores the user's newly selected university

	JPanel screen = new JPanel(); // JPanel screen

	JComboBox preferenceSelection[] = new JComboBox[5]; // JCombo box array that allow users to enter their preferences

	String[] universityList = new String[15]; // The university list for user to choose from

	// JLabel for the titles
	JLabel title = new JLabel(); // Account Information
	JLabel preferenceTitle = new JLabel(); // Preference Selection
	JLabel notificationLabel = new JLabel(); // Notify the user with the errors
	JLabel distanceTitle = new JLabel(); // Distance Calculator
	JLabel longitudeTitle = new JLabel(); // Longitude
	JLabel latitudeTitle = new JLabel(); // Latitude
	JLabel postalCodeTitle = new JLabel(); // Canadian Postal Code
	JLabel distanceDescription = new JLabel(); // Describe the distance calculator
	JLabel[] choiceTitle = new JLabel[5]; // No.1 choice to No.5 Choice
	JLabel[] colorTitle = new JLabel[5];// The colour representing each uni
	JLabel userColorTitle = new JLabel();// The colour representing the user

	// JButton for the buttons
	JButton toHomeGUI = new JButton(); // The button directs the user to the home page
	JButton saveButton = new JButton(); //The button saves the user's selection

	// JTextField for user entering his/her longitude, latitude, postal code
	JTextField longitudeField = new JTextField();
	JTextField latitudeField = new JTextField();
	JTextField postalCodeField = new JTextField();

	// JLabel for the map icon
	JLabel ontarioMapLabel = new JLabel();

	// JLabel to display the distance between the user and the selected university
	JLabel distanceLabel = new JLabel();

	// JLabel that indicates location
	JLabel yourLocationLabel = new JLabel(); // Indicates the user's location on the map
	JLabel[] universityLocationLabel = new JLabel[5]; // Indicates the selected university location on the map

	private static ImageIcon ontarioMap; // The ImageIcon that stores the Ontario's map

	// The location of the user
	double longitude; // The double variable that stores the user's longitude
	double latitude; // The double variable that stores the user's latitude
	String postalCode = "L6E1G4"; // Default to Bur Oak for the postal code

	int uniIndex; // The integer variable that store the selected university index

	boolean selectionValid; // The boolean variable that stores whether the selection is valid

	boolean[] repeatCheck = new boolean[14]; // The boolean array that stores whether the university is selected

	// The button group that selects which method to determine location
	ButtonGroup locationSelection = new ButtonGroup();

	JRadioButton usePostalCode = new JRadioButton(); // Use postal code to determine location
	JRadioButton useLatLong = new JRadioButton(); // Use Latitude/Longitude to determine location

	boolean validLocationEntry = true; // Whether the postal code is enter correctly

	/**
	 * The constructor that creates a new UniversityPrefereceGUI page.
	 * 
	 * @param currentUser
	 */
	public UniversityPreferenceGUI(User currentUser) {

		new DistanceCalculator(latitude, longitude); // Create a new DistanceCalculator object

		// Initialize the previousPreference array
		for (int x = 0; x < 5; x++) {
			previousPreference[x] = -1;
			universityLocationLabel[x] = new JLabel();
			colorTitle[x] = new JLabel();
		}

		// Initialize the universityList array

		universityList[0] = "Please Make Your Selection";

		for (int x = 0; x < 14; x++) {
			universityList[x + 1] = Initialize.universities[x].getName();
		}

		// Initialize ontarioMap with an image
		ontarioMap = new ImageIcon(new ImageIcon("Images/OntarioMap.png").getImage().getScaledInstance(269, 225, 0));

		this.currentUser = currentUser;
		newSelectedUni = -1;

		// Add the buttons defining the way to locate in the locationSelection group
		locationSelection.add(usePostalCode);
		locationSelection.add(useLatLong);

		//Initialize latitude and longitude
		latitude = currentUser.getLatitude();
		longitude = currentUser.getLongitude();

		// Methods that setup the GUI screen
		frameSetup();
		panelDesign();

	}

	/**
	 * Setup the frame
	 */
	public void frameSetup() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1280, 720);
		setLayout(null);
		setVisible(true);
		setResizable(false);
		setContentPane(screen);
		setTitle("University Assistant");

	}

	/**
	 * Setup the panel, labels and buttons on the screen
	 */
	public void panelDesign() {

		// Panel
		screen.setBorder(null);
		screen.setBounds(0, 0, 1280, 720);
		screen.setLayout(null);

		// Buttons
		toHomeGUI.addActionListener(this);
		toHomeGUI.setText("BACK");
		toHomeGUI.setFont(new Font("Tahoma", Font.BOLD, 24));
		toHomeGUI.setBounds(1100, 20, 120, 50);
		toHomeGUI.setForeground(Color.RED);
		toHomeGUI.setFocusable(false);
		screen.add(toHomeGUI);

		saveButton.addActionListener(this);
		saveButton.setText("SAVE");
		saveButton.setFont(new Font("Tahoma", Font.BOLD, 24));
		saveButton.setBounds(600, 570, 120, 50);
		saveButton.setForeground(Color.RED);
		saveButton.setFocusable(false);
		screen.add(saveButton);

		// JLabel

		title.setText("  University Preference");
		title.setBackground(Color.WHITE);
		title.setFont(new Font("Tahoma", Font.BOLD, 30));
		title.setBounds(0, 0, 1280, 80);
		title.setOpaque(true);
		title.setVisible(true);
		screen.add(title);

		preferenceTitle.setText("Preference Selection");
		preferenceTitle.setFont(new Font("Tahoma", Font.BOLD, 22));
		preferenceTitle.setBounds(60, 100, 300, 40);
		screen.add(preferenceTitle);

		distanceTitle.setText("Distance Calculator");
		distanceTitle.setFont(new Font("Tahoma", Font.BOLD, 22));
		distanceTitle.setBounds(700, 100, 300, 40);
		screen.add(distanceTitle);

		// Create the choice titles from the first to the fifth
		for (int x = 0; x < 5; x++) {
			choiceTitle[x] = new JLabel("No. " + (x + 1) + " Choice:");
			choiceTitle[x].setFont(new Font("Tahoma", Font.PLAIN, 22));
			choiceTitle[x].setBounds(80, 150 + 80 * x, 200, 60);
			choiceTitle[x].setVisible(true);
			screen.add(choiceTitle[x]);
		}

		latitudeTitle.setText("Latitude: ");
		latitudeTitle.setFont(new Font("Tahoma", Font.PLAIN, 18));
		latitudeTitle.setBounds(750, 200, 120, 40);
		screen.add(latitudeTitle);

		longitudeTitle.setText("longitude: ");
		longitudeTitle.setFont(new Font("Tahoma", Font.PLAIN, 18));
		longitudeTitle.setBounds(1000, 200, 120, 40);
		screen.add(longitudeTitle);

		postalCodeTitle.setText("Canadian Postal Code: ");
		postalCodeTitle.setFont(new Font("Tahoma", Font.PLAIN, 18));
		postalCodeTitle.setBounds(750, 200, 350, 40);
		postalCodeTitle.setVisible(false);
		screen.add(postalCodeTitle);

		distanceDescription.setText("Calculate the distance between your location and the selected university.");
		distanceDescription.setFont(new Font("Tahoma", Font.PLAIN, 15));
		distanceDescription.setBounds(700, 250, 500, 40);
		screen.add(distanceDescription);

		distanceLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		distanceLabel.setBounds(700, 510, 500, 40);
		screen.add(distanceLabel);

		yourLocationLabel.setBackground(Color.BLUE);
		yourLocationLabel.setOpaque(true);
		yourLocationLabel.setBounds((int) (820 + (longitude + 83.21356) * 33.59),
				(int) (282 + (46.70970 - latitude) * 47.89), 10, 10);
		screen.add(yourLocationLabel);

		for (int x = 0; x < 5; x++) {
			universityLocationLabel[x].setOpaque(true);
			universityLocationLabel[x].setVisible(false);
			screen.add(universityLocationLabel[x]);

			colorTitle[x].setOpaque(true);
			colorTitle[x].setVisible(true);
			colorTitle[x].setBounds(55, 175 + 80 * x, 10, 10);
			screen.add(colorTitle[x]);
		}

		colorTitle[0].setBackground(Color.PINK);
		colorTitle[1].setBackground(Color.GREEN);
		colorTitle[2].setBackground(Color.ORANGE);
		colorTitle[3].setBackground(Color.RED);
		colorTitle[4].setBackground(Color.YELLOW);

		universityLocationLabel[0].setBackground(Color.PINK);
		universityLocationLabel[1].setBackground(Color.GREEN);
		universityLocationLabel[2].setBackground(Color.ORANGE);
		universityLocationLabel[3].setBackground(Color.RED);
		universityLocationLabel[4].setBackground(Color.YELLOW);

		userColorTitle.setOpaque(true);
		userColorTitle.setVisible(true);
		userColorTitle.setBounds(705, 213, 10, 10);
		userColorTitle.setBackground(Color.BLUE);
		screen.add(userColorTitle);

		ontarioMapLabel.setIcon(ontarioMap);
		ontarioMapLabel.setBounds(820, 282, 269, 225);
		screen.add(ontarioMapLabel);

		notificationLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		notificationLabel.setForeground(Color.RED);
		notificationLabel.setBounds(430, 620, 450, 50);
		screen.add(notificationLabel);

		// JRadioButtons
		// Choose which way to determine location
		usePostalCode.setText("Canadian Postal Code");
		usePostalCode.setFont(new Font("Tahoma", Font.PLAIN, 15));
		usePostalCode.setBounds(900, 150, 250, 40);
		usePostalCode.addActionListener(this);
		screen.add(usePostalCode);

		useLatLong.setText("Latitude/Longitude");
		useLatLong.setFont(new Font("Tahoma", Font.PLAIN, 15));
		useLatLong.setBounds(700, 150, 150, 40);
		useLatLong.setSelected(true);
		useLatLong.addActionListener(this);
		screen.add(useLatLong);

		// JComboBox
		// Create 5 preference selection JComboBox from the first to the fifth
		for (int x = 0; x < 5; x++) {
			preferenceSelection[x] = new JComboBox(universityList);
			preferenceSelection[x].setBounds(220, 170 + 80 * x, 300, 30);
			preferenceSelection[x].setFont(new Font("Tahoma", Font.PLAIN, 18));
			preferenceSelection[x].addActionListener(this);
			preferenceSelection[x].setVisible(true);
			preferenceSelection[x].setAlignmentX(Component.LEFT_ALIGNMENT);
			screen.add(preferenceSelection[x]);

		}

		// JTextField
		// Enter the location
		longitudeField.addActionListener(this);
		longitudeField.setText(String.valueOf(longitude));
		longitudeField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		longitudeField.setBounds(1100, 200, 100, 40);
		screen.add(longitudeField);

		latitudeField.addActionListener(this);
		latitudeField.setText(String.valueOf(latitude));
		latitudeField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		latitudeField.setBounds(850, 200, 100, 40);
		screen.add(latitudeField);

		postalCodeField.addActionListener(this);
		postalCodeField.setText(postalCode);
		postalCodeField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		postalCodeField.setBounds(950, 200, 100, 40);
		postalCodeField.setVisible(false);
		screen.add(postalCodeField);

		// If user has entered their preferences before
		if (currentUser.getUniversityPreference()[0] != null) {
			// Run through each university preference
			for (int x = 0; x < 5; x++) {
				preferenceSelection[x].setSelectedIndex(currentUser.getUniversityPreference()[x].getIndex() + 1);
			}
		}

	}

	/**
	 * The method used whenever any JButton, JComboBox, JTextField is
	 * clicked/modified
	 */
	public void actionPerformed(ActionEvent event) {

		// If the user chooses to use Latitude/Longitude to determine the location
		if (useLatLong.isSelected()) {

			// The entry must be valid
			validLocationEntry = true;

			// Show the fields for Lat/Long
			latitudeTitle.setVisible(true);
			longitudeTitle.setVisible(true);
			latitudeField.setVisible(true);
			longitudeField.setVisible(true);
			postalCodeTitle.setVisible(false);
			postalCodeField.setVisible(false);

			// If JTextField is updated to modify latitude/longitude
			if (latitude != Double.parseDouble(latitudeField.getText())
					|| longitude != Double.parseDouble(longitudeField.getText())) {

				// Update latitude/longitude variable
				latitude = Double.parseDouble(latitudeField.getText());
				longitude = Double.parseDouble(longitudeField.getText());

				//The entry in valid
				validLocationEntry = true;

				// If the user's location is in Ontario
				if (longitude > -83.2 && longitude < -75.2 && latitude > 42 && latitude < 46.7) {

					// Update the user's location label with the correct position
					yourLocationLabel.setBounds((int) (820 + (longitude + 83.21356) * 33.59),
							(int) (282 + (46.70970 - latitude) * 47.89), 10, 10);

					// Set the location label visible
					yourLocationLabel.setVisible(true);
				} else {

					// If outside Ontario, set the label invisible
					yourLocationLabel.setVisible(false);
				}

				// If a university is selected
				if (newSelectedUni != -1) {
					// Call the method to calculate the distance
					calculateDistance();
				}
			}
		}

		// If the user chooses to use postal code
		if (usePostalCode.isSelected()) {

			// Show the fields for postal code
			postalCodeTitle.setVisible(true);
			postalCodeField.setVisible(true);
			latitudeTitle.setVisible(false);
			longitudeTitle.setVisible(false);
			latitudeField.setVisible(false);
			longitudeField.setVisible(false);

			// If user enters new postal code
			if (postalCode != postalCodeField.getText()) {

				postalCode = postalCodeField.getText();

				// Create new ConvertPostalCode object
				new ConvertPostalCode(postalCode);

				try {

					// The website is read
					validLocationEntry = true;

					// Set the latitude and longitude
					latitude = ConvertPostalCode.getLatitude();
					longitude = ConvertPostalCode.getLongitude();

					// If correct longitude/latitude is retrieved
					if (longitude != -1 && latitude != -1) {

						// If the user's location is in Ontario
						if (longitude > -83.2 && longitude < -75.2 && latitude > 42 && latitude < 46.7) {

							// Update the user's location label with the correct position
							yourLocationLabel.setBounds((int) (820 + (longitude + 83.21356) * 33.59),
									(int) (282 + (46.70970 - latitude) * 47.89), 10, 10);

							// Set the location label visible
							yourLocationLabel.setVisible(true);
						} else {

							// If outside Ontario, set the label invisible
							yourLocationLabel.setVisible(false);
						}

						// If a university is selected
						if (newSelectedUni != -1) {
							// Call the method to calculate the distance
							calculateDistance();
						}

						// The longitude and latitude is not retrieved
					} else {

						// The entry is not valid
						validLocationEntry = false;

						// Notify the user
						distanceLabel.setText("Incorrect Postal Code Entered");

						// Hide the user's location label
						yourLocationLabel.setVisible(false);
					}

				} catch (IOException e) {

					validLocationEntry = false;
					distanceLabel.setText("Incorrect Postal Code Entered");
					yourLocationLabel.setVisible(false);
				}

			}
		}

		// Run through the university preference selection from the first to the fifth
		for (int x = 0; x < 5; x++) {

			// If a university is selected
			if (preferenceSelection[x].getSelectedIndex() != 0) {

				// If the previous selection is different from the JComboBox
				if (previousPreference[x] != preferenceSelection[x].getSelectedIndex() - 1) {

					// Store the new selection to the previous selection array
					previousPreference[x] = preferenceSelection[x].getSelectedIndex() - 1;

					// Record the new selected university
					newSelectedUni = previousPreference[x];

					// Call the method to calculate the user's location and the university location
					calculateDistance();
				}

				// Set each university label invisible
				setUniversityLocationLabel(x, true);

				// If no university selected
			} else {
				// Set the university location label invisible
				setUniversityLocationLabel(x, false);
			}

		}

		// If save button is clicked
		if (event.getSource() == saveButton) {

			// Initialize the boolean variable selectionValid
			selectionValid = true;

			// Run through each preference selection JComboBox
			for (int x = 0; x < 5; x++) {
				// If any of the JComboBox is not selected
				if (preferenceSelection[x].getSelectedItem() == "Please Make Your Selection") {

					// The selection is not valid
					selectionValid = false;

					// Pop up a notification to prompt the user to select all choices
					notificationLabel.setText("Please make all your selections before proceeding");
				}
			}

			// Initialize the repeatCheck array
			Arrays.fill(repeatCheck, false);

			// If user has made all their choices
			if (selectionValid) {

				// Run through each JComboBox
				for (int x = 0; x < 5; x++) {

					// Stores the index of the university
					uniIndex = preferenceSelection[x].getSelectedIndex() - 1;

					// The university has been selected
					if (repeatCheck[uniIndex] == true) {

						// The selection is not valid
						selectionValid = false;

						// Pop up a notification to prompt the user to modify repeated university
						notificationLabel.setText("Adjust your repeated selections before proceeding");

						// Break the for loop
						break;
					} else {

						// If the university has not yet been selected,
						// Indicate it's now been selected.
						repeatCheck[uniIndex] = true;

					}
				}
			}

			// If the user's preference selection is valid
			if (selectionValid) {

				// Save the user's preference
				saveUserPreference();

				if (validLocationEntry) {
					currentUser.setLatitude(latitude);
					currentUser.setLongitude(longitude);

					// Pop up the screen that notifies the user their selection has been saved
					JOptionPane.showMessageDialog(null, "Your preferences and location have been saved.",
							"Confirmation: " + "Preferences and Location Saved", JOptionPane.INFORMATION_MESSAGE);
				} else {
					// Pop up the screen that notifies the user their preference has been saved
					// But the location has not been saved because it is invalid
					JOptionPane.showMessageDialog(null, "Your preferences have been saved. Location is not valid.",
							"Confirmation: " + "Preferences Saved", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}

		// If the toHomeGUI button is selected
		if (event.getSource() == toHomeGUI) {

			// Pop up the screen that notifies the user any unsaved changes will be lost
			// Record their selection
			int exit = JOptionPane.showConfirmDialog(null,
					"<html>Are you sure you want to return to home screen? <br>Anything not saved will be lost.<html>",
					"Exit University Preference Screen", JOptionPane.YES_NO_OPTION);

			// If user chooses to exit
			if (exit == JOptionPane.YES_OPTION) {

				// Return to the home screen
				new HomeScreenGUI(currentUser);

				// Close the screen
				this.dispose();
			}

		}

	}

	/**
	 * The method that stores the user's university preference
	 */
	public void saveUserPreference() {

		// University array that stores the user's university preference
		University[] userPreference = new University[5];

		// Run through each JComboBox to store the user's preference
		for (int x = 0; x < 5; x++) {
			userPreference[x] = Initialize.universities[preferenceSelection[x].getSelectedIndex() - 1];
		}

		// Set the user's preference
		currentUser.setUniversityPreference(userPreference);
	}

	/**
	 * The method that updates the distance label
	 */
	public void calculateDistance() {

		// Only works when the entry is valid
		if (validLocationEntry) {
			// Set the new user's location
			DistanceCalculator.setLocation(latitude, longitude);

			// Update the distance label
			distanceLabel.setText(
					String.format("%.1f", DistanceCalculator.getDistance(Initialize.universities[newSelectedUni]))
							+ " km away between you and " + Initialize.universities[newSelectedUni].getName());
		}

	}

	/**
	 * The method that updates the university location label
	 * 
	 * @param index
	 * @param selected
	 */
	public void setUniversityLocationLabel(int index, boolean selected) {

		// If the university is selected
		if (selected) {
			// Show the university location label
			universityLocationLabel[index].setVisible(true);

			// Set the location of the universityLocationLabel
			universityLocationLabel[index].setBounds(
					(int) (820
							+ (Initialize.universities[preferenceSelection[index].getSelectedIndex() - 1].getLongitude()
									+ 83.21356) * 33.59),
					(int) (282 + (46.70970
							- Initialize.universities[preferenceSelection[index].getSelectedIndex() - 1].getLatitude())
							* 47.89),
					10, 10);
			// If not selected
		} else {
			// Hide the university location label
			universityLocationLabel[index].setVisible(false);
		}

	}
}