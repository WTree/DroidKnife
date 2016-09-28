package com.tools.knife;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;


/**
 * 3des加密
 * @author WTree
 *
 *
 * 使用 例子:
 * CBC加密 Des3EncodeCBC("aa","123456");
 * CBC解密 DesDecodeCBC("123456","密文");
 */
public class ThreeDesUtis {

	/**
	 *
	 * 偏移向量
	 * @param iv
     */
	public static void setKeyiv(byte[] iv){
		keyiv=iv;
	}
	protected static byte[] keyiv= { 0x6b,0x6e,0x69, 0x66 ,0x65 ,0x31 ,0x32 ,0x33};;// IV=knife123
	/**
	 * 
	 * @param arg1 key
	 * @param arg2  需要加密的字符串
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String Des3EncodeCBC(String arg1, String arg2) {
		
		try {
			byte[] key=byteToHex(getMD5(arg1)).getBytes();
			byte[] encode=des3EncodeCBC(key, arg2.getBytes());
			return Base64.encodeToString(encode, Base64.DEFAULT).trim();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";

	}

	/**
	 *
	 * @param arg1 key
	 * @param arg2 密文
     * @return
     */
	public static String DesDecodeCBC(String arg1, String arg2){
		try {
			byte[] key=byteToHex(getMD5(arg1)).getBytes();
			byte[] data= Base64.decode(arg2, Base64.DEFAULT);
			byte[] msg=des3DecodeCBC(key,data);
			return  new String(msg,"utf-8").trim();
		}catch (NoSuchAlgorithmException e){
			e.printStackTrace();
		}catch (UnsupportedEncodingException e){
			e.printStackTrace();
		}
		return "";

	}

	/**
	 * 3des 加密
	 * @param key 密钥
	 * @param data 明文
	 * @return Base64编码的密文
	 * @throws Exception
	 */
	protected static byte[] des3EncodeECB(byte[] key, byte[] data)
	{
		byte[] bOut=null;
		try {
				Key deskey = null;
				DESedeKeySpec spec = new DESedeKeySpec(key);
				SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
				deskey = keyfactory.generateSecret(spec);
				Cipher cipher = Cipher.getInstance("desede" + "/ECB/PKCS5Padding");
				cipher.init(Cipher.ENCRYPT_MODE, deskey);
				bOut = cipher.doFinal(data);
       
			} catch (Exception e) {
			e.printStackTrace();
		}
       return bOut;
   }

	/**
	 * @param key 密钥
	 * @param data Base64编码的密文
	 * @return 明文
	 * @throws Exception
	 */
	protected static byte[] ees3DecodeECB(byte[] key, byte[] data)
	 {
		byte[] bOut=null;
		try {
			Key deskey = null;
			DESedeKeySpec spec = new DESedeKeySpec(key);
			SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
			deskey = keyfactory.generateSecret(spec);
			Cipher cipher = Cipher.getInstance("desede" + "/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, deskey);
			bOut = cipher.doFinal(data);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
       return bOut;

   }

	/**
    * @param key 密钥
    * @param data 明文
    * @return Base64编码的密文
    * @throws Exception
    */
	protected static byte[] des3EncodeCBC(byte[] key, byte[] data)
	{
		 byte[] bOut=null;
		 try {
		       Key deskey = null;
		       DESedeKeySpec spec = new DESedeKeySpec(key);
		       SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
		       deskey = keyfactory.generateSecret(spec);
		       Cipher cipher = Cipher.getInstance("desede" + "/CBC/PKCS5Padding");
		       IvParameterSpec ips = new IvParameterSpec(keyiv);
		       cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
		       bOut = cipher.doFinal(data);
		  } catch (Exception e) {
			e.printStackTrace();
		  }
       return bOut;
   }

	/**
    * @param key 密钥
    * @param data Base64编码的密文
    * @return 明文
    * @throws Exception
    */
	protected static byte[] des3DecodeCBC(byte[] key, byte[] data)
	{
		byte[] bOut=null;
		try {
				Key deskey = null;
				DESedeKeySpec spec = new DESedeKeySpec(key);
				SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
				deskey = keyfactory.generateSecret(spec);
				Cipher cipher = Cipher.getInstance("desede" + "/CBC/PKCS5Padding");
				IvParameterSpec ips = new IvParameterSpec(keyiv);
				cipher.init(Cipher.DECRYPT_MODE, deskey, ips);
				bOut = cipher.doFinal(data);
       
		 } catch (Exception e) {
			e.printStackTrace();
		}
       return bOut;
    }
	
	protected static byte[] getMD5(String key) throws NoSuchAlgorithmException
	{
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(key.getBytes()); 
		byte b[] = md.digest(); 
		return b;
	}
	protected static String byteToHex(byte[] b)
	{
		int i;
		StringBuffer buf = new StringBuffer("");
		for (int offset = 0; offset < b.length; offset++) { 
		i = b[offset]; 
		if(i<0) i+= 256; 
		if(i<16) 
		buf.append("0"); 
		buf.append(Integer.toHexString(i));
		} 
		return buf.toString();
	}
}