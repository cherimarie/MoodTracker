package com.example.moodtracker;

import java.util.ArrayList;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLHelper extends SQLiteOpenHelper  {

	private static final String DATABASE_NAME = "moodtracker.db";
	private static final int DATABASE_VERSION = 1;

	// Table name
	public static final String WHATUP_TABLE = "whatups";
	
	//names of table columns
	public static final String MOOD = "mood";
	public static final String EVENT =  "event";
	public static final String INTAKE = "intake"; 
	public static final String SLEEP = "sleep";

	public void onCreate(SQLiteDatabase db) {
		String sql_whatups = "create table " + WHATUP_TABLE + "( " + WHATUP_TABLE + 
				" integer primary key autoincrement, " +
				MOOD + "int" +
				EVENT + " text, " + 
				INTAKE + " text, " + 
				SLEEP + "text" +
				");";
		//to make mood required, add "not null" to string after it
		db.execSQL(sql_whatups);
		
	}
	
	public long addRecordToDB(int mood, String event, String intake, String sleep)
	{
		ContentValues values = new ContentValues();
        values.put(SQLHelper.MOOD, mood);
        values.put(SQLHelper.EVENT, event);
        values.put(SQLHelper.INTAKE, intake);
        values.put(SQLHelper.SLEEP, sleep);
        
        long recordId = getWritableDatabase().insert(WHATUP_TABLE, null, values);
        
        return recordId;
	}

	public ArrayList<WhatsupEntry> getWhatups()
	{
		ArrayList<WhatsupEntry> whatupList = new ArrayList<WhatsupEntry>();
		
        Cursor entryCursor = getReadableDatabase().query(SQLHelper.WHATUP_TABLE, 
        		new String[] {"*"}, "", null, null, null, null);
        
        int recordId = -1;
        WhatsupEntry entry;
        
        //cycle through each row
		while(entryCursor.moveToNext())
		{
			recordId = entryCursor.getInt(0);
					
			if(recordId != -1)
			{
				entry = new WhatsupEntry(recordId, entryCursor.getInt(1), entryCursor.getString(2), entryCursor.getString(3), entryCursor.getString(4));
				
				whatupList.add(entry);	//add the patients to a list of patients
			}
		}
		entryCursor.close();
		return whatupList;
		
	}
	
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}
	
	public SQLHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);

	}
}