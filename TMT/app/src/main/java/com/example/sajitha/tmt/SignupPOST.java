package com.example.sajitha.tmt;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class SignupPOST extends AsyncTask<String,Void,String> {

    @Override
    protected String doInBackground(String... params) {

        try{
            //send using post method
            String username = (String)params[0];
            String email = (String)params[1];
            Log.i("FullName",username);
            Log.i("email",email);

            String link="http://takeyourleave.azurewebsites.net/android/index.php";
            String data  = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");
            data += "&" + URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8");

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

}
