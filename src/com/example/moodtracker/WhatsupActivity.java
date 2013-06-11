package com.example.moodtracker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class WhatsupActivity extends BaseActivity  {
	
	private SharedPreferences settings;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_whatsup);
		settings = getSharedPreferences(SETTINGS_PREFS, Context.MODE_PRIVATE);
		initButton(R.id.btn_save, MainActivity.class);
		initButton(R.id.btn_send, MainActivity.class);
		initButton(R.id.btn_back, MainActivity.class); 
		initSpinner(R.id.spinner_sleep, R.array.sleep_array, ""); 
		initTextField(R.id.edittext_event, SETTINGS_PREFS_EVENT1);
		initTextField(R.id.edittext_intake, SETTINGS_PREFS_INTAKE1);
		Button saveButton = (Button)findViewById(R.id.btn_save);
		saveButton.setOnClickListener(new SaveButtonListener());
		Button sendButton = (Button)findViewById(R.id.btn_send);
		sendButton.setOnClickListener(new SaveButtonListener());
	}
	
	private class SaveButtonListener implements View.OnClickListener 
	{
		public void onClick(View v) 
		{
			//get int from star rating here
			EditText Eevent = (EditText)findViewById(R.id.edittext_event);
			EditText Eintake = (EditText)findViewById(R.id.edittext_intake);
			Spinner Esleep = (Spinner)findViewById(R.id.spinner_sleep);
			String event = Eevent.toString();
			String intake = Eintake.toString();
			String sleep = Esleep.toString();
			
			
			long recordId =  new SQLHelper(WhatsupActivity.this).addRecordToDB(5, event, intake, sleep);
    		
    		if(recordId == -1)
    		{
    			Toast successEntry = Toast.makeText(WhatsupActivity.this, "Error adding record", Toast.LENGTH_LONG);
    			return;
    		}
    		
             Toast successEntry = Toast.makeText(WhatsupActivity.this, "Record successfully added", Toast.LENGTH_LONG);
        	 successEntry.show();
			startActivity(new Intent(WhatsupActivity.this, MainActivity.class));
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
	
	public void initSpinner(int spinnerId, int arrayId, String prefKey)
	{//how are we going to populate these with info from settings? do checkboxes/text views instead? or figure out how to string - > array convert and back 
		Spinner spinner = (Spinner)findViewById(spinnerId);
		
		ArrayAdapter<CharSequence> adapter = 
			ArrayAdapter.createFromResource(this, arrayId, android.R.layout.simple_spinner_item);
		
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
	
	}
	
//a method to take you places! pass in Button Id and class of destination 
		private void initButton(int btnId, final Class destination)
		{
			Button button = (Button)findViewById(btnId);
			button.setOnClickListener(new OnClickListener(){
				
				public void onClick(View v) {
					startActivity(new Intent(WhatsupActivity.this, destination));
				}
				
			});
		}
}
