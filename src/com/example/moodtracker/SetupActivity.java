package com.example.moodtracker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SetupActivity extends BaseActivity  {

	private SharedPreferences settings;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setup);
		initButton(R.id.btn_back, MainActivity.class);
		
		//call preferences, init name text field, attach listener to save button 
		settings = getSharedPreferences(SETTINGS_PREFS, Context.MODE_PRIVATE);
		initTextField(R.id.edittext_name, SETTINGS_PREFS_NAME);
		Button saveButton = (Button)findViewById(R.id.btn_save);
		saveButton.setOnClickListener(new ContButtonListener());
	}
	
	private class ContButtonListener implements View.OnClickListener 
	{
		public void onClick(View v) 
		{
			boolean validName = validTextBox(R.id.edittext_name);
		
			if(validName)
			{
							
			}
			saveTextToPrefs(R.id.edittext_name, SETTINGS_PREFS_NAME);	
			startActivity(new Intent(SetupActivity.this, MainActivity.class));
		}
	}
	
	public void saveTextToPrefs(int editTextId, String prefKey)
	{
		//get text from text box
		EditText editText = (EditText)findViewById(editTextId);
		String text = editText.getText().toString().trim();
		
		//save text to shared preferences
		Editor editor = settings.edit(); 
		editor.putString(prefKey, text);
		editor.commit(); 
		//if you have many fields, only open editor and commit once, write a bunch of times in the middle 
	}
	
	public boolean validTextBox(int editTextId)
	{
		//get text from text box
		EditText editText = (EditText)findViewById(editTextId);
		String text = editText.getText().toString().trim();
		
		if(text.length() < 2)
		{
			return false;
		}
		else
		{
			return true;
		}
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
	
	//a method to take you places! pass in Button Id and class of destination 
			private void initButton(int btnId, final Class destination)
			{
				Button button = (Button)findViewById(btnId);
				button.setOnClickListener(new OnClickListener(){
					
					public void onClick(View v) {
						startActivity(new Intent(SetupActivity.this, destination));
					}
					
				});
			}
}
