package com.happy.smilely.main_project.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
@SuppressWarnings("null")
public class AdminInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler)
			throws Exception {
		
		if(isLogin(req)) {
			return true;
		}else {
			res.setContentType("text/html;charset=utf-8");
			res.getWriter().write("<script type='text/javascript'>" 
									+ " alert(\"" + "관리자 로그인이 필요합니다." + "\");"
									+ " location.href=\"" + "/admin/login" + "\";"
									+ "</script>"
			);
			return false;
		}
	}

	private boolean isLogin(HttpServletRequest req) {
		HttpSession session = req.getSession();
		if(session != null) {
			String adminId = (String) session.getAttribute("ADMIN");
			if(adminId != null) {
				return true;
			}
		}
		return false;
	}

}
