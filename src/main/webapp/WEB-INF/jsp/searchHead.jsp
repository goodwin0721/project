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
				<li><a href="<%=request.getContextPath()%>/type/all/1">全部商品</a></li>
				<li><a href="<%=request.getContextPath()%>/type/active/1">生活用品</a></li>
				<li><a href="<%=request.getContextPath()%>/type/toy/1">玩具</a></li>
				<li><a href="<%=request.getContextPath()%>/type/stationery/1">文具</a></li>
				<li><a href="<%=request.getContextPath()%>/type/snacks/1">零食</a></li>
				<li><a href="<%=request.getContextPath()%>/type/vegetables/1">蔬菜</a></li>
				<li><a href="<%=request.getContextPath()%>/type/meat/1">肉类</a></li>
				<li><a href="<%=request.getContextPath()%>/type/fruits/1">水果</a></li>
			</ul>
		</div>
	</div>