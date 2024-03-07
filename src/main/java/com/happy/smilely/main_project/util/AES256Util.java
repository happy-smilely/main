package com.happy.smilely.main_project.util;

import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

import com.happy.smilely.main_project.exception.BaseRuntimeException;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AES256Util {
	
	private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";
	private static final String SECRET_KEY = "Seoul_Clinical_Laboratories_API@";

	public static String encrypt(String data) {
		try {
			byte[] keyData = SECRET_KEY.getBytes(StandardCharsets.UTF_8);
			byte[] ivData = SECRET_KEY.substring(0, 16).getBytes(StandardCharsets.UTF_8);
			SecretKeySpec secretKeySpec = new SecretKeySpec(keyData, "AES");
			Cipher cipher = Cipher.getInstance(TRANSFORMATION);
			IvParameterSpec ivParameterSpec = new IvParameterSpec(ivData);
			cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
			
			byte[] encrypted = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
			return new String(Base64.getEncoder().encode(encrypted));
		
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | 
				InvalidKeyException | InvalidAlgorithmParameterException | 
				IllegalBlockSizeException | BadPaddingException e) {
			
			log.error("@@@ AES256 encrpyt failed - {}", e.getMessage());
			throw new BaseRuntimeException(e.getMessage());
			
		}
	}
	
	public static String decrypt(String encryptedData) {
		try {
			byte[] keyData = SECRET_KEY.getBytes(StandardCharsets.UTF_8);
			byte[] ivData = SECRET_KEY.substring(0, 16).getBytes(StandardCharsets.UTF_8);
			SecretKeySpec secretKeySpec = new SecretKeySpec(keyData, "AES");
			Cipher cipher = Cipher.getInstance(TRANSFORMATION);
			IvParameterSpec ivParameterSpec = new IvParameterSpec(ivData);
			cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
			
			byte[] decrypted = Base64.getDecoder().decode(encryptedData.getBytes(StandardCharsets.UTF_8));
			return new String(cipher.doFinal(decrypted), StandardCharsets.UTF_8);
			
			} catch (NoSuchAlgorithmException | NoSuchPaddingException |
					InvalidKeyException | InvalidAlgorithmParameterException |
					IllegalBlockSizeException | BadPaddingException e) {
				
				log.error("@@@ AES256 decrypt failed - {}", e.getMessage());
				throw new BaseRuntimeException(e.getMessage());
			
			}
	}
	
}
