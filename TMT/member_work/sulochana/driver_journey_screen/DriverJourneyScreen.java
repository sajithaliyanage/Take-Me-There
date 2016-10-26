package com.teamwhileloop.my_uis;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class DriverJourneyScreen extends AppCompatActivity {
    private List<PassengerRequest> requested_passenger = new ArrayList<PassengerRequest>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_journey_screen);
        generatePassengerRequestList();
        displayPassengerRequestList();
    }

    private void displayPassengerRequestList() {
        ArrayAdapter<PassengerRequest> adapter = new MyListAdapter();
        ListView listview = (ListView)findViewById(R.id.passenger_request_list);
        listview.setAdapter(adapter);
    }

    private void generatePassengerRequestList() {
        requested_passenger.add(new PassengerRequest(4545,"sulochana",54545,"Male",4.5,56,5));
        requested_passenger.add(new PassengerRequest(4545,"sulochana1",54545,"Male",4.5,56,5));
        requested_passenger.add(new PassengerRequest(4545,"sulochana2",54545,"Male",4.5,56,5));
        requested_passenger.add(new PassengerRequest(4545,"sulochana3",54545,"Male",4.5,56,5));
        requested_passenger.add(new PassengerRequest(4545,"sulochana4",54545,"Male",4.5,56,5));
        requested_passenger.add(new PassengerRequest(4545,"sulochana5",54545,"Male",4.5,56,5));
        requested_passenger.add(new PassengerRequest(4545,"sulochana6",54545,"Male",4.5,56,5));
    }

    private class MyListAdapter extends ArrayAdapter<PassengerRequest>{

        public MyListAdapter(){
            super(DriverJourneyScreen.this,R.layout.passenger_request_list_item,requested_passenger);
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

            PassengerRequest current_passenger = requested_passenger.get(position);
            return itemview;
        }
    }
}

/*
*
* <LinearLayout
                android:orientation="horizontal"
                android:layout_width="match_parent"
                android:layout_height="match_parent">

                <TextView
                    android:text="12 seats"
                    android:gravity="center_vertical"
                    android:layout_weight="1"
                    android:layout_width="0dp"
                    android:layout_height="match_parent" />

                <TextView
                    android:text="12.9 km"
                    android:gravity="center_vertical"
                    android:layout_weight="1"
                    android:layout_width="0dp"
                    android:layout_height="match_parent" />

                <TextView
                    android:text="Female"
                    android:gravity="center_vertical"
                    android:layout_weight="1"
                    android:layout_width="0dp"
                    android:layout_height="match_parent" />
*
*
* */