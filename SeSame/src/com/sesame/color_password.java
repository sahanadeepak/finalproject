package com.sesame;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import scottyab.aescrypt.AESCrypt;

import HttpClient.CustomHttpClient;
import aes.AESHelper;
import aes.enc;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.StrictMode;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class color_password extends Activity{
	
	ArrayList alNum=new ArrayList();
	Vector v=new Vector();
	ArrayList alNum_assigned=new ArrayList();
	int count=0;
	String selected_color[]=new String[6];
	String color_name[]={"RED","BLUE","GREEN","WHITE","YELLOW","GRAY"};
	String current_color="";
    int c1=0,c2=0,c3=0,c4=0,c5=0,c6=0;
String matrix[][]=new String [6][6];
	final Button b[]=new Button[36];
	final Button btn[][]=new Button[6][6];
	int s=0;
	String epk="",esk="";
	Intent next;
	
	@Override
	   protected void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.color_password);
	      final Button b1=(Button)findViewById(R.id.b1);
	      final Button b2=(Button)findViewById(R.id.b2);
	      final Button b3=(Button)findViewById(R.id.b3);
	      final Button b4=(Button)findViewById(R.id.b4);
	      final Button b5=(Button)findViewById(R.id.b5);
	      final Button b6=(Button)findViewById(R.id.b6);
	      final Button b7=(Button)findViewById(R.id.b7);
	      final Button b8=(Button)findViewById(R.id.b8);
	      final Button b9=(Button)findViewById(R.id.b9); 
	      final Button b10=(Button)findViewById(R.id.b10);
	      final Button b11=(Button)findViewById(R.id.b11);
	      final Button b12=(Button)findViewById(R.id.b12);
	      final Button b13=(Button)findViewById(R.id.b13);
	      final Button b14=(Button)findViewById(R.id.b14);
	      final Button b15=(Button)findViewById(R.id.b15);
	      final Button b16=(Button)findViewById(R.id.b16);
	      final Button b17=(Button)findViewById(R.id.b17);
	      final Button b18=(Button)findViewById(R.id.b18);
	      final Button b19=(Button)findViewById(R.id.b19);
	      final Button b20=(Button)findViewById(R.id.b20); 
	      final Button b21=(Button)findViewById(R.id.b21);
	      final Button b22=(Button)findViewById(R.id.b22);
	      final Button b23=(Button)findViewById(R.id.b23);
	      final Button b24=(Button)findViewById(R.id.b24);
	      final Button b25=(Button)findViewById(R.id.b25);
	      final Button b26=(Button)findViewById(R.id.b26);
	      final Button b27=(Button)findViewById(R.id.b27);
	      final Button b28=(Button)findViewById(R.id.b28);
	      final Button b29=(Button)findViewById(R.id.b29);
	      final Button b30=(Button)findViewById(R.id.b30);
	      final Button b31=(Button)findViewById(R.id.b31); 
	      final Button b32=(Button)findViewById(R.id.b32);
	      final Button b33=(Button)findViewById(R.id.b33);
	      final Button b34=(Button)findViewById(R.id.b34); 
	      final Button b35=(Button)findViewById(R.id.b35);
	      final Button b36=(Button)findViewById(R.id.b36);
	      final Button bSet=(Button)findViewById(R.id.button1);
	      
	      final Button bVerify=(Button)findViewById(R.id.button2);
	      next =new Intent(this,select_app.class);
//	      
//	        b[0]=b1;b[1]=b2;b[2]=b3;b[3]=b4;b[4]=b5;
//			b[5]=b6;b[6]=b7;b[7]=b8;b[8]=b9;b[9]=b10;
//			b[10]=b11;b[11]=b12;b[12]=b13;b[13]=b14;b[14]=b15;
//			b[15]=b16;b[16]=b17;b[17]=b18;b[18]=b19;b[19]=b20;
//			b[20]=b21;b[21]=b22;b[22]=b23;b[23]=b24;b[24]=b25;
//			b[25]=b26;b[26]=b27;b[27]=b28;b[28]=b29;b[29]=b30;
//			b[30]=b31;b[31]=b32;b[32]=b33;b[33]=b34;b[34]=b35;
//			b[35]=b36;
//	      
			
			btn[0][0]=b1;btn[0][1]=b2;btn[0][2]=b3;btn[0][3]=b4;btn[0][4]=b5;btn[0][5]=b6;
			btn[1][0]=b7;btn[1][1]=b8;btn[1][2]=b9;btn[1][3]=b10;btn[1][4]=b11;btn[1][5]=b12;
			btn[2][0]=b13;btn[2][1]=b14;btn[2][2]=b15;btn[2][3]=b16;btn[2][4]=b17;btn[2][5]=b18;
			btn[3][0]=b19;btn[3][1]=b20;btn[3][2]=b21;btn[3][3]=b22;btn[3][4]=b23;btn[3][5]=b24;
			btn[4][0]=b25;btn[4][1]=b26;btn[4][2]=b27;btn[4][3]=b28;btn[4][4]=b29;btn[4][5]=b30;
			btn[5][0]=b31;btn[5][1]=b32;btn[5][2]=b33;btn[5][3]=b34;btn[5][4]=b35;btn[5][5]=b36;
			
			
			
	      for(int i=0;i<36;i++)
	      {
	    	  
	    	  alNum.add(i);
	    	  v.add(i);
	      }
	      
	      
	        for(int i=0;i<6;i++)
	        {
	        
	        for(int j=0;j<6;j++)
	        {
	         matrix[i][j]=color_name[j];
	        
	        }
	        
	        }
	        
	
	         
	         
	         
	         
	         Random r=new Random();
	        for(int i=0;i<6;i++)
	        {
	        for(int j=0;j<6;j++)
	        {
				    int randomPosition = r.nextInt(6);
				    String temp = matrix[i][j];
				    matrix[i][j] = matrix[i][randomPosition];
				    matrix[i][randomPosition] = temp;
	        }
	        }
	        
	        
	        
	               for(int i=0;i<6;i++)
	        {
	        
	        for(int j=0;j<6;j++)
	        {
	        	String color=matrix[i][j];
	        	if(color.equals("RED")){btn[i][j].setBackgroundColor(Color.RED);}
	    		if(color.equals("BLUE")){btn[i][j].setBackgroundColor(Color.BLUE);}
	    		if(color.equals("GREEN")){btn[i][j].setBackgroundColor(Color.GREEN);}
	    		if(color.equals("WHITE")){btn[i][j].setBackgroundColor(Color.WHITE);}
	    		if(color.equals("YELLOW")){btn[i][j].setBackgroundColor(Color.YELLOW);}
	    		if(color.equals("GRAY")){btn[i][j].setBackgroundColor(Color.GRAY);}
	    		
	        }
	            
	        }
	        
	/*        System.out.println("---------------------------------------");
		 for(int i=0;i<6;i++)
	        {
	        
	        for(int j=0;j<6;j++)
	        {
	            System.out.print(""+matrix[i][j]+"\t");
	        }
	            System.out.println("");
	        }*/
	       
	       
	        
	 /*       int pos1=0,pos2=0,pos3=0,pos4=0,pos5=0,pos6=0;
	        Random r=new Random();
	        	
	        for(int i=0;i<36;i++)
		      {
		    	
	        	int n=r.nextInt(6);
	        	String  cl="";
	        	if(n==0 && c1<6){cl=color_name[0];}
	        	
		      }
	        
//	    	String colred=color_name[0];
//	    	pos1=r.nextInt(36);
//	    	pos2=r.nextInt(36);
//	    	while(pos1==pos2){pos2=r.nextInt(36);}
//	    	pos3=r.nextInt(36);
//	    	while(pos1==pos3 || pos2==pos3){pos3=r.nextInt(36);}
//	    	pos4=r.nextInt(36);
//	    	while(pos1==pos4 || pos2==pos4|| pos3==pos4){pos4=r.nextInt(36);}
//	    	pos5=r.nextInt(36);
//	    	while(pos1==pos5 || pos2==pos5|| pos3==pos5  || pos4==pos5){pos5=r.nextInt(36);}
//	    	pos6=r.nextInt(36);
//	    	while(pos1==pos6 || pos2==pos6|| pos3==pos6  || pos4==pos6 || pos5==pos6){pos6=r.nextInt(36);}
//	    	assign_color(colred,pos1);assign_color(colred,pos2);assign_color(colred,pos3);assign_color(colred,pos4);assign_color(colred,pos5);assign_color(colred,pos6);
//	        
	        
	        new Handler().postDelayed(new Runnable() {
	             @Override
	             public void run() {

	            	  for(int c=0;c<6;c++)
	      	        {
	      	        	
	      	        	String col=color_name[c];
	      	        	
	      	        	 Random r=new Random();
	      	        	
	      	        	
	      	        	
	      	        	
	      	        	         //r.nextInt(36);
	      	        	
	      	        	
	      	        	for(int cc=0;cc<6;cc++)
	      	  	        {
	      	        		int num= v.size();   //alNum.size();
	      	        		
//	      	        	
//	      	        		boolean flag=alNum_assigned.contains(num);
//	      	        		
//	      	        		while(flag==true)
//	      	        		{
//	      	        			num=r.nextInt(36);
//	      	        			
//	      	        		}
//	      	        		
//	      	        		alNum_assigned.add(num);
	      	        	int n=r.nextInt(num);
	      	        	Toast.makeText(getApplicationContext(), num+"------"+n, 1).show();
	      	   	        	assign_color(col,n);
	      	   	    	
	      	   	        	//alNum.remove(n);
	      	        	v.removeElement(n);
	      	  	        }
	      	        }
	      	    
   		 
				
			
	             }
	         }, 5000);
			
*/
	       
	     /*     
	      for(int i=0;i<36;i++)
	      {
	      int num=r.nextInt(36);
	      if(alNum_assigned.size()==0)
	      {
	    	  
	    	  alNum_assigned.add(num);
	    	  count++;
	    	  setColor(count,num);
	      }
	      else{
	    	   
	    	  for(int ii=0;ii<alNum_assigned.size();ii++)
	    		  
	    	  {
	    		  int n=Integer.parseInt(alNum_assigned.get(ii).toString());
	    		  while(num==n)
	    		  {
	    			  num=r.nextInt(36);
	    			  
	    		  }
	    		  alNum_assigned.add(num);
    	    	  count++;
    	    	  setColor(count,num);
	    		  break;
	    	  }
	    	  
	    	  
	      }
	      
	      }*/
	      
	      
	      for(int i=0;i<6;i++)
	      {
	    	  for(int j=0;j<6;j++)
		      {
	    	  final int ii=i;
	    	  final int jj=j;
	    	  btn[i][j].setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
				//String col=	getBtnColor(ii+1);
					String color=matrix[ii][jj];
			//	Toast.makeText(getApplicationContext(), color, 1).show();
				if(s<6)
				{
				selected_color[s]=color;
				s++;
				}
				else{
					Toast.makeText(getApplicationContext(), "Max 6 Color codes", 1).show();
					
				}
				}
			});
		      }
	      }
	      
	      bSet.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String my_col="";
				for(int i=0;i<selected_color.length;i++)
				{
					my_col+=selected_color[i]+"-";
					
					
					
				}
				try
				{
		  	 
					  File fsrc =new File(Environment.getExternalStorageDirectory()+"/sesame/color.txt");
					  //File fsrc =new File(Environment.getExternalStorageDirectory().getPath()+"/debt/"+n+".txt");
					  if(!fsrc.exists())
					  {
						  fsrc.createNewFile();
					  }
			    	  FileOutputStream fosrc =new FileOutputStream(fsrc);
			    	  fosrc.write((my_col).getBytes());
			    	  fosrc.close();
			    	
			    	  
			    	  
			    	  
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				Toast.makeText(getApplicationContext(), "Color Pasword Set", 1).show();

			}
		});
	      
	      bVerify.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String my_col="";
				for(int i=0;i<selected_color.length;i++)
				{
					my_col+=selected_color[i]+"-";
					
					
					
				}
				
			//	Toast.makeText(getApplicationContext(), my_col, 1).show();
				  String c="";
	 				try{
	 			        // File fsrc =new File(Environment.getExternalStorageDirectory()+"/sesame/color.txt");
	            
	       
	               
	                      FileInputStream fis1 = new FileInputStream(Environment.getExternalStorageDirectory()+"/sesame/color.txt");
	            	
	            	
	            			//Toast.makeText(getApplicationContext(), "inside", 1).show();
	            			byte b1[]=new byte[fis1.available()];
	                		fis1.read(b1);
	                		c=new String(b1);
	                   fis1.close();
	 			}catch(Exception e)
	 			{
	 				
	 				
	 			}
	 			//	Toast.makeText(getApplicationContext(), c, 1).show();

					  String k1="";
		 				try{
		 			        // File fsrc =new File(Environment.getExternalStorageDirectory()+"/sesame/color.txt");
		            
		       
		               
		                      FileInputStream fis1 = new FileInputStream(Environment.getExternalStorageDirectory()+"/sesame/k1.txt");
		            	
		            	
		            			//Toast.makeText(getApplicationContext(), "inside", 1).show();
		            			byte b1[]=new byte[fis1.available()];
		                		fis1.read(b1);
		                		k1=new String(b1);
		                   fis1.close();
		 			}catch(Exception e)
		 			{
		 				
		 				
		 			}
		 				Toast.makeText(getApplicationContext(), k1, 1).show();
		 				try {
					//	String ocolor=	AESHelper.decrypt(k1, c);
						String ocolor=	AESCrypt.decrypt(k1, c);
	 					Toast.makeText(getApplicationContext(),"ocolor  =  "+ ocolor,1).show();
	 					
						
		 				if(ocolor.equals(my_col))
		 				{
		 					
		 					
		 					Toast.makeText(getApplicationContext(), "Color code Verified",1).show();
		 					
		 					
		 					
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
	        			
	        				
	                    	String response = null;
			            	try {
			            		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

			            	   StrictMode.setThreadPolicy(policy); 
			            		
			            	   response = CustomHttpClient.executeHttpPost(Global.urlPC+"get_ekeys", postParameters);
			            	
			            	   String res=response.toString();
			            	 
			            	   res= res.replaceAll("\\s+","");   //removing spaces in between the words    

			            	 //  Toast.makeText(getApplicationContext(), res, Toast.LENGTH_LONG).show();
			            	   
			            	  String eks[]=res.split("#");
			            	  
			            	  esk=eks[0];
			            	  epk=eks[1];
			            	  Toast.makeText(getApplicationContext(), "Encrypted Keys from Public cloud received", Toast.LENGTH_LONG).show();
			            	  
			            	  select_app.ePk=epk;
			            	  select_app.eSk=esk;
			            	
			            	  startActivity(next);
			            	}
			            	catch(Exception e)
			            	{
			            		e.printStackTrace();
			            	}
		 					
		 					
		 					
		 					
		 					
		 				}
		 				else{
		 					
		 					Toast.makeText(getApplicationContext(), "Not Matched",1).show();
		 				}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							Toast.makeText(getApplicationContext(), "AES Error"+e.getMessage(), 1).show();
							e.printStackTrace();
						}
	 			
	                 
			}
		});
	}
	
	public String getColor(int num){
		
		String color="";
		if(num>0&&num<7){color=color_name[0];}
		else if(num>6&&num<13){color=color_name[1];}
		else if(num>12&&num<19){color=color_name[2];}
		else if(num>18&&num<25){color=color_name[3];}
		else if(num>24&&num<31){color=color_name[4];}
		else if(num>30&&num<37){color=color_name[5];}
		return color;
	}
	

	public String getBtnColor(int num){
		
		String colorCode = (String)b[num].getTag();
		
//		ColorDrawable buttonColor = (ColorDrawable)	b[num].getBackground();
//		int colorId = buttonColor.getColor();
//		if (colorId == R.color.green) {
//			  log("color is green");
//			}
//		String color="";
//		if(num>0&&num<7){color=color_name[0];}
//		else if(num>6&&num<13){color=color_name[1];}
//		else if(num>12&&num<19){color=color_name[2];}
//		else if(num>18&&num<25){color=color_name[3];}
//		else if(num>24&&num<31){color=color_name[4];}
//		else if(num>30&&num<37){color=color_name[5];}
		return colorCode;
	}
	
	public void assign_color(String color,int num)
	{
		
		if(color.equals("RED")){b[num].setBackgroundColor(Color.RED);}
		if(color.equals("BLUE")){b[num].setBackgroundColor(Color.BLUE);}
		if(color.equals("GREEN")){b[num].setBackgroundColor(Color.GREEN);}
		if(color.equals("WHITE")){b[num].setBackgroundColor(Color.WHITE);}
		if(color.equals("YELLOW")){b[num].setBackgroundColor(Color.YELLOW);}
		if(color.equals("GRAY")){b[num].setBackgroundColor(Color.GRAY);}
		
	}
	
	
	public void setColor(int count,int num)
	{
		
		String color="";
		if(count>=0&&count<6){color=color_name[0];}
		else if(count>=6&&count<12){color=color_name[1];}
		else if(count>=12&&count<18){color=color_name[2];}
		else if(count>=18&&count<24){color=color_name[3];}
		else if(count>=24&&count<30){color=color_name[4];}
		else if(count>=30&&count<36){color=color_name[5];}
		
		if(color.equals("RED")){b[num].setBackgroundColor(Color.RED);}
		if(color.equals("BLUE")){b[num].setBackgroundColor(Color.BLUE);}
		if(color.equals("GREEN")){b[num].setBackgroundColor(Color.GREEN);}
		if(color.equals("WHITE")){b[num].setBackgroundColor(Color.WHITE);}
		if(color.equals("YELLOW")){b[num].setBackgroundColor(Color.YELLOW);}
		if(color.equals("GRAY")){b[num].setBackgroundColor(Color.GRAY);}
		
	}

}
