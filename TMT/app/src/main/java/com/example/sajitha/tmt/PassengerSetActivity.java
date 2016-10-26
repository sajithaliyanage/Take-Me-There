package com.example.sajitha.tmt;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class PassengerSetActivity extends AppCompatActivity implements View.OnClickListener  {
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_set);
        context = this;

        Button one = (Button) findViewById(R.id.continues);
        one.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.continues:
                Log.i("Insurance ","ynna");
                    Intent intent1 = new Intent(PassengerSetActivity.this,InsurancePackagesActivity.class);
                    startActivity(intent1);
                    break;
                }

    }
}
