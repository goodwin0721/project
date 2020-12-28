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
 * 管理员上架新商品
 */
@WebServlet("/addGoods")
public class AddGoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddGoodsServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		String goodsName = request.getParameter("name");
		String describe = request.getParameter("describe");
		String price = request.getParameter("price");
		String sum = request.getParameter("sum");
		String classify = request.getParameter("classify");
		String base64Img = request.getParameter("base64Img");
	/*	System.out.println(goodsName);
		System.out.println(describe);
		System.out.println(price);
		System.out.println(sum);
		System.out.println(classify);
		System.out.println(base64Img);
		*/
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		GoodsService goodsService = (GoodsService)context.getBean("goodsService");

		boolean isSuccess = false;
		isSuccess = goodsService.addGoods(goodsName, describe, price, sum, classify, base64Img);
		String message;
		if(isSuccess)
			message = "<font color='green'>操作成功</font>";
		else
			message = "<font color='red'>操作失败</font>";
		((ClassPathXmlApplicationContext)context).close();
		out.write(message);
		out.close();
	}

}
