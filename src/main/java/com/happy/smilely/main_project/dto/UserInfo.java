package com.happy.smilely.main_project.dto;

import lombok.Getter;
import lombok.Setter;


public class UserInfo {

	@Getter
	@Setter
	public static class Form {
		private String username;
		private String mobileNo;
		private String birthDt;
		private String gender;
		
		private String userToken; //사용자 정보 암호화 문자열 - 내용 전달받을 예정 (웹용)
		private String fcmToken;
		private String order; //정렬
		private String deviceOs; //웹뷰 사용시 deviceOs 구별 어려움
	}
	
	//Lombok 사용시 대소문자 문제로 getter/setter 수동 생성
	public static class ApiBody {
		private String sName;
		private String sMobileNo;
		private String sBirthDate;
		private String sGender;
		
		public String getsName() {
			return sName;
		}
		public void setsName(String sName) {
			this.sName = sName;
		}
		public String getsMobileNo() {
			return sMobileNo;
		}
		public void setsMobileNo(String sMobileNo) {
			this.sMobileNo = sMobileNo;
		}
		public String getsBirthDate() {
			return sBirthDate;
		}
		public void setsBirthDate(String sBirthDate) {
			this.sBirthDate = sBirthDate;
		}
		public String getsGender() {
			return sGender;
		}
		public void setsGender(String sGender) {
			this.sGender = sGender;
		}
		
	}
	
}
