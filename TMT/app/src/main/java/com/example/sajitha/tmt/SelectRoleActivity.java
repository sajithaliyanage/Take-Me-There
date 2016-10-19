package com.example.sajitha.tmt;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SelectRoleActivity extends AppCompatActivity {
    LoginSession sessionLogin;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_role);
        context = this;

        sessionLogin = new LoginSession(this);
        //int id = sessionLogin.getID();
        String x = "saa";

        TextView myAwesomeTextView = (TextView)findViewById(R.id.textView2);
        myAwesomeTextView.setText(x);
    }
}
