package com.example.sajitha.tmt;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
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
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DriveModeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private List<PassengerRequest> requested_passenger = new ArrayList<PassengerRequest>();
    private GoogleMap mMap;
    Context context;
    Activity activity;
    LoginSession sessionLogin;
    private Marker mPositionMarker;
    private Marker userMarker=null;
    SharedPreferences sharedPreferences;
    LatLng current,destination;
    ArrayList<LatLng> markerPoints;
    TextView tvDistanceDuration,tvDistanceDuration1;
    double dest_lat,dest_lng;
    boolean isPathSet=false;
    boolean isCentered=false;
    DriveModeActivity dModeActivity;
    int[] driverId;
    String passengerID = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drive_mode_activity);
        context = this;
        activity = this;
        dModeActivity = this;
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
        tvDistanceDuration = (TextView) findViewById(R.id.textView7);
        tvDistanceDuration1 = (TextView) findViewById(R.id.textView80);

        // Initializing
        markerPoints = new ArrayList<LatLng>();

        // Getting reference to SupportMapFragment of the activity_main
        SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        // Getting Map for the SupportMapFragment
        mMap = fm.getMap();
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(16);
        mMap.animateCamera(zoom);

        Bundle extras = getIntent().getExtras();

        if(extras == null) {
            dest_lat= 0;
            dest_lng= 0;
        } else {
            dest_lat= extras.getDouble("dest_lat");
            dest_lng=  extras.getDouble("dest_lng");
            passengerID =  extras.getString("passenger");

        }
        Log.i("Lat - ", dest_lat+"");
        Log.i("Lang - ", dest_lng+"");
        destination = new LatLng(dest_lat,dest_lng);
        Log.i("Lang -------- ", destination.latitude+"");

        markerPoints.add(destination);

        // Creating MarkerOptions
        MarkerOptions options = new MarkerOptions();

        // Setting the position of the marker
        options.position(destination);
        options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        mMap.addMarker(options);


        final Handler h = new Handler();
        h.postDelayed(new Runnable()
        {
            private long time = 0;

            @Override
            public void run()
            {
                int userid = sessionLogin.sharedpreferences.getInt("userid",0);
                new UpdateCurrentLocation(context).execute(userid+"",current.latitude+"",current.longitude+"");
                new GetRequestedPassengers(context,dModeActivity).execute(userid+"");

                if(passengerID != null){
                    Log.i("Passenger Id", passengerID+"");
                    new GetPassengersForDriver(context,dModeActivity).execute(passengerID+"");
                }
                time += 2000;
                Log.d("TimerExample", "Going for... " + time);
                h.postDelayed(this, 2000);
            }
        }, 1000);

        //generatePassengerRequestList();
        //displayPassengerRequestList();
    }

    @Override
    public void onBackPressed() {
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
                Toast.makeText(DriveModeActivity.this, "You have to accept to enjoy all app's services!", Toast.LENGTH_LONG).show();
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
                        current = new LatLng(arg0.getLatitude(), arg0.getLongitude());
                        if(!isPathSet){
                            String url = getDirectionsUrl(current, destination);

                            DownloadTask downloadTask = new DownloadTask();

                            // Start downloading json data from Google Directions API
                            downloadTask.execute(url);
                            isPathSet = true;


                        }
                        CameraUpdate center = CameraUpdateFactory.newLatLng(current);

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
                                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.pasenger)));
                        }else{
                            mPositionMarker = mMap.addMarker(new MarkerOptions()
                                    .position(current)
                                    .title("My Location")
                                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.appicon)));
                        }


                        //animateMarker(mPositionMarker, arg0);
                        if(!isCentered){
                            mMap.moveCamera(center);
                            isCentered = true;
                        }

                        //mMap.animateCamera(zoom);

                    }
                });

            }
        }
    }

    private String getDirectionsUrl(LatLng origin, LatLng dest) {

        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;

        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;

        // Sensor enabled
        String sensor = "sensor=false";

        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + sensor;

        // Output format
        String output = "json";

        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters;

        return url;
    }

    /**
     * A method to download json data from url
     */
    private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(strUrl);

            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();

            // Connecting to url
            urlConnection.connect();

            // Reading data from url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            data = sb.toString();

            br.close();

        } catch (Exception e) {
            //Log.d("Exception while downloading url", e.toString());
        } finally {
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }

    // Fetches data from url passed
    private class DownloadTask extends AsyncTask<String, Void, String> {

        // Downloading data in non-ui thread
        @Override
        protected String doInBackground(String... url) {

            // For storing data from web service
            String data = "";

            try {
                // Fetching the data from web service
                data = downloadUrl(url[0]);
            } catch (Exception e) {
                Log.d("Background Task", e.toString());
            }
            return data;
        }

        // Executes in UI thread, after the execution of
        // doInBackground()
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            ParserTask parserTask = new ParserTask();

            // Invokes the thread for parsing the JSON data
            parserTask.execute(result);
        }
    }

    /**
     * A class to parse the Google Places in JSON format
     */
    private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {

        // Parsing the data in non-ui thread
        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {

            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try {
                jObject = new JSONObject(jsonData[0]);
                DirectionsJSONParser parser = new DirectionsJSONParser();

                // Starts parsing data
                routes = parser.parse(jObject);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return routes;
        }

        // Executes in UI thread, after the parsing process
        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> result) {
            ArrayList<LatLng> points = null;
            PolylineOptions lineOptions = null;
            MarkerOptions markerOptions = new MarkerOptions();
            String distance = "";
            String duration = "";

            if (result.size() < 1) {
                Toast.makeText(getBaseContext(), "No Destination Detected", Toast.LENGTH_SHORT).show();
                return;
            }

            // Traversing through all the routes
            for (int i = 0; i < result.size(); i++) {
                points = new ArrayList<LatLng>();
                lineOptions = new PolylineOptions();

                // Fetching i-th route
                List<HashMap<String, String>> path = result.get(i);

                // Fetching all the points in i-th route
                for (int j = 0; j < path.size(); j++) {
                    HashMap<String, String> point = path.get(j);

                    if (j == 0) {    // Get distance from the list
                        distance = (String) point.get("distance");
                        continue;
                    } else if (j == 1) { // Get duration from the list
                        duration = (String) point.get("duration");
                        continue;
                    }

                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    LatLng position = new LatLng(lat, lng);

                    points.add(position);
                }

                // Adding all the points in the route to LineOptions
                lineOptions.addAll(points);
                lineOptions.width(8);
                lineOptions.color(Color.RED);
            }

            tvDistanceDuration.setText(distance);
            tvDistanceDuration1.setText(duration);

            // Drawing polyline in the Google Map for the i-th route
            mMap.addPolyline(lineOptions);
        }
    }

    //Navigation BAr
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

    public void displayPassengerRequestList() {
        ArrayAdapter<PassengerRequest> adapter = new MyListAdapter();
        ListView listview = (ListView)findViewById(R.id.passenger_request_list);
        listview.setAdapter(adapter);
    }
    String [] names;
    int [] passengerIds;
    public void generatePassengerRequestList(JSONArray jobj) {
        names = new String[jobj.length()];
        //driverId = new int[jobj.length()];
        passengerIds = new int[jobj.length()];
        for(int i=0;i<jobj.length();i++) {
            try {

                JSONObject object = jobj.getJSONObject(i);
                names[i] = object.getString("user_name");
                //driverId[i] = object.getInt("user_id");
                passengerIds[i] = object.getInt("user_id");
                requested_passenger = new ArrayList<PassengerRequest>();
                requested_passenger.add(new PassengerRequest(object.getInt("user_id"), object.getString("user_name"), 54545, object.getString("user_gender"),(i+3)%5,56,5));
            } catch (Exception e) {
                Log.i("Error", e.getMessage());
            }
        }
        LinearLayout lls = (LinearLayout)findViewById(R.id.requestListArea);
        //Toast.makeText(this,Integer.toString(lls.getId()),Toast.LENGTH_LONG);
        Log.i("Layout - :::::",jobj.length()+"");
        if(jobj.length()==0){
            lls.setVisibility(View.INVISIBLE);
        }else{
            lls.setVisibility(View.VISIBLE);
        }
    }

    public void showPassengers(JSONObject object){
        try{
            //JSONObject object = jobj.getJSONObject(0);
            double lat = object.getDouble("current_lat");
            double lng  = object.getDouble("current_lng");
            LatLng palce1 = new LatLng(lat,lng);
            if(userMarker!=null){
                userMarker.remove();
            }
            userMarker = mMap.addMarker(new MarkerOptions()
                    .position(palce1)
                    .title("Your Passenger")
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.pasenger)));
        }catch (Exception e){

        }



    }

    private class MyListAdapter extends ArrayAdapter<PassengerRequest> {

        public MyListAdapter(){
            super(DriveModeActivity.this,R.layout.passenger_request_list_item,requested_passenger);
        }

        /**
         * {@inheritDoc}
         *
         * @param position
         * @param convertView
         * @param parent
         */
        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemview = convertView;
            if (itemview==null){
                itemview = getLayoutInflater().inflate(R.layout.passenger_request_list_item,parent,false);
            }
            PassengerRequest cp = requested_passenger.get(position);
            TextView txtname = (TextView)itemview.findViewById(R.id.txtNameValue);
            txtname.setText(cp.getName());

            PassengerRequest current_passenger = requested_passenger.get(position);
            itemview.setId(position);
            itemview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("saved","Done");
                    Intent intent = new Intent(context,PassengerApprovedByDriverActivity.class);
                    intent.putExtra("driverName",names[v.getId()]);
                    intent.putExtra("passengerid",passengerIds[v.getId()]);
                    intent.putExtra("dest_lat",dest_lat);
                    intent.putExtra("dest_lng",dest_lng);
                    //intent.putExtra("cname",);
                    context.startActivity(intent);
                }
            });
            return itemview;
        }
    }


}
