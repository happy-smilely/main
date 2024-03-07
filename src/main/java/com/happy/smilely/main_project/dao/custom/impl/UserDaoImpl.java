package com.happy.smilely.main_project.dao.custom.impl;

import static com.happy.smilely.main_project.entity.QUser.user;

import java.util.List;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.happy.smilely.main_project.dao.custom.UserDaoCustom;
import com.happy.smilely.main_project.dto.UserInfo;
import com.happy.smilely.main_project.entity.User;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class UserDaoImpl implements UserDaoCustom {
	
	private final JPAQueryFactory queryFactory;

	@Override
	public User findByUserInfo(UserInfo.Form userInfo) {
		
		return queryFactory.selectFrom(user)
				.where(user.username.eq(userInfo.getUsername()),
						user.birthDt.eq(userInfo.getBirthDt()),
						user.gender.eq(userInfo.getGender()),
						user.mobileNo.eq(userInfo.getMobileNo()))
				.fetchFirst();
		
	}

	@Override
	public List<User> findAllByNoticePushTarget() {
		return queryFactory.selectFrom(user)
				.where(user.fcmToken.isNotEmpty())
				.fetch();
	}

}
