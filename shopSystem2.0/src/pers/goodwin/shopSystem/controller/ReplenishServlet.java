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

import pers.goodwin.shopSystem.service.GoodsService;


/**
 * @author goodwin
 * 管理员补充商品库存
 */
@WebServlet("/replenish")
public class ReplenishServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String goodsId = request.getParameter("goodsId");
		String count = request.getParameter("count");
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		GoodsService goodsService = (GoodsService)context.getBean("goodsService");
		boolean isSuccess = goodsService.addStockById(goodsId, count);
		String message;
		if(isSuccess)
			message = "成功上架该商品数量：" + count;
		else
			message = "上架失败";
		((ClassPathXmlApplicationContext)context).close();
		out.write(message);
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
