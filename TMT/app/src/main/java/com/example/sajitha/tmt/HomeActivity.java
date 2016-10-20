package com.example.sajitha.tmt;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback {
    private GoogleMap mMap;
    private Marker mPositionMarker;
    Context context;
    SharedPreferences sharedPreferences;
    LoginSession sessionLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        context = this;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //maps
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        setUpMapIfNeeded();
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        //Log.i("Google Maps","Marker ");
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(6.9029514,79.8608261);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in UCSC"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,14.0f));

    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();

        }
    }

    private void setUpMapIfNeeded() {
        Log.i("Google Maps","Weda");
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                mMap.setMyLocationEnabled(true);
            } else {
                Toast.makeText(HomeActivity.this, "You have to accept to enjoy all app's services!", Toast.LENGTH_LONG).show();
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
                    mMap.setMyLocationEnabled(true);
                }
            }

            if (mMap != null) {


                mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
                    @Override
                    public void onMyLocationChange(Location arg0) {
                        Log.i("Google Maps","location changed");
                        // TODO Auto-generated method stub
                        LatLng current = new LatLng(arg0.getLatitude(), arg0.getLongitude());
                        CameraUpdate center = CameraUpdateFactory.newLatLng(current);
                        CameraUpdate zoom = CameraUpdateFactory.zoomTo(16);
                        if(mPositionMarker!=null){
                            mPositionMarker.remove();
                        }
                        sessionLogin = new LoginSession(context);
                        sharedPreferences = sessionLogin.sharedpreferences;

                        boolean userMode = sessionLogin.sharedpreferences.getBoolean("is_vehicle",false);
                        //String x = Integer.toString(userid);
                        if(!userMode){
                            mPositionMarker = mMap.addMarker(new MarkerOptions()
                                    .position(current)
                                    .title("My Location")
                                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.man)));
                        }else{
                            mPositionMarker = mMap.addMarker(new MarkerOptions()
                                    .position(current)
                                    .title("My Location")
                                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.appicon)));
                        }


                        //animateMarker(mPositionMarker, arg0);
                        //mMap.moveCamera(center);
                        mMap.animateCamera(zoom);

                    }
                });

            }
        }
    }

    public void animateMarker(final Marker marker, final Location location) {
        final Handler handler = new Handler();
        final long start = SystemClock.uptimeMillis();
        final LatLng startLatLng = marker.getPosition();
        final double startRotation = marker.getRotation();
        final long duration = 500;

        final Interpolator interpolator = new LinearInterpolator();

        handler.post(new Runnable() {
            @Override
            public void run() {
                long elapsed = SystemClock.uptimeMillis() - start;
                float t = interpolator.getInterpolation((float) elapsed
                        / duration);

                double lng = t * location.getLongitude() + (1 - t)
                        * startLatLng.longitude;
                double lat = t * location.getLatitude() + (1 - t)
                        * startLatLng.latitude;

                float rotation = (float) (t * location.getBearing() + (1 - t)
                        * startRotation);

                marker.setPosition(new LatLng(lat, lng));
                marker.setRotation(rotation);

                if (t < 1.0) {
                    // Post again 16ms later.
                    handler.postDelayed(this, 16);
                }
            }
        });
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
}
