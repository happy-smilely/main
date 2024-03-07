package com.happy.smilely.main_project.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="tb_user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", columnDefinition = "INT(11) UNSIGNED")
	private Long id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "birthDt")
	private String birthDt;
	
	@Column(name = "mobile_no")
	private String mobileNo;
	
	@Column(name = "gender")
	private String gender;
	
	@Column(name = "device_os")
	private String deviceOs;
	
	@Column(name = "fcm_token")
	private String fcmToken;
	
	@Column(name = "last_auth_dt")
	private LocalDateTime lastAuthDt;
	
	@Column(name = "privacy_yn", columnDefinition = "VARCHAR(1)")
	private String privacyYn;
	
	@Column(name = "privacy_dt")
	private LocalDateTime privacyDt;
	
}
