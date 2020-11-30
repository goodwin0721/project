package pers.goodwin.shopSystem.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/jumpTo")
public class JumpTo extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public JumpTo() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action == null) {
			response.sendRedirect(request.getContextPath() + "/404.jsp");
		} else {
			switch (action) {
			case "register": {
				String isSuccess = request.getParameter("isSuccess");
				//注册成功，跳转到登录页面
				if(isSuccess.equals("true")) {
					request.setAttribute("message", "<font color = 'green'>注册成功，请登录！</font><br>");
					request.getRequestDispatcher("/login.jsp").forward(request, response);
				}else {
					//注册失败，跳回注册页面
					request.setAttribute("message", "<font color='red'>注册失败！</font><br>");
					request.getRequestDispatcher("/register.jsp").forward(request, response);
				}
				break;
			}
			case "login": {
				String isSuccess = request.getParameter("isSuccess");
				//登录成功，跳转到主页
				if(isSuccess.equals("true")) {
					response.sendRedirect(request.getContextPath() + "/showPageServlet?currentPage=index");
					//request.getRequestDispatcher(request.getContextPath() + "/showPageServlet?currentPage=index").forward(request, response);
				}else {
					//登录失败，跳回登录页面
					request.setAttribute("message", "<font color = 'red'>登录失败！</font><br>");
					//System.out.println(request.getContextPath() + "/login.jsp");
					request.getRequestDispatcher("/login.jsp").forward(request, response);
				}
				break;
			}
			case "logout": {
				response.sendRedirect(request.getContextPath() + "/showPageServlet?currentPage=index");
				break;
			}
			default: {
				response.sendRedirect(request.getContextPath() + "/404.jsp");
				break;
			}
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
