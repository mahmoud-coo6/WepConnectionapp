package com.example2.acer.wepconnectionapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    public  class DownloadTask extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... params) {

            //Log.i("URL",params[0]);

            String result="";
            URL url;
            HttpURLConnection urlConnection=null;
            try {
            url=new URL(params[0]);
                urlConnection= (HttpURLConnection) url.openConnection();
                InputStream in=urlConnection.getInputStream();
                InputStreamReader reader=new InputStreamReader(in);
                int data=reader.read();
                while (data != -1){
                    char current=(char)data;
                    result +=current;
                    data=reader.read();

                }
              return result;
            }catch (Exception e){
                e.printStackTrace();
                Toast.makeText(MainActivity.this, "Feild", Toast.LENGTH_SHORT).show();
                return "Feild";
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DownloadTask task=new DownloadTask();
        String result= null;
        try {
            result = task.execute("https://www.ecowebhosting.co.uk/").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Toast.makeText(MainActivity.this, "connect to URL "+result, Toast.LENGTH_SHORT).show();
        Log.i("connect to URL",result);

    }
}
