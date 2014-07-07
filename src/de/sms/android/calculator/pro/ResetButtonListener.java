package de.sms.android.calculator.pro;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * listener to reset calculators input field
 * @author streeter
 *
 */
public class ResetButtonListener implements OnClickListener
{

	/** fields */
	private TextView inputField;
	private TextView errorField;
	private Button addButton;
	private Button subButton;
	private Button multButton;
	private Button divButton;
	
	
	/** constructor */
	public ResetButtonListener(TextView inputField, Button addButton, Button subButton, Button multButton, Button divButton, TextView errorField)
	{
		this.inputField = inputField;
		this.addButton = addButton;
		this.subButton = subButton;
		this.multButton = multButton;
		this.divButton = divButton;
		this.errorField = errorField;
	}

	
	/**
	 * function to reset calculators input field
	 */
	public void onClick(View v)
	{
		if((MainActivity.globalSettings.isVibrationOn()))
			MainActivity.myVib.vibrate(MainActivity.globalSettings.getVibrationDensityNumber());
		inputField.setText("");
		errorField.setText("");
		
		//enable buttons
		addButton.setClickable(true);
		subButton.setClickable(true);
		multButton.setClickable(true);
		divButton.setClickable(true);
	}

}
