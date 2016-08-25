package aes;

import java.io.IOException;
import java.util.Random;


public class enc {
	
	public static void main(String args[]) throws IOException{
		String key = "1234567890";
		String msg1 = "sdafaksjjhasdaskjdhsjkahdahd";
		String msg = "hello worlskzudgfvilssivsdubvkjxzvkcxjbvkxzbvkjbcxkvbkcjxbvkbxckjvbxcvj"
                        + "zndvkjbxcvkjbcxkjvbkjxcbvkjxbcvkjbxcv"
                        + "xkcnvkjcxbvkjbxckjvbxc"
                        + "xz3nvkxcjbvkxjbcvkjbccccccccccccccccccccccccccccdkubzukbvd";
		StringBuilder sbuf = new StringBuilder();
		
		enc en = new enc();
		//System.out.println(cipher);
		//System.out.println(enc.decryption(cipher, key));
		System.out.println("Message= "+msg);
		
		String cipher1 = enc.encryption(msg, "123456");
		//String cipher = enc.binary_to_String(cipher1);
		System.out.println("Cipher= "+cipher1);
		 
                                         String plntxxt = enc.decryption(cipher1, "123456");
                                         System.out.println("plntxt= "+plntxxt);
	 
			
		}
	
	public static String encryption(String msg,String key) throws IOException,StringIndexOutOfBoundsException
	{
		String out = "";
		StringBuilder sbuf = new StringBuilder(msg);
		int mlen = msg.length();
                System.out.println("length messgae--"+mlen);
		int klen = key.length();
                System.out.println("klen messgae--"+klen);
		//String msg1[] = msg.split(" ");
		//String key1[] = key.split(" ");
		int count=0,cnt=0;
		
		for(int i=0,j=0;i<mlen;i++,j++)
		{
			if(j>=klen){
				j=0;
			}
			sbuf.setCharAt(i,(char)(msg.charAt(i)^key.charAt(j)));
		}
		out+=sbuf.toString();
		return out;
	}
	
	public static String decryption(String msg,String key) throws IOException{
		StringBuilder sbuf = new StringBuilder(msg);
		int mlen = msg.length();
		int slen = key.length();
		String pkey = "";
		String newkey="";
		Random rnd = new Random();
		for(int i=0;i<slen;i++){
			pkey+=rnd.nextInt(9);
		}
		for(int i=0,j=0;i<mlen;i++,j++)
		{
			if(j>=key.length())
			{
				j=0;
			}
			sbuf.setCharAt(i,(char)(msg.charAt(i)^key.charAt(j)));
		}
		return sbuf.toString();
	}
	
	public static String convert_to_binary(String msg,int pad){
		StringBuilder sbuf = new StringBuilder();
		byte[] bytes = msg.getBytes();
		for(byte b:bytes){
			int val = b;
			for(int i=1;i<=pad;i++)
			{
				//System.out.println(val);
			sbuf.append((val & 128)==0? 0 : 1);
			//System.out.println(sbuf);
			val = val<<1;
			}
			sbuf.append(' ');
		 }
		String out = sbuf.toString();
		return out;
	}

	public static void my_convert_to_binary(String msg){
		int len = msg.length();
		char[] ch = new char[len];
		int[] in = new int[len];
		String[] bin = new String[len];
		for(int i=0;i<len;i++)
		{
			ch[i] = msg.charAt(i);
			in[i] = ch[i];
			//System.out.println(in[i]);
		}
		int j=0;
		int temp;
		String out = "";
		while(j<len){
			out+=in[j]%2;
			j++;
		}
		System.out.println(out);
	}
	
	public static String binary_to_String(String msg)
	{
		{ 
		    String[] ss = msg.split( " " );
		    StringBuilder sb = new StringBuilder();
		    for ( int i = 0; i < ss.length; i++ ) { 
		        sb.append( (char)Integer.parseInt( ss[i], 2 ) );                                                                                                                                                        
		    }   
		    return sb.toString();
		}   
	}
	
	public static String encrypt(String data, String key)
    {
        byte[] bytes1 = data.getBytes();
        StringBuilder binaryData = new StringBuilder();
        for (byte b : bytes1)
        {
            int val = b;
            for (int i = 0; i < 8; i++)
            {
                binaryData.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
            //binaryData.append(' ');
        }
        //System.out.println(binaryData);

        byte[] bytes2 = key.getBytes();
        StringBuilder binaryKey = new StringBuilder();
        for (byte b : bytes2)
        {
            int val = b;
            for (int i = 0; i < 8; i++)
            {
                binaryKey.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
            //binaryKey.append(' ');
        }
        //System.out.println(binaryKey);

        StringBuffer encryptedData = new StringBuffer();
        int x = 0;
        for (int i = 0; i < binaryData.length(); i++)
        {

            if (binaryData.charAt(i) == binaryKey.charAt(x))
            {
                encryptedData.append(0);
            }
            else
            {
                encryptedData.append(1);
            }
            if (x == binaryKey.length() - 1)
            {
                x = 0;
            }
            else
            {
                x++;
            }
        }
        return encryptedData.toString();
    }
	

	public static String decrypt(String encryptedDataTest, String key)
    {


        StringBuilder encryptedData = new StringBuilder();
        encryptedData.append(encryptedDataTest);
        StringBuffer unencryptedData = new StringBuffer();

        byte[] bytes2 = key.getBytes();
        StringBuilder binaryKey = new StringBuilder();
        for (byte b : bytes2)
        {
            int val = b;
            for (int i = 0; i < 8; i++)
            {
                binaryKey.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
        }
        int x = 0;
        for (int i = 0; i < encryptedData.length(); i++)
        {

            if (encryptedData.charAt(i) == binaryKey.charAt(x))
            {
                unencryptedData.append(0);
            }
            else
            {
                unencryptedData.append(1);
            }
            if (x == binaryKey.length() - 1)
            {
                x = 0;
            }
            else
            {
                x++;
            }
        }
        //System.out.println(unencryptedData);
        String out = "";
        char character = ' ';
        String testToString;
        String unencryptedDataToString = unencryptedData.toString();
        int currentBinary = 0;
        int currentInt = 0;
        for (int i = 0; i < unencryptedDataToString.length() / 8; i++)
        {
            unencryptedDataToString = unencryptedData.toString();
            testToString = (unencryptedDataToString.substring(i * 8, i * 8 + 8));
            currentBinary = Integer.parseInt(testToString, 2);
            currentInt = (int) currentBinary;
            character = (char) currentInt;
            out+=character;
            //System.out.print(character);
        }
        //System.out.println(out);
        //System.exit(0);
        return out;
    }
        
    }

