<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel='stylesheet' type = 'text/css' href ='${pageContext.request.contextPath}/css/style.css'/>
<div id="header">
		<div>
			<div id="userMsg">
				<c:choose>
					<c:when test="${not empty user && isManager eq 'true'}">
						<span>你好，管理员-${user.username}</span>
						<a href="${pageContext.request.contextPath}/admin/manager.jsp" target="_blank" >[管理商品]</a>
						<a href="${pageContext.request.contextPath}/logout">[退出]</a>
						<input type="text" id="isLogin" style="visibility:hidden;" value="true"/>
					</c:when>			
					<c:when test="${empty user}">
						<a href="${pageContext.request.contextPath}/login.jsp" style="color:red;">你好，请登录</a>
						&nbsp;
						<a href="${pageContext.request.contextPath}/register.jsp">免费注册</a>
						<input type="text" id="isLogin" style="visibility:hidden;"  value="false"/>
					</c:when>					
					<c:otherwise>
						<span>你好，${user.username}</span>
						<a href="${pageContext.request.contextPath}/logout">[退出]</a>
						<input type="text" id="isLogin" style="visibility:hidden;" value="true"/>
					</c:otherwise>
				</c:choose>			
			</div>
			<a class="logo" href="<%=request.getContextPath()%>/showPageServlet?currentPage=index"">
				<img src="${pageContext.request.contextPath}/images/iconshop.png" alt="Logo" />
			</a>		
			<ul>
				<li><a href="<%=request.getContextPath()%>/search?select=all">全部商品</a></li>
				<li><a href="<%=request.getContextPath()%>/search?select=active">生活用品</a></li>
				<li><a href="<%=request.getContextPath()%>/search?select=toy">玩具</a></li>
				<li><a href="<%=request.getContextPath()%>/search?select=stationery">文具</a></li>
				<li><a href="<%=request.getContextPath()%>/search?select=snacks">零食</a></li>
				<li><a href="<%=request.getContextPath()%>/search?select=vegetables">蔬菜</a></li>
				<li><a href="<%=request.getContextPath()%>/search?select=meat">肉类</a></li>
				<li><a href="<%=request.getContextPath()%>/search?select=fruits">水果</a></li>		
			</ul>
		</div>
	</div>