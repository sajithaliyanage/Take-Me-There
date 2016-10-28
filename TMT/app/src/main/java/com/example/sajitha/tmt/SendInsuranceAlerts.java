package com.example.sajitha.tmt;

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

public class SendInsuranceAlerts extends AsyncTask<String,Void,String> {
    Context context;

    public SendInsuranceAlerts(Context context){
        this.context = context;

    }

    @Override
    protected String doInBackground(String... params) {

        try{
            //send using post method
            String userid = (String)params[0];
            String latitude = (String)params[1];
            String longitude = (String)params[2];


            String link="http://hydrosaver.azurewebsites.net/takemethere/php/setinsuarence.php";
            String data  = URLEncoder.encode("userid", "UTF-8") + "=" + URLEncoder.encode(userid, "UTF-8");
            data += "&" + URLEncoder.encode("latitude", "UTF-8") + "=" + URLEncoder.encode(latitude, "UTF-8");
            data += "&" + URLEncoder.encode("longitude", "UTF-8") + "=" + URLEncoder.encode(longitude, "UTF-8");

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
       Log.i("Alert Now","###################### TOAST0"+ result);
        Toast.makeText(context, "ALERTED TO NEAREST Insurance Cooparation", Toast.LENGTH_SHORT).show();
        Intent move = new Intent(context,HomeActivity.class);
        context.startActivity(move);
    }

}
