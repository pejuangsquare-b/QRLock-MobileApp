package com.splash.qeysplash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.splash.qeysplash.MainActivity;
import com.splash.qeysplash.MainActivity2;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.internal.widget.ActionBarOverlayLayout.ActionBarVisibilityCallback;
import android.app.ActionBar;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
	
	private static int SPLASH_TIME_OUT = 4000;
	/*
	private ListView lstView;
	private String jsonResult;
	private String url = "http://raspberrypi.mshome.net:5000/status";
		*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        //fullscreen
        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
          
        }
        //!fullscreen!
        
        /*
        //min SDK req
        
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = 
            new StrictMode.ThreadPolicy.Builder().permitAll().build();      
                StrictMode.setThreadPolicy(policy);
         }
         //!SDK!
        */
        
        
        setContentView(R.layout.activity_main);
        
      //delay
        new Handler().postDelayed(new Runnable() {
       	 
            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(i);
                
                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
        
        //end
        
        
    }
    
}
