package com.happy.smilely.main_project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.happy.smilely.main_project.service.UpbitApiService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/upbit")
public class UpbitApiController {

	private final UpbitApiService upbitApiService;
    
    @GetMapping("/candle-data")
    public ModelAndView getCandleData(ModelAndView mav) {

        try {
            
            String candleData = upbitApiService.getCandleData();
            mav.addObject("candleData", candleData);
            
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

		mav.setViewName("admin/upbit/candle");
		return mav;
    }
}
