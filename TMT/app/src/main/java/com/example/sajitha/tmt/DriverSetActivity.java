package com.example.sajitha.tmt;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class DriverSetActivity extends AppCompatActivity implements View.OnClickListener{
    Context context;
    Activity activity;
    LoginSession sessionLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.driver_set_activity);
        context = this;
        activity=this;

        Button one = (Button) findViewById(R.id.confirmRide);
        one.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.confirmRide:
                int userid = sessionLogin.sharedpreferences.getInt("userid",0);
                String[] arrayOfValue = new String[3];
                arrayOfValue[2] = Integer.toString(userid);
                new DriverDestination(context,activity).execute(arrayOfValue);
                break;
        }
    }
}
