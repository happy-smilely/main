package com.happy.smilely.main_project.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.happy.smilely.main_project.dao.UserDao;
import com.happy.smilely.main_project.dto.UserInfo;
import com.happy.smilely.main_project.dto.UserInfo.ApiBody;
import com.happy.smilely.main_project.entity.User;
import com.happy.smilely.main_project.exception.BaseRuntimeException;
import com.happy.smilely.main_project.util.AES256Util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
	
	private final UserDao userDao;

	//사용자 정보 저장
	@Transactional
	public void saveUser(String userToken, UserInfo.Form form) {
		
		User user = userTokenToUser(userToken);
		String deviceOs = form.getDeviceOs();
		String fcmToken = form.getFcmToken();
		
		if(user != null) {
			user.setDeviceOs(deviceOs);
			user.setFcmToken(fcmToken);
			user.setLastAuthDt(LocalDateTime.now());
		}else {
			form = userTokenToForm(userToken);
			
			user = new User();
			user.setUsername(form.getUsername());
			user.setBirthDt(form.getBirthDt());
			user.setGender(form.getGender());
			user.setMobileNo(form.getMobileNo());
			user.setDeviceOs(deviceOs);
			user.setFcmToken(fcmToken);
			user.setLastAuthDt(LocalDateTime.now());
			user.setPrivacyYn("Y");
			user.setPrivacyDt(LocalDateTime.now());
		}
		
		userDao.save(user);
	}
	
	
	public UserInfo.ApiBody convertUserInfoToApiBody(UserInfo.Form form) {
		UserInfo.ApiBody body = new ApiBody();
		body.setsName(form.getUsername());
		body.setsMobileNo(form.getMobileNo());
		body.setsGender(form.getGender());
		body.setsBirthDate(form.getBirthDt());
		return body;
	}
	
	public boolean isValidUser(UserInfo.Form form) {
		
		try {
			String decryptedStr = AES256Util.decrypt(form.getUserToken());
			String[] split = decryptedStr.split("\\|");
			
			if(split != null && split.length == 4) {
				if(split[0].equals((String)form.getUsername())
					&& split[1].equals((String)form.getMobileNo())
					&& split[2].equals((String)form.getBirthDt())
					&& split[3].equals((String)form.getGender())) {
					return true;
				}
			}
		} catch (Exception e) {
			log.error("@@@ 사용자 검증 실패 - {}", e.getMessage());
			return false;
		}
		
		
		return false;	
	}
	
	public String makeUserToken(UserInfo.Form form) {
		String str = new StringBuilder()
				.append(form.getUsername())
				.append("|")
				.append(form.getMobileNo())
				.append("|")
				.append(form.getBirthDt())
				.append("|")
				.append(form.getGender())
				.toString();
		
		String encryptedStr = AES256Util.encrypt(str);
		
		return encryptedStr;
		
	}
	
	public UserInfo.Form userTokenToForm(String userToken) {
		try {
			
			String decryptedStr = AES256Util.decrypt(userToken);
			String[] split = decryptedStr.split("\\|");
			
			UserInfo.Form form = new UserInfo.Form();
			form.setUsername(split[0]);
			form.setMobileNo(split[1]);
			form.setBirthDt(split[2]);
			form.setGender(split[3]);
			
			return form;
		}catch (Exception e) {
			log.error("@@@ user token 변환 실패 - {}", e.getMessage());
			throw new BaseRuntimeException("user toekn 변환 실패 : " + e.getLocalizedMessage());
		}
	}
	
	public User userTokenToUser(String userToken) {
			
		UserInfo.Form form = userTokenToForm(userToken);
		User user = userDao.findByUserInfo(form);
		return user;
		
	}


	public void logout(String userToken) {
		
		User user = userTokenToUser(userToken);
		
		if(user != null) {
			user.setFcmToken("");
			userDao.save(user);
		}
	}
	
}
