/*
 * Contributors: Ankit
 * Class is JFrame that displays the results of the ranking algorithm using a JScrollPane
 */
package universityProgram;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ResultsGUI extends JFrame implements ActionListener {

	JScrollPane scrollablePanel = new JScrollPane();			//Scrollable Panel that stores each university label
	User currentUser;											// Stores the current User
	private JButton emailButton;
	static University[] rankedUnis = new University[14];				// Stores the universities in order of their rank

	/**
	 * Constructor that will create the results GUI window
	 * @param currentUser
	 */
	public ResultsGUI(User currentUser) {
		//new Ranking(currentUser);
		this.currentUser = currentUser;
		sortUnis();
		setBounds(0, 0, 1280, 720);
		setResizable(false);
		setVisible(true);
		addHeader();
		addResults();
		add(scrollablePanel);
		revalidate();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("University Assistant");
	}

	/**
	 * Method sorts the universities from the Initialize class and stores it into the rankedUnis array using insertion sort
	 * The ranked universities are stored in a separate array to prevent an errors when other classes try and access the Initialize array
	 */
	private void sortUnis() {

		// Creates the rankedUnis array with the unsorted Initialize array
		for (int uni = 0; uni < Initialize.universities.length; uni++) {
			rankedUnis[uni] = Initialize.universities[uni];
			rankedUnis[uni].setUserChance(Ranking.uniChance[uni]);
			//System.out.println("Uni b4 Sort: " + rankedUnis[uni]);
		}
		
		//System.out.println();
		
		// Insertion Sort
		for (int i = 0; i < rankedUnis.length; i++) {

			for (int j = i - 1; j >= 0; j--) {
				if (rankedUnis[i].getRank() > rankedUnis[j].getRank())

					// It's i-- since the next iteration needs to compare next 2 indexes
					swap(rankedUnis, i--, j);
				else
					break;
			}
		}
		
		/*
		for (int uni = 0; uni < Initialize.universities.length; uni++) {
			System.out.println("Uni a4 Sort: " + rankedUnis[uni]);
		}
		*/
	}

	/**
	 * Method swaps 2 universities. Used by insertion sort
	 * @param data
	 * @param x
	 * @param y
	 */
	public static void swap(University[] data, int x, int y) {
		University temp = data[x];
		data[x] = data[y];
		data[y] = temp;
	}

	/**
	 * Method uses the UniversityPanel class to add multiple labels displaying a University's info in order of it's ranking
	 */
	private void addResults() {

		JPanel storagePanel = new JPanel();											// Panel that contains all the University Panel on the GUI itself
		storagePanel.setLayout(new BoxLayout(storagePanel, BoxLayout.PAGE_AXIS));

		JPanel[] uniResults = new JPanel[10];										// Array to stores all the University Panel
		
		// For each uni in the top ten, create it's university label
		for (int y = 0; y < 10; y++) {

			uniResults[y] = new JPanel();
			JPanel tempLabel = new UniversityPanel(y + 1, rankedUnis[y], currentUser); 		// See UniversityPanel constructor
																										
			tempLabel.setFont(new Font("Tahoma", Font.BOLD, 80));

			uniResults[y].add(tempLabel);			// It may seem redundant to add a single Panel to a new panel but this way allows for the university
													// panels to be centered in the middle of the screen

			storagePanel.add(uniResults[y], -1);
			//System.out.println("Placing Label " + y + " at" + uniResults[y].getBounds());

		}

		scrollablePanel.setViewportView(storagePanel);
	}

	/**
	 * Method sets the header of the JScrollPane
	 */
	public void addHeader() {

		JPanel headerPanel = new JPanel();
		headerPanel.setBackground(Color.WHITE);

		// Sets title
		JLabel title = new JLabel("Personalized University Rankings");		
		title.setFont(new Font("Tahoma", Font.BOLD, 30));
		headerPanel.add(title);

		// Sets send button
		
		emailButton = new JButton("Email My Rankings");					
		emailButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		emailButton.setForeground(Color.BLACK);
		emailButton.setBackground(new Color(144, 238, 144));
		emailButton.addActionListener(this);
		emailButton.setFocusable(false);
		headerPanel.add(emailButton);
		
		//Sets Home Button
		JButton homeButton = new JButton("Home");					
		homeButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		homeButton.setForeground(Color.BLACK);
		homeButton.setBackground(new Color(250, 128, 114));
		homeButton.addActionListener(this);
		homeButton.setFocusable(false);
		headerPanel.add(homeButton);

		scrollablePanel.setColumnHeaderView(headerPanel);

		// Create the corners. Sourced from
		// https://docs.oracle.com/javase/tutorial/uiswing/components/scrollpane.html
		JPanel blankCorner = new JPanel(); // use FlowLayout
		blankCorner.setBackground(Color.WHITE);

		// Set the corners.
		scrollablePanel.setCorner(JScrollPane.UPPER_RIGHT_CORNER, blankCorner);

	}

	/**
	 * Method runs when the user clicks the home button
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == emailButton) {
			SendEmail.sendResults(currentUser);
			//confirmation message for user. 
			JOptionPane.showMessageDialog(null, "Your Ranking has been sent to :" + currentUser.getEmail() , "", JOptionPane.INFORMATION_MESSAGE);
		} else {
			new HomeScreenGUI(currentUser);
			dispose();
		}
	}
	
	/**
	 * Returns the currentUser
	 * @return
	 */
	public User getCurrentUser() {
		return currentUser;
	}

	/**
	 * Sets the currentUser
	 * @param currentUser
	 */
	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	
	
}
