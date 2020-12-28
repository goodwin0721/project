function descrease(goodsId){
	var pickId = "pick" + goodsId;
	var amount = document.getElementById(pickId).value;
	amount = parseInt(amount);
	if(!isNaN(amount) && amount >= 1){
		amount = amount - 1;
		document.getElementById(pickId).value = amount;
	}
}

function inscrease(goodsId){
	var pickId = "pick" + goodsId;
	var amount = document.getElementById(pickId).value;
	amount = parseInt(amount);
	if(!isNaN(amount)){
		amount = amount + 1;
		document.getElementById(pickId).value = amount;
	}
}


function changeAmount(goodsId){
	var pickId = "pick" + goodsId;
	var amount = document.getElementById(pickId).value;
	var data = {
		"goodsId":goodsId,
		"amount":amount
	}
	var url = "/" + window.location.pathname.split("/")[1] + "/changeCartGoodsAmount";
	$.post(url,data,function(data,status){
		var message = data.split(",");
		if(message[0] == "true"){
			document.getElementById("cAubtotal"+goodsId).innerHTML = message[1];
			$("#payCount").val(message[2]);
		}
		else{
			alert("修改失败");
		}		
	});	
}










