package com.example.sajitha.tmt;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class GetPassengersForDriver extends AsyncTask<String,Void,String> {
    Context context;
    DriveModeActivity activity;
    String passengerid;
    public GetPassengersForDriver(Context context, DriveModeActivity activity){
        this.context = context;
        this.activity = activity;

    }

    @Override
    protected String doInBackground(String... params) {

        try{
            //send using post method
            passengerid = (String)params[0];


            String link="http://hydrosaver.azurewebsites.net/takemethere/php/getuserlocation.php";
            String data  = URLEncoder.encode("passengerid", "UTF-8") + "=" + URLEncoder.encode(passengerid, "UTF-8");


            URL url = new URL(link);
            URLConnection conn = url.openConnection();

            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

            wr.write( data );
            wr.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String line = null;

            // Read Server Response
            while((line = reader.readLine()) != null)
            {
                sb.append(line);
                break;
            }
            return sb.toString();

        }
        catch(Exception e){
            return new String("Exception: " + e.getMessage());
        }

    }

    @Override
    protected void onPostExecute(String result){
       Log.i("User Location","###################### :::::::"+passengerid+"::::::::"+ result);
        try{
            //JSONArray json  = new JSONArray(result);
            JSONObject json = new JSONObject(result);
            activity.showPassengers(json);
        }catch (Exception e){
            Log.i("Result -",e.getMessage());
        }
    }

}
