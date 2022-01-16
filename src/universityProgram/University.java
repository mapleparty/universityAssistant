/*
 * Contributors: Ankit, Ryker, Simran
 * Class creates university objects which then stores information related to the university
 */
package universityProgram;

public class University {

	public String name;									// Stores University Name
	public Program programs;							// Stores the program of the University
	public double overallAdmissionAverage;				// Stores the admission average based on B3 Cudo data
	public double latitude, longitude;					// Stores the latitude and longitude of the university location
	public int index;									// Stores the index of the university in the Intialize array
	int rank;											// Stores the rank of the univeristy which is determined by the Ranking Class
	int userChance;										// Stores the chance of a user getting into the university which is determined by the Ranking Class
	int tuition; 										// Stores the tuition cost of university based on G2 CUDO data
	boolean coop;										// Stores whether or not the university has coop

	/**
	 * Constructor to initialize a University object, does not initialize University rank or userChance
	 * @param name
	 * @param programs
	 * @param overallAdmissionAverage
	 * @param index
	 */
	public University(String name, Program programs, double overallAdmissionAverage, int index) {
		super();
		this.name = name;
		this.programs = programs;
		this.overallAdmissionAverage = overallAdmissionAverage;
		this.index=index;
	}

	/**
	 * Returns the name of the University
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name of the University
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the program of the university
	 * @return
	 */
	public Program getPrograms() {
		return programs;
	}

	/**
	 * Sets the program of the university
	 * @param programs
	 */
	public void setPrograms(Program programs) {
		this.programs = programs;
	}
	
	/**
	 * Returns the overall admission average based on B3 CUDO data
	 * @return
	 */
	public double getOverallAdmissionAverage() {
		return overallAdmissionAverage;
	}

	/**
	 * Sets the overall admission average based on B3 CUDO data
	 * @param overallAdmissionAverage
	 */
	public void setOverallAdmissionAverage(double overallAdmissionAverage) {
		this.overallAdmissionAverage = overallAdmissionAverage;
	}

	/**
	 * Returns the latitude position of the University
	 * @return
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * Sets the latitude position of the University
	 * @param latitude
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	/**
	 * Returns the longitude position of the university 
	 * @return
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * Sets the longitude position of the university
	 * @param longitude
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	/**
	 * Returns the index of the university in the Initialize array
	 * @return
	 */
	public int getIndex() {
		return index;
	}
	
	/**
	 * Gets the rank of the university based on the Ranking class
	 * @return
	 */
	public int getRank() {
		return rank;
	}

	/**
	 * Sets the rank of the university based on the Ranking class
	 * @param rank
	 */
	public void setRank(int rank) {
		this.rank = rank;
	}

	/**
	 * Returns the chances of the user getting into the University based on the Ranking class
	 * @return
	 */
	public int getUserChance() {
		return userChance;
	}

	/**
	 * Sets the chances of the user getting into the University based on the Ranking class
	 * @param userChance
	 */
	public void setUserChance(int userChance) {
		this.userChance = userChance;
	}
	
	/**
	 * Returns tuition cost of university
	 * @return
	 */
	public int getTuition() {
		return tuition;
	}

	/**
	 * Sets tuition cost of university
	 * @param tuition
	 */
	public void setTuition(int tuition) {
		this.tuition = tuition;
	}
	
	/**
	 * Returns whether the university has coop or not
	 * @return
	 */
	public boolean isCoop() {
		return coop;
	}

	/**
	 * Sets whether the university has coop or not
	 * @param coop
	 */
	public void setCoop(boolean coop) {
		this.coop = coop;
	}

	/**
	 * Method overrides original toString method and displays properties of a University object. Used for debugging
	 */
	@Override
	public String toString() {
		return "University [name=" + name + ", programs=" + programs + ", overallAdmissionAverage="
				+ overallAdmissionAverage + ", latitude=" + latitude + ", longitude=" + longitude + ", index=" + index
				+ ", rank=" + rank + ", userChance=" + userChance + "]";
	}
	
}