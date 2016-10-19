package com.example.sajitha.tmt;

import android.content.Context;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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
        int userid = sessionLogin.sharedpreferences.getInt("userid",0);
        String x = Integer.toString(userid);

        TextView myAwesomeTextView = (TextView)findViewById(R.id.textView2);
        myAwesomeTextView.setText(x);
    }

}
