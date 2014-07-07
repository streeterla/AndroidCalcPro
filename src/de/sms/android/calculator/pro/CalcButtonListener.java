package de.sms.android.calculator.pro;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * sets arithmetic operator into text field and deactivates the clicked button
 * @author streeter
 *
 */
public class CalcButtonListener extends ErrorResetListener implements OnClickListener {

	
	//fields
	private char arithmeticOperator;
	private TextView inputField;
	
	
	//constructor
	public CalcButtonListener(TextView inputField, Button button, TextView errorField)
	{
		super(errorField);
		this.arithmeticOperator = button.getText().charAt(0); 
		this.inputField = inputField;
	}
	
	
	/**
	 * sets arithmetic operator into text field and deactivates the clicked button
	 */
	public void onClick(View v)
	{
		super.onClick(v);
		if((MainActivity.globalSettings.isVibrationOn()))
			MainActivity.myVib.vibrate(MainActivity.globalSettings.getVibrationDensityNumber());
		CharSequence currentText = inputField.getText();
		inputField.setText(currentText.toString() + arithmeticOperator);
		for(Button calcButton : MainActivity.getCalcButtons())
		{
			calcButton.setEnabled(false);
		}
	}

}
