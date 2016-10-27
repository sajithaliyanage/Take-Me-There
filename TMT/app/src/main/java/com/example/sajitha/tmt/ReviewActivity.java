package com.example.sajitha.tmt;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class ReviewActivity extends AppCompatActivity implements View.OnClickListener  {
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        context=this;

        ImageButton one1 = (ImageButton) findViewById(R.id.report);
        one1.setOnClickListener(this);

        Button one = (Button) findViewById(R.id.over);
        one.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.report:
                Intent intent = new Intent(context,JourneyCompleteActivity.class);
                context.startActivity(intent);
                break;

            case R.id.over:
                Intent intent1 = new Intent(context,SelectRoleActivity.class);
                context.startActivity(intent1);
                break;

        }
    }
}
