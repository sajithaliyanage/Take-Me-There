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

public class SigninPOST extends AsyncTask<String,Void,String> {
    Context context;
    private Activity activity;

    public SigninPOST(Context context,Activity activity){
        this.context = context;
        this.activity = activity;
    }

    @Override
    protected String doInBackground(String... params) {

        try{
            //send using post method
            String email = (String)params[0];
            String password = (String)params[1];
            Log.i("FullName",email);
            Log.i("email",password);

            String link="http://takeyourleave.azurewebsites.net/android/login.php";
            String data  = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8");
            data += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");

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
        Log.i("Loggin - success", values[0]);
        //Log.i("Loggin2 - success", values[1]);

        if(values[0].equals("done")){
            //int userID = Integer.parseInt(values[1]);
            Log.i("Loggin","All set");
            int userid = Integer.parseInt(values[1]);

            LoginSession sessionLogin = new LoginSession(context);
            sessionLogin.setLogged(true,userid);

            Intent intent = new Intent(context,SelectRoleActivity.class);;
            context.startActivity(intent);
        }else{
            Toast.makeText(activity,"Invalid username or password" , Toast.LENGTH_LONG).show();

            activity.startActivity(new Intent(activity, LoginActivity.class));

            //Toast.makeText(getApplicationContext(),"My status is"+result,Toast.LENGTH_SHORT).show();
            //Toast.makeText(LoginActivity.this,"Invalid username or password", Toast.LENGTH_SHORT).show();
        }

    }

}
