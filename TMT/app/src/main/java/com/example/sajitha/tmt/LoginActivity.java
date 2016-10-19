package com.example.sajitha.tmt;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {
    public static long time;
    private String userEmail,password;
    boolean isDone = true;
    Context context;
    Activity activity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = this;
        activity = this;
    }

    //when pressed SIGN IN button
    public void signInUsers(View v){
        EditText useremail = (EditText)findViewById(R.id.email);
        EditText pswd = (EditText)findViewById(R.id.password);
        //myAwesomeTextView.setText("Done");

        userEmail = useremail.getText().toString();
        password = pswd.getText().toString();

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


        if (userEmail.isEmpty() || !userEmail.matches(emailPattern)) {
            Toast.makeText(context, "Please enter the valid Email", Toast.LENGTH_SHORT).show();
            isDone = false;
        } else if (password.isEmpty()) {
            Toast.makeText(context, "Please enter the  Password", Toast.LENGTH_SHORT).show();
            isDone = false;
        } else {
            isDone = true;
        }


        if(isDone){
            final ProgressDialog progress;

            progress = new ProgressDialog(this);
            progress.setTitle("Please Wait!!");
            progress.setMessage("Authenticating!!");
            progress.setCancelable(true);
            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progress.show();

            String[] arrayOfValue = new String[2];
            arrayOfValue[0] = userEmail;
            arrayOfValue[1] = password;
            Log.i("Login","pp");
//            myAwesomeTextView.setText(fullName+" - "+userEmail+" - "+password+" - "+userGender);
            new SigninPOST(context,activity).execute(arrayOfValue);

            new CountDownTimer(6000, 1000) {
                public void onFinish() {
                    progress.dismiss();
                    // my whole code
                }

                public void onTick(long millisUntilFinished) {
                    progress.show();
                }
            }.start();


        }

    }


    private Boolean exit = false;
    @Override
    public void onBackPressed() {
        if (time + 1000>System.currentTimeMillis()) {
            super.onBackPressed();
        } else {
            Toast.makeText(LoginActivity.this,"Press once again to exit", Toast.LENGTH_SHORT).show();
        }
        time = System.currentTimeMillis();
    }


    public void addListenerOnText(View view) {

        Intent intent = new Intent(LoginActivity.this, Signup.class);
        startActivity(intent);

    }

    public void slide(View view) {

        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);

    }
}

