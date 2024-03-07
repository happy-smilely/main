package com.happy.smilely.main_project.util;

import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class CommonUtil {

	
	public static String getUserToken(HttpServletRequest req) {
		
		return req.getHeader("X-USER-TOKEN");
		
	}
	
	public static String usernameMasking(String username) {
		
		String maskingChar = "O";
		String maskingUsername = "";
		
		if(username.length() <= 2) {
			maskingUsername = username.replaceAll("(?<=.{1}).", maskingChar);
		}else {
			String firstName = username.substring(0, 1);
			String lastName = username.substring(username.length()-1, username.length());
			String midName = username.substring(1, username.length()-1);
			
			//midName = maskingChar.repeat(midName.length());
			//JDK11 -> JDK8 로 변경
			StringBuilder sb = new StringBuilder();
			for(int i=0; i<midName.length(); i++) {
				sb.append(maskingChar);
			}
			midName = sb.toString();
			
			maskingUsername = firstName + midName + lastName;
		}
		
		return maskingUsername;
	}
	
}
