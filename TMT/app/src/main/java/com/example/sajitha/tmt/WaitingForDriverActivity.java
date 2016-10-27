package com.example.sajitha.tmt;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WaitingForDriverActivity extends AppCompatActivity implements View.OnClickListener {
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting_for_driver);
        context = this;

        Button one = (Button) findViewById(R.id.cancelButton);
        one.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancelButton:
                Intent intent1 = new Intent(context,SelectedDriverActivity.class);
                context.startActivity(intent1);
                break;
        }

    }
}
