package de.sms.android.calculator.pro;

import de.sms.android.calculator.pro.NumberClickListener;
import de.sms.android.calculator.pro.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	//fields
	private Button button0;
	private Button button1;
	private Button button2;
	private Button button3;
	private Button button4;
	private Button button5;
	private Button button6;
	private Button button7;
	private Button button8;
	private Button button9;
	private Button buttonOk;
	private Button buttonAdd;
	private Button buttonSub;
	private Button buttonMult;
	private Button buttonDiv;
	private Button buttonReset;
	private Button buttonCancel;
	private Button buttonDot;
	private Button buttonOpenBrace;
	private Button buttonCloseBrace;
	private TextView inputField;
	private TextView errorField;
	private static Button[] numberButtons = new Button[10];
	private static Button[] calcButtons = new Button[4];
	public static Vibrator myVib;
	public static GlobalSettings globalSettings = new GlobalSettings();
	

    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //check if standby should be disabled e.g. for long calculations
        if(globalSettings.isDisableStandby())
        	getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        
        //haptic feedbac
        myVib = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);
        
        //set gui elements
        setGui();
        
        //set function for numbers
        for(int i=0; i<10; i++)
        {
        	setNumButtons(i);
        }
        
        //set function for calculate buttons
        calcButtons[0] = buttonAdd;
        calcButtons[1] = buttonSub;
        calcButtons[2] = buttonMult;
        calcButtons[3] = buttonDiv;
        for(Button calcButton : calcButtons)
        {
        	calcButton.setOnClickListener(new CalcButtonListener(this.inputField, calcButton, this.errorField));
        }
        
        //set function to calculate
        buttonOk.setOnClickListener(new CalculateListener(this.inputField, this.buttonAdd, this.buttonSub, this.buttonMult, this.buttonDiv, this.errorField));

        //set function for reset button
        buttonReset.setOnClickListener(new ResetButtonListener(this.inputField, this.buttonAdd, this.buttonSub, this.buttonMult, this.buttonDiv, this.errorField));
        
        //set function to delete last token
        buttonCancel.setOnClickListener(new CancelButtonListener(this.inputField));
        
        //set function for dot button
        buttonDot.setOnClickListener(new NumberClickListener(".", this.inputField));
        
        //set function for braces
        buttonOpenBrace.setOnClickListener((new NumberClickListener("(", this.inputField)));
        buttonCloseBrace.setOnClickListener((new NumberClickListener(")", this.inputField)));
       
    }
 
    @Override
    public boolean onCreateOptionsMenu(Menu menu) 
    {
    	//settings
        getMenuInflater().inflate(R.menu.activity_main, menu);
        MenuItem settingsItem = menu.getItem(0);
        Intent preferencesIntent = new Intent(this, Preferences.class);
        settingsItem.setIntent(preferencesIntent);
        
        //exit
        MenuItem exitItem = menu.getItem(1);
        exitItem.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			public boolean onMenuItemClick(MenuItem item) {
				finish();
				return false;
			}
		});
        return true;
    }
    
    
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) 
    {
    	//settings
        getMenuInflater().inflate(R.menu.activity_main, menu);
        MenuItem settingsItem = menu.getItem(0);
        Intent preferencesIntent = new Intent(this, Preferences.class);
        settingsItem.setIntent(preferencesIntent);
        
        //exit
        MenuItem exitItem = menu.getItem(1);
        exitItem.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			public boolean onMenuItemClick(MenuItem item) {
				finish();
				return false;
			}
		});
    }
    
    
    /**
     * sets GUI elements and put the number buttons in an array
     */
    private void setGui()
    {
    	this.button0 = (Button) findViewById(R.id.buttonZero);
    	this.button1 = (Button) findViewById(R.id.buttonOne);
    	this.button2 = (Button) findViewById(R.id.buttonTwo);
    	this.button3 = (Button) findViewById(R.id.buttonThree);
    	this.button4 = (Button) findViewById(R.id.buttonFour);
    	this.button5 = (Button) findViewById(R.id.buttonFive);
    	this.button6 = (Button) findViewById(R.id.buttonSix);
    	this.button7 = (Button) findViewById(R.id.buttonSeven);
    	this.button8 = (Button) findViewById(R.id.buttonEight);
    	this.button9 = (Button) findViewById(R.id.buttonNine);
    	this.buttonOk = (Button) findViewById(R.id.buttonOk);
    	this.buttonAdd = (Button) findViewById(R.id.buttonAdd);
    	this.buttonSub = (Button) findViewById(R.id.buttonSub);
    	this.buttonMult = (Button) findViewById(R.id.buttonMult);
    	this.buttonDiv = (Button) findViewById(R.id.buttonDiv);
    	this.inputField = (TextView) findViewById(R.id.inputfield);
    	this.errorField = (TextView) findViewById(R.id.errorField);
    	this.buttonReset = (Button) findViewById(R.id.buttonReset);
    	this.buttonCancel = (Button) findViewById(R.id.buttonCancel);
    	this.buttonDot = (Button) findViewById(R.id.buttonDot);
    	this.buttonOpenBrace = (Button) findViewById(R.id.buttonOpenBrace);
    	this.buttonCloseBrace = (Button) findViewById(R.id.buttonCloseBrace);
    	View completeLayout = findViewById(R.id.completelayout);
    	
    	numberButtons[0] = button0;
    	numberButtons[1] = button1;
    	numberButtons[2] = button2;
    	numberButtons[3] = button3;
    	numberButtons[4] = button4;
    	numberButtons[5] = button5;
    	numberButtons[6] = button6;
    	numberButtons[7] = button7;
    	numberButtons[8] = button8;
    	numberButtons[9] = button9;
    	
    	inputField.setClickable(false);
    	registerForContextMenu(completeLayout);
    }
    
    
    /**
     * set click listener to specified button
     * @param number number to set
     */
    private void setNumButtons(int number)
    {
    	String numberString = String.valueOf(number);
    	numberButtons[number].setOnClickListener(new NumberClickListener(numberString, this.inputField));
    }
    
    
    
    //getter and setter
    public static Button[] getCalcButtons()
    {
    	return calcButtons;
    }
    
    
    public static Button[] getNumberButtons()
    {
    	return numberButtons;
    }
}
