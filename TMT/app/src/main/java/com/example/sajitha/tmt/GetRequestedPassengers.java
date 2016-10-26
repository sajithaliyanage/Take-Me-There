package com.example.sajitha.tmt;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class GetRequestedPassengers extends AsyncTask<String,Void,String> {
    Context context;
    private DriveModeActivity activity;

    public GetRequestedPassengers(Context context, DriveModeActivity activity){
        this.context = context;
        this.activity = activity;
    }

    @Override
    protected String doInBackground(String... params) {

        try{
            //send using post method
            String userid = (String)params[0];

            String link="http://hydrosaver.azurewebsites.net/takemethere/php/getRequestedPassengers.php";
            String data  = URLEncoder.encode("userid", "UTF-8") + "=" + URLEncoder.encode(userid, "UTF-8");

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
            return new String("Exceptionzzz: " + e.getMessage());
        }

    }

    @Override
    protected void onPostExecute(String result){
        Log.i("Result -",result);
        try{
            JSONArray json  = new JSONArray(result);
            //activity.generateVehicleList(json);
            //activity.displayVehicleList();
        }catch (Exception e){
            Log.i("Result -",e.getMessage());
        }



    }


}
