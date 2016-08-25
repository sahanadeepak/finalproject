package com.rsa;

import java.util.Random;

public class public_token_gen {
	
	public static void main(String fg[])
	{
		public_token_gen tok = new public_token_gen();
		String arr = tok.generate();
		System.out.println("arr= "+arr);
	}
public static String generate()
  {
	String st ="";
	Random rnd = new Random();
	for(int i=0;i<16;i++)
    {
    char c = (char)(rnd.nextInt(26)+'a');
    int d = rnd.nextInt(9);
    st += c+""+d;
    }
	return st;
  }
}
