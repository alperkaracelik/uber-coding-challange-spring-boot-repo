package com.uber.coding_challange.food_trucks_web_service.dataaccess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.uber.coding_challange.food_trucks_web_service.model.FoodTruck;
import com.uber.coding_challange.food_trucks_web_service.model.enums.FoodTruckStatusEnum;

/**
 * Storage accessor class. Singleton pattern is used.
 * Allows to add/update/delete food trucks.
 * Maintains a food truck status -> food truck list map for faster query results.
 * 
 * @author alper.karacelik
 *
 */
public class FoodTruckAccessor 
{
	// Attributes --------------------------------------------------------
	private static HashMap<Long, FoodTruck> foodTruckMap;
	private static HashMap<FoodTruckStatusEnum, List<FoodTruck>> statusMap;
	// -------------------------------------------------------------------
	
	// SINGLETON Implementation ------------------------------------------
	private static FoodTruckAccessor INSTANCE = new FoodTruckAccessor();
	private FoodTruckAccessor() {initialize();}
	public static FoodTruckAccessor getInstance() {return INSTANCE;}
	// -------------------------------------------------------------------
	
	/**
	 * Initializes the mappings
	 */
	private static void initialize()
	{
		foodTruckMap = new HashMap<Long, FoodTruck>();
		statusMap = new HashMap<FoodTruckStatusEnum, List<FoodTruck>>();
	}
	
	/**
	 * Returns all the food trucks in the storage
	 * This method is synchronized on this class (Manipulation on storage elements is prevented.)
	 * 
	 * @return All the food trucks in the storage
	 */
	public List<FoodTruck> getAllFoodTrucks()
	{
		synchronized (FoodTruckAccessor.class) 
		{
			return new ArrayList<FoodTruck>(foodTruckMap.values());
		}
	}
	
	/**
	 * Returns the food trucks that have the specified status.
	 * If the specified status is ALL, then all food trucks are returned.
	 * If the specified status is NO_STATEMENT, then an empty list is returned.
	 * This method is synchronized on this class (Manipulation on storage elements is prevented.)
	 * 
	 * @param status Food Truck Status for querying
	 * @return The food trucks which have the specified status
	 */
	public List<FoodTruck>getFoodTrucks(FoodTruckStatusEnum status)
	{
		synchronized (FoodTruckAccessor.class) 
		{
			if (status == FoodTruckStatusEnum.ALL)
			{
				return getAllFoodTrucks();
			}
			else if (status == FoodTruckStatusEnum.NO_STATEMENT)
			{
				return new ArrayList<FoodTruck>();
			}
			else
			{
				return statusMap.get(status);
			}
		}
	}
	
	/**
	 * Returns true if a food truck with given id exist, false otherwise
	 * This method is synchronized on this class (Manipulation on storage elements is prevented.)
	 * 
	 * @param id Object id of a food truck
	 * @return True if a food truck with given id exist, false otherwise
	 */
	public boolean foodTruckExist(long id)
	{
		synchronized (FoodTruckAccessor.class) 
		{
			return foodTruckMap.containsKey(id);
		}
	}
	
	/**
	 * Returns the food truck with the given id
	 * This method is synchronized on this class (Manipulation on storage elements is prevented.)
	 * 
	 * @param id Object id of a food truck
	 * @return The food truck with the given id
	 */
	public FoodTruck getFoodTruck(long id)
	{
		synchronized (FoodTruckAccessor.class) 
		{
			return foodTruckMap.get(id);
		}
	}
	
	/**
	 * Adds the given food truck to the storage
	 * Also populates the status map
	 * This method is synchronized on this class (Manipulation on storage elements is prevented.)
	 * 
	 * @param foodTruck new food truck
	 */
	public void addFoodTruck(FoodTruck foodTruck)
	{
		synchronized (FoodTruckAccessor.class) 
		{
			foodTruckMap.put(foodTruck.getObjectid(), foodTruck);
			addToStatusMap(foodTruck);
		}
	}

	/**
	 * Updates the given food truck. 
	 * If there no food truck with the object id of the given food truck, then does nothing.
	 * Also update the status map if the status is changed.
	 * This method is synchronized on this class (Manipulation on storage elements is prevented.)
	 * 
	 * @param foodTruck updated food truck
	 */
	public void updateFoodTruck(FoodTruck foodTruck)
	{
		synchronized (FoodTruckAccessor.class) 
		{
			if (foodTruckMap.containsKey(foodTruck.getObjectid()))
			{
				// Update the status map first.
				updateStatusChange(foodTruck);
				foodTruckMap.put(foodTruck.getObjectid(), foodTruck);
			}
		}
	}
	
	/**
	 * Removes and returns the food truck with the given id.
	 * Also updates the status map
	 * This method is synchronized on this class (Manipulation on storage elements is prevented.)
	 * 
	 * @param id
	 * @return The removed food truck
	 */
	public FoodTruck removeFoodTruck(long id)
	{
		synchronized (FoodTruckAccessor.class) 
		{
			removeFromStatusMap(foodTruckMap.get(id));
			return foodTruckMap.remove(id);
		}
	}
	
	/**
	 * Adds the given food truck to the related list in the status map.
	 * Example: If the status of the given food truck is 'REQUESTED';
	 * then, the given truck is added to the list of the 'REQUESTED' map item.
	 * This method is synchronized on this class (Manipulation on storage elements is prevented.)
	 * 
	 * @param foodTruck new food truck
	 */
	private void addToStatusMap(FoodTruck foodTruck)
	{
		synchronized (FoodTruckAccessor.class) 
		{
			FoodTruckStatusEnum status = foodTruck.getStatusEnum();
			List<FoodTruck> foodTrucks;
			
			// If an existing entry will be updated.
			if (statusMap.containsKey(status))
			{
				foodTrucks = statusMap.get(status);
			}
			// If a new entry is created.
			else
			{
				foodTrucks = new ArrayList<FoodTruck>();
				statusMap.put(status, foodTrucks);
			}
			
			foodTrucks.add(foodTruck);
		}
	}
	
	/**
	 * Updates the status map if the status of the given food truck is changed.
	 * This method is synchronized on this class (Manipulation on storage elements is prevented.)
	 * 
	 * @param updatedFoodTruck Updated food truck
	 */
	private void updateStatusChange(FoodTruck updatedFoodTruck)
	{
		synchronized (FoodTruckAccessor.class) 
		{
			// Continue only if the given food truck already exist
			if (foodTruckMap.containsKey(updatedFoodTruck.getObjectid()))
			{
				FoodTruck existingFoodTruck = foodTruckMap.get(updatedFoodTruck.getObjectid());
				
				// Check if the status is changed.
				if (existingFoodTruck.getStatusEnum() != updatedFoodTruck.getStatusEnum())
				{
					// Remove the food truck from the map of previous status
					removeFromStatusMap(existingFoodTruck);
					// Add the food truck to the map of current status
					addToStatusMap(updatedFoodTruck);
				}
			}
		}
	}
	
	/**
	 * Updates the status map by removing the given food truack from the related status map item.
	 * Example: If the status of the given food truck is 'REQUESTED';
	 * then, the given truck is from the list of the 'REQUESTED' map item.
	 * This method is synchronized on this class (Manipulation on storage elements is prevented.)
	 * 
	 * @param foodTruck Removed food truck
	 */
	private void removeFromStatusMap(FoodTruck foodTruck)
	{
		synchronized (FoodTruckAccessor.class) 
		{
			// Continue only if the given food truck already exist
			if (foodTruckMap.containsKey(foodTruck.getObjectid()))
			{
				if (statusMap.containsKey(foodTruck.getStatusEnum()))
				{
					statusMap.get(foodTruck.getStatusEnum()).remove(foodTruck);
				}
			}
		}
	}
}
