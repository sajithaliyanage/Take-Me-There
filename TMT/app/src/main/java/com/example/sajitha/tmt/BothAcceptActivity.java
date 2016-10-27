package com.example.sajitha.tmt;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BothAcceptActivity extends AppCompatActivity implements View.OnClickListener {
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_both_accept);
        context = this;

        Button one1 = (Button) findViewById(R.id.btn_picked_me_up);
        one1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_picked_me_up:
                Intent intent = new Intent(context,JourneyCompleteActivity.class);
                context.startActivity(intent);
                break;

        }
    }
}
