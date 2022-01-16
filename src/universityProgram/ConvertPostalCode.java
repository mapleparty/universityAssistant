/**
 * Contributor: Ryker
 * 
 * The class ConveryPostalCode uses web scraping feature to retrieve longitude/latitude of a postal code from a website
 * 
 * Website used: https://geocoder.ca
 */

package universityProgram;

import java.net.*;
import java.io.*;

public class ConvertPostalCode {

	private String link;
	private static FileInputStream input;

	/**
	 * Constructor that creates a new ConvertPostal Code Object. The link will be
	 * generated.
	 * 
	 * @param postalCode
	 */
	public ConvertPostalCode(String postalCode) {
		// Generate the web link
		link = "https://geocoder.ca/?locate=" + postalCode + "&geoit=GeoCode";

		// Read the web page
		readWebPage();
	}

	/**
	 * Read the data from the website and stores it into a text file. Some online
	 * resources are used to help me write this method.
	 */

	private void readWebPage() {

		int chunksize = 4096;
		byte[] chunk = new byte[chunksize];
		int count;

		try {
			// Generate the URL
			URL pageURL = new URL(link);

			// BufferedInputStream and BufferedOutputStream
			BufferedInputStream input = new BufferedInputStream(pageURL.openStream());
			BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream("PostalCodeFile.txt", false));

			// Read the website and write it into the text file
			while ((count = input.read(chunk, 0, chunksize)) != -1) {
				output.write(chunk, 0, count);
			}

			// Close BufferedInputStream and BufferedOutputStream
			input.close();
			output.close();

		} catch (IOException e) {
			System.out.println("NO INTERNET CONNECTION!");
		}
	}

	/**
	 * Return the latitude of the postal code. Otherwise, return -1.
	 * 
	 * @return
	 * @throws IOException
	 */
	public static double getLatitude() throws IOException {

		try {
			// Use the text file
			input = new FileInputStream("PostalCodeFile.txt");

			// BufferedReader
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));

			// Skip through line 1 to line 20
			for (int i = 0; i < 20; i++) {
				reader.readLine();
			}

			// Retrieve line 21
			String titleLine = reader.readLine();

			// Retrieve line 21 word index 22 to 29
			String latitudeString = titleLine.substring(21, 29);

			// Cast string to double
			double latitude = Double.parseDouble(latitudeString);

			// Return the latitude
			return latitude;

			// If file is not found
		} catch (FileNotFoundException e) {

			// If user entered the wrong postal code
		} catch (StringIndexOutOfBoundsException k) {

			// Line 21 will not be read properly and therefore the substring method will not
			// be working
		}

		// Return -1 if no latitude retrieved
		return -1;
	}

	/**
	 * Return the longitude of the postal code. Otherwise, return -1.
	 * 
	 * @return
	 * @throws IOException
	 */
	public static double getLongitude() throws IOException {

		try {
			// Use the text file
			input = new FileInputStream("PostalCodeFile.txt");

			// BufferedReader
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));

			// Skip through line 1 to line 20
			for (int i = 0; i < 20; i++) {
				reader.readLine();
			}

			// Retrieve line 21
			String titleLine = reader.readLine();

			// Retrieve line 21 word index 32 to 40
			String latitudeString = titleLine.substring(31, 40);

			// Cast string to double
			double latitude = Double.parseDouble(latitudeString);

			// Return the latitude
			return latitude;

			// If file is not found
		} catch (FileNotFoundException e) {

			// If user entered the wrong postal code
		} catch (StringIndexOutOfBoundsException k) {

			// Line 21 will not be read properly and therefore the substring method will not
			// be working
		}

		// Return -1 if no longitude retrieved
		return -1;
	}

}
