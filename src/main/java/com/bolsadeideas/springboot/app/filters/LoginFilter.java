package com.bolsadeideas.springboot.app.filters;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import jakarta.servlet.*;

import com.bolsadeideas.springboot.app.models.entity.User;

//@WebFilter("/*")
public class LoginFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        User user = (User) httpRequest.getSession().getAttribute("username");
        
        if (user != null) {
        	// Redirect to index if already logged in
        	if (httpRequest.getRequestURI().equals("/login")) {
                httpResponse.sendRedirect("/");
                return;
            } else {
            	chain.doFilter(request, response);
            }
        } else {
        	if (httpRequest.getRequestURI().equals("/login")) {
        		chain.doFilter(request, response);
        	} else {
        		httpResponse.sendRedirect("/login");
        		// httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "You don't have access to this page");
        	}
        	 
        }
	}
}