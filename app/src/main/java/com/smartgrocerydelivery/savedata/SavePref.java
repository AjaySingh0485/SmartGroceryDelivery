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



	public void saveuser_type(Context ctx, String user_type){
		result=false;
		prefs = ctx.getSharedPreferences(myprefs, mode);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString("user_type",user_type);
		result= editor.commit();

	}

	public void saveusername(Context ctx, String login){
		result=false;
		prefs = ctx.getSharedPreferences(myprefs, mode);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString("username",login);
		result= editor.commit();

	}

	public void saveuser_email(Context ctx, String plans_id){
		result=false;
		prefs = ctx.getSharedPreferences(myprefs, mode);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString("email",plans_id);
		result= editor.commit();

	}




	public void saveuserbirthcountry(Context ctx, String plans_id){
		result=false;
		prefs = ctx.getSharedPreferences(myprefs, mode);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString("birthcountry",plans_id);
		result= editor.commit();

	}

	public void saveuser_pic(Context ctx, String plans_id){
		result=false;
		prefs = ctx.getSharedPreferences(myprefs, mode);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString("image",plans_id);
		result= editor.commit();

	}


	public void saveLoggedIn(Context ctx, String login){
		result=false;
		prefs = ctx.getSharedPreferences(myprefs, mode);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString("LoggedIn",login);
		result= editor.commit();

	}






	public void savedatecount(Context ctx, String membership_status){
		result=false;
		prefs = ctx.getSharedPreferences(myprefs, mode);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString("datecount",membership_status);
		result= editor.commit();

	}


	public void savecount(Context ctx, String plan_id){
		result=false;
		prefs = ctx.getSharedPreferences(myprefs, mode);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString("count",plan_id);
		result= editor.commit();

	}










	/*public void saveuserId(Context ctx, String id){
		result=false;
		prefs = ctx.getSharedPreferences(myprefs, mode);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString("userId",id);
		result= editor.commit();

	}
	public void saveuser_type(Context ctx, String user_type){
		result=false;
		prefs = ctx.getSharedPreferences(myprefs, mode);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString("user_type",user_type);
		result= editor.commit();

	}

	public void savemembership_status(Context ctx, String membership_status){
		result=false;
		prefs = ctx.getSharedPreferences(myprefs, mode);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString("membership_status",membership_status);
		result= editor.commit();

	}


	public void saveplan_id(Context ctx, String plan_id){
		result=false;
		prefs = ctx.getSharedPreferences(myprefs, mode);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString("plan_id",plan_id);
		result= editor.commit();

	}


	public void saveuserstatus(Context ctx, String status){
		result=false;
		prefs = ctx.getSharedPreferences(myprefs, mode);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString("status",status);
		result= editor.commit();

	}


	public void savecount(Context ctx, String count){
		result=false;
		prefs = ctx.getSharedPreferences(myprefs, mode);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString("count",count);
		result= editor.commit();

	}
	public void saveplans_id(Context ctx, String plans_id){
		result=false;
		prefs = ctx.getSharedPreferences(myprefs, mode);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString("active_plan_id",plans_id);
		result= editor.commit();

	}



	public void saveuser_voice_type(Context ctx, String plans_id){
		result=false;
		prefs = ctx.getSharedPreferences(myprefs, mode);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString("voice_type",plans_id);
		result= editor.commit();

	}




	public void saveusertype(Context ctx, String plans_id){
		result=false;
		prefs = ctx.getSharedPreferences(myprefs, mode);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString("user_tepe",plans_id);
		result= editor.commit();

	}



	public void saveusertimezone(Context ctx, String plans_id){
		result=false;
		prefs = ctx.getSharedPreferences(myprefs, mode);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString("TimeZoneId",plans_id);
		result= editor.commit();

	}

	public void saveuserlatitude(Context ctx, String plans_id){
		result=false;
		prefs = ctx.getSharedPreferences(myprefs, mode);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString("latitude",plans_id);
		result= editor.commit();

	}
	public void saveuserlongitude(Context ctx, String plans_id){
		result=false;
		prefs = ctx.getSharedPreferences(myprefs, mode);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString("longitude",plans_id);
		result= editor.commit();

	}






	public void saveuserdateofbirth(Context ctx, String plans_id){
		result=false;
		prefs = ctx.getSharedPreferences(myprefs, mode);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString("dateofbirth",plans_id);
		result= editor.commit();

	}
	public void saveuserdateoftime(Context ctx, String plans_id){
		result=false;
		prefs = ctx.getSharedPreferences(myprefs, mode);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString("dateoftime",plans_id);
		result= editor.commit();

	}


	public void saveusergender(Context ctx, String plans_id){
		result=false;
		prefs = ctx.getSharedPreferences(myprefs, mode);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString("gender",plans_id);
		result= editor.commit();

	}

	public void saveusermanifesto(Context ctx, String plans_id){
		result=false;
		prefs = ctx.getSharedPreferences(myprefs, mode);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString("manifesto",plans_id);
		result= editor.commit();

	}

	public void saveuserbirth_of_city(Context ctx, String plans_id){
		result=false;
		prefs = ctx.getSharedPreferences(myprefs, mode);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString("city",plans_id);
		result= editor.commit();

	}


	public void saveuserinstructions(Context ctx, String Subscription){
		result=false;
		prefs = ctx.getSharedPreferences(myprefs, mode);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString("Instructions",Subscription);
		result= editor.commit();

	}








	public void saveuserSubscription(Context ctx, String Subscription){
		result=false;
		prefs = ctx.getSharedPreferences(myprefs, mode);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString("Subscription",Subscription);
		result= editor.commit();

	}
	public void saveuserFirst(Context ctx, String First){
		result=false;
		prefs = ctx.getSharedPreferences(myprefs, mode);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString("First",First);
		result= editor.commit();

	}

	public void saveusercountry(Context ctx, String First){
		result=false;
		prefs = ctx.getSharedPreferences(myprefs, mode);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString("country",First);
		result= editor.commit();

	}



	public void saveuserprofile_updated(Context ctx, String First){
		result=false;
		prefs = ctx.getSharedPreferences(myprefs, mode);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString("profile_updated",First);
		result= editor.commit();

	}









	public  void deletealldata(Context ctx){
		SharedPreferences preferences = ctx.getSharedPreferences(myprefs, mode);
		SharedPreferences.Editor editor = preferences.edit();
		editor.clear();
		editor.commit();
	}*/
}



