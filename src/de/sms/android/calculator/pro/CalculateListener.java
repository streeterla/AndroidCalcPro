package de.sms.android.calculator.pro;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.ExpressionBuilder;

public class CalculateListener implements OnClickListener
{
	//fields
	private TextView inputField;
	private Button addButton;
	private Button subButton;
	private Button multButton;
	private Button divButton;
	private TextView errorField;
	private CharSequence calculation;
	private double firstOperator;
	private double secondOperator;
	private double result;
	
	//constructor
	public CalculateListener(TextView inputField, Button addButton, Button subButton, Button multButton, Button divButton, TextView errorField)
	{
		this.inputField = inputField;
		this.addButton = addButton;
		this.subButton = subButton;
		this.multButton = multButton;
		this.divButton = divButton;
		this.errorField = errorField;
	}
	

	public void onClick(View v) 
	{
		if((MainActivity.globalSettings.isVibrationOn()))
			MainActivity.myVib.vibrate(MainActivity.globalSettings.getVibrationDensityResult());
		
		//enable buttons
		addButton.setEnabled(true);
		subButton.setEnabled(true);
		multButton.setEnabled(true);
		divButton.setEnabled(true);
		
		//get calculation
		calculation = inputField.getText();
		
		int operators = countOperators(calculation);
        
		if(operators >= 2)
		{
			Log.i("calculation", "parser");
	        try
	        {
	        	Calculable calc = new ExpressionBuilder(String.valueOf(calculation)).build();
	        	double result = calc.calculate();
		        inputField.setText(cutZero(String.valueOf(result)));
	        }
	        catch (Exception e) 
	        {
				MainActivity.myVib.vibrate(MainActivity.globalSettings.getVibrationDensityError());
				this.errorField.setText(R.string.errorCalc);
			} 
		}
		else
		{
			Log.i("calculation", "own");
			calculate("+");
			calculate("-");
			calculate("*");
			calculate("/");
		}
	}
	
	
	/**
	 * counts how much operators there are
	 * @param calculation - input calculation
	 * @return the number of operators
	 */
	private int countOperators(CharSequence calculation)
	{
		int counter = 0; 
		for(int i = 0; i < calculation.length(); i++)
		{
			char currentSign = calculation.charAt(i);
			if(currentSign == '+' || currentSign == '-' || currentSign == '*' || currentSign == '/' || currentSign == '(' || currentSign == ')')
			{
				counter++;
			}
		}
		Log.i("counter", String.valueOf(counter));
		return counter;
	}
	
	
	/**
	 * the real calculation
	 * @param arithmeticOperator to calculate
	 */
	private void calculate(String arithmeticOperator)
	{
		try
		{
			String calcStr = calculation.toString();
			if(calcStr.indexOf(arithmeticOperator) != -1)
			{
				firstOperator = Double.parseDouble(calcStr.split("\\" + arithmeticOperator)[0]);
				secondOperator = Double.parseDouble(calcStr.split("\\" + arithmeticOperator)[1]);
				
				switch(arithmeticOperator.charAt(0))
				{
				case '-':
					result = firstOperator - secondOperator;
					break;
				case '*':
					result = firstOperator * secondOperator;
					break;
				case '/':
					if(secondOperator == 0)
					{
						if((MainActivity.globalSettings.isVibrationOn()))
							MainActivity.myVib.vibrate(MainActivity.globalSettings.getVibrationDensityError());
						this.errorField.setText(R.string.errorCalc);
					}
					result = firstOperator / secondOperator;
					break;
				default:
					result = firstOperator + secondOperator;
				}
				
				inputField.setText(cutZero(result + ""));
			}
		}
		catch(Exception ex)
		{
			MainActivity.myVib.vibrate(MainActivity.globalSettings.getVibrationDensityError());
			this.errorField.setText(R.string.errorCalc);
		}
		
	}
	
	
	/**
	 * cuts all occurrences with .0 from the result
	 * @param doubleResult - result with .0 occurrences
	 * @return result without .0 occurrences
	 */
	private String cutZero(String doubleResult)
	{
//		Log.i("cutZero", doubleResult);
//		String[] resultArr = doubleResult.split("\\.0\b");
//		
//		String result = "";
//		
//		for(String tmp : resultArr)
//		{
//			result += tmp;
//		}
//		
//		return result;
		
		if(doubleResult.endsWith(".0"))
		{
			return doubleResult.substring(0, doubleResult.length()-2);
		}
		return doubleResult;
	}

}
