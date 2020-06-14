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



	public String getuser_type(){
		String userId="";
		userId=prefs.getString("user_type", "");
		return userId;
	}



	public String getusername(){
		String userId="";
		userId=prefs.getString("username", "");
		return userId;
	}


	public String getuseremail(){
		String userId="";
		userId=prefs.getString("email", "");
		return userId;
	}



	public String getuserbirthcountry(){
		String userId="";
		userId=prefs.getString("birthcountry", "");
		return userId;
	}




	public String getuserpic(){
		String userId="";
		userId=prefs.getString("image", "");
		return userId;
	}


	public String getLoggedIn(){
		String userId="";
		userId=prefs.getString("LoggedIn", "");
		return userId;
	}


	public String getdatecount(){
		String userId="";
		userId=prefs.getString("datecount", "");
		return userId;
	}


	public String getcount(){
		String userId="";
		userId=prefs.getString("count", "");
		return userId;
	}
	/*

	public String getmembership_status(){
		String userId="";
		userId=prefs.getString("membership_status", "");
		return userId;
	}
	public String getplan_id(){
		String userId="";
		userId=prefs.getString("plan_id", "");
		return userId;
	}


	public String getstatus(){
		String userId="";
		userId=prefs.getString("status", "");
		return userId;
	}





	public String getusertypr(){
		String userId="";
		userId=prefs.getString("user_tepe", "");
		return userId;
	}
	public String getusertimezone(){
		String userId="";
		userId=prefs.getString("TimeZoneId", "");
		return userId;
	}


	public String getuserlatitude(){
		String userId="";
		userId=prefs.getString("latitude", "");
		return userId;
	}

	public String getuserlongitude(){
		String userId="";
		userId=prefs.getString("longitude", "");
		return userId;
	}







	public String getuserdateofbirth(){
		String userId="";
		userId=prefs.getString("dateofbirth", "");
		return userId;
	}

	public String getuserdateoftime(){
		String userId="";
		userId=prefs.getString("dateoftime", "");
		return userId;
	}





	public String getusergender(){
		String userId="";
		userId=prefs.getString("gender", "");
		return userId;
	}
	public String getusermanifesto(){
		String userId="";
		userId=prefs.getString("manifesto", "");
		return userId;
	}

	public String getusercity(){
		String userId="";
		userId=prefs.getString("city", "");
		return userId;
	}





	public String getuserinstructions(){
		String userId="";
		userId=prefs.getString("Instructions", "");
		return userId;
	}




	public String getuserSubscription(){
		String userId="";
		userId=prefs.getString("Subscription", "");
		return userId;
	}
	public String getuserfirst(){
		String userId="";
		userId=prefs.getString("First", "");
		return userId;
	}
	public String getusercountry(){
		String userId="";
		userId=prefs.getString("country", "");
		return userId;
	}

	public String getuservoicetype(){
		String userId="";
		userId=prefs.getString("voice_type", "");
		return userId;
	}

	public String getuserprofile_updated(){
		String userId="";
		userId=prefs.getString("profile_updated", "");
		return userId;
	}*/










}
