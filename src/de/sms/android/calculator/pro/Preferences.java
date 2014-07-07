package de.sms.android.calculator.pro;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;
import android.view.Menu;
import android.view.MenuItem;

public class Preferences extends PreferenceActivity
{
	//fields
	//preferences
	private SharedPreferences sharedPrefs;
	//whether to vibrate or not checkbox
	private Preference shouldVibrateCheckbox;
	//to choose whether to vibrate or not
	private boolean shouldVibrate = true;
	//vibration density for number buttons
	private Preference vibDensityNumber;
	//vibration density for result button
	private Preference vibDensityResult;
	//vibration density if an error occurs
	private Preference vibDensityError;
	//disable standby for longer calculations
	private SwitchPreference disableStandby;
	
    /* (non-Javadoc)
     * @see android.preference.PreferenceActivity#onCreate(android.os.Bundle)
     * 
     * deprecation due to API older than 11 (Honeycomb) as minSDK
     */
    @SuppressWarnings("deprecation") 
	@Override
    public void onCreate(Bundle savedInstanceState) 
    {
    	super.onCreate(savedInstanceState);
    	addPreferencesFromResource(R.xml.preferences);
    	
    	//get elements
    	sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
    	shouldVibrateCheckbox = findPreference("pref_enableVibration");
    	vibDensityNumber = findPreference("pref_vib_density_number");
    	vibDensityResult = findPreference("pref_vib_density_result");
    	vibDensityError = findPreference("pref_vib_density_error");
    	shouldVibrate = sharedPrefs.getBoolean("pref_enableVibration", true);
    	disableStandby = (SwitchPreference) findPreference("pref_disable_standby_switch");
		enableSlider(shouldVibrate);
    	
    	//set listener for checkbox
    	shouldVibrateCheckbox.setOnPreferenceClickListener(new OnPreferenceClickListener()
    	{
			
			public boolean onPreferenceClick(Preference preference) 
			{
				shouldVibrate = sharedPrefs.getBoolean("pref_enableVibration", true);
				enableSlider(shouldVibrate);
				return false;
			}
		});
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        menu.add(Menu.NONE, 0, 0, "Show current settings");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case 0:
                startActivity(new Intent(this, MainActivity.class));
                return true;
        }
        return false;
    }
    
    @Override
    public void onBackPressed()
    {
    	super.onBackPressed();
    	
    	//should vibrate?
        sharedPrefs.edit().putBoolean("pref_enableVibration", sharedPrefs.getBoolean("pref_enableVibration", true)).commit();
        shouldVibrate = sharedPrefs.getBoolean("pref_enableVibration", true);
    	MainActivity.globalSettings.setVibrationOn(shouldVibrate);
    	
    	//how long?
    	sharedPrefs.edit().putInt("pref_vib_density_number", sharedPrefs.getInt("pref_vib_density_number", 50)).commit();
        int densityNumber = sharedPrefs.getInt("pref_vib_density_number", 50);
    	MainActivity.globalSettings.setVibrationDensityNumber(densityNumber);
    	
    	sharedPrefs.edit().putInt("pref_vib_density_result", sharedPrefs.getInt("pref_vib_density_result", 150)).commit();
        int densityResult = sharedPrefs.getInt("pref_vib_density_result", 150);
    	MainActivity.globalSettings.setVibrationDensityResult(densityResult);
    	
    	sharedPrefs.edit().putInt("pref_vib_density_error", sharedPrefs.getInt("pref_vib_density_error", 300)).commit();
        int densityError = sharedPrefs.getInt("pref_vib_density_error", 300);
    	MainActivity.globalSettings.setVibrationDensityError(densityError);
    	
    	//disable standby for longer calculations
    	sharedPrefs.edit().putBoolean("pref_disable_standby_switch", sharedPrefs.getBoolean("pref_disable_standby_switch", false)).commit();
    	MainActivity.globalSettings.setDisableStandby(disableStandby.isChecked());
    }
    
    
    private void enableSlider(boolean shouldEnable)
    {
		if(shouldVibrate)
		{
			vibDensityNumber.setEnabled(true);
			vibDensityResult.setEnabled(true);
			vibDensityError.setEnabled(true);
		}
		else
		{
			vibDensityNumber.setEnabled(false);
			vibDensityResult.setEnabled(false);
			vibDensityError.setEnabled(false);
		}
    }
    
}
