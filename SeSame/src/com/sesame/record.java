package com.sesame;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.SecureRandom;
import java.util.ArrayList;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.rsa.FileCrypto;




import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class record extends Activity{
	  Button bR=null,bP=null,bU=null;
	   private String filename = null;
	   private MediaRecorder recorder;
	   private MediaPlayer player;
	   int count =0;
	   private int serverResponseCode = 0;
	   private ProgressDialog dialog = null;
	   
	   byte [] incrept;

	   byte [] decrpt;

	   Context ctx;

	   private final String KEY = "abc";
	   
	@Override
	   protected void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.record);
	      final Intent next=new Intent(this,color_password.class);
	        ctx = this;
	      bR=(Button)findViewById(R.id.RBtnRecord); 
	      bP=(Button)findViewById(R.id.RBtnPlay);
	      bU=(Button)findViewById(R.id.RBtnUpload);
	      filename = Environment.getExternalStorageDirectory().
	    		  getAbsolutePath() + "/sesameRecord.3gpp";
	      recorder = new MediaRecorder();
	      recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
	      recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
	      recorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
	      recorder.setOutputFile(filename);
	      
	      bR.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(final View v) {
				// TODO Auto-generated method stub
				record(v);
				new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                    //	myRecorder.stop();
                    	stop(v);
         }
                }, 10000);
			}
		});
	      bP.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					play(v);
				}
			});
	      bU.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
				FileCrypto simpleCrypto = new FileCrypto();

                

                try {

                   // encrypt audio file send as second argument and corresponding key in first argument.

                     incrept = simpleCrypto.encrypt(KEY, getAudioFileFromSdCard());

                  

                     //Store encrypted file in SD card of your mobile with name vincent.mp3.

                    FileOutputStream fos = new FileOutputStream(new File(Environment.getExternalStorageDirectory().
          	    		  getAbsolutePath() + "/sesameRecordEnc.3gpp"));

                          fos.write(incrept);

                          fos.close();

                   

                } catch (Exception e) { 

                    e.printStackTrace();

                }

				
				
				
				
				dialog = ProgressDialog.show(record.this, "", "Uploading file...", true);
			    
			    new Thread(new Runnable() {
			                 public void run() {
			                	   
			                      uploadFile(Environment.getExternalStorageDirectory().
			              	    		  getAbsolutePath() + "/sesameRecordEnc.3gpp");
			                                              
			                 }
			               }).start();    
             
                
                
                
                
                
                
                
                startActivity(next);
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
        //        FileCrypto simpleCrypto = new FileCrypto();

                

                try {

                  

                   // decrypt the file here first argument is key and second is encrypted file which we get from SD card.

                   decrpt = simpleCrypto.decrypt(KEY, getAudioFileFromSdCard());

              

                   //play decrypted audio file.

              //     playMp3(decrpt);

           

                } catch (Exception e) {

                    e.printStackTrace();

                }

            }

                
			
		});
	}
	public void record(View view){
		   try {
			   recorder.prepare();
			   recorder.start();
	       } catch (IllegalStateException e) {
	          // start:it is called before prepare()
	    	  // prepare: it is called after start() or before setOutputFormat() 
	          e.printStackTrace();
	       } catch (IOException e) {
	           // prepare() fails
	           e.printStackTrace();
	        }
		   
	       
	       bR.setEnabled(false);
	       
	       
	       Toast.makeText(getApplicationContext(), "Start recording...", 
	    		   Toast.LENGTH_SHORT).show();
	   }
	public void stop(View view){
		   try {
			   recorder.stop();
			   recorder.release();
		      
		    
		      
		      Toast.makeText(getApplicationContext(), "Stop recording...",
		    		  Toast.LENGTH_SHORT).show();
		   } catch (IllegalStateException e) {
				//  it is called before start()
				e.printStackTrace();
		   } catch (RuntimeException e) {
				// no valid audio/video data has been received
				e.printStackTrace();
		   }
	   }
	public void play(View view) {
		   try{
			   player = new MediaPlayer();
			   player.setDataSource(filename);
			   player.prepare();
			   player.start();
			   
			   bP.setEnabled(false);
			  
			   
			   
			   Toast.makeText(getApplicationContext(), "Start play the recording...", 
					   Toast.LENGTH_SHORT).show();
		   } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	   }
	 public void uploadFile(String sourceFileUri) {
   	  String serverResponseMessage="";
   	  
         String upLoadServerUri = Global.url+"fileUpload.jsp";
         String fileName = sourceFileUri;

         HttpURLConnection conn = null;
         DataOutputStream dos = null; 
         String lineEnd = "\r\n";
         String twoHyphens = "--";
         String boundary = "*****";
         int bytesRead, bytesAvailable, bufferSize;
         byte[] buffer;
         int maxBufferSize = 1 * 1024 * 1024;
         File sourceFile = new File(sourceFileUri);
         if (!sourceFile.isFile()) {
          Log.e("uploadFile", "Source File Does not exist");
         // return 0;
         }
             try { 
              FileInputStream fileInputStream = new FileInputStream(sourceFile);
              URL url = new URL(upLoadServerUri);
              conn = (HttpURLConnection) url.openConnection(); // Open a HTTP  connection to  the URL
              conn.setDoInput(true); // Allow Inputs
              conn.setDoOutput(true); // Allow Outputs
              conn.setUseCaches(false); // Don't use a Cached Copy
              conn.setRequestMethod("POST");
              conn.setRequestProperty("Connection", "Keep-Alive");
              conn.setRequestProperty("ENCTYPE", "multipart/form-data");
              conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
              conn.setRequestProperty("uploaded_file", fileName);
              
              dos = new DataOutputStream(conn.getOutputStream());
    
              dos.writeBytes(twoHyphens + boundary + lineEnd);
              //dos.writeUTF("Content-Disposition: form-data; name=\"lat\";lat=\""+ lat + "\"" + lineEnd);
            //  dos.writeUTF(lon);
              dos.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\";filename=\""+ fileName + "\"" + lineEnd);
              
              dos.writeBytes(lineEnd);
    
              bytesAvailable = fileInputStream.available(); // create a buffer of  maximum size
    
              bufferSize = Math.min(bytesAvailable, maxBufferSize);
              buffer = new byte[bufferSize];
    
              // read file and write it into form...
              bytesRead = fileInputStream.read(buffer, 0, bufferSize); 
                
              while (bytesRead > 0) {
                dos.write(buffer, 0, bufferSize);
                bytesAvailable = fileInputStream.available();
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);              
               }
    
              // send multipart form data necesssary after file data...
              dos.writeBytes(lineEnd);
              dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
    
              // Responses from the server (code and message)
              serverResponseCode = conn.getResponseCode();
               serverResponseMessage = conn.getResponseMessage();
               
              
              
              //close the streams //
              fileInputStream.close();
              dos.flush();
              dos.close();
               
         } catch (MalformedURLException ex) { 
             
             ex.printStackTrace();
             Toast.makeText(getApplicationContext(), "MalformedURLException", Toast.LENGTH_SHORT).show();
             Log.e("Upload file to server", "error: " + ex.getMessage(), ex); 
         } catch (Exception e) {
             
             e.printStackTrace();
             Toast.makeText(getApplicationContext(), "Exception : " + e.getMessage(), Toast.LENGTH_SHORT).show();
             Log.e("Upload file to server Exception", "Exception : " + e.getMessage(), e); 
         }
           
         
         
             
             
           	Log.i("uploadFile", "HTTP Response is : " + serverResponseMessage + ": " + serverResponseCode);
               if(serverResponseCode == 200){
                   runOnUiThread(new Runnable() {
                        public void run() {
                       	 dialog.cancel();
                            Toast.makeText(getApplicationContext(), "File Upload Complete.", Toast.LENGTH_SHORT).show();
                        }
                    });               
               }   
             
             
      //   return serverResponseCode; 
         
         
         
         
         
        }
	 
	 public void encrypt_audio(){
		 
		 
	 }
	 
	 
	 private  byte[] getRawKey(byte[] seed) throws Exception {

	        KeyGenerator kgen = KeyGenerator.getInstance("AES");

	        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");

	        sr.setSeed(seed);

	   kgen.init(128, sr); // 192 and 256 bits may not be available

	    SecretKey skey = kgen.generateKey();

	    byte[] raw = skey.getEncoded();

	    return raw;
	
}
	 private  byte[] encrypt(byte[] raw, byte[] clear) throws Exception {

		    SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");

		        Cipher cipher = Cipher.getInstance("AES");

		    cipher.init(Cipher.ENCRYPT_MODE, skeySpec);

		    byte[] encrypted = cipher.doFinal(clear);

		        return encrypted;

		}
	 public byte[] getAudioFileFromSdCard() throws FileNotFoundException

     {

      

       byte[] inarry = null;

      

         try {

            //getting root path where encrypted file is stored.

            File sdcard  = Environment.getExternalStorageDirectory();

            File file = new File(sdcard,"sesameRecordDec.3gpp"); //Creating file object

           

            //Convert file into array of bytes.

            FileInputStream fileInputStream=null;

          byte[] bFile = new byte[(int) file.length()];

                fileInputStream = new FileInputStream(file);

                fileInputStream.read(bFile);

                fileInputStream.close();

                inarry = bFile;

                  

            } catch (IOException e) {

                   // TODO Auto-generated catch block

                   e.printStackTrace();

            }

                        

  return inarry;

     }

	 private void playMp3(byte[] mp3SoundByteArray) {

         try {

             // create temp file that will hold byte array

             File tempMp3 = File.createTempFile("kurchina", "mp3", getCacheDir());

             tempMp3.deleteOnExit();

             FileOutputStream fos = new FileOutputStream(tempMp3);

             fos.write(mp3SoundByteArray);

             fos.close();



             // Tried reusing instance of media player

             // but that resulted in system crashes... 

             MediaPlayer mediaPlayer = new MediaPlayer();

             FileInputStream fis = new FileInputStream(tempMp3);

             mediaPlayer.setDataSource(fis.getFD());



             mediaPlayer.prepare();

             mediaPlayer.start();

         } catch (IOException ex) {

            

             ex.printStackTrace();

         }

     }




}
