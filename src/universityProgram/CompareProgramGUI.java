
package universityProgram;

/**
 * Contributor: Carlos Wong
 * The class ComapreProgramGUI creates a screen that displays data on university's engineering program
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class CompareProgramGUI extends JFrame implements ActionListener {

	// Panel
	private JPanel panel = new JPanel();

	// Label
	private JLabel title = new JLabel();;
	private JLabel instruction = new JLabel();
	private JLabel dataDetails = new JLabel();

	// Table and ScrollPane
	private JTable table;
	private JScrollPane scrollPane;
	private JTableHeader header;

	// Button
	private JButton buttonReturn = new JButton();

	// Index of university image
	private int index;

	// Column names for table
	private String[] colNames = { "Name", "Location", "Rank", "Tuition Fee (~$)", "Graduation Rate (%)",
			"Employeement Rate (%)" };

	// Object variable to store program data
	Object[][] programData = {
			{ "Toronto University", "Toronto", new Integer(1), new Integer(15800), new Double(88.01),
					new Double(88.66) },
			{ "Waterloo University", "Waterloo", new Integer(2), new Integer(15500), new Double(82.80),
					new Double(81.91) },
			{ "Queen's University", "Kingston", new Integer(3), new Integer(13200), new Double(89.60),
					new Double(87.91) },
			{ "McMaster University", "Hamilton", new Integer(4), new Integer(13800), new Double(75.90),
					new Double(76.11) },
			{ "Western Main Campus", "London", new Integer(5), new Integer(13700), new Double(81.90),
					new Double(85.41) },
			{ "University of Ottawa", "Ottawa", new Integer(6), new Integer(10500), new Double(66.30),
					new Double(60.41) },
			{ "Ryerson University", "Toronto", new Integer(7), new Integer(11300), new Double(70.4),
					new Double(82.27) },
			{ "Carlton University", "Ottawa", new Integer(8), new Integer(11700), new Double(73.6), new Double(85.51) },
			{ "York University", "Toronto", new Integer(9), new Integer(12800), new Double(37.5), new Double(80.01) },
			{ "University of Guelph", "Guelph", new Integer(10), new Integer(12500), new Double(76.3),
					new Double(91.18) },
			{ "Ontario Tech University", "Oshawa", new Integer(11), new Integer(10400), new Double(61.9),
					new Double(84.15) },
			{ "University of Windsor", "Windsor", new Integer(12), new Integer(10600), new Double(77.1),
					new Double(90.32) },
			{ "Lakehead University", "Thunder Bay", new Integer(13), new Integer(8600), new Double(63.8),
					new Double(83.65) },
			{ "Laurentian University", "Subury", new Integer(14), new Integer(9000), new Double(59.3),
					new Double(90.01) } };

	/**
	 * Constructor to create the GUI window
	 * 
	 * @param index
	 */
	public CompareProgramGUI(int index) {
		setFrame();
		addButtons();
		setPanel();
		addJTable();
	}

	/**
	 * Method sets up the frame design
	 */
	private void setFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1280, 720);
		setLayout(null);
		setVisible(true);
		setResizable(false);
		setTitle("University Assistant");
		setContentPane(panel);
	}

	/**
	 * Method creates back buttons to return to UniversityInformation GUI
	 */
	private void addButtons() {

		// set return to UniversityInformation GUI button
		buttonReturn.setText("BACK");
		buttonReturn.setFont(new Font("Tahoma", Font.BOLD, 24));
		buttonReturn.setBounds(1110, 10, 120, 50);
		buttonReturn.setForeground(Color.RED);
		buttonReturn.addActionListener(this);
		buttonReturn.setFocusable(false);
		panel.add(buttonReturn);
	}

	/**
	 * Method creates a panel and creates the title of the window and is added to
	 * the panel
	 */
	private void setPanel() {

		// Create panel for the frame
		panel.setBorder(null);
		panel.setBounds(0, 0, 1280, 720);
		panel.setLayout(null);

		// Create title for the GUI and add it to the panel
		title.setText("  Engineering Program Comparison");
		title.setBackground(Color.WHITE);
		title.setFont(new Font("Tahoma", Font.BOLD, 30));
		title.setBounds(0, 0, 1280, 80);
		title.setOpaque(true);
		title.setVisible(true);
		panel.add(title);

		// Create instruction to notify the user on how to compare program data
		instruction.setText(
				"    For comparison, click on the desired column once for ascending order and twice for descending order.");
		instruction.setFont(new Font("Tahoma", Font.PLAIN, 20));
		instruction.setBounds(0, 80, 1079, 78);
		panel.add(instruction);

		// Create description to notify where the engineering program data is from
		dataDetails.setText("<html>*Ranking based on Maclean's Report 2020<html>"
				+ " <br>*Tuition fee is rounded to the nearest thousands and is based on CUDO 2018 data<html>"
				+ " <br>*Graduation rate is based on CUDO 2018 data<html>"
				+ " <br>*Employment rate is calculated within 6 months after graduation and is based on CUDO 2016 data<html>");
		dataDetails.setFont(new Font("Tahoma", Font.PLAIN, 16));
		dataDetails.setBounds(30, 350, 1079, 308);
		panel.add(dataDetails);
	}

	/**
	 * Method initializes the first table
	 */
	private void addJTable() {

		// Create model of the table and set column data to its corresponding class type
		DefaultTableModel model = new DefaultTableModel(programData, colNames) {

			public Class getColumnClass(int column) {
				if (column == 0 || column == 1) {
					return String.class;
				} else if (column == 2 || column == 3 || column == 4) {
					return Integer.class;
				} else if (column == 5) {
					return Double.class;
				} else {
					return String.class;
				}
			}
		};

		// Set table of program details and prevents the user to edit the table
		table = new JTable(model);
		table.setPreferredScrollableViewportSize(new Dimension(300, 100));
		table.setFillsViewportHeight(true);
		table.setFont(new Font("Tahome", Font.PLAIN, 16));
		table.setAutoCreateRowSorter(true);
		table.setDefaultEditor(Object.class, null);

		// Change header of table in terms of color, background and font size
		header = table.getTableHeader();
		header.setBackground(Color.ORANGE);
		header.setForeground(Color.BLACK);
		header.setFont(new Font("Tahome", Font.BOLD, 16));

		// Set scroll pane and adds the table to it
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(25, 180, 1220, 253);
		panel.add(scrollPane);
	}

	/**
	 * Method creations action for return to UniversityInformation GUI button
	 */
	@Override
	public void actionPerformed(ActionEvent event) {

		// returns the user to UniversityInformation GUI and disposes the current window
		if (event.getSource() == buttonReturn) {
			dispose();
			new UniversityInformationGUI(index);
		}
	}
}