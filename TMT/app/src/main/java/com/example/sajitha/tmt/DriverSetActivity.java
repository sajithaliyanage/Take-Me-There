package com.example.sajitha.tmt;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DriverSetActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener{
    Context context;
    Activity activity;
    LoginSession sessionLogin;
    double dest_lat,dest_lng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.driver_set_activity);
        context = this;
        activity=this;

        //left navigate drawer set
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        Bundle extras = getIntent().getExtras();

        if(extras == null) {
            dest_lat= 0;
            dest_lng= 0;
        } else {
            dest_lat= extras.getDouble("dest_lat");
            dest_lng=  extras.getDouble("dest_lng");

        }

        Button one = (Button) findViewById(R.id.confirmRide);
        one.setOnClickListener(this);

        Button one1 = (Button) findViewById(R.id.addVechicle);
        one1.setOnClickListener(this);

        sessionLogin = new LoginSession(this);
    }

    @Override
    public void onBackPressed() {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        StaticImports.navigate_menu(id,context);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.confirmRide:
                Log.i("Set","Done");
                TextView one = (TextView) findViewById(R.id.total_seats);
                String total = one.getText().toString();

                TextView two = (TextView) findViewById(R.id.available_seats);
                String available = two.getText().toString();

                int userid = sessionLogin.sharedpreferences.getInt("userid",0);
                String[] arrayOfValue = new String[3];
                arrayOfValue[0] = Integer.toString(userid);
                arrayOfValue[1] = total;
                arrayOfValue[2] = available;
                new ConfirmRide(context,activity,dest_lat,dest_lng).execute(arrayOfValue);
                break;
            case R.id.addVechicle:
                Log.i("Add or Remove","Done");
                Intent intent = new Intent(context,AddRemoveVehicle.class);
                context.startActivity(intent);
                break;
        }
    }
}
