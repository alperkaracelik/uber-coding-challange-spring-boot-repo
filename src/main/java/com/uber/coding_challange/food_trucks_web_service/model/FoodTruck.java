package com.uber.coding_challange.food_trucks_web_service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.uber.coding_challange.food_trucks_web_service.model.enums.FoodTruckStatusEnum;

/**
 * Food Truck class. 
 * This class is associated with both requested and provided Food Truck JSON objects
 * Jackson framework is used for JSON convertions
 * 
 * @author alper.karacelik
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class FoodTruck 
{	
	// Attributes --------------------------------------------------------
	private long objectid;
	private String address;
	private String locationdescription;
	private String applicant;
	private int cnn;
	private String dayshours;
	private String facilitytype;
	private String fooditems;
	private double latitude;
	private double longitude;
	private String permit;
	private String schedule;
	private String status;
	// -------------------------------------------------------------------
	
	// Constructor(s) ----------------------------------------------------
	public FoodTruck() 
	{
		super();
	}
	// -------------------------------------------------------------------
	
	// Getters & Setters -------------------------------------------------
	public long getObjectid() {
		return objectid;
	}
	public void setObjectid(long objectid) {
		this.objectid = objectid;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLocationdescription() {
		return locationdescription;
	}
	public void setLocationdescription(String locationdescription) {
		this.locationdescription = locationdescription;
	}
	public String getApplicant() {
		return applicant;
	}
	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}
	public int getCnn() {
		return cnn;
	}
	public void setCnn(int cnn) {
		this.cnn = cnn;
	}
	public String getDayshours() {
		return dayshours;
	}
	public void setDayshours(String dayshours) {
		this.dayshours = dayshours;
	}
	public String getFacilitytype() {
		return facilitytype;
	}
	public void setFacilitytype(String facilitytype) {
		this.facilitytype = facilitytype;
	}
	public String getFooditems() {
		return fooditems;
	}
	public void setFooditems(String fooditems) {
		this.fooditems = fooditems;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public String getPermit() {
		return permit;
	}
	public void setPermit(String permit) {
		this.permit = permit;
	}
	public String getSchedule() {
		return schedule;
	}
	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	// -------------------------------------------------------------------
	
	// Miscellaneous -----------------------------------------------------
	/**
	 * Returns the related Status enumeration
	 * @return The related Status enumeration
	 */
	public FoodTruckStatusEnum getStatusEnum()
	{
		return FoodTruckStatusEnum.getFromStringValue(status);
	}
	// -------------------------------------------------------------------
}
