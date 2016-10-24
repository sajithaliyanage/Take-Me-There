package com.example.sajitha.tmt;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class DriverDestination extends AsyncTask<String,Void,String> {
    Context context;
    private Activity activity;

    public DriverDestination(Context context,Activity activity){
        this.context = context;
        this.activity = activity;
    }

    @Override
    protected String doInBackground(String... params) {

        try{
            //send using post method
            String latitude1 = (String)params[0];
            String longitude1 = (String)params[1];
            String userid = (String)params[2];
            //double latitude = Double.parseDouble(latitude1);
            //double longitude = Double.parseDouble(longitude1);

            Log.i("Lati -",latitude1);
            Log.i("Long -",longitude1);

            String link="http://hydrosaver.azurewebsites.net/takemethere/php/confirmDestination.php";
            String data  = URLEncoder.encode("latitude", "UTF-8") + "=" + URLEncoder.encode(latitude1, "UTF-8");
            data += "&" + URLEncoder.encode("longitude", "UTF-8") + "=" + URLEncoder.encode(longitude1, "UTF-8");
            data += "&" + URLEncoder.encode("userd", "UTF-8") + "=" + URLEncoder.encode(userid, "UTF-8");

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

            Intent intent = new Intent(context,DriverSetActivity.class);;
            context.startActivity(intent);
        }else{

        }

    }

}