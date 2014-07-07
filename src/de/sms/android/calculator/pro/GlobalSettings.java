package de.sms.android.calculator.pro;

public class GlobalSettings 
{
	//Fields
	private boolean isVibrationOn;
	private int vibrationDensityNumber;
	private int vibrationDensityResult;
	private int vibrationDensityError;
	private boolean disableStandby;
	
	
	//constructor
	public GlobalSettings()
	{
		isVibrationOn = true;
		vibrationDensityNumber = 50;
		vibrationDensityResult = 150;
		vibrationDensityError = 300;
		disableStandby = false;
	}
	
	
	//getters and setters
	public boolean isVibrationOn()
	{
		return isVibrationOn;
	}
	public void setVibrationOn(boolean isVibrationOn) 
	{
		this.isVibrationOn = isVibrationOn;
	}
	public int getVibrationDensityNumber() 
	{
		return vibrationDensityNumber;
	}
	public void setVibrationDensityNumber(int vibrationDensityNumber)
	{
		this.vibrationDensityNumber = vibrationDensityNumber;
	}
	public int getVibrationDensityResult() 
	{
		return vibrationDensityResult;
	}
	public void setVibrationDensityResult(int vibrationDensityResult) 
	{
		this.vibrationDensityResult = vibrationDensityResult;
	}
	public int getVibrationDensityError()
	{
		return vibrationDensityError;
	}
	public void setVibrationDensityError(int vibrationDensityError)
	{
		this.vibrationDensityError = vibrationDensityError;
	}
	public boolean isDisableStandby() 
	{
		return disableStandby;
	}
	public void setDisableStandby(boolean disableStandby) 
	{
		this.disableStandby = disableStandby;
	}
}
