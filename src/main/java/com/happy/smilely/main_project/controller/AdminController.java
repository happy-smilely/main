package com.happy.smilely.main_project.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.happy.smilely.main_project.dao.AdminDao;
import com.happy.smilely.main_project.dto.ResponseDto;
import com.happy.smilely.main_project.entity.Admin;
import com.happy.smilely.main_project.util.AES256Util;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
	
	private final AdminDao adminDao;
	
	@GetMapping(value = {"", "/"})
	public ModelAndView index(ModelAndView mav) {

		mav.setViewName("admin/index");
		return mav;
	}

	@GetMapping("/login")
	public ModelAndView login(ModelAndView mav) {
		
		mav.setViewName("admin/login");
		return mav;
	}
	
	@PostMapping("/login/proc")
	public ResponseDto loginProc(HttpServletRequest req, @RequestBody Map<String, Object> map) {
		
		String adminId = (String) map.get("adminId");
		String adminPw = (String) map.get("adminPw");
		String encPw = AES256Util.encrypt(adminPw);
		
		Admin admin = adminDao.findByAdminIdAndAdminPw(adminId, encPw);
		if(admin != null) {
			req.getSession().setAttribute("ADMIN", admin.getAdminId());
			return ResponseDto.setSuccess();
		}else {
			return ResponseDto.setFail(401, "로그인에 실패했습니다.");
		}
		
	}
	
	@PostMapping("/logout")
	public ResponseDto logout(HttpServletRequest req) {
		
		req.getSession().invalidate();
		
		return ResponseDto.setSuccess();
	}
	
}
