package com.example.sajitha.tmt;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class PassengerApprovedByDriverActivity extends AppCompatActivity implements View.OnClickListener {
    Context context;
    int userID,passenderID;
    LoginSession sessionLogin;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_approved_by_driver);
        context = this;
        sessionLogin = new LoginSession(context);
        sharedPreferences = sessionLogin.sharedpreferences;

        Button one1 = (Button) findViewById(R.id.accept);
        one1.setOnClickListener(this);
        Button one = (Button) findViewById(R.id.reject);
        one.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Bundle extras = getIntent().getExtras();
        userID= sessionLogin.sharedpreferences.getInt("userid",0);
        passenderID= extras.getInt("passengerid");
        double dest_lat = extras.getDouble("dest_lat");
        double dest_lng = extras.getDouble("dest_lng");

        switch (v.getId()) {
            case R.id.accept:
                int value = 2;
                new AcceptRejectRequest(context,dest_lat,dest_lng).execute(userID+"",passenderID+"",value+"");
                break;
            case R.id.reject:
                int values  =1;
                new AcceptRejectRequest(context,dest_lat,dest_lng).execute(userID+"",passenderID+"",values+"");
                break;
        }
    }
}
