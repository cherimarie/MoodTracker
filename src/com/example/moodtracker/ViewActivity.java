package com.example.moodtracker;

import java.io.OutputStream;
import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ViewActivity extends BaseActivity  {

	private SharedPreferences settings;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		settings = getSharedPreferences(SETTINGS_PREFS, Context.MODE_PRIVATE);
		setContentView(R.layout.activity_view);
		initButton(R.id.btn_back, MainActivity.class);
		initButton(R.id.btn_share, ShareActivity.class);
	}

	
	public void onResume() 
	{
		super.onResume();
		initEntryList();
	}
	
	private void initEntryList()
	{		
		ArrayList<WhatsupEntry> whatupList = new SQLHelper(this).getWhatups();
		
		if (whatupList.size() > 0) 
		{
			//add the patients to the list view
			ListView entryListView = (ListView)findViewById(R.id.ListView_Entries);
			EntryAdapter entryAdapter = new EntryAdapter(this, R.layout.whatsup_row, whatupList);
			entryListView.setAdapter(entryAdapter);
		}
	}
	
	
    private class EntryAdapter extends ArrayAdapter<WhatsupEntry> 
    {
    	public EntryAdapter(Context context, int textViewResourceId, ArrayList<WhatsupEntry> whatupList) 
    	{
			super(context, textViewResourceId, whatupList);
		}
    	String sharable = "";
		public View getView(int position, View convertView, ViewGroup parent) 
    	{
    		if (convertView == null) 
    		{
				LayoutInflater mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = mInflater.inflate(R.layout.whatsup_row, null);
			}
    		
    		final WhatsupEntry entry = getItem(position);
    		
    		if(entry != null)
    		{
    			

    			TextView moodTV = (TextView) convertView.findViewById(R.id.TextView_Mood);
    			moodTV.setText("mood: " + entry.getMood());
    			sharable += "mood: " + entry.getMood() + " , ";
    			
    			TextView intakeTV = (TextView) convertView.findViewById(R.id.TextView_Intake);
    			intakeTV.setText("intake: " + entry.getIntake());
    			sharable += "intake: " + entry.getIntake() + " , ";
    			
    			TextView eventTV = (TextView) convertView.findViewById(R.id.TextView_Event);
    			eventTV.setText("event: " + entry.getEvent());
    			sharable += "event: " + entry.getEvent() + " , ";
    			
    			TextView sleepTV = (TextView) convertView.findViewById(R.id.TextView_Sleep);
    			sleepTV.setText("sleep: " + entry.getSleep());
    			sharable += "sleep: " + entry.getSleep() + " ; ";
    			
    			saveTextToPrefs(sharable, SETTINGS_PREFS_SHARABLE);
    		
    		}
    		
    		return convertView;
    	}
    }
    
	public void saveTextToPrefs(String text, String prefKey)
	{
		//save text to shared preferences
		Editor editor = settings.edit(); 
		editor.putString(prefKey, text);
		editor.commit(); 
		//if you have many fields, only open editor and commit once, write a bunch of times in the middle 
	}
	
//a method to take you places! pass in Button Id and class of destination 
	private void initButton(int btnId, final Class destination)
	{
		Button button = (Button)findViewById(btnId);
		button.setOnClickListener(new OnClickListener(){
			
			public void onClick(View v) {
				startActivity(new Intent(ViewActivity.this, destination));
			}
			
		});
	}
}
