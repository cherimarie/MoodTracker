package com.example.moodtracker;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class AboutActivity extends BaseActivity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		populateText();
		initButton(R.id.btn_home, MainActivity.class);
		
		makeAnimated(R.id.textview_about_headline, R.anim.fade_in, new FinishAnimationListener());
		}
		
		public void makeAnimated(int fieldId, int animId, AnimationListener listener){
			View view = (View)findViewById(fieldId);
			Animation anim = AnimationUtils.loadAnimation(this, animId);
			view.startAnimation(anim);
			
			if(listener == null) return;  //if no listener assigned, we're done
			anim.setAnimationListener(listener); //if not null, attach the listener to animation
		}
		
		private class FinishAnimationListener implements AnimationListener {
			public void onAnimationEnd(Animation animation) 
			{ 
				findViewById(R.id.textview_about_headline).clearAnimation();			
			}
			public void onAnimationRepeat(Animation animation) {}
			public void onAnimationStart(Animation animation) {}		
		}
		
		public void onPause()
		{  //stop animations if app is paused to save processing power
			//do the super activities for pausing
			super.onPause();
			findViewById(R.id.textview_about_headline).clearAnimation();
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
