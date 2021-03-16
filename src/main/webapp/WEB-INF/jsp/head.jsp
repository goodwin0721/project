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
						<a href="${pageContext.request.contextPath}/admin/manager" target="_blank" >[管理商品]</a>
						<a href="${pageContext.request.contextPath}/m/logout">[退出]</a>
						<input type="hidden" id="isLogin" value="true"/>
					</c:when>
					<c:when test="${empty user}">
						<a href="${pageContext.request.contextPath}/p/login" style="color:red;">你好，请登录</a>
						&nbsp;
						<a href="${pageContext.request.contextPath}/p/register">免费注册</a>
						<input type="hidden" id="isLogin" value="false"/>
					</c:when>
					<c:otherwise>
							<span>你好，${user.username}</span>
							<a href="${pageContext.request.contextPath}/m/logout">[退出]</a>
							<input type="hidden" id="isLogin" value="true"/>
					</c:otherwise>
				</c:choose>			
			</div>
			<a class="logo" href="<%=request.getContextPath()%>/p/index">
				<img src="${pageContext.request.contextPath}/images/iconshop.png" alt="Logo" />
			</a>
			<ul>
				<li><a href="<%=request.getContextPath()%>/p/index">首页</a></li>
				<li><a href="<%=request.getContextPath()%>/p/cart">购物车</a></li>
				<li><a href="<%=request.getContextPath()%>/p/indent">我的订单</a></li>
				<li><a href="<%=request.getContextPath()%>/p/classify">商品分类</a></li>
				<li><a href="<%=request.getContextPath()%>/p/center">个人中心</a></li>
				<li><a href="<%=request.getContextPath()%>/p/service">联系客服</a></li>
			</ul>
		</div>
	</div>