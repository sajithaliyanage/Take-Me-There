package com.example.sajitha.tmt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Signup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }

    public void moveLogin(View view){
        Intent move = new Intent(Signup.this,LoginActivity.class);
        startActivity(move);
    }
}
