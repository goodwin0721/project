package pers.goodwin.shopSystem.test;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class JsonServletTest
 */
@WebServlet("/ajaxServlet")
public class AjaxServletTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public AjaxServletTest() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet");
		request.setAttribute("msg", "修改后");
		String msg = "修改后";
		response.setContentType("text/html; charset=utf-8");
		response.getWriter().append(msg);
		
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
