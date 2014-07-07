package de.sms.android.calculator.pro;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

/**
 * resets the error field
 * @author streeter
 *
 */
public class ErrorResetListener implements OnClickListener
{
	//field
	private TextView errorField;
	
	
	//constructors
	public ErrorResetListener()
	{
		
	}
	
	
	public ErrorResetListener(TextView errorField)
	{
		this.errorField = errorField;
	}
	
	
	//action
	public void onClick(View v)
	{
		this.errorField.setText("");
	}

}
