function replenish(goodsId){
	var count = document.getElementById("addCount"+goodsId).value;
	count = parseInt(count);
	stock = parseInt(document.getElementById("stock"+goodsId).value);
	if(count > 0){
		//var url = "/"+window.location.pathname.split("/")[1] + "/replenish?goodsId=" + goodsId + "&count=" + count;
		var url = "/"+window.location.pathname.split("/")[1] + "/replenish";
		stock = count + stock;
		var data = {
			goodsId:goodsId,
			count:count
		};
		$.ajax({
			url : url,
			type:"POST",
			contentType:"application/json",
			data:JSON.stringify(data),
			success:function (data) {
				document.getElementById("stock"+goodsId).value = stock;
				alert(data);
			}
		})
		/*
		$.get(url,function(data,status){
			document.getElementById("stock"+goodsId).value = stock;
			alert(data);
		});	*/
	}
	document.getElementById("addCount"+goodsId).value = 0;
}

function unShelve(goodsId){
	//var url = "/"+window.location.pathname.split("/")[1] + "/unShelve?goodsId=" + goodsId + "&stock=" + stock;
	var url = "/"+window.location.pathname.split("/")[1] + "/unShelve";
	let stock = parseInt(document.getElementById("stock"+goodsId).value);
	var data = {
		goodsId:goodsId,
		stock:stock
	};
	$.ajax({
		url : url,
		type:"POST",
		contentType:"application/json",
		data:JSON.stringify(data),
		success:function (data) {
			document.getElementById("stock"+goodsId).value = 0;
			alert(data);
		}
	})
	document.getElementById("addCount"+goodsId).value = 0;
}