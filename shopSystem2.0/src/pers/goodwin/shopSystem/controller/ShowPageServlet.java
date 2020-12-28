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

import com.alibaba.fastjson.JSON;

import pers.goodwin.shopSystem.model.CartShow;
import pers.goodwin.shopSystem.model.Goods;
import pers.goodwin.shopSystem.model.IndentShow;
import pers.goodwin.shopSystem.model.User;
import pers.goodwin.shopSystem.service.GoodsService;
import pers.goodwin.shopSystem.service.ShoppingService;

/**
 * @author goodwin
 * 点击链接跳转页面（主页、购物车、订单、个人中心、联系客服、登录、注册、管理员页面）展示
 */
@WebServlet("/showPageServlet")
public class ShowPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public ShowPageServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		String currentPage = request.getParameter("currentPage");
		if (currentPage == null) {
			//System.out.println("404");
			response.sendRedirect(request.getContextPath() + "/404.jsp");
		} else {
			//System.out.println(currentPage);
			switch (currentPage) {
			case "init": {
				//第一次打开主页时是没有推荐商品信息的，所以在这里用ajax添加，还真是麻烦呢
				// 查询一些数据保存
				GoodsService goodsService = (GoodsService)context.getBean("goodsService");
				List<Goods> hotSale = goodsService.getHotSale(10);
				//要把商品信息转化为json字符串，传给js处理
				String hotSaleStr = "";
				for(int i = 0 ; i < hotSale.size() ; i++) {
					hotSaleStr = hotSaleStr + JSON.toJSONString(hotSale.get(i));
					if(i != hotSale.size() - 1)
						hotSaleStr += "#";
				}
				//hotSaleStr = JSON.toJSONString(hotSale);
				//System.out.println(hotSaleStr);
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				out.write(hotSaleStr);
				break;
			}
			case "index": {
				// 查询一些数据保存
				GoodsService goodsService = (GoodsService)context.getBean("goodsService");
				List<Goods> hotSale = goodsService.getHotSale(10);
				/*
				System.out.println(hotSale.size());
				for(Goods g : hotSale) {
					System.out.println(g);
				}*/
				request.setAttribute("hotSale", hotSale);
				// 跳转页面
				request.getRequestDispatcher("/index.jsp").forward(request, response);
				break;
			}
			case "cart": {
				User user = (User) request.getSession().getAttribute("user");				
				if(user != null) {
					int userId = user.getId();
					ShoppingService shoppingService = (ShoppingService)context.getBean("shoppingService");
					List<CartShow> cartList = shoppingService.showUserCart(userId);				
					request.setAttribute("cartList", cartList);
					int total = 0;//总计
					for(CartShow cart : cartList)
						total += cart.getAubtotal();
					request.setAttribute("total", total);
					request.getRequestDispatcher("/cart.jsp").forward(request, response);
				}else
					response.sendRedirect(request.getContextPath() + "/login.jsp");
				break;
			}
			case "indent": {
				User user = (User) request.getSession().getAttribute("user");				
				if(user != null) {
					int userId = user.getId();
					ShoppingService shoppingService = (ShoppingService)context.getBean("shoppingService");
					List<IndentShow> indentList = shoppingService.showUserIndent(userId);
					request.setAttribute("indentList", indentList);
					request.getRequestDispatcher("/indent.jsp").forward(request, response);
				}else
					response.sendRedirect(request.getContextPath() + "/login.jsp");		
				break;
			}
			case "classify": {
				request.getRequestDispatcher("/classify.jsp").forward(request, response);
				break;
			}
			case "center": {
				User user = (User) request.getSession().getAttribute("user");				
				if(user != null) {
					request.getRequestDispatcher("/center.jsp").forward(request, response);
				}else
					response.sendRedirect(request.getContextPath() + "/login.jsp");	
				break;
			}
			case "service": {
				request.getRequestDispatcher("/service.jsp").forward(request, response);
				break;
			}
			case "login": {
				request.getRequestDispatcher("/login.jsp").forward(request, response);
				break;
			}
			case "register": {
				request.getRequestDispatcher("/register.jsp").forward(request, response);
				break;
			}
			case "manager": {
				boolean isManager = (boolean) request.getSession().getAttribute("isManager");
				if(isManager) {
					GoodsService goodsService = (GoodsService)context.getBean("goodsService");
					List<Goods> goodsList = goodsService.getAll();
					request.setAttribute("goodsList", goodsList);
//					for(Goods g : goodsList) 
//						System.out.println(g);
					request.getRequestDispatcher("/admin/manager.jsp").forward(request, response);
				}else
					response.sendRedirect(request.getContextPath() + "showPageServlet?currentPage=index");
				break;
			}
			default: {
				//System.out.println("other");
				response.sendRedirect(request.getContextPath() + "/404.jsp");
				break;
			}
			}
		}
		((ClassPathXmlApplicationContext)context).close();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
