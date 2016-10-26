package com.example.sajitha.tmt;

import android.app.Activity;
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

public class PassengerDestination extends AsyncTask<String,Void,String> {
    Context context;
    private Activity activity;
    double dest_lat,dest_lng;
    public PassengerDestination(Context context, Activity activity){
        this.context = context;
        this.activity = activity;
    }

    @Override
    protected String doInBackground(String... params) {

        try{
            dest_lat = Float.parseFloat(params[0]);
            dest_lng = Float.parseFloat(params[1]);
            //send using post method
            String latitude1 = (String)params[0];
            String longitude1 = (String)params[1];
            String userid = (String)params[2];
            String clatitude1 = (String)params[3];
            String clongitude1 = (String)params[4];
            //double latitude = Double.parseDouble(latitude1);
            //double longitude = Double.parseDouble(longitude1);

            Log.i("Lati -",latitude1);
            Log.i("Long -",longitude1);

            String link="http://hydrosaver.azurewebsites.net/takemethere/php/passengerConfirmDestination.php";
            String data  = URLEncoder.encode("latitude", "UTF-8") + "=" + URLEncoder.encode(latitude1, "UTF-8");
            data += "&" + URLEncoder.encode("longitude", "UTF-8") + "=" + URLEncoder.encode(longitude1, "UTF-8");
            data += "&" + URLEncoder.encode("userid", "UTF-8") + "=" + URLEncoder.encode(userid, "UTF-8");
            data += "&" + URLEncoder.encode("clatitude", "UTF-8") + "=" + URLEncoder.encode(clatitude1, "UTF-8");
            data += "&" + URLEncoder.encode("clongitude", "UTF-8") + "=" + URLEncoder.encode(clongitude1, "UTF-8");

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
        Log.i("Desti - success",result);

        if(result.equals("done")){

            Intent intent = new Intent(context,PassengerSetActivity.class);
            intent.putExtra("dest_lat",dest_lat);
            intent.putExtra("dest_lng",dest_lng);
            context.startActivity(intent);
        }else{

        }

    }

}
