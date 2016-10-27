package com.example.sajitha.tmt;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FilterVehiclesActivity extends AppCompatActivity{
    private List<Vehicle> selected_vehicles;
    Context context;
    LoginSession sessionLogin;
    SharedPreferences sharedPreferences;
    String[] names;
    int[] driverId;
    int userid;
    FilterVehiclesActivity fva;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_vehicles);
        //generateVehicleList();
        //displayVehicleList();
        context = this;
        sessionLogin = new LoginSession(context);
        sharedPreferences = sessionLogin.sharedpreferences;
        fva = this;
        userid = sessionLogin.sharedpreferences.getInt("userid",0);


        final Handler h = new Handler();
        h.postDelayed(new Runnable()
        {
            private long time = 0;

            @Override
            public void run()
            {

                //int userid = sessionLogin.sharedpreferences.getInt("userid",0);
                new GetPossibleVehicles(context,fva).execute(userid+"");

                //new UpdateCurrentLocation(context).execute(userid+"",current.latitude+"",current.longitude+"");
                //new GetRequestedPassengers(context,).execute(userid+"");
                time += 2000;
                Log.d("TimerExample", "Going for... " + time);
                h.postDelayed(this, 2000);
            }
        }, 1000);
    }

    public void displayVehicleList() {
        ArrayAdapter<Vehicle> adapter = new MyListAdapter();
        ListView listview = (ListView)findViewById(R.id.vehicle_list);
        listview.setAdapter(adapter);
    }

    public void generateVehicleList(JSONArray jobj) {
        names = new String[jobj.length()];
        driverId = new int[jobj.length()];
        selected_vehicles = new ArrayList<Vehicle>();
        for(int i=0;i<jobj.length();i++){
            try{
                JSONObject object = jobj.getJSONObject(i);
                names[i] = object.getString("name");
                driverId[i] = object.getInt("user_id");
                selected_vehicles.add(new Vehicle(object.getString("name"),object.getString("user_gender"),i+4,object.getInt("user_id"), Float.parseFloat("48.854"),true));
            }catch (Exception e){
                Log.i("Error",e.getMessage());
            }

        }

//        selected_vehicles.add(new Vehicle("BMW","M5",4,65564, Float.parseFloat("48.854")));
//        selected_vehicles.add(new Vehicle("AUDI","M5",4,2232, Float.parseFloat("48.854")));
//        selected_vehicles.add(new Vehicle("MERCEDES BENZ","E300",4,343123, Float.parseFloat("48.854")));
//        selected_vehicles.add(new Vehicle("TOYOTA","PRIUS",4,3532, Float.parseFloat("48.854")));
//        selected_vehicles.add(new Vehicle("TOYOTA","PRIUS",4,3532, Float.parseFloat("48.854")));

    }

    private class MyListAdapter extends ArrayAdapter<Vehicle>{
        public MyListAdapter(){
            super(FilterVehiclesActivity.this,R.layout.vehicle_list_item,selected_vehicles);
        }


        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemview  = convertView;
            if (itemview == null){
                itemview = getLayoutInflater().inflate(R.layout.vehicle_list_item,parent,false);
            }

            Vehicle current_vehicle = selected_vehicles.get(position);
            //Toast.makeText(VehicleList.this,current_vehicle.getMaker(),Toast.LENGTH_LONG).show();
            //System.out.println(current_vehicle.getMaker());
            TextView txt_make = (TextView)itemview.findViewById(R.id.maker_value);
            txt_make.setText(current_vehicle.getMaker());

            TextView txtModel = (TextView)itemview.findViewById(R.id.model_value);
            txtModel.setText(current_vehicle.getModel());

            TextView txtSeating = (TextView)itemview.findViewById(R.id.seating_value);
            txtSeating.setText(Integer.toString(current_vehicle.getAvailable_seats()));

            TextView txt_ac = (TextView)itemview.findViewById(R.id.ac_value);
            txt_ac.setText(current_vehicle.isAc()?"Available":"Unavailable");

            //TextView txt_speed = (TextView)itemview.findViewById(R.id.speed_value);
            //txt_speed.setText(Float.toString(current_vehicle.getAvarage_speed()));

            TextView txt_rate = (TextView)itemview.findViewById(R.id.rating_value);
            txt_rate.setText(Float.toString(current_vehicle.getRate()));
            itemview.setId(position);
            itemview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("saved","Done");
                    Intent intent = new Intent(context,SelectedDriverActivity.class);
                    intent.putExtra("driverName",names[v.getId()]);
                    intent.putExtra("driverId",driverId[v.getId()]);
                    //intent.putExtra("cname",);
                    context.startActivity(intent);
                }
            });
            return itemview;
        }
    }

}
