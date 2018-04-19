package com.smartres.phone.controller;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.shiro.codec.Base64;

public class AESUtil {
	  //加密  
    public static String Encrypt(String str,String key) {  
        try{  
           
            byte[] kb = key.getBytes("utf-8");  
            SecretKeySpec sks = new SecretKeySpec(kb, "AES");  
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");//算法/模式/补码方式  
            cipher.init(Cipher.ENCRYPT_MODE, sks);  
            byte[] eb = cipher.doFinal(str.getBytes("utf-8"));  
            return new Base64().encodeToString(eb);  
        }catch(Exception e){  
            e.printStackTrace();  
            return null;  
        }  
    }  
      
    //解密  
    public static String Decrypt(String str,String key) {  
        try{  
            byte[] kb = key.getBytes("utf-8");  
            SecretKeySpec sks = new SecretKeySpec(kb, "AES");  
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");  
            cipher.init(Cipher.DECRYPT_MODE, sks);  
            byte[] by = new Base64().decode(str);  
            byte[] db = cipher.doFinal(by);  
            return new String(db);  
        }catch(Exception e){  
            e.printStackTrace();  
            return null;  
        }  
    }  
}
