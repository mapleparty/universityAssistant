/*
 * Jason Ta-min
 * Login screen
 * Dec, 18, 2020
 */

package universityProgram;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Scanner;

import javax.swing.*;

public class Login extends JFrame implements ActionListener {

	private JFrame Login;
	private JButton newacc, go;
	private JCheckBox show;
	private JLabel top, title, username, password;
	private JTextField enteruser;
	private JPasswordField enterpass;
	String filepath = "users.txt";
	private static Scanner input;
	public static String storeuser;
	public static String storepass;

	public Login() {

		// intialize all local variable
		Login = new JFrame("University Assistant");
		top = new JLabel("University Assistant");
		title = new JLabel("Login");
		username = new JLabel("Username: ");
		password = new JLabel("Password: ");
		newacc = new JButton("Create New Account");
		go = new JButton("Login");
		show = new JCheckBox();
		enteruser = new JTextField(20);
		enterpass = new JPasswordField();
		ImageIcon showp = new ImageIcon("Images/show.png");

		// set locatian and asthtics of "create account" button
		newacc.setBounds(200, 600, 400, 50);
		newacc.setFocusable(false);
		newacc.setBackground(Color.gray);
		newacc.setFont(new Font("Arial", Font.BOLD, 20));
		newacc.setBorder(BorderFactory.createRaisedBevelBorder());
		newacc.addActionListener(this);

		// set locatian and asthtics of "login" button
		go.setBounds(700, 600, 400, 50);
		go.setFocusable(false);
		go.setBackground(Color.green);
		go.setFont(new Font("Arial", Font.BOLD, 20));
		go.setBorder(BorderFactory.createRaisedBevelBorder());
		go.addActionListener(this);

		// set locatian, asthtics, and icon of "show password" button
		show.setBounds(890, 402, 40, 40);
		show.setFocusable(false);
		show.setIcon(showp);
		show.setHorizontalAlignment(JButton.CENTER);
		show.setBackground(Color.red);
		show.setBorder(BorderFactory.createEmptyBorder());
		show.addActionListener(this);

		// set locatian and asthtics of "page header" label
		top.setBounds(0, 0, 1280, 120);
		top.setFont(new Font("Arial", Font.BOLD, 45));
		top.setOpaque(true);
		top.setBackground(Color.white);

		// set locatian and asthtics of "title" label
		title.setBounds(200, 200, 300, 50);
		title.setFont(new Font("Tahoma", Font.BOLD, 45));

		// set locatian and asthtics of "username" label
		username.setBounds(400, 300, 300, 45);
		username.setFont(new Font("Tahoma", Font.BOLD, 30));

		// set locatian and asthtics of "password" label
		password.setBounds(400, 400, 300, 45);
		password.setFont(new Font("Tahoma", Font.BOLD, 30));

		// set locatian of username input text box
		enteruser.setBounds(570, 300, 300, 45);
		enteruser.setFont(new Font("Tahoma", Font.PLAIN, 20));

		// set locatian of password input text box, and set the default character to be
		// stars to allow oneness with the "show password" function
		enterpass.setBounds(570, 400, 300, 45);
		enterpass.setFont(new Font("Tahoma", Font.PLAIN, 20));
		enterpass.setEchoChar('*');

		// set up operations and asthtics of the "login" screen/frame
		Login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Login.setVisible(true);
		Login.setPreferredSize(new Dimension(1280, 720));
		Login.pack();
		Login.setVisible(true);

		// add everything to the "login" screen/frame so that they can be seen and used
		Login.setLayout(null);
		Login.add(newacc);
		Login.add(go);
		Login.add(show);
		Login.add(top);
		Login.add(title);
		Login.add(username);
		Login.add(password);
		Login.add(enteruser);
		Login.add(enterpass);
	}

	// method to give buttons actions
	@Override
	public void actionPerformed(ActionEvent e) {

		// if "show password" button is pressed show the characters, otherwise display
		// characters as a "*".
		if (show.isSelected()) {
			enterpass.setEchoChar((char) 0);
			show.setBackground(Color.gray);
		} else {
			enterpass.setEchoChar('*');
			show.setBackground(Color.red);
		}

		// if "create a new account" button is pressed terminate the login screen/frame
		// and open the account creation screen/frame
		if (e.getSource() == newacc) {

			Login.dispose();
			new AccountCreation();
		}

		// if "login" button is selected read the inputs and compare to the txt database
		if (e.getSource() == go) {

			// creationg and initalizing local variables
			boolean found = false;
			String storeuser = enteruser.getText(); // stores the users username input
			char[] storepass = enterpass.getPassword(); // stores the users password input
			String username = storeuser; // change the variable name to username
			String password = new String(storepass); // switch the char data type to a string data type
			String tempuser = ""; // temporary username that is used for comparison reasons
			String temppass = ""; // temporary password that is used for comparison reasons

			try {
				input = new Scanner(new File(filepath)); // read what is in the text file
				input.useDelimiter(","); // seperate the different regions of the txt file, from the commas

				while (input.hasNext() && !found) { // loop that keeps going to the next line of the txt file if "found
													// is not switched to true

					// gives the temporary variables values
					String tempFirst = input.next();
					String tempLast = input.next();
					tempuser = input.next();
					temppass = input.next();
					String tempEmail = input.next();

					// if the temporary username and password from the save file is the same as the
					// useres input, found is true
					if (tempuser.trim().equals(username) && temppass.trim().equals(password)) {

						found = true;
					}

					// if found is true the set the current user and initalize all their saved data
					if (found == true) {
						User currentUser = new User(tempFirst, tempLast, tempuser, password,tempEmail);
						new Initialize(currentUser);

						// terminate the login screen/frame and open a new homescreen based off the
						// current user
						Login.dispose();
						new HomeScreenGUI(currentUser);
					}
				}

				// if found is not still not true after comparing through the wole txt file, the
				// dispaly and error message that infroms the user
				if (found != true) {
					JOptionPane.showMessageDialog(null, "Username and/or password incorrect!", "ERROR!",
							JOptionPane.ERROR_MESSAGE);
				}

				input.close();

			} catch (Exception ex) {
			}
		}
	}
}