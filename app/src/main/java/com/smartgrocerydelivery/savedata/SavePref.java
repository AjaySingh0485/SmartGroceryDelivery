package com.smartgrocerydelivery.savedata;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SavePref {
	
	private SharedPreferences prefs;
	String myprefs="Entrepreneur_Challenge";
	int mode = Activity.MODE_PRIVATE;
	boolean result=false;
	String TAG="SavePref";
	Context context;



	public void saveuserId(Context ctx, String id){
		result=false;
		prefs = ctx.getSharedPreferences(myprefs, mode);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString("userId",id);
		result= editor.commit();

	}



	public void saveuser_token(Context ctx, String user_type){
		result=false;
		prefs = ctx.getSharedPreferences(myprefs, mode);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString("user_token",user_type);
		result= editor.commit();

	}


}



