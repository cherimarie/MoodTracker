package com.example.moodtracker;



import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
	


public class BaseActivity  extends Activity{
	
	private SharedPreferences settings;
	
	/* The keys for all the settings shared preferences */
	public static final String SETTINGS_PREFS = "SETTINGS PREFS";
	public static final String SETTINGS_PREFS_NAME = "NAME";
	public static final String SETTINGS_PREFS_AVATAR = "AVATAR";
	public static final String SETTINGS_PREFS_EVENT1 = "EVENT1";
	public static final String SETTINGS_PREFS_INTAKE1 = "INTAKE1";
	public static final String SETTINGS_PREFS_REMINDER = "REMINDER";
	public static final String SETTINGS_PREFS_REMINDER_ID = "ReminderID";
	public static final int SETTINGS_PREFS_WUP_MOOD = 0;
	public static final String SETTINGS_PREFS_WUP_INTAKE1 = "W_INTAKE1";
	public static final String SETTINGS_PREFS_WUP_INTAKE2 = "W_INTAKE2";
	public static final String SETTINGS_PREFS_WUP_INTAKE3 = "W_INTAKE3";
	public static final String SETTINGS_PREFS_WUP_EVENT1 = "W_EVENT1";
	public static final String SETTINGS_PREFS_WUP_EVENT2 = "W_EVENT2";
	public static final String SETTINGS_PREFS_WUP_EVENT3 = "W_EVENT3";
	public static final int SETTINGS_PREFS_WUP_SLEEP = 0;
	public static final String SETTINGS_PREFS_SHARABLE = "info to share";
	

	public boolean onCreateOptionsMenu(Menu menu)
	{
		super.onCreateOptionsMenu(menu); 
		
		getMenuInflater().inflate(R.menu.general_menu, menu); 
		
		menu.findItem(R.id.menuitem_about).setIntent(new Intent(this, AboutActivity.class));
		menu.findItem(R.id.menuitem_settings).setIntent(new Intent(this, SetupActivity.class));
		return true; 
	}
	
	public void initTextField(int fieldId, String prefKey)
	{
		if(settings.contains(prefKey))
		{
			String text = settings.getString(prefKey, "");
			//second arg is default value- what should pop out if there's nothing in there? 
			EditText edittext = (EditText)findViewById(fieldId);
			edittext.setText(text);
		}
		//use prefKey to get the value, then put it in the text field
	}
	
	
	public void initSpinner(int spinnerId, int arrayId, String prefKey)
	{
		Spinner spinner = (Spinner)findViewById(spinnerId);
		
		ArrayAdapter<CharSequence> adapter =
				ArrayAdapter.createFromResource(this, arrayId, android.R.layout.simple_spinner_item);
		
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		
		if(settings.contains(prefKey))
		{
			int selectedId = settings.getInt(prefKey, 0);
			spinner.setSelection(selectedId);
		}
	}
	

}
//dont yet have prefs for view options