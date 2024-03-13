package com.happy.smilely.main_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class MainProjectApplication extends SpringBootServletInitializer {
	
	@Override 
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) { return application.sources(MainProjectApplication.class); }

	public static void main(String[] args) {
		SpringApplication.run(MainProjectApplication.class, args);
	}

}
