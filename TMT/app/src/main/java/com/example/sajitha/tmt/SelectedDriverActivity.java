package com.example.sajitha.tmt;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SelectedDriverActivity extends AppCompatActivity implements View.OnClickListener  {
    int driverID,userid ;
    Context context;
    SharedPreferences sharedPreferences;
    LoginSession sessionLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selected_driver_activity);
        context = this;
        sessionLogin = new LoginSession(context);
        sharedPreferences = sessionLogin.sharedpreferences;

        TextView one = (TextView) findViewById(R.id.driverName);
        Bundle extras = getIntent().getExtras();
        String name= extras.getString("driverName");
        driverID= extras.getInt("driverId");
        one.setText(name);

        Button one1 = (Button) findViewById(R.id.makeRequest);
        one1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.makeRequest:
                Log.i("Request - ","ynna");
                userid = sessionLogin.sharedpreferences.getInt("userid",0);
                new SendRequest(context).execute(driverID+"",userid+"");
                break;
        }

    }
}
