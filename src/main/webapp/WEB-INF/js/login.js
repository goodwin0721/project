$(document).ready(function(){
    $("#usernameTx").blur(function (){
        var url = "/" + window.location.pathname.split("/")[1] +"/m/isExist";
        $.ajax({
            url:url,
            data:{"username":$("#usernameTx").val()},
            type:"POST",
            success:function (data) {
                console.log(data);
                var msg = (data + "").trim();
                console.log(msg);
                if(msg == "true"){
                    $("#isE").css("color","green");
                    $("#isE").html("√");
                }
                else{
                    $("#isE").css("color","red");
                    $("#isE").html("×");
                }
            }
        })
    });
});