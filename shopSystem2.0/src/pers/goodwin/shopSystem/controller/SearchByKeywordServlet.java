package pers.goodwin.shopSystem.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pers.goodwin.shopSystem.model.Goods;
import pers.goodwin.shopSystem.service.GoodsService;

@WebServlet("/searchByKeyword")
public class SearchByKeywordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SearchByKeywordServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String keyword = request.getParameter("keyword");
		String time = request.getParameter("time");
		//String timeSession = (String) request.getSession().getAttribute("time");
		if (keyword == null || keyword.trim().equals("")/* || (timeSession != null && time.equals(timeSession))*/) {
			System.out.println("无效操作");
			//如果搜索框为无效信息或者重复提交、刷新时，啥也不干
		} else {
			ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
			GoodsService goodsService = (GoodsService) context.getBean("goodsService");
			List<Goods> goodslist = goodsService.searchByKeyword(keyword);
			request.setAttribute("keyword", keyword);//用来更新“你的位置”
			request.getSession().setAttribute("time", time);//记录请求的time属性到session里，免得重复提交
			((ClassPathXmlApplicationContext)context).close();
			if(goodslist != null && goodslist.size() != 0 ) {
				//查到商品信息	
				request.setAttribute("isSuccess", "true");
				request.setAttribute("goodslist", goodslist);
			/*	for(Goods g:goodslist)
					System.out.println(g);*/
				request.getRequestDispatcher("/search.jsp").forward(request, response);				
			}else {
				//查不到商品信息
				request.setAttribute("isSuccess", "false");
				request.getRequestDispatcher("/search.jsp").forward(request, response);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
