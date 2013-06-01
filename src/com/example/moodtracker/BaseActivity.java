package com.example.moodtracker;

import android.app.Activity;

public class BaseActivity  extends Activity{
	/* The keys for all the settings shared preferences */
	public static final String SETTINGS_PREFS = "SETTINGS PREFS";
	public static final String SETTINGS_PREFS_NAME = "NAME";
	public static final String SETTINGS_PREFS_AVATAR = "AVATAR";
	public static final String SETTINGS_PREFS_EVENT1 = "EVENT1";
	public static final String SETTINGS_PREFS_EVENT2 = "EVENT2";
	public static final String SETTINGS_PREFS_EVENT3 = "EVENT3";
	public static final String SETTINGS_PREFS_INTAKE1 = "INTAKE1";
	public static final String SETTINGS_PREFS_INTAKE2 = "INTAKE2";
	public static final String SETTINGS_PREFS_INTAKE3 = "INTAKE3";
	public static final String SETTINGS_PREFS_REMINDER = "REMINDER";
	public static final int SETTINGS_PREFS_WUP_MOOD = 0;
	public static final String SETTINGS_PREFS_WUP_INTAKE1 = "W_INTAKE1";
	public static final String SETTINGS_PREFS_WUP_INTAKE2 = "W_INTAKE2";
	public static final String SETTINGS_PREFS_WUP_INTAKE3 = "W_INTAKE3";
	public static final String SETTINGS_PREFS_WUP_EVENT1 = "W_EVENT1";
	public static final String SETTINGS_PREFS_WUP_EVENT2 = "W_EVENT2";
	public static final String SETTINGS_PREFS_WUP_EVENT3 = "W_EVENT3";
	public static final int SETTINGS_PREFS_WUP_SLEEP = 0;
	
}
//dont yet have prefs for view options