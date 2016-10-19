package com.example.sajitha.tmt;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Signup extends AppCompatActivity {
    private String fullName,userEmail,password,repassword,userGender;
    boolean isDone = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

    }


    public void moveLogin(View view){
        Intent move = new Intent(Signup.this,LoginActivity.class);
        startActivity(move);
    }

    public void signUpUsers(View v){
        TextView myAwesomeTextView = (TextView)findViewById(R.id.test);
        AutoCompleteTextView fullname = (AutoCompleteTextView)findViewById(R.id.name);
        EditText useremail = (EditText)findViewById(R.id.email);
        EditText pswd = (EditText)findViewById(R.id.password);
        EditText repswd = (EditText)findViewById(R.id.repassword);
        Spinner gender = (Spinner)findViewById(R.id.gender);
        //myAwesomeTextView.setText("Done");

        fullName = fullname.getText().toString();
        userEmail = useremail.getText().toString();
        password = pswd.getText().toString();
        repassword = repswd.getText().toString();
        userGender = gender.getSelectedItem().toString();

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if(fullName.isEmpty()){
            Toast.makeText(Signup.this,"Please enter the Name", Toast.LENGTH_SHORT).show();
            isDone = false;
        }else if(userEmail.isEmpty() || !userEmail.matches(emailPattern)){
            Toast.makeText(Signup.this,"Please enter the valid Email", Toast.LENGTH_SHORT).show();
            isDone = false;
        }else if(password.isEmpty()){
            Toast.makeText(Signup.this,"Please enter the  Password", Toast.LENGTH_SHORT).show();
            isDone = false;
        }else if(repassword.isEmpty()){
            Toast.makeText(Signup.this,"Please enter the Repassword", Toast.LENGTH_SHORT).show();
            isDone = false;
        }else if(!password.equals(repassword)){
            Toast.makeText(Signup.this,"Passwords not matched", Toast.LENGTH_SHORT).show();
            isDone = false;
        }

        if(isDone){
            final ProgressDialog progress;

            progress = new ProgressDialog(this);
            progress.setTitle("Please Wait!!");
            progress.setMessage("Authenticating!!");
            progress.setCancelable(true);
            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progress.show();

            String[] arrayOfValue = new String[4];
            arrayOfValue[0] = fullName;
            arrayOfValue[1] = userEmail;
            arrayOfValue[2] = password;
            arrayOfValue[3] = userGender;

//            myAwesomeTextView.setText(fullName+" - "+userEmail+" - "+password+" - "+userGender);
            new SignupPOST().execute(arrayOfValue);

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
}
