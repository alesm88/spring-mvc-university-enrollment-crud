package com.bolsadeideas.springboot.app.conf;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bolsadeideas.springboot.app.filters.UserFilter;

@Configuration
public class FilterConfig {
	
	@Bean
	public FilterRegistrationBean<UserFilter> registerUserFilter() {
		FilterRegistrationBean<UserFilter> registerFilter = new FilterRegistrationBean<>();
		registerFilter.setFilter(new UserFilter());
		registerFilter.addUrlPatterns("/subjects/form/*", "/subjects/delete/*", "/professors/*", "/students/*");
		registerFilter.setOrder(1);
		return registerFilter;
	}

}
