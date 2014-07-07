package de.sms.android.calculator.pro;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * listener to set number of the button into input field
 * @author streeter
 *
 */
public class NumberClickListener implements OnClickListener {
	
	
	/** number to set */
	private String number;
	/** input field */
	private TextView tv;
	
	
	/** constructor */
	public NumberClickListener(String number, TextView tv)
	{
		this.number = number;
		this.tv = tv;
	}

	
	/**
	 * function to set number of the button into input field
	 */
	public void onClick(View v)
	{
		if(MainActivity.globalSettings.isVibrationOn())
			MainActivity.myVib.vibrate(MainActivity.globalSettings.getVibrationDensityNumber());
		CharSequence currentText =  tv.getText();
		char numberChar = ("" + number).charAt(0);
		String newText = currentText.toString() + numberChar;
		tv.setText(newText);
		for(Button calcButton : MainActivity.getCalcButtons())
		{
			calcButton.setEnabled(true);
		}
	}

}
