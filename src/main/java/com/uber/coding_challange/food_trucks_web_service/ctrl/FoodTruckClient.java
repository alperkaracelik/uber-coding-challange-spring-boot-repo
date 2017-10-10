package com.uber.coding_challange.food_trucks_web_service.ctrl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uber.coding_challange.food_trucks_web_service.dataaccess.FoodTruckAccessor;
import com.uber.coding_challange.food_trucks_web_service.model.FoodTruck;

/**
 * The client class. Singleton pattern is used. 
 * Responsible for establishing a connection with DataSF API and obtaining the food truck data. 
 * It is initialized only once (with lazy initialization); 
 * therefore, the connection is established only once.
 * 
 * @author alper.karacelik
 *
 */
public class FoodTruckClient 
{
	// Constants ---------------------------------------------------------
	private static final String DATASF_URL = "https://data.sfgov.org/resource/6a9r-agq8.json";
	// -------------------------------------------------------------------
	
	// Attributes --------------------------------------------------------
	private static boolean initialized = false;
	// -------------------------------------------------------------------
	
	// SINGLETON Implementation ------------------------------------------
	private static FoodTruckClient INSTANCE = new FoodTruckClient();
	private FoodTruckClient() {}
	public static FoodTruckClient getInstance() {return INSTANCE;}
	// -------------------------------------------------------------------
	
	/**
	 * Uses the public API provided by DataSF, 
	 * Obtains all the food truck data
	 * Pushes the obtained data to the Food Truck Accessor (the storage handler)
	 * Uses lazy initialization. 
	 * This method is called after the first request to the web service.
	 * Other calls will simply be ignored.
	 * No two different threads can access this method at the same time.
	 */
	public void initialize()
	{
		System.out.println("Initializing Food Truck Data...");
		synchronized (FoodTruckAccessor.class) 
		{
			// Continue if not already initialized.
			if (! initialized)
			{
				try 
				{
					URL url = new URL(DATASF_URL);
					ObjectMapper jsonMapper = new ObjectMapper();
					
					// Obtain the food trucks
					List<FoodTruck> foodTruckList = 
							jsonMapper.readValue(url, new TypeReference<List<FoodTruck>>(){});
					
					// Add the received food trucks to our storage
					for (FoodTruck foodTruck : foodTruckList)
					{
						FoodTruckAccessor.getInstance().addFoodTruck(foodTruck);
					}
					
					// set initialized flag true
					initialized = true;
				} 
				catch (MalformedURLException e) 
				{
					e.printStackTrace();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
		}
		
		System.out.println("Food Truck Data Inizialization is Finished!");
	}
}
