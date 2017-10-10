package com.uber.coding_challange.food_trucks_web_service.ctrl;

import com.uber.coding_challange.food_trucks_web_service.model.enums.DistanceUnitEnum;

/**
 * 
 * @author alper.karacelik
 * Original source: http://www.geodatasource.com/developers/java
 */
public class GeodesicDistanceCalculator 
{
	// Constants ---------------------------------------------------------
	// -------------------------------------------------------------------
	
	// Attributes --------------------------------------------------------
	// -------------------------------------------------------------------
	
	// SINGLETON Implementation ------------------------------------------
	private static GeodesicDistanceCalculator INSTANCE = new GeodesicDistanceCalculator();
	private GeodesicDistanceCalculator() {}
	public static GeodesicDistanceCalculator getInstance() {return INSTANCE;}
	// -------------------------------------------------------------------
	
	/**
	 * Calculates the distance between given two positions 
	 * and returns it in the given unit format.
	 * 
	 * @param lat1 Latitude of first position (in decimal degrees)
	 * @param lon1 Longitude of first position (in decimal degrees)
	 * @param lat2 Latitude of second position (in decimal degrees)
	 * @param lon2 Longitude of second position (in decimal degrees)
	 * @param unit Distance unit
	 * @return Distance between given two positions
	 */
	public double distance(double lat1, double lon1, double lat2, double lon2, DistanceUnitEnum unit) 
	{
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) 
					  + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		
		if (unit == DistanceUnitEnum.KILOMETERS) 
		{
			dist = dist * 1.609344;
		} 
		else if (unit == DistanceUnitEnum.NAUTICAL_MILES) 
		{
			dist = dist * 0.8684;
		}

		return (dist);
	}
	
	/**
	 * Converts decimal degree to radian
	 * 
	 * @param deg Degree
	 * @return Radian equivalent of given degree.
	 */
	private static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	/**
	 * Converts radian to decimal degree  
	 * 
	 * @param rad Radian
	 * @return Degree equivalent of the given radian.
	 */
	private static double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}
}
