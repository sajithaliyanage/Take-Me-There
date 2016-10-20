package com.teamwhileloop.my_uis;

import android.support.annotation.FloatRange;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class VehicleList extends AppCompatActivity {
    private List<Vehicle> selected_vehicles = new ArrayList<Vehicle>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_list);
        generateVehicleList();
        displayVehicleList();
    }

    private void displayVehicleList() {
        ArrayAdapter<Vehicle> adapter = new MyListAdapter();
        ListView listview = (ListView)findViewById(R.id.vehicle_list);
        listview.setAdapter(adapter);
    }

    private void generateVehicleList() {
        selected_vehicles.add(new Vehicle("BMW","M5",4,65564, Float.parseFloat("48.854")));
        selected_vehicles.add(new Vehicle("AUDI","M5",4,2232, Float.parseFloat("48.854")));
        selected_vehicles.add(new Vehicle("MERCEDES BENZ","E300",4,343123, Float.parseFloat("48.854")));
        selected_vehicles.add(new Vehicle("TOYOTA","PRIUS",4,3532, Float.parseFloat("48.854")));

    }

    private class MyListAdapter extends ArrayAdapter<Vehicle>{
        public MyListAdapter(){
            super(VehicleList.this,R.layout.vehicle_list_item,selected_vehicles);
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
            txt_ac.setText(Boolean.toString(current_vehicle.isAc()));

            TextView txt_speed = (TextView)itemview.findViewById(R.id.speed_value);
            txt_speed.setText(Float.toString(current_vehicle.getAvarage_speed()));

            TextView txt_rate = (TextView)itemview.findViewById(R.id.rating_value);
            txt_rate.setText(Float.toString(current_vehicle.getRate()));

            return itemview;
        }
    }
}
