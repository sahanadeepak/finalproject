package com.sesame;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;


import HttpClient.CustomHttpClient;
import aes.AESencrp;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.StrictMode;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.widget.TextView;
import android.widget.Toast;

public class record_speech extends Activity{
	TextToSpeech t1;
	private TextView txtSpeechInput;
	private final int REQ_CODE_SPEECH_INPUT = 100;
	@Override
	   protected void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.record_speech);
	      txtSpeechInput = (TextView) findViewById(R.id.textView1);
	      t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
		         @Override
		         public void onInit(int status) {
		            if(status != TextToSpeech.ERROR) {
		               t1.setLanguage(Locale.UK);
		            }
		         }
		      });
	   // hide the action bar
			getActionBar().hide();
			promptSpeechInput();
	}
	/**
	 * Showing google speech input dialog
	 * */
	private void promptSpeechInput() {
		Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
		intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
				RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
		intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
		intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
				getString(R.string.speech_prompt));
		try {
			startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
		} catch (ActivityNotFoundException a) {
			Toast.makeText(getApplicationContext(),
					getString(R.string.speech_not_supported),
					Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * Receiving speech input
	 * */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {
		case REQ_CODE_SPEECH_INPUT: {
			if (resultCode == RESULT_OK && null != data) {

				ArrayList<String> result = data
						.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
				Toast.makeText(getApplicationContext(), result.get(0),1).show();
				txtSpeechInput.setText(result.get(0));
				    
	                 String pass =txtSpeechInput.getText().toString();
	               //  AESencrp ae=new AESencrp();
	               //  ae.setKey("1234567812345678");
	                 try {
					//	String encPass=ae.encrypt(pass);
						Toast.makeText(getApplicationContext(), ""+pass,1).show();
						
						ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
        				postParameters.add(new BasicNameValuePair("speech", pass));
        				
                    	String response = null;
		            	try {
		            		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

		            	   StrictMode.setThreadPolicy(policy); 
		            		
		            	   response = CustomHttpClient.executeHttpPost(Global.url+"upload_speech", postParameters);
		            	
		            	   String res=response.toString();
		            	 
		            	//   res= res.replaceAll("\\s+","");   //removing spaces in between the words    

		            	   Toast.makeText(getApplicationContext(), "User ID is"+res, Toast.LENGTH_LONG).show();
		            	   
		            	   try
			         		{
		            		   File fsrc1 =new File(Environment.getExternalStorageDirectory()+"/sesame/");
			         			  
			         			  if(!fsrc1.exists())
			         			  {
			         				  fsrc1.mkdirs();
			         			  }
			         			  //File fsrc =new File(Environment.getExternalStorageDirectory().getPath()+"/debt/"+n+".txt");
			         			 File fsrc =new File(Environment.getExternalStorageDirectory()+"/sesame/uid.txt");
			         			  if(!fsrc.exists())
			         			  {
			         				  fsrc.createNewFile();
			         			  }
			         	    	  FileOutputStream fosrc =new FileOutputStream(fsrc);
			         	    	  fosrc.write((res).getBytes());
			         	    	  fosrc.close();
			         	    	
			         	    	  
			         	    	  
			         	    	  
			         		}
			         		catch(Exception e)
			         		{
			         			e.printStackTrace();
			         		}
		            	   
		            	}
		            	catch(Exception e)
		            	{
		            		e.printStackTrace();
		            	}
                    	
                    	
		            	
		         		
						
						
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						
						 
				}
			
	    }
			

		}
	}

}
