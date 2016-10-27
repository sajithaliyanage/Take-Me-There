package com.example.sajitha.tmt;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class WaitingForDriverActivity extends AppCompatActivity implements View.OnClickListener {
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting_for_driver);
        context = this;

        Button one = (Button) findViewById(R.id.cancelButton);
        one.setOnClickListener(this);
        ImageView one1 = (ImageView) findViewById(R.id.butn1);
        one1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancelButton:
                Intent intent1 = new Intent(context,SelectRoleActivity.class);
                context.startActivity(intent1);
                break;
            case R.id.butn1:
                Intent intent = new Intent(context,BothAcceptActivity.class);
                context.startActivity(intent);
                break;
        }

    }
}
