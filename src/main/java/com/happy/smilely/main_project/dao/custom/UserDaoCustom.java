package com.happy.smilely.main_project.dao.custom;

import java.util.List;

import com.happy.smilely.main_project.dto.UserInfo;
import com.happy.smilely.main_project.entity.User;

public interface UserDaoCustom {

	User findByUserInfo(UserInfo.Form userInfo);
	
	List<User> findAllByNoticePushTarget();
	
}
