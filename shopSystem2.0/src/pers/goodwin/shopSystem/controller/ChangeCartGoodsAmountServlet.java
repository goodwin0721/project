package pers.goodwin.shopSystem.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pers.goodwin.shopSystem.model.CartShow;
import pers.goodwin.shopSystem.model.User;
import pers.goodwin.shopSystem.service.ShoppingService;

@WebServlet("/changeCartGoodsAmount")
public class ChangeCartGoodsAmountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ChangeCartGoodsAmountServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String message;
		boolean isSuccess = false;
		User user = (User)request.getSession().getAttribute("user");
		if(user == null)
			message = "false";
		int userId = user.getId();
		int goodsId = Integer.parseInt(request.getParameter("goodsId"));
		int amount = Integer.parseInt(request.getParameter("amount"));
		int aubtotal = 0;
		int total = 0;
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		ShoppingService shoppingService = (ShoppingService)context.getBean("shoppingService");
		if(amount == 0) 
			isSuccess = shoppingService.removeGoodsFromCart(userId, goodsId);
		else {
			isSuccess = shoppingService.updateGoodsAmountInCart(userId, goodsId, amount);
		}
		List<CartShow> cartList = shoppingService.showUserCart(userId);		
		for(CartShow cart : cartList) {
			if(cart.getGoodsId() == goodsId) {
				aubtotal = cart.getAubtotal();
			}
			total += cart.getAubtotal();
		}
		if(isSuccess)
			message = "true," + aubtotal + "," + total;
		else
			message = "false";
		((ClassPathXmlApplicationContext)context).close();
		out.write(message);
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
