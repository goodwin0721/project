<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE>
<html>
<head>
<meta charset='utf-8'>
<meta name="keywords" content="html,css,xml,xhtml,JavaScript">
<meta name="author" content="goodwin">
<!--meta http-equiv="refresh" content="300"-->
<title>上架新商品</title>
<!----
<link rel='stylesheet' type = 'text/css' href ='css/style.css'/>
!---->
<link rel='stylesheet' type = 'text/css' href ='${pageContext.request.contextPath}/css/addGoods.css'/>
<script src="${pageContext.request.contextPath}/js/jquery-3.5.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/addGoods.js?version=1.1.7"></script>
</head>

<body>
	<div id="content">
		<div id="message">	
		</div>
		<div id="addGoods">
			<form id="addGoodsForm" action="${pageContext.request.contextPath}/addGoods" method="get">
				<div>
					<span class="key">商品名：</span>
					<input id="name" class="value" type="text"><br>
					<span class="key">商品描述：</span>
					<input id="describe" class="value" type="textarea"><br>
					<span class="key">价格：</span>
					<input id="price" class="value" type="text"><br>
					<span class="key">上架数量：</span>
					<input id="sum" class="value" type="text"><br>
					<span class="key">分类：</span>
					<select id="classify" class="value">
						<option selected="selected">生活用品</option>
						<option>玩具</option>
						<option>文具</option>
						<option>零食</option>
						<option>蔬菜</option>
						<option>肉类</option>
						<option>水果</option>
					</select>			
					<span class="key">商品图片：</span>
					<label id="chooseImg" for="image_uploads">选择图片(PNG, JPG等)</label>
					<input class="inputImg" type="file" id="image_uploads" name="image_uploads" accept=".jpg,.jpeg,.png"onchange="updateImageDisplay()">  <!-- multiple-->
				</div>
				<div id="preview">
					<p>未选择图片</p>
				</div>
				<div>
					<input type="button" id="addGoodsBtn" value="上架商品"/>
				</div>
			</form>		
		</div>
	</div>
</body>
</html>
