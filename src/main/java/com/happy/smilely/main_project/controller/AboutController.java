package com.happy.smilely.main_project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/about")
public class AboutController {
	
	@GetMapping(value = {"", "/"})
	public ModelAndView main(ModelAndView mav) {

		mav.setViewName("about/index");
		return mav;
	}

}