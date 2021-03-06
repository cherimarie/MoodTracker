package com.example.moodtracker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends BaseActivity {
	
	private SharedPreferences settings; 
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		settings = getSharedPreferences(SETTINGS_PREFS, Context.MODE_PRIVATE);
		setContentView(R.layout.activity_main);
		initButton(R.id.btn_whatsup, WhatsupActivity.class);
		initButton(R.id.btn_view, ViewActivity.class);
		initButton(R.id.btn_setup, SetupActivity.class);
		initTextField(R.id.home_headline, SETTINGS_PREFS_NAME);
		
		makeAnimated(R.id.image_happy_penguin, R.anim.rotate, new FinishAnimationListener());	
		
	}
	
	public void initTextField(int fieldId, String prefKey)
	{
		TextView tv = (TextView)findViewById(fieldId);
		tv.setText("Hello!");
		
		if(settings.contains(prefKey))
		{
			String text = settings.getString(prefKey, "");
			//second arg is default value- what should pop out if there's nothing in there? 
			tv.setText("Hello " + text + "!");
		}
		//use prefKey to get the value, then put it in the text field
	}
	
	public void makeAnimated(int fieldId, int animId, AnimationListener listener){
		View view = (View)findViewById(fieldId);
		Animation anim = AnimationUtils.loadAnimation(this, animId);
		view.startAnimation(anim);
		
		if(listener == null) return;  //if no listener assigned, we're done
		anim.setAnimationListener(listener); //if not null, attach the listener to animation
	}
	
	public class FinishAnimationListener implements AnimationListener {
		public void onAnimationEnd(Animation animation) 
		{
			findViewById(R.id.image_happy_penguin).clearAnimation();		
		}
		public void onAnimationRepeat(Animation animation) {}
		public void onAnimationStart(Animation animation) {}		
	}
	
	public void onPause()
	{  //stop animations if app is paused to save processing power
		//do the super activities for pausing
		super.onPause();
		findViewById(R.id.image_happy_penguin).clearAnimation();
	}

//a method to take you places! pass in Button Id and class of destination 
	public void initButton(int btnId, final Class destination)
	{
		Button button = (Button)findViewById(btnId);
		button.setOnClickListener(new OnClickListener(){
			
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this, destination));
			}
			
		});
	}
		
}
