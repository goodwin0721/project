package pers.goodwin.shopSystem.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pers.goodwin.shopSystem.model.User;
import pers.goodwin.shopSystem.service.UserService;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private ApplicationContext context;
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserService userService = (UserService) context.getBean("userService");
		User user = userService.login(username, password);
		if(user != null) {
			request.getSession().setAttribute("user", user);
			boolean isManager = userService.isManager(username);
			request.getSession().setAttribute("isManager", isManager);
			response.sendRedirect(request.getContextPath()+"/jumpTo?action=login&isSuccess=true");
			//request.getRequestDispatcher("/showPageServlet?currentPage=index").forward(request, response);
		}else {
			//request.setAttribute("message", "<font color = 'red'>登录失败</font><br>");
			//request.getRequestDispatcher("/login.jsp").forward(request, response);
			response.sendRedirect(request.getContextPath()+"/jumpTo?action=login&isSuccess=false");
		}
		
	}

}
