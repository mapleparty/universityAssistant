/**
 * Contributor: Ryker, Ankit, Simran
 * The class User will hold the information of the user.
 */

package universityProgram;

public class User {

	private String firstName; // String variable that stores the first name of the user
	private String lastName; // String variable that stores the last name of the user
	private String userName; // String variable that stores the user name of the user
	private String password; // String variable that stores the password of the user
	private String email; //String variable that stores the email of the user
	private double latitude; //Double variable that stores the latitude of the user
	private double longitude; //Double variable that stores the longtitude of the user
	
	public final static double BUR_OAK_LATITUDE=43.8971;
	public final static double BUR_OAK_LONGITUDE=-79.2786;
	
	// Index for subject/grade 0: English 1: Calculus 2: Physics 3: Advanced 4:
	// Chemistry 5:Elective
	private String[] subject = new String[6]; // String array that stores the subjects the user is taking
	private int[] grade = new int[6]; // Integer array that stores the grade of the user

	private University[] universityPreference = new University[5]; // University array that stores User's university
																	// preferences

	/**
	 * Constructor that initializes a new user object
	 * 
	 * @param firstName
	 * @param lastName
	 * @param userName
	 * @param password
	 */

	public User(String firstName, String lastName, String userName, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;

		// Initialize grade/subject array from index 0 to index 5
		for (int x = 0; x < 6; x++) {
			grade[x] = -1;
			subject[x] = "No Subject";
		}
		
		latitude=BUR_OAK_LATITUDE;
		longitude=BUR_OAK_LONGITUDE;

	}
	
	/**
	 * Overloaded Constructor that initializes a new user object with their email as well
	 * 
	 * @param firstName
	 * @param lastName
	 * @param userName
	 * @param password
	 */

	public User(String firstName, String lastName, String userName, String password, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.email = email;
		
		// Initialize grade/subject array from index 0 to index 5
		for (int x = 0; x < 6; x++) {
			grade[x] = -1;
			subject[x] = "No Subject";
		}

		latitude=BUR_OAK_LATITUDE;
		longitude=BUR_OAK_LONGITUDE;
	}

	/**
	 * Returns the users email
	 * @return
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the users email
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Return the first name of the user.
	 * 
	 * @return
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Setter for firstName. Set the first name of the user.
	 * 
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Getter for lastName. Return the last name of the user.
	 * 
	 * @return
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Setter for lastName. Set the last name of the user.
	 * 
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Getter for userName. Return the user name of the user.
	 * 
	 * @return
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Setter for userName. Set the user name of the user.
	 * 
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Getter for password. Return the password of the user.
	 * 
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Setter for password. Set the password of the user.
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Getter for the grade array. Return the grade array of the user.
	 * 
	 * @return
	 */
	public int[] getGrade() {
		return grade;
	}

	/**
	 * Setter for the grade array. Set the grade array of the user
	 * 
	 * @param grade
	 */
	public void setGrade(int[] grade) {
		this.grade = grade;
	}

	/**
	 * Getter for the subject array. Return the subject array of the user
	 * 
	 * @return
	 */
	public String[] getSubject() {
		return subject;
	}

	/**
	 * Setter for the subject array. Setter the subject array of the user
	 * 
	 * @param subject
	 */
	public void setSubject(String[] subject) {
		this.subject = subject;
	}

	/**
	 * Getter for the university preference array. Return the university preference
	 * array of the user.
	 * 
	 * @return
	 */
	public University[] getUniversityPreference() {
		return universityPreference;
	}

	/**
	 * Setter for the university preference array. Set the university preference
	 * array of the user.
	 * 
	 * @param universityPreference
	 */
	public void setUniversityPreference(University[] universityPreference) {
		this.universityPreference = universityPreference;
	}
	
	/**
	 * Getter for the latitude. Return the latitude of the user.
	 * @return
	 */

	public double getLatitude() {
		return latitude;
	}
	
	/**
	 * Setter for the latitude. Set the latitude of the user.
	 * @param latitude
	 */

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	/**
	 * Getter for the longitude. Get the longitude of the user.
	 * @return
	 */
	
	public double getLongitude() {
		return longitude;
	}

	/**
	 * Setter for the longitude. Set the longitude of the user.
	 * @param longitude
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	/**
	 * calculate the mean of the six subjects of the user. return the average grade
	 * of the user
	 * 
	 * @return
	 */
	public int getGradeAverage() {

		int average = 0; // Temporary integer variable that stores the user's average

		// Add up the grade of six subjects
		for (int x = 0; x < 6; x++) {
			average += grade[x];
		}

		average = average / 6; // Calculate the mean

		return average;

	}
}
