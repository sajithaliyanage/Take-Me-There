package com.example.sajitha.tmt;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;

public class StaticImports {
    public static void navigate_menu(int id, Context context){
        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow2) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.about) {

        } else if (id == R.id.chatbox) {

        }else if (id == R.id.logout){

            LoginSession sessionLogin = new LoginSession(context);
            sessionLogin.logOut();

        }
    }
}
