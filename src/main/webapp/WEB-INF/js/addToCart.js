function descrease(goodsId){
//	alert("descrease");
	var pickId = "pick" + goodsId;
//	alert(pickId);
	var sum = document.getElementById(pickId).value;//预购数量
	sum = parseInt(sum);
	if(!isNaN(sum) && sum >= 1){
		sum = sum - 1;
		document.getElementById(pickId).value = sum;
		//如果选择数量等于0，隐藏“加入购物车”按钮
		if(sum == 0)
			document.getElementById("addToCart"+goodsId).style.visibility="hidden";
	}
}

function inscrease(goodsId,stock){
//	alert("inscrease");
	var pickId = "pick" + goodsId;
//	alert(pickId);
	var sum = document.getElementById(pickId).value;
//	alert("sum");
	sum = parseInt(sum);
	// alert(sum);
	// alert("stock");
	// alert(stock);
	stock = parseInt(stock);
	//alert(stock);
	if(!isNaN(sum) && sum < stock){
		sum = sum + 1;
		document.getElementById(pickId).value = sum;
		//如果选择数量0，显示“加入购物车”按钮
		if(sum != 0)
			document.getElementById("addToCart"+goodsId).style.visibility="visible";
	}
}

function addToCart(goodsId,price){
	var isLogin = $("#isLogin").val();
	if(isLogin == "true"){
		var pickId = "pick" + goodsId;
		var amount = document.getElementById(pickId).value;
		var url = "/" + window.location.pathname.split("/")[1] + "/addToCart";
		var data = {
			"goodsId" : goodsId,
			"amount" : amount
		}
		$.post(url, data, function(data, status) {
			console.log(data);
			alert(data);
			document.getElementById(pickId).value = 0;
			document.getElementById("addToCart"+goodsId).style.visibility="hidden";	
		});			
	}else{
		alert("请先登录");
		location.href="/" + window.location.pathname.split("/")[1] + "/p/login";
	}	
}


























/****此方法意于在插入不同页面时，动态修改iframe的高度，
但由于不再使用iframe的方式，所以此方法暂时不继续调试，该方法没完成，就当看不见。***/
function setFrame(){
	
//	var ifm = document.getElementById("goodsListFrame");
	var ifm = document.getElementById("goodsPage");
	alert(ifm);
	var subWeb = ifm.height;
	alert(subWeb);
//	var subWeb = document.frames?document.frames["goodsListFrame"].document:ifm.contentDocument;
	if(ifm != null && subWeb != null){
		ifm.style.height = "auto";
		alert(subWeb.body.scrollHeight);
		ifm.style.height = subWeb.body.scrollHeight + "px";
	}
	
	/*
	alert("123");
	var subWeb = document.getElementById().contentDocument;
	alert(document.getElementById("goodsDiv"));
	var heigth = document.getElementById("goodsDiv").scrollHeight;
	alert(heigth);
	document.getElementById("goodsListFrame").style.height = heigth;*/
}









