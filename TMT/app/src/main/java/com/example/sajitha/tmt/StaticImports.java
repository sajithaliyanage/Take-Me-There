package com.example.sajitha.tmt;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.util.Log;

public class StaticImports {
    public static void navigate_menu(int id, Context context){
        Log.i("Home","INSIDE");
        if (id == R.id.home) {
            Log.i("Home","Done");
            Intent intent = new Intent(context,HomeActivity.class);;
            context.startActivity(intent);

        } else if (id == R.id.profile) {
            Intent intent = new Intent(context,ProfileActivity.class);;
            context.startActivity(intent);

        } else if (id == R.id.nav_slideshow2) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.about) {

        } else if (id == R.id.chatbox) {

        } else if (id == R.id.mode) {
            Intent intent = new Intent(context,SelectRoleActivity.class);;
            context.startActivity(intent);
        }else if (id == R.id.logout){

            LoginSession sessionLogin = new LoginSession(context);
            sessionLogin.logOut();

        }
    }
}
