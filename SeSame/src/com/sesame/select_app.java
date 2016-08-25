package com.sesame;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.rsa.RSA;

import scottyab.aescrypt.AESCrypt;

import HttpClient.CustomHttpClient;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class select_app extends Activity{
	TextToSpeech t1;
	public static String eSk="",ePk="",k1="",sk="",pk="";
	 String uid="";
		public static	BigInteger E, D ,N;

	@Override
	   protected void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.select_app);
	      final Button bg=(Button)findViewById(R.id.button1); 
	      final Button bf=(Button)findViewById(R.id.button2);
	     
//	      final Intent nextCP=new Intent(this,color_password.class);
//	      final Intent nextSP=new Intent(this,set_application_password.class);
//	      final Intent nextCapture=new Intent(this,capture_speech.class);
	      
	      
	    
			try{
		         File fsrc =new File(Environment.getExternalStorageDirectory()+"/sesame/k1.txt");
      
 
         
                FileInputStream fis1 = new FileInputStream(Environment.getExternalStorageDirectory()+"/sesame/k1.txt");
      	
      	
      			//Toast.makeText(getApplicationContext(), "inside", 1).show();
      			byte b1[]=new byte[fis1.available()];
          		fis1.read(b1);
          		k1=new String(b1);
             fis1.close();
		}catch(Exception e)
		{
			
			
		}
			
				try{
			         File fsrc =new File(Environment.getExternalStorageDirectory()+"/sesame/uid.txt");
         
    
            
                   FileInputStream fis1 = new FileInputStream(Environment.getExternalStorageDirectory()+"/sesame/uid.txt");
         	
         	
         			//Toast.makeText(getApplicationContext(), "inside", 1).show();
         			byte b1[]=new byte[fis1.available()];
             		fis1.read(b1);
             		uid=new String(b1);
                fis1.close();
			}catch(Exception e)
			{
				
				
			}
	      
	      
	      bg.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			   
                 
                 try {
				//	String encPass=ae.encrypt(pass);
					
					
					ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
    				postParameters.add(new BasicNameValuePair("uid", uid));
    				
    				postParameters.add(new BasicNameValuePair("app", "gmail"));
    				
                	String response = null;
	            	
	            		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

	            	   StrictMode.setThreadPolicy(policy); 
	            		
	            	   response = CustomHttpClient.executeHttpPost(Global.url+"verify_app", postParameters);
	            	
	            	   String res=response.toString();
	            	 
	            	   res= res.replaceAll("\\s+","");   //removing spaces in between the words    

	            	 //  Toast.makeText(getApplicationContext(), res, Toast.LENGTH_LONG).show();
	            	   
	            	  
		         		
                	  if(!res.equals(""))
                	  {
                		  
                		
                		 sk= AESCrypt.decrypt(k1, eSk);
                	//	 Toast.makeText(getApplicationContext(), sk, Toast.LENGTH_LONG).show();
                		 String a[]=sk.split("-");
                		 D=new BigInteger(a[0]);
                		 N=new BigInteger(a[1]);
                		 
                		 
                		 String t[]=res.split("-");
                		 BigInteger[] bigdigits = new BigInteger[t.length] ;
         				
         				for(int i = 0 ; i < bigdigits.length ; i++ )
         				{
         					//temp[0] = digits[i] ;
         					bigdigits[i] = new BigInteger( t[i] ) ;
         				}
         				
         				
         				
         				BigInteger[] encrypted = new BigInteger[bigdigits.length] ;
        				for(int i = 0 ; i < bigdigits.length ; i++ )
        					encrypted[i] = bigdigits[i];
        			//	 Toast.makeText(getApplicationContext(), encrypted.length+"", Toast.LENGTH_LONG).show();
                		
                		 String key=RSA.decrypt( encrypted ,D,N) ;
                		 
                		 
                	
                		  Toast.makeText(getApplicationContext(),"Gmail Password is "+ key, 1).show();
                		  
                	  }
	         		
					
					
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	      
	      
	      bf.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
	                 
	                 try {
					//	String encPass=ae.encrypt(pass);
						
						
						ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
	    				postParameters.add(new BasicNameValuePair("uid", uid));
	    				
	    				postParameters.add(new BasicNameValuePair("app", "facebook"));
	    				
	                	String response = null;
		            	
		            		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

		            	   StrictMode.setThreadPolicy(policy); 
		            		
		            	   response = CustomHttpClient.executeHttpPost(Global.url+"verify_app", postParameters);
		            	
		            	   String res=response.toString();
		            	 
		            	   res= res.replaceAll("\\s+","");   //removing spaces in between the words    

		            //	   Toast.makeText(getApplicationContext(), res, Toast.LENGTH_LONG).show();
		            	   
		            	  
		            		
		                	  if(!res.equals(""))
		                	  {
		                		  
		                		

		                 		 sk= AESCrypt.decrypt(k1, eSk);
		                 		// Toast.makeText(getApplicationContext(), sk, Toast.LENGTH_LONG).show();
		                 		 String a[]=sk.split("-");
		                 		 D=new BigInteger(a[0]);
		                 		 N=new BigInteger(a[1]);
		                 		 
		                 		 
		                 		 String t[]=res.split("-");
		                 		 BigInteger[] bigdigits = new BigInteger[t.length] ;
		          				
		          				for(int i = 0 ; i < bigdigits.length ; i++ )
		          				{
		          					//temp[0] = digits[i] ;
		          					bigdigits[i] = new BigInteger( t[i] ) ;
		          				}
		          				
		          				
		          				
		          				BigInteger[] encrypted = new BigInteger[bigdigits.length] ;
		         				for(int i = 0 ; i < bigdigits.length ; i++ )
		         					encrypted[i] = bigdigits[i];
		         			//	 Toast.makeText(getApplicationContext(), encrypted.length+"", Toast.LENGTH_LONG).show();
		                 		
		                 		 String key=RSA.decrypt( encrypted ,D,N) ;
		                 		 
		                 		 
		                 	
		                 		  Toast.makeText(getApplicationContext(),"Facebook Password is "+ key, 1).show();
		                 		  
		                	  }
			         		
							
	                	
		            	
		         		
						
						
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		});
	}
public String get_keys(String uid){
	String res1="";
	try
		{
  	 
			  File fsrc =new File(Environment.getExternalStorageDirectory()+"/sesame/k1.txt");
			  //File fsrc =new File(Environment.getExternalStorageDirectory().getPath()+"/debt/"+n+".txt");
			  if(!fsrc.exists())
			  {
				  fsrc.createNewFile();
			  }
	    	  FileOutputStream fosrc =new FileOutputStream(fsrc);
	    	  fosrc.write((k1).getBytes());
	    	  fosrc.close();
	    	
	    	  
	    	  
	    	  
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		  
	 try {
			//	String encPass=ae.encrypt(pass);
				String response="";
				
				ArrayList<NameValuePair> postParameters1 = new ArrayList<NameValuePair>();
				postParameters1.add(new BasicNameValuePair("uid", uid));
				
				postParameters1.add(new BasicNameValuePair("k1", k1));
				
           
           	
           		StrictMode.ThreadPolicy policy1 = new StrictMode.ThreadPolicy.Builder().permitAll().build();

           	   StrictMode.setThreadPolicy(policy1); 
           		
           	   response = CustomHttpClient.executeHttpPost(Global.urlPC+"get_keys", postParameters1);
           	
           	    res1=response.toString();
           	 
           	//   res= res.replaceAll("\\s+","");   //removing spaces in between the words    

           	   Toast.makeText(getApplicationContext(), res1, Toast.LENGTH_LONG).show();
           	   
           	  
	         		
           	 
        		
				
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	 return res1;
		  
	
}
}
