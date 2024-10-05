package com.bolsadeideas.springboot.app.conf;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bolsadeideas.springboot.app.filters.AdminFilter;
import com.bolsadeideas.springboot.app.filters.LoginFilter;
import com.bolsadeideas.springboot.app.filters.StudentFilter;

@Configuration
public class FilterConfig {
	
	@Bean
	public FilterRegistrationBean<LoginFilter> registerLoginFilter() {
		FilterRegistrationBean<LoginFilter> registerFilter = new FilterRegistrationBean<>();
		registerFilter.setFilter(new LoginFilter());
		registerFilter.addUrlPatterns("/login", "/menu", "/subjects/*", "/professors/*", "/students/*");
		registerFilter.setOrder(1);
		return registerFilter;
	}
	
	@Bean
	public FilterRegistrationBean<StudentFilter> registerStudentFilter() {
		FilterRegistrationBean<StudentFilter> registerFilter = new FilterRegistrationBean<>();
		registerFilter.setFilter(new StudentFilter());
		registerFilter.addUrlPatterns("/subjects/form/*", "/subjects/delete/*", "/professors/*", "/students/*");
		registerFilter.setOrder(2);
		return registerFilter;
	}
	
	@Bean
	public FilterRegistrationBean<AdminFilter> registerAdminFilter() {
		FilterRegistrationBean<AdminFilter> registerFilter = new FilterRegistrationBean<>();
		registerFilter.setFilter(new AdminFilter());
		registerFilter.addUrlPatterns("/subjects/enrol/*");
		registerFilter.setOrder(2);
		return registerFilter;
	}

}
