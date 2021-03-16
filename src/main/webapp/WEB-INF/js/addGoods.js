var base64Img = "";// 存图片base64
function updateImageDisplay() {
	var input = document.getElementById('image_uploads');
	var preview = document.getElementById("preview");
	while (preview.firstChild) {
		preview.removeChild(preview.firstChild);
	}
	var curFiles = input.files;
	if (curFiles.length === 0) {
		const para = document.createElement('p');
		para.textContent = '未选择图片';
		preview.appendChild(para);
	} else {
		const list = document.createElement('ol');
		preview.appendChild(list);
		for (i = 0; i < curFiles.length; i++) {
			var file = curFiles[i];
			const listItem = document.createElement('li');
			const para = document.createElement('p');
			if (validFileType(file.type)) {
				para.textContent = "图片名:" + file.name + ", 图片大小:"
						+ returnFileSize(file.size) + ".";
				const image = document.createElement('img');
				image.setAttribute("id", "goodsImg");
				image.src = URL.createObjectURL(file);
				listItem.appendChild(image);
				listItem.appendChild(para);

				// 将图片文件转为base64格式，存到全局变量base64Img
				var reader = new FileReader();
				var allowImgFileSize = 2100000; // 上传图片最大值(单位字节)（ 2 M = 2097152
				// B ）超过2M上传失败
				var reader = new FileReader();
				var allowImgFileSize = 2100000; // 上传图片最大值(单位字节)（ 2 M = 2097152
				// B ）超过2M上传失败
				if (file) {
					// 将文件以Data URL形式读入页面
					imgUrlBase64 = reader.readAsDataURL(file);

					reader.onload = function(e) {
						// var ImgFileSize =
						// reader.result.substring(reader.result.indexOf(",") +
						// 1).length;//截取base64码部分（可选可不选，需要与后台沟通）
						if (allowImgFileSize != 0
								&& allowImgFileSize < reader.result.length) {
							alert('上传失败，请上传不大于2M的图片！');
						} else {
							// data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/
							base64Img = e.target.result;
							return;
						}
					}
				}
				/**/
				else {
					para.textContent = "文件名:" + file.name
							+ ": 这不是一个图片，重新选择你的图片。";
					listItem.appendChild(para);
				}
				/***/
				list.appendChild(listItem);
			}
		}
	}
}

$(document).ready(
	function() {
		$("#addGoodsBtn").click(
			function() {
				var name = $("#name").val();
				var describe = $("#describe").val();
				var price = $("#price").val();
				var sum = $("#sum").val();
				var classify = $("#classify").val();
				// var base64Img = getBase64Img();
				if (name.trim().length === 0
						|| describe.trim().length === 0
						|| price.trim().length === 0
						|| sum.trim().length === 0
						|| classify.trim().length === 0
						|| base64Img.trim().length === 0) {
					alert("请填入详细商品信息");
				} else {
					// var url = "/" + window.location.pathname.split("/")[1] + "/addGoods"/" + new Date().getTime();
					var url = "/" + window.location.pathname.split("/")[1] + "/addGoods";
					var data = {
						name : name,
						describe : describe,
						price : price,
						sum : sum,
						classify : classify,
						base64Img : base64Img
					}
					//var dataStr = JSON.stringify(data);
					//console.log(dataStr);
					$.ajax({
						url:url,
						type:"POST",
						contentType:"application/json",
						data:JSON.stringify(data),
						success:function (data) {
							$("#name").val('');
							$("#describe").val('');
							$("#price").val('');
							$("#sum").val('');
							$("#message").html(data);
							let preview = document.getElementById("preview");
							preview.removeChild(preview.firstChild);
							let para = document.createElement('p');
							para.textContent = '未选择图片';
							preview.appendChild(para);
						}
					})
				}
			});
	});

function validFileType(type) {
	const fileTypes = [ 'image/apng', 'image/bmp', 'image/gif', 'image/jpeg',
			'image/pjpeg', 'image/png', 'image/svg+xml', 'image/tiff',
			'image/webp', 'image/x-icon' ];
	// alert("validFileType");
	var isMsg = false;
	// alert(fileTypes.length);
	for (i = 0; i < fileTypes.length; i++) {
		if (fileTypes[i] == type)
			isMsg = true;
	}
	// alert(isMsg);
	return isMsg;
}

function returnFileSize(number) {
	if (number < 1024) {
		return number + 'bytes';
	} else if (number > 1024 && number < 1048576) {
		return (number / 1024).toFixed(1) + 'KB';
	} else if (number > 1048576) {
		return (number / 1048576).toFixed(1) + 'MB';
	}
}