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
import pers.goodwin.shopSystem.service.ShoppingService;

@WebServlet("/pay")
public class PayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PayServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		ShoppingService shoppingService = (ShoppingService)context.getBean("shoppingService");
		User user = (User)request.getSession().getAttribute("user");
		((ClassPathXmlApplicationContext)context).close();
		if(user != null && shoppingService.shopping(user.getId())) 
			response.sendRedirect(request.getContextPath()+"/showPageServlet?currentPage=indent");
		else
			response.sendRedirect(request.getContextPath()+"/showPageServlet?currentPage=cart");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
