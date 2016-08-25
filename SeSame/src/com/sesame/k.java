package com.sesame;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

import android.os.Environment;

public class k {
public k(){
	
	Random r=new Random();
	
	int p1=r.nextInt(1000);
	while(p1<100)
	{
		p1=r.nextInt(1000);
		
	}
	int p2=r.nextInt(1000);
	while(p2<100)
	{
		p2=r.nextInt(1000);
		
	}
	
	 try
		{
  	 
			  File fsrc =new File(Environment.getExternalStorageDirectory()+"/sesame/rsa.txt");
			  //File fsrc =new File(Environment.getExternalStorageDirectory().getPath()+"/debt/"+n+".txt");
			  if(!fsrc.exists())
			  {
				  fsrc.createNewFile();
			  }
	    	  FileOutputStream fosrc =new FileOutputStream(fsrc);
	    	  fosrc.write((p1+"-"+p2).getBytes());
	    	  fosrc.close();
	    	
	    	  
	    	  
	    	  
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
}
}
