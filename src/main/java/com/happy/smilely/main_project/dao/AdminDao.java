package com.happy.smilely.main_project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.happy.smilely.main_project.entity.Admin;

@Repository
public interface AdminDao extends JpaRepository<Admin, String> {

	Admin findByAdminIdAndAdminPw(String adminId, String encPw);
}
