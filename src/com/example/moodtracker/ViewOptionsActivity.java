package com.example.moodtracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ViewOptionsActivity extends BaseActivity  {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_options);
		initButton(R.id.btn_go, ViewActivity.class);
		initButton(R.id.btn_back, MainActivity.class);
		initButton(R.id.btn_about, AboutActivity.class);
	}
	
	
	//a method to take you places! pass in Button Id and class of destination 
			private void initButton(int btnId, final Class destination)
			{
				Button button = (Button)findViewById(btnId);
				button.setOnClickListener(new OnClickListener(){
					
					public void onClick(View v) {
						startActivity(new Intent(ViewOptionsActivity.this, destination));
					}
					
				});
			}
}
