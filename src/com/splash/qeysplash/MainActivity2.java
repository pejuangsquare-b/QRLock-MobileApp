package com.splash.qeysplash;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.splash.qeysplash.CustomHttpClient;
import com.splash.qeysplash.R;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends ActionBarActivity {

	private TextView txtHasil, txtScan, txtTitle;
	private Button btnScan, btnLock;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//fullscreen
		if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
          
        }
		//!fullscreen!
		
		//min SDK req
        
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = 
            new StrictMode.ThreadPolicy.Builder().permitAll().build();      
                StrictMode.setThreadPolicy(policy);
         }
         //!SDK!
        
		setContentView(R.layout.activity_main2);
		
        
        btnScan		= (Button)findViewById(R.id.btnScan);
        btnLock		= (Button)findViewById(R.id.btnLock);
        
        //font        
        String fontPath ="fonts/BPdotsLight.otf";
        txtTitle	= (TextView)findViewById(R.id.txtTitle);
        Typeface tf= Typeface.createFromAsset(getAssets(), fontPath);
        txtTitle.setTypeface(tf);
             
        String lainFont ="fonts/CaviarDreams.ttf";
        txtHasil	= (TextView)findViewById(R.id.txtHasil);
        txtScan		= (TextView)findViewById(R.id.txtScan);
        Typeface ft= Typeface.createFromAsset(getAssets(), lainFont);
        txtHasil.setTypeface(ft);
        txtScan.setTypeface(ft);
        //!font!
        
        
        btnScan.setOnClickListener(new OnClickListener() {
			
        	@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
        		try {
					
					Intent intent = new Intent("com.google.zxing.client.android.SCAN");
					intent.putExtra("SCAN_MODE", "QR_CODE_MODE,PRODUCT_MODE");
					startActivityForResult(intent, 0);
				
				} catch (Exception e) {
					
					e.printStackTrace();
					Toast.makeText(getApplicationContext(), "ERROR:" + e, 1).show();

				}
			}
        });
        
        
        
        
        
        btnLock.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String lock = "lock";
				try {
			        java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			        byte[] array = md.digest(lock.getBytes());
			        StringBuffer sb = new StringBuffer();
			        for (int i = 0; i < array.length; ++i) {
			          sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
			          
			        }
			        
			        //POST
					ArrayList<NameValuePair> postparameter= new ArrayList<NameValuePair>();
					postparameter.add(new BasicNameValuePair("lock", sb.toString()));
					String respon = null;
					
					try{
						respon=CustomHttpClient.executeHttpPost("alamat domain", postparameter);
						String res=respon.toString();
						res=res.trim();
						res=res.replace("\\s", "");
						if(res.equals("1")){
							Toast.makeText(getApplicationContext(),"PINTU TERKUNCI" , Toast.LENGTH_LONG).show();
						}else{
							Toast.makeText(getApplicationContext(),"PINTU TERKUNCI" , Toast.LENGTH_LONG).show();
						}
					}catch(Exception e){
						Toast.makeText(getApplicationContext(), "Error :"+ e.toString(), Toast.LENGTH_LONG).show();
	                }
					//!POST!
			        
			    } catch (java.security.NoSuchAlgorithmException e) {
			    	e.printStackTrace();
			    }
			}
		});
        
        
       
	}

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if (requestCode == 0) {

			if (resultCode == RESULT_OK) {
					
				
				
				txtHasil.setText(intent.getStringExtra("SCAN_RESULT_FORMAT"));
				//txtScan.setText(intent.getStringExtra("SCAN_RESULT"));				
				String txtContent = intent.getStringExtra("SCAN_RESULT")+"1727";
				
				//encrypt
				
				try {
			        java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			        byte[] array = md.digest(txtContent.getBytes());
			        StringBuffer str = new StringBuffer();
			        for (int i = 0; i < array.length; ++i) {
			          str.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
			          
			        }			        
					
			        //1727
			        
					//POST				
					ArrayList<NameValuePair> postparameter= new ArrayList<NameValuePair>();
					postparameter.add(new BasicNameValuePair("txtHasil", txtHasil.getText().toString()));
					postparameter.add(new BasicNameValuePair("txtScan", str.toString()));
					String respon=null;
												
					try{
						respon=CustomHttpClient.executeHttpPost("alamat domain", postparameter);
						String res=respon.toString();
						res=res.trim();
						res=res.replace("\\s", "");
						if(res.equals("1")){
							
							Toast.makeText(getApplicationContext(),str.toString() , Toast.LENGTH_LONG).show();
						}else{
							Toast.makeText(getApplicationContext(),str.toString() , Toast.LENGTH_LONG).show();
						}
					}catch(Exception e){
						Toast.makeText(getApplicationContext(), "Error :"+ e.toString(), Toast.LENGTH_LONG).show();
	                }
					//!END!
			        
			        
			        
			        
			        
				} catch (java.security.NoSuchAlgorithmException e) {
			    	e.printStackTrace();
			    }
				
				//!encrypt!
					
				
			} else if (resultCode == RESULT_CANCELED) {
				Toast.makeText(this, "SCAN CANCELED", Toast.LENGTH_LONG).show();
			}
		}
	}
}
