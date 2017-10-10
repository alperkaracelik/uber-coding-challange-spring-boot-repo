package com.uber.coding_challange.food_trucks_web_service;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uber.coding_challange.food_trucks_web_service.ctrl.FoodTruckQueryHandler;
import com.uber.coding_challange.food_trucks_web_service.dataaccess.FoodTruckAccessor;
import com.uber.coding_challange.food_trucks_web_service.model.FoodTruck;

/**
 * This class provides the entry point for the web service by providing two different GET methods.
 * 1) getFoodTrucks(): Returns all the food trucks.
 * 2) getFoodTrucksByQuery(): Returns the food trucks that provides the given query conditions.
 * 
 * @author alper.karacelik
 *
 */
@RestController
@RequestMapping(value= "/food-trucks") //Root resource (exposed at "/food-trucks" path)
public class FoodTruckRestController 
{
    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "application/json" media type.
     *
     * @return Food Trucks in JSON format
     */
	@CrossOrigin(origins = "https://foodtruckradar.herokuapp.com/")
    @RequestMapping(
            method = RequestMethod.GET,
            produces = "application/json")
    public List<FoodTruck> getFoodTrucks() 
    {
    	return FoodTruckAccessor.getInstance().getAllFoodTrucks();
    }
    
    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "application/json" media type.
     *
     * @return Food Trucks in JSON format
     */
	@CrossOrigin(origins = "https://foodtruckradar.herokuapp.com/")
    @RequestMapping(
    		value = "/query",
            method = RequestMethod.GET,
            produces = "application/json")
    public List<FoodTruck> getFoodTrucksByQuery(
    		@RequestParam(value = "status", required = false) String status,
    		@RequestParam(value = "latitude", required = false) String latitude,
    		@RequestParam(value = "longitude", required = false) String longitude,
    		@RequestParam(value = "radius", required = false) String radius,
    		@RequestParam(value = "radius_unit", required = false) String radius_unit)
    {
    	return FoodTruckQueryHandler.getInstance().getFoodTrucksByQuery(
    			status, latitude, longitude, radius, radius_unit);
    }
}
