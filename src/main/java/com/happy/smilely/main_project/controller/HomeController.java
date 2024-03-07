package com.happy.smilely.main_project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HomeController {
	
	@GetMapping(value = {"", "/"})
	public ModelAndView home(ModelAndView mav) {

		mav.setViewName("index");
		return mav;
	}

}