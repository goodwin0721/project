package pers.goodwin.shopSystem.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebFilter("/AdminFilter")
public class AdminFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// place your code here
		HttpServletRequest httpServletRequest = (HttpServletRequest)request;
		HttpServletResponse httpServletResponse = (HttpServletResponse)response;
		Object o = httpServletRequest.getSession().getAttribute("user");
		Object o2 = httpServletRequest.getSession().getAttribute("isManager");
		if(o==null) {
			httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/showPageServlet?currentPage=index");
		}else {
			boolean isManager = (boolean)o2;
			if(isManager) {
				chain.doFilter(request, response);
			}else
				httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/showPageServlet?currentPage=index");
		}
		// pass the request along the filter chain
	}
}
