function checkPhone(){
			var phone = $("#phone").val();
			var reg = new RegExp("^[0-9]*$");
			if(null==phone||""==phone||phone.length!=11||(!reg.test(phone))){
				$("#hid_phone").val("0");
				document.getElementById("phone_span").innerHTML = "手机号码不符合格式，请重新输入";
				return;	
			}else{
				$("#hid_phone").val("1");
				document.getElementById("phone_span").innerHTML = "";	
			}
		}
		function checkForm(){
			checkUser();
			checkPhone();
			checkCode();
			var account = $("#hid_username").val();
			var phone = $("#hid_phone").val();
			var code = $("#hid_code").val();
			if(account =="1" && phone=="1" && code == "1"){
				var v = checkAccountAndPhone();
				if(v){
					return true;
				}else{
					return false;
				}
			}else{
				return false;
			}
		}
		var countdown=60; 
	 function settime(val) { 
		if (countdown == 0) { 
			val.removeAttribute("disabled");    
			val.value="免费获取验证码"; 
			countdown = 60;
		} else { 
			val.setAttribute("disabled", true); 
			val.value="重新发送(" + countdown + ")"; 
			countdown--;
			setTimeout(function() { 
			settime(val) 
			},1000) 
		} 
	}
	 
	 function sendCode(obj){
		checkPhone();
		var hid_phone = $("#hid_phone").val();
		if(hid_phone=='1'){
			settime(obj);
		}else{
			return;
		}
		 var code = $("#code").val();
		 var phone = $("#phone").val();
		 $.ajax( {
				type : "post",
				url : "./user/sendcode",
				data : "code=" + code+"&type=3&phone="+phone,
				async : true,
				success : function(data) { 
  				  if("1"!=data){
			 		   alert(data);
			 	  }
				}
				});
		$("#hid_phone_num").val(phone);
	 }
	 
	 function codeonchange(){
		  var code = $("#code").val();
		  if($.trim(code).length==6){
			  checkCode();
		  }
	 }
	 function checkCode(){
  		  var code = $("#code").val();
  		  var phone = $("#hid_phone_num").val();
  		  if(null==$.trim(code)||""==$.trim(code)){
  			  $("#hid_code").val("0");
				document.getElementById("code_span").innerHTML = "验证码不能为空";	
				return;
  		  }else{
  			  //发送ajax请求校验验证码
  			  $.ajax( {
				type : "post",
				url : "./user/checkcode",
				data : "code=" + code+"&type=3&phone="+phone,
				async : false,
				success : function(data) { 
  				  if(data=='1'){
  					$("#hid_code").val("1");
					document.getElementById("code_span").innerHTML = "<font color='green'>验证码正确</font>";	
  				  }else{
  					$("#hid_code").val("0");
					document.getElementById("code_span").innerHTML = "验证码不正确";	
  				  }
				}
				});
  		  }
  	  }
  	  
function checkUser() {
	var account = $("#username").val();
	
	if (null == $.trim(account) || "" == $.trim(account)) {
		$("#hid_username").val("0");
		document.getElementById("username_span").innerHTML = "身份证号不能为空";
		return;
	} else {
		$("#hid_username").val("1");
		document.getElementById("username_span").innerHTML = "";
	}
	$.ajax( {
		type : "post",
		url : "./user/acountIsExist",
		data : "username=" + $("#username").val(),
		async : false,
		success : function(data) {
			if (data != "false") {
				$("#hid_username").val("1");
				document.getElementById("username_span").innerHTML = "";
				$("#phone").val(data);
				$("#show_phone").val(data.substring(0,3)+"****"+data.substring(7,11));
			} else {
				$("#hid_username").val("0");
				document.getElementById("username_span").innerHTML = "身份证号不存在";
			}
		}
	});
}

function checkAccountAndPhone(){
	var result = false;
	var account = $("#username").val();
	var phone = $("#phone").val();
	$.ajax( {
		type : "post",
		url : "./user/checkAccountAdnPhone",
		data : "account=" + account+"&phone="+phone,
		async : false,
		success : function(data) {
			if (data == "true") {
				result = true;
			} else {
				alert("根据此身份证号及手机号码系统查不到相关信息或多条记录");
			}
		}
	});
	return result;
}


function submitBtnOnClick (){
	if(checkForm()){
		$("#form1").submit();
	}
}

