package com.uber.coding_challange.food_trucks_web_service.model.enums;

/**
 * Food Truck Status enumeration.
 * NO_STATEMENT: None of the enumerations
 * ALL: All of the enumerations (except NO STATEMENT)
 * 
 * @author alper.karacelik
 *
 */
public enum FoodTruckStatusEnum 
{
	NO_STATEMENT(0, "NO STATEMENT"),
	REQUESTED(1, "REQUESTED"),
	APPROVED(2, "APPROVED"),
	INACTIVE(3, "INACTIVE"),
	SUSPEND(4, "SUSPEND"),
	EXPIRED(5, "EXPIRED"),
	ONHOLD(6, "ONHOLD"),
	ALL(7, "ALL");
	
	private int value;
	private String strValue;
	
	FoodTruckStatusEnum(int value, String strValue)
	{
		this.value = value;
		this.strValue = strValue;
	}
	
	public int getValue()
	{
		return value;
	}
	
	public String getStrValue()
	{
		return strValue;
	}
	
	/**
	 * Returns the enumeration value that has the same 'strValue' with the given 'inpStrVal'
	 * 
	 * @param inpStrVal
	 * @return
	 */
	public static FoodTruckStatusEnum getFromStringValue(String inpStrVal)
	{
		if (inpStrVal == null)
		{
			return NO_STATEMENT;
		}
		else
		{
			if (inpStrVal.compareToIgnoreCase(REQUESTED.strValue) == 0)
			{
				return REQUESTED;
			}
			else if (inpStrVal.compareToIgnoreCase(APPROVED.strValue) == 0)
			{
				return APPROVED;
			}
			else if (inpStrVal.compareToIgnoreCase(INACTIVE.strValue) == 0)
			{
				return INACTIVE;
			}
			else if (inpStrVal.compareToIgnoreCase(SUSPEND.strValue) == 0)
			{
				return SUSPEND;
			}
			else if (inpStrVal.compareToIgnoreCase(EXPIRED.strValue) == 0)
			{
				return EXPIRED;
			}
			else if (inpStrVal.compareToIgnoreCase(ONHOLD.strValue) == 0)
			{
				return ONHOLD;
			}
			else if (inpStrVal.compareToIgnoreCase(ALL.strValue) == 0)
			{
				return ALL;
			}
			else
			{
				return NO_STATEMENT;
			}
		}
	}
}
