/**
 * version 20150817001
 * @return {TypeName} 
 */
function checkUser() {
	var account = $("#username").val();
	if (!checkAAC002()) {
		return;
	}
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
				$("#hid_username").val("0");
				document.getElementById("username_span").innerHTML = "身份证号已存在";
			} else {
				$("#hid_username").val("1");
				document.getElementById("username_span").innerHTML = "";
			}
		}
	});
}
function checkPassword() {
	var password = $("#password").val();
	 var pattern=new RegExp(/^[0-9a-zA-Z]*$/g);
	 //alert(pattern.test(password));
	 
	if (null == $.trim(password) || "" == $.trim(password)) {
		$("#hid_password").val("0");
		document.getElementById("password_span").innerHTML = "密码不能为空";
		return;
	} else if($.trim(password).length<8||$.trim(password).length>20||!pattern.test(password)){
		$("#hid_password").val("0");
		document.getElementById("password_span").innerHTML = "密码为8-20位数字字母组合";
		return;
	}else{
		$("#hid_password").val("1");
		document.getElementById("password_span").innerHTML = "";
	}
}
function checkrePassword() {
	var password = $("#password").val();
	var repassword = $("#repassword").val();
	if (null == $.trim(repassword) || "" == $.trim(repassword)) {
		$("#hid_repassword").val("0");
		document.getElementById("repassword_span").innerHTML = "重复密码不能为空";
		return;
	} else {
		$("#hid_repassword").val("1");
		document.getElementById("repassword_span").innerHTML = "";
	}
	if (password != repassword) {
		$("#hid_repassword").val("0");
		document.getElementById("repassword_span").innerHTML = "两次密码不一致";
	} else {
		$("#hid_repassword").val("1");
		document.getElementById("repassword_span").innerHTML = "";
	}
}
function checkPhone() {
	var phone = $("#phone").val();
	var reg = new RegExp("^[0-9]*$");
	if (null == phone || "" == phone || phone.length != 11
			|| (!reg.test(phone))) {
		$("#hid_phone").val("0");
		document.getElementById("phone_span").innerHTML = "手机号码不符合格式，请重新输入";
		return;
	} else {
		$("#hid_phone").val("1");
		document.getElementById("phone_span").innerHTML = "";
	}
	$.ajax( {
		type : "post",
		url : "./user/phoneIsExist",
		data : "phone=" + phone,
		async : false,
		success : function(data) {
			if (data == "true") {
				$("#hid_phone").val("0");
				document.getElementById("phone_span").innerHTML = "电话号已存在";
			} else {
				$("#hid_phone").val("1");
				document.getElementById("phone_span").innerHTML = "";
			}
		}
	});
}
function checkName() {
	var name = $("#name").val();
	if (null == $.trim(name) || "" == $.trim(name)) {
		$("#hid_name").val("0");
		document.getElementById("name_span").innerHTML = "姓名不能为空";
		return;
	} else {
		$("#hid_name").val("1");
		document.getElementById("name_span").innerHTML = "";
	}
}

function setSubmitBtn() {
	var username = $("#hid_username").val();
	var name = $("#hid_name").val();
	var password = $("#hid_password").val();
	var repassword = $("#hid_repassword").val();
	var phone = $("#hid_phone").val();
	if (username == '1' && name == '1' && password == '1' && repassword == '1'
			&& phone == '1') {
		document.getElementById("submitBtn").disabled = false;
	} else {
		document.getElementById("submitBtn").disabled = true;
	}
}
function doSubmit() {
	checkUser();
	checkName();
	checkPassword();
	checkrePassword();
	checkPhone();
	checkCode();
	var username = $("#hid_username").val();
	var name = $("#hid_name").val();
	var password = $("#hid_password").val();
	var repassword = $("#hid_repassword").val();
	var phone = $("#hid_phone").val();
	var code = $("#hid_code").val();
	if (username == '1' && name == '1' && password == '1' && repassword == '1'
			&& phone == '1' && code == '1') {
		return true;
	} else {
		return false;
	}
}

function checkAAC002(){
  	  	   	 var aac002 = document.getElementById("username").value;
			 if(aac002.length!=18&&aac002.length!=15){
				$("#hid_username").val("0");
				document.getElementById("username_span").innerHTML = "身份证号长度不符合格式";	
                return false;
			 }else if(!checkCity(aac002)){
				return false;
		     }else{
				return true;
			 }
  	  	  }

  	  	  function checkCity(aac002){
  	  		 var pattern=new RegExp(/(^\d{17}(\d|x|X)$)/i);
  	  		var pattern15=new RegExp(/^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$/); 
  	  		 var aCity = { 11: "北京", 12: "天津", 13: "河北", 14: "山西", 15: "内蒙古", 21: "辽宁", 22: "吉林", 23: "黑龙江", 31: "上海", 32: "江苏", 33: "浙江", 34: "安徽", 35: "福建", 36: "江西", 37: "山东", 41: "河南", 42: "湖北", 43: "湖南", 44: "广东", 45: "广西", 46: "海南", 50: "重庆", 51: "四川", 52: "贵州", 53: "云南", 54: "西藏", 61: "陕西", 62: "甘肃", 63: "青海", 64: "宁夏", 65: "新疆", 71: "台湾", 81: "香港", 82: "澳门", 91: "国外" };
  	  		 if(!pattern.exec(aac002)&&!pattern15.exec(aac002)){
  	  			 $("#hid_username").val("0");
				 document.getElementById("username_span").innerHTML = "身份证号不符合格式";	
				 return false;
  	  	  	 }else if(aCity[parseInt(aac002.substring(0, 2))] == null){
  	  	  	     $("#hid_username").val("0");
				 document.getElementById("username_span").innerHTML = "身份证号不符合格式";	
  	  	  	  	 return false;
  	  	  	 }else if(!checkBirthday(aac002)){
  	  	  		 $("#hid_username").val("0");
				 document.getElementById("username_span").innerHTML = "身份证号出生日期不符合格式";	
				 return false;
  	  	  	 }else{
				 return true;
  	  	  	 }
  	  	  }

  	  	  function checkBirthday(aac002){
			 var birthday;
			 if(aac002.length==18){
				 birthday =aac002.substring(6,10)+"-"+aac002.substring(10,12)+"-"+aac002.substring(12,14);
			 }else if(aac002.length==15){
				 birthday = "19"+aac002.substring(6,8)+"-"+aac002.substring(8,10)+"-"+aac002.substring(10,12);
			 }else{
				return false;
			 }
			 var dateStr = new Date(birthday.replace(/-/g, "/")); 
			 if(birthday != (dateStr.getFullYear()+"-"+ Append_zore(dateStr.getMonth()+1)+"-"+ Append_zore(dateStr.getDate()))){
				return false;
			 }else{
				return true;
			 }
  	  	  }
  	  	function Append_zore(temp){  
  	      if(temp<10)   
  	      {  
  	          return "0"+temp;  
  	      }  
  	      else   
  	      {  
  	          return temp;  
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
				data : "code=" + code+"&type=1&phone="+phone,
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
				data : "code=" + code+"&type=1&phone="+phone,
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

	 function submitBtnOnClick(){
		 if(doSubmit()){
			 //$("#registeform").submit();
			 document.getElementById("registeform").submit();
		 }
	 }