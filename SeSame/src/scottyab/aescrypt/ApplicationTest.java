package scottyab.aescrypt;

import android.app.Application;
import android.test.ApplicationTestCase;

import java.security.GeneralSecurityException;

import com.sesame.BuildConfig;


/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }


   public void testEncryptDecrypt(){

       String password = "password";
       String message = "hello world";

       if (BuildConfig.DEBUG) {
           AESCrypt.DEBUG_LOG_ENABLED = true;
       }

       String encryptedMsg = null;
       try {
           encryptedMsg = AESCrypt.encrypt(password, message);
       }catch (GeneralSecurityException e){
           fail("error occurred during encrypt");
           e.printStackTrace();
       }

       String messageAfterDecrypt = null;
       try {
           messageAfterDecrypt = AESCrypt.decrypt(password, encryptedMsg);

       }catch (GeneralSecurityException e){
           fail("error occurred during Decrypt");
           e.printStackTrace();
       }

       if (!messageAfterDecrypt.equals(message)){
           fail("messages don't match after encrypt and decrypt");
       }
   }





    public String testEncryt( String message,String password){

         password = "password";
         message = "hello world";
        String encryptedMsg="";
        try {
             encryptedMsg = AESCrypt.encrypt(password, message);

        }catch (GeneralSecurityException e){
            //handle error

            fail("error occurred during encrypt");
            e.printStackTrace();
        }
        return encryptedMsg;
    }


    public String testDecrpyt(String encryptedMsg,String password){

         password = "password";
         encryptedMsg = "2B22cS3UC5s35WBihLBo8w==";
         String messageAfterDecrypt ="";
        try {

             messageAfterDecrypt = AESCrypt.decrypt(password, encryptedMsg);

        }catch (GeneralSecurityException e){
            fail("error occurred during Decrypt");
            e.printStackTrace();
        }
        return messageAfterDecrypt;
    }


}