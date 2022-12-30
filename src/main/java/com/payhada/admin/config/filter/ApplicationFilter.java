package com.payhada.admin.config.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@Component
public class ApplicationFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		/* For SameSite issue
		 * See https://stackoverflow.com/questions/58270663/samesite-warning-chrome-77
		 */
        HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		res.setHeader("Access-Control-Allow-Origin", "*");
		res.setHeader("Set-Cookie", "HttpOnly;Secure;SameSite=None");
		chain.doFilter(req, res);
	}

	// private HttpServletResponse addSameSite(HttpServletResponse res,String sameSite) {
	// 	Collection<String> headers = res.getHeaders(HttpHeaders.SET_COOKIE);
	// 	boolean firstHeader = true;
	// 	for (String header : headers) {
	// 		if(firstHeader) {
	// 			res.setHeader(HttpHeaders.SET_COOKIE, String.format("%s; Secure; %s",header,"SameSite="+sameSite));
	// 			firstHeader=false;
	// 			continue;
	// 		}
	// 		res.addHeader(HttpHeaders.SET_COOKIE, String.format("%s; Secure; %s",header,"SameSite="+sameSite));
	// 	}
	// 	return res;
	// }
}
