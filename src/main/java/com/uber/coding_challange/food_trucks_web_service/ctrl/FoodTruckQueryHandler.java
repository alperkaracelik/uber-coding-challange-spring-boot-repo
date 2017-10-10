package com.uber.coding_challange.food_trucks_web_service.ctrl;

import java.util.ArrayList;
import java.util.List;

import com.uber.coding_challange.food_trucks_web_service.dataaccess.FoodTruckAccessor;
import com.uber.coding_challange.food_trucks_web_service.model.FoodTruck;
import com.uber.coding_challange.food_trucks_web_service.model.enums.DistanceUnitEnum;
import com.uber.coding_challange.food_trucks_web_service.model.enums.FoodTruckStatusEnum;

/**
 * The Query Handler class. Singleton pattern is used.
 * Rest Controller propagates the incoming queries to this class
 * 
 * @author alper.karacelik
 *
 */
public class FoodTruckQueryHandler 
{
	// SINGLETON Implementation ------------------------------------------
	private static FoodTruckQueryHandler INSTANCE = new FoodTruckQueryHandler();
	private FoodTruckQueryHandler() {}
	public static FoodTruckQueryHandler getInstance() {return INSTANCE;}
	// -------------------------------------------------------------------
	
	/**
	 * 
	 * Checks query inputs and 
	 * returns a list of food trucks that provides the given query conditions
	 * 
	 * @param statusStr Food Track status in String format
	 * @param latitudeStr Latitude of the center in String format
	 * @param longitudeStr Longitude of the center in String format
	 * @param radiusStr Radius of the circle in String format
	 * @param radiusUnitStr Radius unit in String format
	 * @return
	 */
	public List<FoodTruck> getFoodTrucksByQuery(
    		String statusStr,
            String latitudeStr,
            String longitudeStr,
            String radiusStr,
            String radiusUnitStr)
    {
    	// Query Results
    	List<FoodTruck> queryResult = null;
    	
    	// Food truck status
		FoodTruckStatusEnum statusEnum = FoodTruckStatusEnum.ALL;
		
    	// If status is specified
    	if (statusStr != null)
    	{
    		// Obtain the status
    		statusEnum = FoodTruckStatusEnum.getFromStringValue(statusStr);
    	}
    	
    	// Update the query results by querying on 'status'
		queryResult = FoodTruckAccessor.getInstance().getFoodTrucks(statusEnum);
    	
    	// If latitude and longitude is specified
    	if (latitudeStr != null && longitudeStr != null && radiusStr != null && radiusUnitStr != null)
    	{
    		try 
    		{
    			// Obtain the latitude, longitude, radius and radius unit
        		double latitude = Double.parseDouble(latitudeStr);
        		double longitude = Double.parseDouble(longitudeStr);
        		double radius = Double.parseDouble(radiusStr);
        		DistanceUnitEnum radiusUnit = DistanceUnitEnum.getFromStringValue(radiusUnitStr); 
        		
        		// Update the query results 
        		queryResult = 
        				getFoodTrucksInsideCircle(queryResult, latitude, longitude, radius, radiusUnit);
			} 
    		catch (Exception e) 
    		{
				e.printStackTrace();
			}
    	}
    	
    	// Return the query result
    	return queryResult;
    }
	
	/**
	 * Returns the food trucks that reside in the specified circle.
	 * Center of the circle: ['latitude', 'longitude'], 
	 * Radius of the circle: 'radius', Unit of the radius: 'radiusUnit'
	 * This method is synchronized on this class (Manipulation on storage elements is prevented.)
	 * 
	 * @param foodTrucks List that contains trucks that will be checked
	 * @param latitude Latitude of the center of the circle
	 * @param longitude Longitude of the center of the circle
	 * @param radius Radius of the circle
	 * @param radiusUnit Unit of the radius
	 * @return The food trucks that reside in the specified circle.
	 */
	public List<FoodTruck>getFoodTrucksInsideCircle(
			List<FoodTruck> foodTrucks, 
			double latitude, 
			double longitude, 
			double radius,
			DistanceUnitEnum radiusUnit)
	{
		synchronized (FoodTruckAccessor.class) 
		{
			// Initialize the result list
			List<FoodTruck> foodTrucksInsideCircle = new ArrayList<FoodTruck>();
			
			// Traverse through the food trucks
			for (FoodTruck foodTruck:foodTrucks)
			{
				// Calculate the distance between current food truck and the center of the circle
				double distance = 
						GeodesicDistanceCalculator.getInstance().distance(
								foodTruck.getLatitude(), foodTruck.getLongitude(),
								latitude, longitude, radiusUnit);
				
				// If distance is smaller than the radius,
				if (distance < radius)
				{
					// Then it is in the circle, add it to the result list.
					foodTrucksInsideCircle.add(foodTruck);
				}
			}
			
			// Return the resulting list.
			return foodTrucksInsideCircle;
		}
	}
}
