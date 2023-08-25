package com.crypto.project.helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.web.multipart.MultipartFile;

public class ImgEncHelper {
	private static SecretKeySpec secretKey;
    private static byte[] key;
    public static void setKey(String myKey)
    {
        MessageDigest sha = null;
        try {
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

	 public static byte[] getFile(File file) {

	        File f = file;
	        InputStream is = null;
	        try {
	            is = new FileInputStream(f);
	        } catch (FileNotFoundException e2) {
	            // TODO Auto-generated catch block
	            e2.printStackTrace();
	        }
	        byte[] content = null;
	        try {
	            content = new byte[is.available()];
	        } catch (IOException e1) {
	            // TODO Auto-generated catch block
	            e1.printStackTrace();
	        }
	        try {
	            is.read(content);
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        System.out.println(content);
	        return content;
	        
	    }

    public static File encryptPdfFile(String key, byte[] content,String userId,String fileName) {
    	setKey(key);
        Cipher cipher;
        byte[] encrypted = null;
        try {
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            encrypted = cipher.doFinal(content);
            File f = saveFile(encrypted,userId,fileName);
            return f;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public static File decryptPdfFile(String key, byte[] textCryp,String userId,String fileName) {
    	setKey(key);
        Cipher cipher;
        byte[] decrypted = null;
        try {
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            decrypted = cipher.doFinal(textCryp);
             File f = saveFile(decrypted,userId,fileName);
             return f;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static File saveFile(byte[] bytes,String userId ,String fileName) throws IOException {
    	 
    	
    	String filePath = "E:\\CryptoGraphy_project\\EncData\\"+userId;
    	File f = new File(filePath);
    	f.mkdir();
    	
        FileOutputStream fos = new FileOutputStream(filePath+"\\"+fileName);
        fos.write(bytes);
        fos.close();
        File encrtfile = new File(filePath+"\\"+fileName);
		return encrtfile;
        
    }
    
    public static File convert(MultipartFile file) throws IOException {
    	File convFile = new File(file.getOriginalFilename());
    	convFile.createNewFile();
    	FileOutputStream fos = new FileOutputStream(convFile);
    	fos.write(file.getBytes());
    	fos.close();
    	return convFile;
    }
}