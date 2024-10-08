package com.bolsadeideas.springboot.app.filters;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import jakarta.servlet.*;

import com.bolsadeideas.springboot.app.models.entity.User;

//@WebFilter({"/subjects/form/*", "/subjects/delete/*", "/professors/*", "/students/*"})
public class StudentFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        User user = (User) httpRequest.getSession().getAttribute("username");
        if (user.getRole().isAdmin()) {
        	chain.doFilter(request, response);
        } else {
        	httpResponse.sendRedirect("/menu");
        	// httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "You don't have access to this page");
        }
	}
}