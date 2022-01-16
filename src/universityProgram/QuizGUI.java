/*
 * Contributors: Ankit
 * Class is JFrame that displays a survey using a JScrollPane
 */
package universityProgram;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.Scanner;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import java.awt.Dimension;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.Color;

public class QuizGUI extends JFrame implements ActionListener, ChangeListener{

	JScrollPane scrollablePanel = new JScrollPane();						// Scrollable Panel that stores each question
	private final int NUM_OF_QUESTIONS = 4;									// Stores number of questions
	private JSlider[] slider = new JSlider[NUM_OF_QUESTIONS];				// Stores each question slider
	private JSlider[] importanceSlider = new JSlider[NUM_OF_QUESTIONS];		// Stores each question's importance slider
	private String[] topic = new String[NUM_OF_QUESTIONS];					// Stores each topic from the QuizQuestion.txt
	private String[] questions = new String[NUM_OF_QUESTIONS];				// Stores each question from the QuizQuestion.txt
	private boolean[] importanceNeeded = new boolean[NUM_OF_QUESTIONS];		// Stores whether each question needs an importance question from the QuizQuestion.txt
	private String[][] options = new String[NUM_OF_QUESTIONS][5];			// Stores the options given for each question from QuizQuestion.txt
	private User currentUser;												// Stores the currentUser
	private Quiz quiz;														// Quiz object which will add points to Universities based user's response to questions
	private JButton btnNewButton_1;

	
	
	/**
	 * Main method to launch the application. Used for testing
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuizGUI window = new QuizGUI(null);
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/
	
	/**
	 * Create the application. Partly created using WindowBuilder then modified as needed. 
	 */
	public QuizGUI(User user) {
		quiz = new Quiz();
		new DistanceCalculator(user.getLatitude(), user.getLongitude());
		currentUser = user;
		readQuestions();
		setTitle("University Assitant");
		setBounds(0, 0, 1280, 720);
		setResizable(false);			
		setVisible(true);
		add(scrollablePanel);
		addHeader();
		initialize();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}

	/**
	 * Method reads the necessary data from the QuizQuestion.txt
	 */
	private void readQuestions() {

		Scanner input;

		// This try & catch will initialize all the question related strings and values
		try {

			input = new Scanner(new File("UniversityDataFiles/QuizQuestions.txt"));
			input.useDelimiter(","); // Every time it sees a comma it marks it as a new input

			int index = 0;

			while (input.hasNext()) {

				// .replaceAll Removes new lines and character commands
				topic[index] = input.next().replaceAll("\n", "").replaceAll("\r", "");		// Gets the question topic
				//System.out.println(topic[index]);
				
				questions[index] = input.next();											// Gets the question
				//System.out.println(questions[index]);
				
				// For each option
				for (int option = 0; option < 5; option++) {
					
					options[index][option] = input.next();									// Adds each option to an array
					//System.out.println(options[index][option]);

				}
				
				importanceNeeded[index] = input.nextBoolean();								// Gets whether or not the question needs importance
				//System.out.println(importanceNeeded[index] + "\n");
				index++;

			}

		} catch (FileNotFoundException e) {

			System.out.println("File Not Found");

		}
	}

	/**
	 * Method sets the header of the JScrollPane
	 */
	private void addHeader() {
		JPanel headerPanel = new JPanel();
		headerPanel.setBackground(Color.WHITE);

		// Sets title
		JLabel title = new JLabel("What University is Right for You?");		
		title.setFont(new Font("Tahoma", Font.BOLD, 30));
		headerPanel.add(title);

		//Sets Home Button
		JButton homeButton = new JButton("Home");					
		homeButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		homeButton.setForeground(Color.BLACK);
		homeButton.setBackground(new Color(250, 128, 114));
		homeButton.addActionListener(this);
		homeButton.setBounds(1000, 0, 50, 80);
		homeButton.setFocusable(false);
		headerPanel.add(homeButton);

		scrollablePanel.setColumnHeaderView(headerPanel);

		//Create the corners. Sourced from https://docs.oracle.com/javase/tutorial/uiswing/components/scrollpane.html
		JPanel blankCorner = new JPanel(); //use FlowLayout
		blankCorner.setBackground(Color.WHITE);
		
		//Set the corners.
		scrollablePanel.setCorner(JScrollPane.UPPER_RIGHT_CORNER, blankCorner);
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		JPanel storagePanel = new JPanel();											// Panel that contains all the Question Panels on the GUI itself
		storagePanel.setLayout(new BoxLayout(storagePanel, BoxLayout.PAGE_AXIS));
		
		
		JPanel[] questionPanels = new JPanel[NUM_OF_QUESTIONS];										// Array to stores all the Question Panels
		
		// For each question, create the question
		for (int questionNum = 0; questionNum < NUM_OF_QUESTIONS; questionNum++) {

			questionPanels[questionNum] = new JPanel();
			JPanel tempPanel = new JPanel(); 		
			
			// Set its GridBagLayout
			GridBagLayout gbl_panel_1 = new GridBagLayout();
			gbl_panel_1.columnWidths = new int[]{10, 36, 1058, 36, 10};
			
			// If the question requires an importance section add space for more rows
			if (importanceNeeded[questionNum]) 
				gbl_panel_1.rowHeights = new int[]{32, 1, 49, 44, 70, 35, 29, 43, 31, 50, 0};
			else  
				gbl_panel_1.rowHeights = new int[]{32, 1, 49, 44, 70, 35}; 
				
			
			gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			tempPanel.setLayout(gbl_panel_1);
			
			// Create blank space label
			JLabel label = new JLabel("");
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.anchor = GridBagConstraints.NORTHWEST;
			gbc_label.insets = new Insets(0, 0, 5, 5);
			gbc_label.gridx = 0;
			gbc_label.gridy = 1;
			tempPanel.add(label, gbc_label);
			
			// Create question label
			JLabel questionLabel = new JLabel(questions[questionNum]);
			questionLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
			GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
			gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_1.gridx = 2;
			gbc_lblNewLabel_1.gridy = 3;
			tempPanel.add(questionLabel, gbc_lblNewLabel_1);
			
			// Create the slider for the question
			slider[questionNum] = new JSlider();
			slider[questionNum].setFont(new Font("Tahoma", Font.PLAIN, 14));
			
			// Sourced from https://docs.oracle.com/javase/tutorial/uiswing/components/slider.html
			Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
			
			// For each option make a label
			for (int counter = 0; counter < 5; counter++) {
				labelTable.put( counter, new JLabel(options[questionNum][counter]));
			}
			
			slider[questionNum].setLabelTable(labelTable);
			slider[questionNum].setPaintLabels(true);
			slider[questionNum].setSnapToTicks(true);
			slider[questionNum].setName("Tuition Costs");
			slider[questionNum].setMinimum(0);
			slider[questionNum].setBorder(null);
			slider[questionNum].setPreferredSize(new Dimension(960, 60));
			slider[questionNum].setMajorTickSpacing(1);
			slider[questionNum].setValue(2);
			slider[questionNum].setMaximum(4);
			slider[questionNum].addChangeListener(this);
			GridBagConstraints gbc_slider = new GridBagConstraints();
			gbc_slider.insets = new Insets(0, 0, 5, 5);
			gbc_slider.gridx = 2;
			gbc_slider.gridy = 4;
			tempPanel.add(slider[questionNum], gbc_slider);
			
			
			// If an importance slider is needed add it
			if (importanceNeeded[questionNum]) {
				
				// Creates a blank space
				JLabel lblNewLabel_2 = new JLabel("");
				lblNewLabel_2.setPreferredSize(new Dimension(600, 30));
				GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
				gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
				gbc_lblNewLabel_2.fill = GridBagConstraints.VERTICAL;
				gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel_2.gridwidth = 3;
				gbc_lblNewLabel_2.gridx = 0;
				gbc_lblNewLabel_2.gridy = 6;
				tempPanel.add(lblNewLabel_2, gbc_lblNewLabel_2);
				
				// Add Label
				JLabel importanceLabel = new JLabel("How Important is " + topic[questionNum]  +" for you?");
				importanceLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
				GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
				gbc_lblNewLabel_4.anchor = GridBagConstraints.NORTH;
				gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel_4.gridx = 2;
				gbc_lblNewLabel_4.gridy = 7;
				tempPanel.add(importanceLabel, gbc_lblNewLabel_4);
				
				// Create JLabel Hashtable to add to slider
				Hashtable<Integer, JLabel> labelTable2 = new Hashtable<Integer, JLabel>();
				labelTable2.put( 0, new JLabel("<HTML>Not<br>Important<HTML>") );
				labelTable2.put( 1, new JLabel(" | ") );
				labelTable2.put( 2, new JLabel("<HTML>Somewhat<br>Important<HTML>"));
				labelTable2.put( 3, new JLabel(" | ") );
				labelTable2.put( 4, new JLabel("<HTML>Very<br>Important<HTML>") );
				
				// Create slider
				importanceSlider[questionNum] = new JSlider();
				importanceSlider[questionNum].addChangeListener(this);
				importanceSlider[questionNum].setValue(2);
				importanceSlider[questionNum].setSnapToTicks(true);
				importanceSlider[questionNum].setPreferredSize(new Dimension(960, 70));
				importanceSlider[questionNum].setLabelTable(labelTable2);
				
				importanceSlider[questionNum].setPaintLabels(true);
				
				importanceSlider[questionNum].setMinimum(0);
				importanceSlider[questionNum].setMaximum(4);
				importanceSlider[questionNum].setMajorTickSpacing(1);
				importanceSlider[questionNum].setFont(new Font("Tahoma", Font.PLAIN, 14));
				importanceSlider[questionNum].setBorder(null);
				GridBagConstraints gbc_slider_2 = new GridBagConstraints();
				gbc_slider_2.fill = GridBagConstraints.VERTICAL;
				gbc_slider_2.insets = new Insets(0, 0, 5, 5);
				gbc_slider_2.gridx = 2;
				gbc_slider_2.gridy = 8;
				tempPanel.add(importanceSlider[questionNum], gbc_slider_2);
				
				
			}
			
			
			questionPanels[questionNum].add(tempPanel);			// It may seem redundant to add a single Panel to a new panel but this way allows for the university
																// panels to be centered in the middle of the screen

			storagePanel.add(questionPanels[questionNum], -1);
			//System.out.println("Placing Label " + y + " at" + uniResults[y].getBounds());

		}
		
		// Add the button to move to the results screen
		btnNewButton_1 = new JButton("See Results");
		btnNewButton_1.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnNewButton_1.addActionListener(this);
		btnNewButton_1.setBackground(new Color(144, 238, 144));
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton_1.setFocusable(false);
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.anchor = GridBagConstraints.CENTER;
		gbc_btnNewButton_1.fill = GridBagConstraints.VERTICAL;
		gbc_btnNewButton_1.gridx = 5;
		gbc_btnNewButton_1.gridy = 10;
		storagePanel.add(btnNewButton_1, gbc_btnNewButton_1);

		scrollablePanel.setViewportView(storagePanel);
		
	}

	/**
	 * Method for when user clicks button to move to the results screen
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnNewButton_1) {
			dispose();
			quiz.calculateTotals();
			new Ranking(currentUser, quiz.getQuizRankingsTotal());
		} else {
			dispose();
			new HomeScreenGUI(currentUser);
		}
			
		
	}

	/**
	 * Method for when user adjusts a slider
	 */
	@Override
	public void stateChanged(ChangeEvent e) {
		
		// For each slider
		for (int slider = 0; slider < NUM_OF_QUESTIONS; slider++) {
			
			if (e.getSource() == this.slider[slider]) {
				//System.out.println(this.slider[slider].getValue());
				quiz.updateRanking(topic[slider], this.slider[slider].getValue());				// Update the uniRankings using the new slider value
			}
			
			if (e.getSource() == this.importanceSlider[slider]) {
				//System.out.println(importanceSlider[slider] == null);
			}
			
		}
		// For each slider, pass the corresponding Token
		
	}

}

