package com.happy.smilely.main_project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.happy.smilely.main_project.interceptor.AdminInterceptor;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {
	
	private final AdminInterceptor adminInterceptor;
	
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor(adminInterceptor)
			.addPathPatterns("/admin/**")
			.excludePathPatterns("/admin/login")
			.excludePathPatterns("/admin/login/proc")
			.excludePathPatterns("/static/**");
		
	}
	
	@Bean(name="jsonView")
    public MappingJackson2JsonView jsonView() {
		MappingJackson2JsonView view = new MappingJackson2JsonView();
		view.setPrettyPrint(true);
		view.setModelKey("result"); //modelKey로 지정된 result만 response에 포함
		view.setExtractValueFromSingleKeyModel(true); //하나의 modelKey 만 응답 (modelAndView modelKey 제외)
        return view;
    }

    @Bean
    public ViewResolver viewResolver() {
        BeanNameViewResolver viewResolver = new BeanNameViewResolver();
        viewResolver.setOrder(0);
        return viewResolver;
    }
	
}
