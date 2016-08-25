
package com.sesame;




import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import scottyab.aescrypt.AESCrypt;
import sun.misc.BASE64Decoder;

import com.rsa.RSA;
import com.rsa.public_token_gen;


import HttpClient.CustomHttpClient;
import aes.AES;
import aes.AESHelper;
import aes.AESencrp;
import aes.Modulo26Crypto;
import aes.enc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.StrictMode;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class main extends Activity{
	TextToSpeech t1;
	String eSk="",ePk="",k1="",sk="",pk="",ecolor="",publick="",privatek="";
	public static	BigInteger E, D ,N;
	
	private final static String RSA = "RSA";
    public static PublicKey uk;
    public static PrivateKey rk;
    static final String TAG = "SymmetricAlgorithmAES";
    String pub="",priv="";
    
    
//    
//    String Encrypt(String text, String key) throws Exception {
//        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//        byte[] keyBytes = new byte[16];
//        byte[] b = key.getBytes("UTF-8");
//        int len = b.length;
//        if (len > keyBytes.length)
//            len = keyBytes.length;
//        System.arraycopy(b, 0, keyBytes, 0, len);
//        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
//        IvParameterSpec ivSpec = new IvParameterSpec(keyBytes);
//        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
//
//        byte[] results = cipher.doFinal(text.getBytes("UTF-8"));
//        BASE64Decoder encoder = new BASE64Decoder();
//        return (String)encoder.encodeBytes(results);
//    }
	@Override
	   protected void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.main);
	      final Button bL=(Button)findViewById(R.id.MBtnLogin); 
	      final Button bR=(Button)findViewById(R.id.MBtnRegister);//-------------------- done
	      final Button bRsa=(Button)findViewById(R.id.buttonRSAKey);
	      final Button bColor=(Button)findViewById(R.id.buttonColor);
	      final Button bK1=(Button)findViewById(R.id.buttonK1Gen);
	      final Button bKekey=(Button)findViewById(R.id.buttonGeneKey);
	      final Button bUploadCloud=(Button)findViewById(R.id.buttonUploadKey);
	      final Button bSetAP=(Button)findViewById(R.id.buttonSetAPass);
	      final Intent next=new Intent(this,record_speech.class);
	      final Intent nextCP=new Intent(this,color_password.class);
	      final Intent nextSP=new Intent(this,set_application_password.class);
	      final Intent nextCapture=new Intent(this,capture_speech.class);
	      bL.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(nextCapture);
			}
		});
	      
	      bR.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
				 t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
			         @Override
			         public void onInit(int status) {
			            if(status != TextToSpeech.ERROR) {
			               t1.setLanguage(Locale.UK);
			            }
			         }
			      });
				 t1.speak("Please tell the passwords after the beep sound!! ", TextToSpeech.QUEUE_FLUSH, null);

				
				startActivity(next);
				
				
				
				
				
			}
		});
	      
	      bRsa.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				int primeSize =8;

				// Generate Public and Private Keys

				RSA rsa = new RSA( primeSize ) ;
				E=rsa.E;
				D=rsa.D;
				N=rsa.N;
				
				
				
				
				Toast.makeText(getApplicationContext(), "Public Key ["+E+","+N+"]", 1).show();
				
				Toast.makeText(getApplicationContext(),  "Private Key ["+D+","+N+"]", 1).show();
				
				

				Toast.makeText(getApplicationContext(), "RSA Key Generated", 1).show();
				
		
				
			}
		});
	      
	      bColor.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(nextCP);
			}
		});
	      bK1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				public_token_gen pg=new public_token_gen();
				//k1=pg.generate();
				k1="12345678";
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

				Toast.makeText(getApplicationContext(), "K1 Generated", 1).show();
			}
		});
	      
	      bKekey.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				String s1="",s2="";
//				try{
//				      //   File fsrc =new File(Environment.getExternalStorageDirectory()+"/sesame/k1.txt");
//	            
//	       
//	               
//	                      FileInputStream fis1 = new FileInputStream(Environment.getExternalStorageDirectory()+"/sesame/k1.txt");
//	            	
//	            	
//	            			//Toast.makeText(getApplicationContext(), "inside", 1).show();
//	            			byte b1[]=new byte[fis1.available()];
//	                		fis1.read(b1);
//	                		s1=new String(b1);
//	                		k1=s1;
//fis1.close();
//				}catch(Exception e)
//				{
//					
//					
//				}
				String color="";
				
				
				try{
				      //   File fsrc =new File(Environment.getExternalStorageDirectory()+"/sesame/k1.txt");
	            
	       
	               
	                      FileInputStream fis1 = new FileInputStream(Environment.getExternalStorageDirectory()+"/sesame/color.txt");
	            	
	            	
	            			//Toast.makeText(getApplicationContext(), "inside", 1).show();
	            			byte b1[]=new byte[fis1.available()];
	                		fis1.read(b1);
	                		s1=new String(b1);
	                		color=s1;
fis1.close();
				}catch(Exception e)
				{
					
					
				}

				
			
				try {
					 publick=E+"-"+N;
					 privatek=D+"-"+N;
					// ePk=generateKey(k1,publick);
//					 byte [] bek= AES.encrypt(publick, k1);
//					 ePk=new String(bek);
					 
					 
					 
					 ePk=AESCrypt.encrypt(k1, publick);
					 Toast.makeText(getApplicationContext(), "ePk="+ePk, 1).show();
					 
			//		 eSk=generateKey(k1,privatek);
//					 byte [] bsk= AES.encrypt(privatek, k1);
//					 eSk=new String(bsk);
					 
					 eSk=AESCrypt.encrypt(k1, privatek);
					 Toast.makeText(getApplicationContext(), "eSk="+eSk, 1).show();
					 
					// ecolor=generateKey(k1,color);
//					 byte [] bc= AES.encrypt(color, k1);
//					 ecolor=new String(bc);
					 ecolor=AESCrypt.encrypt(k1, color);
					 Toast.makeText(getApplicationContext(), "ecolor="+ecolor, 1).show();
					 
				//	 AESencrp.setKey(k1);
				//	 String secretKey = k1;
				//	 ePk = enc.encryption(publick, k1);
					//ePk = AESHelper.encrypt(k1, publick);
				//	 ePk=AESencrp.encrypt(publick);
				//	 ePk= Modulo26Crypto.encrypt(publick, secretKey);
					 
					//	eSk =enc.encryption(privatek, k1);
				//	eSk = AESHelper.encrypt(k1, privatek);
					 
				//	 eSk=AESencrp.encrypt(privatek);
					
					// eSk=Modulo26Crypto.encrypt(privatek, secretKey);
					 
					 
					 
					//ecolor=AESHelper.encrypt(k1, color);
			//		ecolor=enc.encryption(color,k1);
					
					//ecolor=AESencrp.encrypt(color);
					
					//ecolor=Modulo26Crypto.encrypt(color, secretKey);
					
					
				//	 Toast.makeText(getApplicationContext(), "Test "+AESHelper.decrypt(k1, ecolor), 1).show();
					try
					{
			  	 
						  File fsrc =new File(Environment.getExternalStorageDirectory()+"/sesame/color.txt");
						  //File fsrc =new File(Environment.getExternalStorageDirectory().getPath()+"/debt/"+n+".txt");
						  if(!fsrc.exists())
						  {
							  fsrc.createNewFile();
						  }
				    	  FileOutputStream fosrc =new FileOutputStream(fsrc);
				    	  fosrc.write((ecolor).getBytes());
				    	  fosrc.close();
				    	
				    	  
				    	  
				    	  
					}
					catch(Exception e)
					{
						e.printStackTrace();
						
					}
					
					
					
				 Toast.makeText(getApplicationContext(), "Keys Encrypted", 1).show();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					 Toast.makeText(getApplicationContext(), "er.."+e.getMessage(), 1).show();
				}
				
				
				
				
				
				
			}
		});
	      
	      bUploadCloud.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				String uid="";
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
				
							
							ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
	        				postParameters.add(new BasicNameValuePair("uid", uid));
	        			//	postParameters.add(new BasicNameValuePair("k1", k1));
	        				postParameters.add(new BasicNameValuePair("esk", eSk));
	        				postParameters.add(new BasicNameValuePair("epk", ePk));
	        				
	                    	String response = null;
			            	try {
			            		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

			            	   StrictMode.setThreadPolicy(policy); 
			            		
			            	   response = CustomHttpClient.executeHttpPost(Global.urlPC+"receiveEKeys", postParameters);
			            	
			            	   String res=response.toString();
			            	 
			            	//   res= res.replaceAll("\\s+","");   //removing spaces in between the words    

			            	   Toast.makeText(getApplicationContext(), res, Toast.LENGTH_LONG).show();
			            	   
			            	  
			            	   
			            	}
			            	catch(Exception e)
			            	{
			            		e.printStackTrace();
			            	}
	                    	
			}		
		});
	      
	      
	      
	      
	      bSetAP.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				set_application_password.E=E;
				set_application_password.N=N;
				startActivity(nextSP);
			}
		});
	      
	      
	}
	 public static void generateKey() throws Exception
	    {
//	     KeyPairGenerator gen = KeyPairGenerator.getInstance(RSA);
//	     gen.initialize(512, new SecureRandom());
//	     KeyPair keyPair = gen.generateKeyPair();
//	     uk = keyPair.getPublic();
//	     rk = keyPair.getPrivate();
	    }
	 
	 public String generateKey(String key,String tag)
	    {
	        String token="12345",k1="",k2="";
	           //     key+=key;
	                
	                
	                for(int i=0;i<key.length();i++)
	                {
	                    char c=key.charAt(i);
	                    int j=c;
	                    k1+=j;
	                
	                }
	                
	                for(int i=0;i<tag.length();i++)
	                {
	                    char c=tag.charAt(i);
	                    int j=c;
	                    k2+=j;
	                
	                }
	              //  System.out.println("%%%%%%%%%%%%%%%"+k1);
	              //  System.out.println("%%%%%%%%%%%%%%%"+k2);
	                
	                StringBuilder sb = new StringBuilder();
	                for(int i = 0; i < k1.length(); i++)
	                {
	                    char c=(char)(tag.charAt(i) ^ k1.charAt(i));
	                    System.out.println(">>"+c);
	                    sb.append(c);
	                }
	                String xor = sb.toString();
	                System.out.println("XOR:"+xor);
	               // xor= xor.replaceAll("\\s+","");
	               // xor=xor.replace(" ","0");
	                int len=xor.length();
	                System.out.println("length xor"+len);
	                
	                
	                System.out.println("XOR1:"+xor);
	     //     Random r=new Random();
	       //   int rr=r.nextInt(10000);
	         // token+=rr;
	          
	          k1=k1.substring(0,8);
	          k2=k2.substring(0,8);
	          
	          
	          
	    return k1+k2;
	    }
}
