package com.example.moodtracker;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class AboutActivity extends BaseActivity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		populateText();
		initButton(R.id.btn_home, MainActivity.class);
	}
	
	private void populateText()
	{ //read in .txt file as an Input Stream
		InputStream file = getResources().openRawResource(R.raw.about);
		//String textFromFile = "testy text";
		//set textFromFile to just a string first, to test the output process
		String textFromFile = getTextFromFile(file);
		TextView textView = (TextView)findViewById(R.id.textview_about);
		textView.setText(textFromFile);
	}
	
	private String getTextFromFile (InputStream file)
	{
		String data = ""; 
		InputStreamReader inputreader = new InputStreamReader(file);
		BufferedReader buffreader = new BufferedReader(inputreader);
		
		try{
			String line = "";
			while((line = buffreader.readLine()) != null)
			{
				// deprecated: String line = dataIO.readLine();
				//if(line == null)break; 
				data += line.toString() + "\n";
				 
			}
		}
		catch(Exception e){
			return "Oops. Please check back later to read about this app." ; 
		}
		return data;
	}
	
	//a method to take you places! pass in Button Id and class of destination 
	private void initButton(int btnId, final Class destination)
	{
		Button button = (Button)findViewById(btnId);
		button.setOnClickListener(new OnClickListener(){
			
			public void onClick(View v) {
				startActivity(new Intent(AboutActivity.this, destination));
			}
			
		});
	}
}
