
$(document).ready(function(){
	$("#searchBtn").click(function(){
		var keyword = $("#searchMsg").val().replace(/^\s+|\s+$/g,"");//去掉前后空格
		//console.log(keyword);
		//输入框没有内容或全是空格时点击“搜索”，清空搜索框
		if(keyword.length == 0)
			$("#searchMsg").val("");
		else{
			var url = "/"+window.location.pathname.split("/")[1] + "/searchByKeyword?keyword=" + keyword + "&time="+new Date().getTime();	
			//console.log(url);	
			location.href=url;
			//$.get(url);	
		}
	});
});


//用ajax的方式更新商品展示信息，思来想去还是用跳转新页面的方式更新，即上面那个方法
/*
$(document).ready(function(){
	$("#searchBtn").click(function(){
		var keyword = $("#searchMsg").val().replace(/^\s+|\s+$/g,"");//去掉前后空格
		//console.log(keyword);
		//输入框没有内容或全是空格时点击“搜索”，清空搜索框
		if(keyword.length == 0)
			$("#searchMsg").val("");
		else{
			var url = "/"+window.location.pathname.split("/")[1] + "/ajaxSearchByKeyword?keyword=" + keyword + "&time="+new Date().getTime();
			//console.log(url);
			//发送ajax请求window.location.pathname.split("/")[1] + 
			$.get(url,function(data,status){
				// alert(data);
				var goodslist = data.split("#");
				var goods;
				var goodsDisplay = "";
				for(var i = 0;i < goodslist.length; i++){
					//goods = JSON.parse(goodslist[i]);
					goods = eval ("(" + goodslist[i] + ")");
					goodsDisplay +='<div class="goods">';
					goodsDisplay +='<form class="goodsForm" id="' + goods.id + '"/>';
					goodsDisplay +='<img class="goodsImg" src="' + goods.pictureUrl + '"/>';
					goodsDisplay +='<p class="goodsid">商品名称:' + goods.name + '</p>';
					goodsDisplay +='<p class="discribe">商品描述:' + goods.described + '</p>';
					goodsDisplay +='<p class="price">价格:<input value="' + goods.price + '"></p>';
					goodsDisplay +='<p class="stock">库存:<input value="' + goods.stock + '"></p>';
					goodsDisplay +='<input class="decrease" type="button" value="-" onclick="descrease(' + goods.id + ')">';
					goodsDisplay +='<input class="pick" type="text" id="pick' + goods.id + '" value="0" date-max="' + goods.stock + '">';
					goodsDisplay +='<input class="increase" type="button" value="+" onclick="inscrease(' + goods.id + ',' + goods.stock + ')">';
					goodsDisplay +='<input class="addToCart" id="addToCart' + goods.id + '" type="button" onclick="addToCart(' + goods.id + ')" value="加入购物车">';
					goodsDisplay +='</form>';
					goodsDisplay +='</div>';	
				}
				//console.log(goodsDisplay);
				$("#goodsDisplay").html(goodsDisplay);
				$("#goodsDisplay").show();	
				$("#searchMsg").val("");				
			});
		}
	});
});
*/