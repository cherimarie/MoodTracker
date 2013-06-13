package com.example.moodtracker;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ShareActivity extends BaseActivity  {

	private SharedPreferences settings;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		settings = getSharedPreferences(SETTINGS_PREFS, Context.MODE_PRIVATE);
		setContentView(R.layout.activity_share);
		initButton(R.id.btn_back, ViewActivity.class);
		Button sendButton = (Button)findViewById(R.id.btn_send);
		sendButton.setOnClickListener(new ShareButtonListener());
	}
	
	private class ShareButtonListener implements View.OnClickListener 
	{
		public void onClick(View v) 
		{
			EditText rawRecpEmail = (EditText)findViewById(R.id.edittext_recp_email);
			String recpEmail = rawRecpEmail.getText().toString().trim();
			
			Intent i = new Intent(Intent.ACTION_SEND);
			i.setType("message/rfc822");
			i.putExtra(Intent.EXTRA_EMAIL  , new String[]{recpEmail});
			i.putExtra(Intent.EXTRA_SUBJECT, "mood tracker from " + SETTINGS_PREFS_NAME);
			i.putExtra(Intent.EXTRA_TEXT   , "body of email");
			try {
			    startActivity(Intent.createChooser(i, "Send mail..."));
			} catch (android.content.ActivityNotFoundException ex) {
			    Toast.makeText(ShareActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
			}
		}
	}
	
//a method to take you places! pass in Button Id and class of destination 
	private void initButton(int btnId, final Class destination)
	{
		Button button = (Button)findViewById(btnId);
		button.setOnClickListener(new OnClickListener(){
			
			public void onClick(View v) {
				startActivity(new Intent(ShareActivity.this, destination));
			}
			
		});
	}
			
}
