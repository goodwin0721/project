package pers.goodwin.shopSystem.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pers.goodwin.shopSystem.model.User;
import pers.goodwin.shopSystem.service.ShoppingService;

/**
 * @author goodwin
 * 用户添加商品到购物车
 */
@WebServlet("/addToCart")
public class AddGoodsToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddGoodsToCartServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		User user = (User)request.getSession().getAttribute("user");
		String message = "";
		boolean flag = false;
		if(user == null) {
			message = "操作失败,请先登录";
		}else {
			int userId = user.getId();
			int goodsId = Integer.parseInt(request.getParameter("goodsId"));
			int amount = Integer.parseInt(request.getParameter("amount"));
//			int price =  Integer.parseInt(request.getParameter("price"));
//			System.out.println(userId);
//			System.out.println(request.getParameter("goodsId"));
//			System.out.println(request.getParameter("amount"));
//			System.out.println(request.getParameter("price"));
			ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
			ShoppingService shoppingService = (ShoppingService) context.getBean("shoppingService");
			flag = shoppingService.addGoodsToCart(userId, goodsId, amount);
			((ClassPathXmlApplicationContext)context).close();
		}
		if(flag)
			message = "已将该商品加入购物车";
		else
			message = "操作失败";
		
		out.write(message);
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
