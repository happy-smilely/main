package com.happy.smilely.main_project.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.happy.smilely.main_project.dao.custom.UserDaoCustom;
import com.happy.smilely.main_project.entity.User;

@Repository
public interface UserDao extends JpaRepository<User, Long>, UserDaoCustom {
	
	List<User> findByMobileNo(String mobileNo); // 개발자 테스트용
	
}
