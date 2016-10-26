package com.example.sajitha.tmt;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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

public class PoliceActivity extends AppCompatActivity implements OnMapReadyCallback,NavigationView.OnNavigationItemSelectedListener {
    private GoogleMap mMap;
    private Marker mPositionMarker;
    Context context;
    SharedPreferences sharedPreferences;
    LoginSession sessionLogin;
    boolean isCentered,userMode=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.police_activity);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        context = this;

        sessionLogin = new LoginSession(context);
        sharedPreferences = sessionLogin.sharedpreferences;

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

        setUpMapIfNeeded();

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng palce1 = new LatLng(6.8890253,79.8532312);
        mPositionMarker = mMap.addMarker(new MarkerOptions()
                .position(palce1)
                .title("Wellawatte Police Station")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.police)));

        LatLng palce2 = new LatLng(6.8790253,79.8532312);
        mPositionMarker = mMap.addMarker(new MarkerOptions()
                .position(palce2)
                .title("Bambalapitiya Police Station")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.police)));

        LatLng palce3 = new LatLng(6.8790253,79.8532312);
        mPositionMarker = mMap.addMarker(new MarkerOptions()
                .position(palce3)
                .title("Narahenpita Police Station")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.police)));

        LatLng palce4 = new LatLng(6.9534142,79.8532312);
        mPositionMarker = mMap.addMarker(new MarkerOptions()
                .position(palce4)
                .title("Kotahena Police Station")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.police)));

        LatLng palce5 = new LatLng(6.9205238,79.8584123);
        mPositionMarker = mMap.addMarker(new MarkerOptions()
                .position(palce5)
                .title("Peliyagoda Police Station")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.police)));

        LatLng palce6 = new LatLng(6.9534142,79.8532312);
        mPositionMarker = mMap.addMarker(new MarkerOptions()
                .position(palce6)
                .title("Sri Lanka Police Headquarters")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.police)));

        LatLng palce7 = new LatLng(6.9534142,79.8532312);
        mPositionMarker = mMap.addMarker(new MarkerOptions()
                .position(palce7)
                .title("City Traffic Police Station")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.police)));

        LatLng palce8 = new LatLng(6.9274247,79.8316841);
        mPositionMarker = mMap.addMarker(new MarkerOptions()
                .position(palce8)
                .title("Slave Island Police Station")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.police)));

        LatLng palce9 = new LatLng(6.9292349,79.8794954);
        mPositionMarker = mMap.addMarker(new MarkerOptions()
                .position(palce9)
                .title("Cinnamon Gardens Police Station")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.police)));

        LatLng palce10 = new LatLng(6.9292349,79.8794954);
        mPositionMarker = mMap.addMarker(new MarkerOptions()
                .position(palce10)
                .title("Maradana Police Station")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.police)));

        LatLng palce11 = new LatLng(6.9292349,79.8794954);
        mPositionMarker = mMap.addMarker(new MarkerOptions()
                .position(palce11)
                .title("Welikada Police Station")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.police)));
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
                Toast.makeText(PoliceActivity.this, "You have to accept to enjoy all app's services!", Toast.LENGTH_LONG).show();
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

                        //String x = Integer.toString(userid);
                        if(!userMode){

                        }else{
                            mPositionMarker = mMap.addMarker(new MarkerOptions()
                                    .position(current)
                                    .title("My Location")
                                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.appicon)));
                        }

                        if(!isCentered){
                            mMap.moveCamera(center);
                            mMap.animateCamera(zoom);
                            isCentered = true;
                        }

                    }
                });

            }
        }
    }

    //Navigation bar
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


