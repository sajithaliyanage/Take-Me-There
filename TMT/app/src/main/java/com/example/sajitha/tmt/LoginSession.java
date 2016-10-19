package com.example.sajitha.tmt;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class LoginSession {
    Context context;
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String LOG_STATUS = "islogged";
    public static final String USER_ID = "userid";

    SharedPreferences sharedpreferences;


    LoginSession(Context context){
        this.context = context;
        sharedpreferences = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
    }

    public boolean isLogged(){
        boolean logged = sharedpreferences.getBoolean(LOG_STATUS, false);
        return logged;
    }

    public void setLogged(boolean value, int userid){
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putBoolean(LOG_STATUS, value);
        editor.putInt(USER_ID, userid);
        editor.commit();
    }

    public void logOut(){
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.clear();
        editor.commit();
    }

    public int getID(){
        sharedpreferences= PreferenceManager.getDefaultSharedPreferences(context);
        int userID = Integer.parseInt(sharedpreferences.getString(USER_ID, null));
        return userID;
    }
}
