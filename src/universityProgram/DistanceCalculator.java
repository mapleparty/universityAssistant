/**
 * Contributor: Ryker
 * 
 * The class DistanceCalculator calculates the distance between two locations with their latitude and longitude
 */

package universityProgram;

import java.lang.Math;

public class DistanceCalculator {
	
	private static double latitudeDegree; //The double variable that stores the user's latitude
	private static double longitudeDegree; //The double variable that stores the user's longitude
	
	final static int EARTH_RADIUS=6371; //Final integer variable representing the radius of the Earth
	
	/**
	 * Constructor that initializes a new DistanceCalculator object
	 * @param latitude
	 * @param longtitude
	 */
	public DistanceCalculator(double latitude, double longtitude) {
		latitudeDegree=latitude;
		longitudeDegree=longtitude;
	}
	
	/**
	 * Setter for the user's location. Set the value of the user's latitude and longitude
	 * @param latitude
	 * @param longitude
	 */
	public static void setLocation(double latitude, double longitude) {
		latitudeDegree=latitude;
		longitudeDegree=longitude;
	}
	
	/**
	 * The method calculating the distance between the selected university and the user
	 * Return the distance between them
	 * @param uni
	 * @return
	 */
	
	public static double getDistance(University uni) {
		
		//Retrieve latitude/longitude of the selected university
		double uniLatitudeDegree=uni.getLatitude();
		double uniLongitudeDegree=uni.getLongitude();
		
		//Convert each arguments from degree to radian
		double latitudeRadian=Math.toRadians(latitudeDegree);
		double longitudeRadian=Math.toRadians(longitudeDegree);
		double uniLatitudeRadian=Math.toRadians(uniLatitudeDegree);
		double uniLongitudeRadian=Math.toRadians(uniLongitudeDegree);
		
		//Calculate the distance using the Great Circle Distance Formula
		double distance=EARTH_RADIUS*Math.acos(Math.sin(uniLatitudeRadian)*Math.sin(latitudeRadian)+Math.cos(uniLatitudeRadian)*Math.cos(latitudeRadian)*Math.cos(uniLongitudeRadian-longitudeRadian));
		
		return distance;
	}
	

	
}
