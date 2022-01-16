/**
 * Contributor: Ryker, Simran
 * 
 * The SaveUserData class will save the user's subject, grade, university preference array by creating a new text file
 * under UserData with a file name of the user's user name.
 */

package universityProgram;

import java.io.*;

public class SaveUserData {

	private User currentUser; // The user variable currentUser stores the user that is being saved

	private String username; // The string variable username stores the user's user name

	/**
	 * Constructor that initializes a new SaveUserData object
	 * 
	 * @param currentUser
	 */

	public SaveUserData(User currentUser) {
		this.currentUser = currentUser;
		username = currentUser.getUserName();

		writeUserData(); // Call the method that writes the user's data to a text file

	}

	/**
	 * The method writeUserData retrieves user's information in the User class.
	 * Stores the user's information in a text file named with the user's user name
	 * under UserData folder
	 */

	private void writeUserData() {

		try {
			// Create new BufferedWriter
			BufferedWriter pr = new BufferedWriter(new FileWriter(new File("UserData/" + username + ".txt")));

			// Write in the user's subject from index 0 to 5
			for (int i = 0; i < 6; i++) {
				pr.write(String.format("%s,", currentUser.getSubject()[i]));
			}

			// Change line
			pr.write(String.format("%s", "\n"));

			// Write in the user's grade from index 0 to 5
			for (int i = 0; i < 6; i++) {
				pr.write(String.format("%d,", currentUser.getGrade()[i]));
			}
			
			// Change line
			pr.write(String.format("%s", "\n"));

			// Write in the user's latitude and longitude
			pr.write(String.format("%.4f,", currentUser.getLatitude()));
			pr.write(String.format("%.4f,", currentUser.getLongitude()));

			if (currentUser.getUniversityPreference()[0] != null) { // If the user enters university preference
				
				// Change line
				pr.write(String.format("%s", "\n"));
				// Write in the user's university preference by university index number from
				// index 0 to 5
				for (int i = 0; i < 5; i++) {
					pr.write(String.format("%d,", currentUser.getUniversityPreference()[i].getIndex()));
				}
			}

			

			// Close the BufferedWriter
			pr.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}