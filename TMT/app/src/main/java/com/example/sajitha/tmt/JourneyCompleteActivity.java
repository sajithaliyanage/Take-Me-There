package com.example.sajitha.tmt;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class JourneyCompleteActivity extends AppCompatActivity implements View.OnClickListener  {
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journey_complete);
        context = this;

        Button one1 = (Button) findViewById(R.id.finish);
        one1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.finish:
                Intent intent = new Intent(context,ReviewActivity.class);
                context.startActivity(intent);
                break;

        }
    }



}
