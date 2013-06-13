package com.example.moodtracker;

public class WhatsupEntry {
	
	private int id;
	private float mood;
	private String event;
	private String intake; 
	private String sleep;
	
	public WhatsupEntry(int id, float mood, String event, String intake, String sleep)
	{
		this.mood = mood;
		this.event = event; 
		this.sleep = sleep;
	}
	
	public int getId(){
		return id;
	}
	
	public float getMood(){
		return mood;
	}
	
	public String getEvent(){
		return event;
	}
	
	public String getIntake(){
		return intake;
	}
	
	public String getSleep(){
		return sleep;
	}

}
