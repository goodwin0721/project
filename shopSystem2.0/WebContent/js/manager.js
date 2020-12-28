function replenish(goodsId){
	var count = document.getElementById("addCount"+goodsId).value;
	count = parseInt(count);
	stock = parseInt(document.getElementById("stock"+goodsId).value);
	if(count > 0){
		var url = "/"+window.location.pathname.split("/")[1] + "/replenish?goodsId=" + goodsId + "&count=" + count;	
		stock = count + stock;
		$.get(url,function(data,status){
			document.getElementById("stock"+goodsId).value = stock;
			alert(data);
		});	
	}
	document.getElementById("addCount"+goodsId).value = 0;
}

function unShelve(goodsId,stock){
	var url = "/"+window.location.pathname.split("/")[1] + "/unShelve?goodsId=" + goodsId + "&stock=" + stock;	
	//location.href=url;
	$.get(url,function(data,status){
		document.getElementById("stock"+goodsId).value = 0;
		alert(data);
	});		
	document.getElementById("addCount"+goodsId).value = 0;
}