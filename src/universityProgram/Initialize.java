/*
 * Contributors: Simran, Ryker, Carlos
 * Description: This class intializes all the university data from the files we have, and the user information
 */

package universityProgram;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Initialize {

	public static User user; // user object to initialize the user
	public static University[] universities = new University[14]; // array to hold all universities and their data
	private static Program[] programs = new Program[14]; //array to hold the engineering program info for each university

	
	/*
	 * Constructor to initialize data
	 */
	public Initialize(User currentUser) {
		user = currentUser;
		initializeUniData();
		initializeUserData();
	}

	
	/*
	 * method to initialize the user object and set all the user info
	 * Made by Oscar and Simran
	 */
	private void initializeUserData() {
		Scanner input;
		String username = user.getUserName();

		try { //try to find file with user's user name
			input = new Scanner(new File("UserData/" + username + ".txt"));
			input.useDelimiter(",");
			String[] subject = new String[6];
			int[] grade = new int[6];
			University[] universityPreference = new University[5];

			for (int x = 0; x < 6; x++) { //set subjects
				subject[x] = input.next();
			}
			input.nextLine();

			for (int x = 0; x < 6; x++) { //set grades
				grade[x] = input.nextInt();
			}
			input.nextLine();
			
			//Set location
			user.setLatitude(input.nextDouble());
			user.setLongitude(input.nextDouble());

			input.nextLine();
			if (input.hasNext()) { //check if user has made uni preferences
				for (int x = 0; x < 5; x++) {
					int index = input.nextInt();
					universityPreference[x] = universities[index];
				}
				user.setUniversityPreference(universityPreference); //set the uni preferences in the user object
			}

			user.setSubject(subject); //set the subjects and grades in the user object
			user.setGrade(grade);

			input.close();

		} 
		catch (FileNotFoundException e) {
			//if a file with the user name is not found then nothing happens
			//a file with the user name will be created when the user logs out
		}
	}

	
	/*
	 * method to initialize the uni data that we collected and put in the text files
	 * Made by Simran and Carlos
	 * Reading the University Location text file was added by Oscar
	 * Reading the Uni Class size, Co-op and Tuition Cost was added by Ankit
	 */
	private static void initializeUniData() {
		Scanner input;

		// read the student engagement data and store that data
		try {
			input = new Scanner(new File("UniversityDataFiles/StudentEngagement.txt"));
			input.useDelimiter(",");
			String universityName;
			double excellent, good, fair, poor;
			int x = 0;

			while (input.hasNext()) {
				universityName = input.next().replaceAll("\n", "").replaceAll("\r", "");
				excellent = input.nextDouble();
				good = input.nextDouble();
				fair = input.nextDouble();
				poor = input.nextDouble();

				double[] percentHappy = { poor, fair, good, excellent };

				// create new program "engineering"
				programs[x] = new Program("Engineering");
				programs[x].setPercentHappy(percentHappy);
				x++;
			}

			input.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}

		//read the student survey data and store that data
		try {
			input = new Scanner(new File("UniversityDataFiles/StudentSurvey.txt"));
			input.useDelimiter(",");
			String universityName;
			double defYes, yes, no, defNo;
			int x = 0;
			while (input.hasNext()) {
				universityName = input.next().replaceAll("\n", "").replaceAll("\r", "");
				defYes = input.nextDouble();
				no = input.nextDouble();
				yes = input.nextDouble();
				defNo = input.nextDouble();
				double[] wouldComeBack = { defYes, yes, no, defNo };
				programs[x].setWouldComeBack(wouldComeBack);
				x++;
			}
			input.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}

		//add the prerequisites for Engineering program for each uni
		for (int i = 0; i < 14; i++) {
			String[] preRequisites = { "English", "Calculus", "Physics", "Advanced Functions", "Chemistry" };
			programs[i].setPreRequisites(preRequisites);
		}

		//read the student first year averages data and create each uni
		try {
			input = new Scanner(new File("UniversityDataFiles/SurveyAverages.txt"));
			input.useDelimiter(",");
			String universityName;
			double A3, A2, AB, B3, B2, B, A1, overallAverage;
			int x = 0;
			while (input.hasNext()) {
				universityName = input.next().replaceAll("\n", "").replaceAll("\r", "");
				A3 = input.nextDouble();
				A2 = input.nextDouble();
				A1 = input.nextDouble();
				AB = input.nextDouble();
				B3 = input.nextDouble();
				B2 = input.nextDouble();
				B = input.nextDouble();
				overallAverage = input.nextDouble();
				double[] firstYearAverages = { A3, A2, A1, AB, B3, B2, B };

				programs[x].setFirstYearAverages(firstYearAverages);
				universities[x] = new University(universityName, programs[x], overallAverage, x);
				x++;
			}
			input.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}

		//read university locations and store that data
		try {
			input = new Scanner(new File("UniversityDataFiles/UniversityLocation.txt"));
			input.useDelimiter(",");
			double latitude, longitude;
			String universityName;
			int x = 0;
			while (input.hasNext()) {
				universityName = input.next().replaceAll("\n", "").replaceAll("\r", "");
				latitude = input.nextDouble();
				longitude = input.nextDouble();
				universities[x].setLatitude(latitude);
				universities[x].setLongitude(longitude);
				//System.out.println(universities[x].getName() + " distance: " +  DistanceCalculator.getDistance(universities[x]));
				x++; 
			}
			input.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
		
		//read university tuition and store data
		try {
			input = new Scanner(new File("UniversityDataFiles/UniversityTuition.txt"));
			input.useDelimiter(",");
			int tuition;
			String universityName;
			int x = 0;
			while (input.hasNext()) {
				universityName = input.next().replaceAll("\n", "").replaceAll("\r", "");
				tuition = input.nextInt();
				universities[x].setTuition(tuition);
				//System.out.println(universities[x].getTuition());
				x++; 
			}
			input.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
		
		// read university coop and store data
		try {
			input = new Scanner(new File("UniversityDataFiles/UniversityCo-op.txt"));
			input.useDelimiter(",");
			boolean coop;
			String universityName;
			int x = 0;
			while (input.hasNext()) {
				universityName = input.next().replaceAll("\n", "").replaceAll("\r", "");
				coop = input.nextBoolean();
				universities[x].setCoop(coop);
				//System.out.println(universities[x].isCoop());
				x++;
			}
			input.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}

		// read university class size data
		try {
			input = new Scanner(new File("UniversityDataFiles/UniversityClassSizes.txt"));
			input.useDelimiter(",");
			
			String universityName;
			int x = 0;
			while (input.hasNext()) {
				universityName = input.next().replaceAll("\n", "").replaceAll("\r", "");
				double [] tempArray = new double[5];
				for (int value = 0; value < 5; value++) {
					tempArray[value] = input.nextDouble();
				}
				universities[x].getPrograms().setClassSize(tempArray);
				/*
				for (int value = 0; value < 5; value++)
					System.out.println(universities[x].getPrograms().getClassSize()[value]);
				System.out.println();
				*/
				x++;
			}
			input.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
	}

}