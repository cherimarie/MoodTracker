package com.example.moodtracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initButton(R.id.btn_about, AboutActivity.class);
		initButton(R.id.btn_whatsup, WhatsupActivity.class);
		initButton(R.id.btn_view, ViewOptionsActivity.class);
		initButton(R.id.btn_setup, SetupActivity.class);
	}

	//a method to take you places! pass in Button Id and class of destination 
		private void initButton(int btnId, final Class destination)
		{
			Button button = (Button)findViewById(btnId);
			button.setOnClickListener(new OnClickListener(){
				
				public void onClick(View v) {
					startActivity(new Intent(MainActivity.this, destination));
				}
				
			});
		}
}
