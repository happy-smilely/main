package com.happy.smilely.main_project.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="tb_admin")
public class Admin {
	
	@Id
	@Column(name = "admin_id")
	private String adminId;
	
	@Column(name = "admin_pw")
	private String adminPw;

}
