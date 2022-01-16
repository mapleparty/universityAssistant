/**
 * Contributor: Ryker, Ankit
 * The class Ranking evaluates the chances and ranks each university with user's and unioversity's data
 */
package universityProgram;

public class Ranking {

	// The array used to store the chances of getting into each university
	public static int[] uniChance = new int[14];

	// The array used to store the points for ranking for each university
	public static int[] uniRankings = new int[14];

	public User currentUser; // The current user

	/**
	 * Constructor that creates a new Ranking object
	 * 
	 * @param currentUser
	 */
	public Ranking(User currentUser) {

		// Initialize for the uniRanking array
		for (int university : uniRankings) {
			university = 0;
			Initialize.universities[university].setRank(0);
		}

		// Initialize the uniChance array
		for (int x = 0; x < uniChance.length; x++) {
			uniChance[x] = 0;
		}

		this.currentUser = currentUser;

		// Call the methods that evaluates the chances
		// and ranks each university
		evaluateChance();
		rankChance();
		addUserPrefereance();
		rankRatings(); // Based on F1a Chart (How happy are you with university)
		rankReturns(); // Based on F1b Chart (How likely are you to redo)
		addRankingToUni();
		new ResultsGUI(currentUser);
	}

	// Constructor
	public Ranking(User currentUser, int[] quizResults) {

		// Initialize for the uniRanking array with the results of the quiz
		int counter = 0;
		for (int university : uniRankings) {
			university = 0;
			Initialize.universities[university].setRank(0);
			university += quizResults[counter];
			counter++;
		}

		for (int x = 0; x < uniChance.length; x++) {
			uniChance[x] = 0;
		}

		this.currentUser = currentUser;

		evaluateChance();
		rankChance();
		addUserPrefereance();
		rankRatings(); // Based on F1a Chart (How happy are you with university)
		rankReturns(); // Based on F1b Chart (How likely are you to redo)
		addRankingToUni();
		new ResultsGUI(currentUser);
	}

	/**
	* Store the ranking to the University object
	*/
	private void addRankingToUni() {
		for (int x = 0; x < 14; x++)
			Initialize.universities[x].setRank(uniRankings[x]);

	}

	/**
	 * Evaluate the chances of getting into each university
	 */
	private void evaluateChance() {

		int index = 0; // The index of the university

		// Run through each university
		for (int z = 0; z < 14; z++) {
			University currentUniversity = Initialize.universities[z];

			int userExceedingPoint = 0; // If grade is too high, the rank may go down

			// If the user's grade is higher than the average admission grade
			if (currentUniversity.getOverallAdmissionAverage() <= currentUser.getGradeAverage()) {

				// The user has at least 50% of chance of getting into the university
				uniChance[index] += 50;

				// For each point higher, the chance will go up by 5%
				for (int x = 0; x < (int) (currentUser.getGradeAverage()
						- currentUniversity.getOverallAdmissionAverage() + 0.5); x++) {
					uniChance[index] += 5;

					// If user's grade is too high and the chance gets too high
					if (uniChance[index] >= 100) {

						// Reduce it to 95% (Nothing is certain)
						uniChance[index] = 95;

						// Calculating how much point user exceeds the cap
						userExceedingPoint = (int) (currentUser.getGradeAverage()
								- currentUniversity.getOverallAdmissionAverage() + 0.5) - 10;

						// Stop the loop
						break;
					}

					// If user's average is too high
					if (userExceedingPoint > 0) {

						// Reduce university ranking point
						for (int y = 0; y < userExceedingPoint; y++) {
							uniRankings[index] -= 10;
						}
					}
				}

				// If user's average is lower than the admission average
			} else {

				// Chance will directly related to the percentage of people admitted
				if (currentUser.getGradeAverage() >= 95) {
					uniChance[index] += currentUniversity.getPrograms().getFirstYearAverages()[0];
				} else if (currentUser.getGradeAverage() >= 90) {
					uniChance[index] += currentUniversity.getPrograms().getFirstYearAverages()[1];
				} else if (currentUser.getGradeAverage() >= 85) {
					uniChance[index] += currentUniversity.getPrograms().getFirstYearAverages()[2];
				} else if (currentUser.getGradeAverage() >= 80) {
					uniChance[index] += currentUniversity.getPrograms().getFirstYearAverages()[3];
				} else if (currentUser.getGradeAverage() >= 75) {
					uniChance[index] += currentUniversity.getPrograms().getFirstYearAverages()[4];
				} else if (currentUser.getGradeAverage() >= 70) {
					uniChance[index] += currentUniversity.getPrograms().getFirstYearAverages()[5];
				} else {
					uniChance[index] += currentUniversity.getPrograms().getFirstYearAverages()[5];
				}

				// Round up to 5% interval
				if (uniChance[index] % 5 > 0) {
					uniChance[index] = uniChance[index] / 5 * 5 + 5;
				}

			}
			// Move to next university
			index++;
		}

	}


/**
	 * Add the chance factor to the university ranking
	 */
	private void rankChance() {

		// Run through each university and add the chance to the ranking
		for (int x = 0; x < uniRankings.length; x++) {
			uniRankings[x] += uniChance[x];
		}
	
	}

/**
	 * Add the user preference factor to the university ranking
	 */
	private void addUserPrefereance() {

		// Starts from adding 100 to the university ranking point
		int counter = 100;

		// Run through from the first choice to the fifth choice
		for (int uniChoice = 0; uniChoice < 5; uniChoice++) {

			// Run through each university for checking
			for (int uni = 0; uni < 14; uni++) {

				// If matches
				if (currentUser.getUniversityPreference()[uniChoice].getName()
						.equals(Initialize.universities[uni].getName()))
					// Add the point to the ranking array
					uniRankings[uni] += counter;
			}

			// Deduct 20 points for the next choice
			counter -= 20;

		}

	
	}

	/**
	 * Based on F1a Chart (How happy are you with university), adjust the university
	 * ranking
	 */
	private void rankRatings() {

		// For each uni
		for (int x = 0; x < 14; x++) {
			int counter = -1;
			// For each rating: Poor subtracts a point, Fair is 0 points, Good adds a point,
			// Excellent adds 2 points
			for (int rating = 0; rating < 3; rating++) {
				uniRankings[x] += ((int) (Initialize.universities[x].getPrograms().getPercentHappy()[rating])
						* counter);
				counter++;
			}
		}
	}

	/**
	 * Based on F1b Chart (How likely are you to undo your choice) to adjust the
	 * university ranking
	 */
	private void rankReturns() {
		// For each uni
		for (int x = 0; x < 14; x++) {
			int counter = 1;
			// For each wouldComeBack: Definitely Yes adds a point, Yes adds 0 points,
			// Probably not
			// subtracts a point, Definitely Not subtracts 2 points
			// Excellent adds 2 points
			for (int rating = 0; rating < 3; rating++) {
				uniRankings[x] += ((int) (Initialize.universities[x].getPrograms().getWouldComeBack()[rating]
						* counter));
				counter--;
			}
		}
	}

}