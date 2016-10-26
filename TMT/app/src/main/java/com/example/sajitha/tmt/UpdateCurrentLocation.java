package com.example.sajitha.tmt;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class UpdateCurrentLocation extends AsyncTask<String,Void,String> {
    Context context;
    //private Activity activity;

    public UpdateCurrentLocation(Context context){
        this.context = context;
        //this.activity = activity;
    }

    @Override
    protected String doInBackground(String... params) {

        try{
            //send using post method
            String lat = (String)params[1];
            String lng = (String)params[2];
            String userid = (String)params[0];

            String link="http://hydrosaver.azurewebsites.net/takemethere/php/updateCurrent.php";
            String data  = URLEncoder.encode("lat", "UTF-8") + "=" + URLEncoder.encode(lat, "UTF-8");
            data += "&" + URLEncoder.encode("lng", "UTF-8") + "=" + URLEncoder.encode(lng, "UTF-8");
            data += "&" + URLEncoder.encode("userid", "UTF-8") + "=" + URLEncoder.encode(userid, "UTF-8");

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
        Log.i("Result -",result);
    }


}
