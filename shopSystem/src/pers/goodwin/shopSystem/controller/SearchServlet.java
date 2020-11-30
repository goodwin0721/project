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

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public SearchServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String select = request.getParameter("select");
	//	System.out.println(select);
		//查询一些数据保存
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		GoodsService goodsService = (GoodsService)context.getBean("goodsService");
		List<Goods> goodslist = goodsService.searchByLClassify(select);
		((ClassPathXmlApplicationContext)context).close();
		switch (select) {
			case "all": {
				request.setAttribute("keyword", "全部商品");//用来更新“你的位置”
				break;
			}
			case "active": {
				request.setAttribute("keyword", "生活用品");//用来更新“你的位置”
				break;
			}
			case "toy": {
				request.setAttribute("keyword", "玩具");//用来更新“你的位置”
				break;
			}
			case "stationery": {
				request.setAttribute("keyword", "文具");//用来更新“你的位置”
				break;
			}
			case "snacks": {
				request.setAttribute("keyword", "零食");//用来更新“你的位置”
				break;
			}
			case "vegetables": {
				request.setAttribute("keyword", "蔬菜");//用来更新“你的位置”
				break;
			}
			case "meat": {
				request.setAttribute("keyword", "肉类");//用来更新“你的位置”
				break;
			}
			case "fruits": {
				request.setAttribute("keyword", "水果");//用来更新“你的位置”
				break;
			}
			default:
				break;
		}
		
		if(goodslist != null ) {
			request.setAttribute("goodslist", goodslist);
			//跳转页面
			request.getRequestDispatcher("/search.jsp").forward(request, response);		
		}else {
			response.sendRedirect(request.getContextPath()+"/404.jsp");
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
