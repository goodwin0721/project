package pers.goodwin.shopSystem.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pers.goodwin.shopSystem.service.UserService;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private ApplicationContext context;
	private static final long serialVersionUID = 1L;
    public RegisterServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserService userService = (UserService) context.getBean("userService");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String birthdate = request.getParameter("birthdate");
		String gender = request.getParameter("sex");
		//System.out.println(birthdate);
		boolean isSuccess = userService.register(username, password, birthdate, gender);
		if(isSuccess) {
			response.sendRedirect(request.getContextPath()+"/jumpTo?action=register&isSuccess=true" );
			//request.setAttribute("message", "<font color = 'green'>注册成功，请登录！!</font><br>");
			//request.getRequestDispatcher("/login.jsp").forward(request, response);
		}else {
			response.sendRedirect(request.getContextPath()+"/jumpTo?action=register&isSuccess=false" );
			//request.setAttribute("message", "<font color='red'>注册失败！</font><br>");
			//request.getRequestDispatcher("/register.jsp").forward(request, response);
		}
	}

}
