package com.smartgrocerydelivery.savedata;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;


public class ReadPref {
	
	private SharedPreferences prefs;
	String myprefs="Entrepreneur_Challenge";
	int mode = Activity.MODE_PRIVATE;
	boolean result=false;
	String TAG="ReadPref";
	Context ctx;
	String res="";
	
	public ReadPref(Context ctx){
		this.ctx=ctx;
		prefs = this.ctx.getSharedPreferences(myprefs, mode);
	}



	public String getuserId(){
		String userId="";
		userId=prefs.getString("userId", "");
		return userId;
	}



	public String getuser_token(){
		String userId="";
		userId=prefs.getString("user_token", "");
		return userId;
	}




}
