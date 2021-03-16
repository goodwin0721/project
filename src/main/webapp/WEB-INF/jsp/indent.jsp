<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE>
<html>
<head>
<meta charset='utf-8'>
<meta name="keywords" content="html,css,xml,xhtml,JavaScript">
<meta name="author" content="goodwin">
<title>我的订单</title>
<link rel='stylesheet' type = 'text/css' href ='${pageContext.request.contextPath}/css/style.css'/>
<base href = '${pageContext.request.contextPath}/p/index'>
</head>

<body>
	<%@ include file="head.jsp" %>
	<br>
	<div id="content">
		<div id="indent">
			<table>
				<tr>
					<th class="iGoodsMsg">商品信息</th>
					<th class="iPrice">单价</th>
					<th class="iAmount">数量</th>
					<th class="iAubtotal">小计</th>
					<th class="iTime">交易时间</th>
				</tr>
				<c:forEach items="${indentList}" var="indentShow">									
				<tr> 
					<td class="iGoodsMsg">
						<ul>
							<li><img src="goodsPictures/${indentShow.pictureUrl}"></li>
							<li>商品名称:${indentShow.goodsName}</li>
						</ul>
					</td>
					<td class="iPrice">${indentShow.price}</td>
					<td class="iAmount">${indentShow.amount}</td>
					<td class="iAubtotal">${indentShow.aubtotal}</td>
					<td class="iTime">${indentShow.tradeTime}</td>
				</tr>
				</c:forEach>	
			</table>
		</div>
	</div>
	<hr>
	<%@ include file="footer.jsp" %>
</body>


</html>