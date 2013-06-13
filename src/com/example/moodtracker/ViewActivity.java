package com.example.moodtracker;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class ViewActivity extends BaseActivity  {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
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
    			
    			TextView intakeTV = (TextView) convertView.findViewById(R.id.TextView_Intake);
    			intakeTV.setText("intake: " + entry.getIntake());
    			
    			TextView eventTV = (TextView) convertView.findViewById(R.id.TextView_Event);
    			eventTV.setText("event: " + entry.getEvent());
    			
    			TextView sleepTV = (TextView) convertView.findViewById(R.id.TextView_Sleep);
    			sleepTV.setText("sleep: " + entry.getSleep());
    		}
    		
    		return convertView;
    	}
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
