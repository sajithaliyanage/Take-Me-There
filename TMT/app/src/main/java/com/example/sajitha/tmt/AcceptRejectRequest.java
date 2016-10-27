package com.example.sajitha.tmt;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class AcceptRejectRequest extends AsyncTask<String,Void,String> {
    Context context;
    //private Activity activity;
    double dest_lat, dest_lng;
    public AcceptRejectRequest(Context context,double dest_lat,double dest_lng){
        this.context = context;
        //this.activity = activity;
        this.dest_lat = dest_lat;
        this.dest_lng = dest_lng;
    }

    @Override
    protected String doInBackground(String... params) {

        try{
            //send using post method
            String userID = (String)params[0];
            String passengerid = (String)params[1];
            String actionType = (String)params[2];

            String link="http://hydrosaver.azurewebsites.net/takemethere/php/acceptReject.php";
            String data  = URLEncoder.encode("driverid", "UTF-8") + "=" + URLEncoder.encode(userID, "UTF-8");
            data += "&" + URLEncoder.encode("passengerid", "UTF-8") + "=" + URLEncoder.encode(passengerid, "UTF-8");
            data += "&" + URLEncoder.encode("actionType", "UTF-8") + "=" + URLEncoder.encode(actionType, "UTF-8");

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
        Intent intent = new Intent(context,DriveModeActivity.class);
        intent.putExtra("dest_lat",dest_lat);
        intent.putExtra("dest_lng",dest_lng);
        context.startActivity(intent);
    }


}
