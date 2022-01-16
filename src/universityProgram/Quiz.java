/*
 * Contributors: Ankit
 * Class is an algorithm that looks at user preferences from the quiz and university data to add points to universities accordingly
 */
package universityProgram;

public class Quiz {

	private int[][] quizRankings;	// For 3 questions store points for each university, then the 4th row is to store the total for each uni
	private int[] importance;					// Store the importance for each question
	private final int NUM_OF_QUESTIONS = 4;		// Stores the number of questions

	public Quiz() {
		// Intialize the importance array
		importance = new int[NUM_OF_QUESTIONS];					
		for (int x = 0; x < NUM_OF_QUESTIONS; x++) {
			importance[x] = 2;
		}
		
		quizRankings = new int[NUM_OF_QUESTIONS + 1][14];		// Intialize the quizRankings array 
	}

	/**
	 * Returns the importance array
	 * @return
	 */
	public int[] getImportance() {
		return importance;
	}

	/**
	 * Sets the importance array;
	 * @param importance
	 */
	public void setImportance(int[] importance) {
		this.importance = importance;
	}
	
	/**
	 * Gets the importance of a certain question
	 * @param index
	 * @return
	 */
	public int getImportance(int index) {
		return importance[index];
	}

	/**
	 * Sets the importance of a certain question
	 * @param index
	 * @param value
	 */
	public void setImportance(int index, int value) {
		this.importance[index] = value;
	}
	
	/**
	 * Returns the quizRankings array
	 * @return
	 */
	public int[][] getQuizRankings() {
		return quizRankings;
	}

	/**
	 * Sets the quizRankings array
	 * @param quizRankings
	 */
	public void setQuizRankings(int[][] quizRankings) {
		this.quizRankings = quizRankings;
	}
	
	/**
	 * Returns the total points for each university
	 * @return
	 */
	public int[] getQuizRankingsTotal() {
		return quizRankings[NUM_OF_QUESTIONS];
	}

	/**
	 * Sets the total points for each university
	 * @param quizRankings
	 */
	public void setQuizRankingsTotal(int[] quizRankings) {
		this.quizRankings[NUM_OF_QUESTIONS] = quizRankings;
	}

	/**
	 * Updates the ranking based on which slider was adjusted in the QuizGUI class
	 * @param topic
	 * @param value
	 */
	public void updateRanking(String topic, int value) {
		
		// Based on the topic
		if (topic.equals("Tuition Costs")) {
			value = value*1000 + 9000;
			int maxTuition = value + 1000;
			int minTuition = value - 1000;
			
			// For each uni
			for (int uni = 0; uni < 14; uni++) {
				
				quizRankings[0][uni] = 0;
				
				// If the uni's tuition is within the user's preferance give the university points
				if (Initialize.universities[uni].getTuition() > minTuition && Initialize.universities[uni].getTuition() < maxTuition) {
					quizRankings[0][uni] += 5;// * importance[0];
				}
				
				//System.out.println("New Uni bonus points for tuition: " + quizRankings[0][uni]);
				
			}
		} else if (topic.equals("Distance from University")) {

			int maxDistance;
			int minDistance;
			// Find what answer the user chose. 
			switch (value) {
			case 0:
				minDistance = 0;
				maxDistance = 10;
				break;
			case 1:
				minDistance = 10;
				maxDistance = 50;
				break;
			case 2:
				minDistance = 50;
				maxDistance = 150;
				break;
			case 3:
				minDistance = 150;
				maxDistance = 250;
				break;
			case 4:
				minDistance = 250;
				maxDistance = 1000;
				break;
			default:
				minDistance = -1;
				maxDistance = -1;
			}

			// For each uni
			for (int uni = 0; uni < 14; uni++) {

				quizRankings[1][uni] = 0;
				double distance = DistanceCalculator.getDistance(Initialize.universities[uni]);
				//System.out.println(uni + " : " + distance);
			
				// If the distance is within the user preference give the university points
				if ( distance> minDistance && distance < maxDistance)
					quizRankings[1][uni] += 5;// * importance[0];
				

				//System.out.println("New Uni bonus points for distance: " + quizRankings[1][uni]);

			}

		} else if (topic.contentEquals("Co-op")) {

			// For each uni
			for (int uni = 0; uni < 14; uni++) {
				
				quizRankings[2][uni] = 0;
				
				// If the university has coop give the uni points
				if (Initialize.universities[uni].isCoop()) {
					quizRankings[2][uni] += 5;// * importance[2];
				}
				//System.out.println("New Uni bonus points for coop: " + quizRankings[2][uni]);

			}
		} else if (topic.contentEquals("Class Size")) {
			
			// For each uni
			for (int uni = 0; uni < 14; uni++) {
				
				quizRankings[3][uni] = 0;
				
				// For the percentage of classes in the user's preferred class size give the uni points
				quizRankings[3][uni] += (int) ((Initialize.universities[uni].getPrograms().getClassSize())[value] * 0.5);// * importance[3]);
				
				//System.out.println("New Uni bonus points for class size: " + quizRankings[0][uni]);

				
			}
			
		}
		
	}

	/**
	 * Update the importance of a certain topic. 
	 * @param topic
	 * @param value
	 */
	public void updateImportance(int topic, int value) {
		
		importance[topic] = value;
		//System.out.println("Updating importance of " + topic + "topic to " + value);
		
	}

	/**
	 * Calculate a universities total points based on the quiz
	 */
	public void calculateTotals() {

		for (int uni = 0; uni < 14; uni++) {
			quizRankings[NUM_OF_QUESTIONS][uni] = 0;
			for (int value = 0; value < NUM_OF_QUESTIONS; value++) {
				quizRankings[NUM_OF_QUESTIONS][uni] += quizRankings[value][uni] * importance[value];
				//System.out.println("Value being added: " + quizRankings[value][uni]);
				//System.out.println("Total sum so far for " + uni + "university: " + quizRankings[NUM_OF_QUESTIONS][uni]);
			}
			//System.out.println();
		}
	}
	
}
