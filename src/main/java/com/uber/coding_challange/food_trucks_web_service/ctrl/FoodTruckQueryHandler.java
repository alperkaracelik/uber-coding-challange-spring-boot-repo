package com.uber.coding_challange.food_trucks_web_service.ctrl;

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
        				FoodTruckAccessor.getInstance().getFoodTrucksInsideCircle(
        						queryResult, latitude, longitude, radius, radiusUnit);
			} 
    		catch (Exception e) 
    		{
				e.printStackTrace();
			}
    	}
    	
    	// Return the query result
    	return queryResult;
    }
}
