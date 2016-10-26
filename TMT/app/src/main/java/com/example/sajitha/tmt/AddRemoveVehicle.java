package com.example.sajitha.tmt;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AddRemoveVehicle extends AppCompatActivity implements View.OnClickListener {
    Context context;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_remove_vehicle);
        context = this;
        activity=this;

        Button one = (Button) findViewById(R.id.saveBUttton);
        one.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.saveBUttton:
                Log.i("saved","Done");
                Intent intent = new Intent(context,DriverSetActivity.class);
                context.startActivity(intent);
                break;
        }
    }
}
