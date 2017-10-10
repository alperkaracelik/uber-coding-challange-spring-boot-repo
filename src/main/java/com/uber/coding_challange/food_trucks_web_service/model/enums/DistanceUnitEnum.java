package com.uber.coding_challange.food_trucks_web_service.model.enums;

/**
 * Distance unit enumeration that is used during distance calculations
 * Available units: Kilometers, Nautical Miles, Miles
 * 
 * @author alper.karacelik
 *
 */
public enum DistanceUnitEnum 
{
	NO_STATEMENT(0, ""),
	KILOMETERS(1, "km"),
	NAUTICAL_MILES(2, "nmi"),
	MILES(3, "mi");
	
	private int value;
	private String strValue;
	
	DistanceUnitEnum(int value, String strValue)
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
	public static DistanceUnitEnum getFromStringValue(String inpStrVal)
	{
		if (inpStrVal == null)
		{
			return NO_STATEMENT;
		}
		else
		{
			if (inpStrVal.compareToIgnoreCase(KILOMETERS.strValue) == 0)
			{
				return KILOMETERS;
			}
			else if (inpStrVal.compareToIgnoreCase(NAUTICAL_MILES.strValue) == 0)
			{
				return NAUTICAL_MILES;
			}
			else if (inpStrVal.compareToIgnoreCase(MILES.strValue) == 0)
			{
				return MILES;
			}
			else
			{
				return NO_STATEMENT;
			}
		}
	}
}
