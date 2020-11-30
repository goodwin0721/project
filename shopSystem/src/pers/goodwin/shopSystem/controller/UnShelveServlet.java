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
 * 管理员下架指定ID商品
 */
@WebServlet("/unShelve")
public class UnShelveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String goodsId = request.getParameter("goodsId");
		String stock = request.getParameter("stock");
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		GoodsService goodsService = (GoodsService)context.getBean("goodsService");
		boolean isSuccess = goodsService.updateGoodsStockById(goodsId, "-" + stock);
		String message;
		if(isSuccess)
			message = "成功下架该商品";
		else
			message = "操作失败";
		out.write(message);
		((ClassPathXmlApplicationContext)context).close();
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
