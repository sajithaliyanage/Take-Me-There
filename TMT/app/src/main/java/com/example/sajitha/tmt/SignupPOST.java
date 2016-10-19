package com.example.sajitha.tmt;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import android.widget.TextView;
import android.widget.Toast;

public class SignupPOST extends AsyncTask<String,Void,String> {

    private Context mContext;

    @Override
    protected String doInBackground(String... params) {

        try{
            //send using post method
            String username = (String)params[0];
            String email = (String)params[1];
            String password = (String)params[2];
            String gender = (String)params[3];
//            Log.i("FullName",username);
//            Log.i("email",email);

            String link="http://takeyourleave.azurewebsites.net/android/index.php";
            String data  = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");
            data += "&" + URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8");
            data += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
            data += "&" + URLEncoder.encode("gender", "UTF-8") + "=" + URLEncoder.encode(gender, "UTF-8");

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
        String[] values = result.split(",");
        Log.i("Loggin1", values[0]);
        Log.i("Loggin2", values[1]);
        Log.i("Loggin3", values[2]);
        Log.i("Loggin4", values[3]);

        int userID = Integer.parseInt(values[1]);
        if(values[0].equals("Done")){
            LoginSession s = new LoginSession(values[2],userID);

            Intent intent = new Intent(mContext, SelectRoleActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(intent);
        }else{
            //Toast.makeText(Signup.class,"User alrady exsits", Toast.LENGTH_SHORT).show();
        }

    }

}
