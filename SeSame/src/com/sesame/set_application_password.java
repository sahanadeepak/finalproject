package com.sesame;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.rsa.RSA;

import HttpClient.CustomHttpClient;
import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class set_application_password extends Activity{
	TextToSpeech t1;
	public static String eSk="",Pk="";
	public static	BigInteger E, D ,N;
	@Override
	   protected void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.set_application_pass);
	      final Button bset=(Button)findViewById(R.id.button1); 
	      final EditText etgm=(EditText)findViewById(R.id.editTextGM);
	      final EditText etFB=(EditText)findViewById(R.id.editTextFB);
	      
	      bset.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			String gm=etgm.getText().toString();
			String fb=etFB.getText().toString();
			
			if(!gm.equals(""))
			{
				send_key("gmail",gm);
				etgm.setText("");
				
			}
			if(!fb.equals(""))
			{
				send_key("facebook",fb);
				etFB.setText("");
			}
			}
		});
	     
	}
	
	public void send_key(String app,String akey)
	{
		
		String uid="";
		try{
	   //      File fsrc =new File(Environment.getExternalStorageDirectory()+"/sesame/uid.txt");
   

      
             FileInputStream fis1 = new FileInputStream(Environment.getExternalStorageDirectory()+"/sesame/uid.txt");
   	
   	
   			//Toast.makeText(getApplicationContext(), "inside", 1).show();
   			byte b1[]=new byte[fis1.available()];
       		fis1.read(b1);
       		uid=new String(b1);
          fis1.close();
	}catch(Exception e)
	{
		
		
	}
//		String s2="",pk="",sk="";
//		try{
//	         File fsrc =new File(Environment.getExternalStorageDirectory()+"/sesame/rsa.txt");
//  
//
//     
//            FileInputStream fis1 = new FileInputStream(Environment.getExternalStorageDirectory()+"/sesame/rsa.txt");
//  	
//  	
//  			//Toast.makeText(getApplicationContext(), "inside", 1).show();
//  			byte b1[]=new byte[fis1.available()];
//      		fis1.read(b1);
//      		s2=new String(b1);
//fis1.close();
//	}catch(Exception e)
//	{
//		
//		
//	}
//		String rKey[]=s2.split("-");
//		sk=rKey[0];
//		pk=rKey[1];
//
//		
		BigInteger[] ciphertext = RSA.encrypt( akey ,E,N) ;
	
		String enc="";
		
		for(int i=0;i<ciphertext.length;i++)
		{
			System.out.println(">"+i+">>>>>>"+ciphertext[i]);
			enc+=ciphertext[i]+"-";
		
		}
		ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
		postParameters.add(new BasicNameValuePair("uid", uid));
		postParameters.add(new BasicNameValuePair("app", app));
		postParameters.add(new BasicNameValuePair("eakey", enc));//ciphertext.toString()
	//	postParameters.add(new BasicNameValuePair("sp", pk));
		
    	String response = null;
    	try {
    		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

    	   StrictMode.setThreadPolicy(policy); 
    		
    	   response = CustomHttpClient.executeHttpPost(Global.url+"receiveAppDet", postParameters);
    	
    	   String res=response.toString();
    	 
    	//   res= res.replaceAll("\\s+","");   //removing spaces in between the words    

    	   Toast.makeText(getApplicationContext(), res, Toast.LENGTH_LONG).show();
    	   
    	  
    	   
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}

		
	}

}
