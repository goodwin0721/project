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

import pers.goodwin.shopSystem.model.Goods;
import pers.goodwin.shopSystem.service.GoodsService;

@WebServlet("/ajaxSearchByKeyword")
public class AjaxSearchByKeywordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public AjaxSearchByKeywordServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String keyword = request.getParameter("keyword");
		//System.out.println(keyword);
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		if(keyword.trim().equals("")) {
			out.write("");
		}else {
			// 查询一些数据保存
			ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
			GoodsService goodsService = (GoodsService)context.getBean("goodsService");
			List<Goods> goodslist = goodsService.searchByKeyword(keyword);
			//要把商品信息转化为json字符串，传给js处理
			String goodslistStr = "";
			for(int i = 0 ; i < goodslist.size() ; i++) {
				goodslistStr = goodslistStr + JSON.toJSONString(goodslist.get(i));
				if(i != goodslist.size() - 1)
					goodslistStr += "#";
			}
			//System.out.println(goodslistStr);
			((ClassPathXmlApplicationContext)context).close();
			out.write(goodslistStr);
		}
		out.close();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
