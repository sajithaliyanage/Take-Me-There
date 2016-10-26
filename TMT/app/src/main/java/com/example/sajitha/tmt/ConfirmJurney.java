package com.example.sajitha.tmt;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class ConfirmJurney extends AsyncTask<String,Void,String> {
    Context context;
    private Activity activity;
    double dest_lat,dest_lng;

    public ConfirmJurney(Context context, Activity activity, double dest_lat, double dest_lng){
        this.context = context;
        this.activity = activity;
        this.dest_lat=dest_lat;
        this.dest_lng=dest_lng;
    }

    @Override
    protected String doInBackground(String... params) {

        try{
            //send using post method
            String userid = (String)params[0];
            String total = (String)params[1];
            String available = (String)params[2];
            //double latitude = Double.parseDouble(latitude1);
            //double longitude = Double.parseDouble(longitude1);

            String link="http://hydrosaver.azurewebsites.net/takemethere/php/confirmRide.php";
            String data  = URLEncoder.encode("userid", "UTF-8") + "=" + URLEncoder.encode(userid, "UTF-8");
            data += "&" + URLEncoder.encode("total", "UTF-8") + "=" + URLEncoder.encode(total, "UTF-8");
            data += "&" + URLEncoder.encode("available", "UTF-8") + "=" + URLEncoder.encode(available, "UTF-8");

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

            final ProgressDialog progress;
            progress = new ProgressDialog(context);
            progress.setTitle("Please Wait!!");
            progress.setMessage("Loading Your Drive Mode!!");
            progress.setCancelable(true);
            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progress.show();

            new CountDownTimer(3000, 1000) {
                public void onFinish() {
                    progress.dismiss();
                    Intent intent = new Intent(context,DriveModeActivity.class);
                    //intent.putExtra()
                    intent.putExtra("dest_lat",dest_lat);
                    intent.putExtra("dest_lng",dest_lng);
                    context.startActivity(intent);
                }

                public void onTick(long millisUntilFinished) {
                    progress.show();
                }
            }.start();

            //Intent intent = new Intent(context,DriveModeActivity.class);;
            //context.startActivity(intent);
        }else{

        }

    }

}
