package com.payhada.admin.config.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApplicationFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		final Logger log = LoggerFactory.getLogger(getClass());
		/* For SameSite issue 
		 * See https://stackoverflow.com/questions/58270663/samesite-warning-chrome-77
		 */
        HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		res.setHeader("Access-Control-Allow-Origin", "*");
		res.setHeader("Set-Cookie", "HttpOnly;Secure;SameSite=None");
		chain.doFilter(req, res);
	}

}
