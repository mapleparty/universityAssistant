/*
 * Contributors: Ankit, Simran, Ryker
 * This class creates a program object that stores information about the program
 */
package universityProgram;

public class Program {

	public String name;										// Name of program
	public String[] preRequisites;							// List of preRequisites
	public double[] firstYearAverages = new double[7];		// Stores the B3 CUDO data
															// Index 0: 95%+ 	1: 90-94	 2:85-89 	3:80-84 	4:75-79 	5:70-74 	6:70-
	public double[] percentHappy = new double[4]; 			// Stores the F1a CUDO data
															// Index 0: Poor	 1: Fair	 2: Good	 3: Excellent
	public double[] wouldComeBack = new double[4]; 			// Stores the F1b CUDO data
															//Index 0: definitely yes, 1: probably yes, 2: probably no, 3: definitely no.
	public double[] classSize = new double[5];				// Stores the H2a CUDO data
															// Index 0: <30   1: 30-60    2: 61-100   3: 101-250    4:251+
	
	/**
	 * Constructor to only initialize program Name
	 * @param name
	 */
	public Program(String name) {
		this.name = name;
	}

	/**
	 * Constructor to initialize all parts of program
	 * @param name
	 * @param preRequisites
	 * @param firstYearAverages
	 * @param percentHappy
	 * @param wouldComeBack
	 */
	public Program(String name, String[] preRequisites, double[] firstYearAverages, double[] percentHappy, double[] wouldComeBack) {
		super();
		this.name = name;
		this.preRequisites = preRequisites;
		this.firstYearAverages = firstYearAverages;
		this.percentHappy = percentHappy;
	}

	
	/**
	 * Returns string of program name
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets programs name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns String array of program prerequisites
	 * @return
	 */
	public String[] getPreRequisites() {
		return preRequisites;
	}

	/**
	 * Sets the program's prerequisites
	 * @param preRequisites
	 */
	public void setPreRequisites(String[] preRequisites) {
		this.preRequisites = preRequisites;
	}

	/**
	 * Returns double array of B3 Cudo data
	 * @return
	 */
	public double[] getFirstYearAverages() {
		return firstYearAverages;
	}

	/**
	 * Sets the B3 Cudo data 
	 * @param firstYearAverages
	 */
	public void setFirstYearAverages(double[] firstYearAverages) {
		this.firstYearAverages = firstYearAverages;
	}

	/**
	 * Returns double array of F1a data
	 * @return
	 */
	public double[] getPercentHappy() {
		return percentHappy;
	}

	/**
	 * Sets the F1a data
	 * @param percentHappy
	 */
	public void setPercentHappy(double[] percentHappy) {
		this.percentHappy = percentHappy;
	}

	/**
	 * Returns double array of F1b data
	 * @return
	 */
	public double[] getWouldComeBack() {
		return wouldComeBack;
	}

	/**
	 * Sets the F1b data
	 * @param wouldComeBack
	 */
	public void setWouldComeBack(double[] wouldComeBack) {
		this.wouldComeBack = wouldComeBack;
	}
	
	/**
	 * Returns the H2a data
	 * @return
	 */
	public double[] getClassSize() {
		return classSize;
	}

	/**
	 * Sets the H2a data
	 * @param classSize
	 */
	public void setClassSize(double[] classSize) {
		this.classSize = classSize;
	}
}