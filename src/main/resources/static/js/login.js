// JavaScript Document

/*鼠标在email文本框中时*/
function emailFocus(){
	var email=document.getElementById("email");
	var emailId=document.getElementById("email_prompt");
	//email.className="register_input register_input_Focus";
	emailId.innerHTML="";
	 //promptId.className="register_prompt";
	}
	
/*鼠标离开email文本框中时*/	
function emailBlur(){
	var email=document.getElementById("email");
	var emailId=document.getElementById("email_prompt");
	emailId.innerHTML="";
	var reg=/^\w+@\w+(\.[a-zA-Z]{2,3}){1,2}$/;	
    if(email.value==""){
		emailId.innerHTML="用户名不能为空";
		return false;
		 }
	   
	 return true;
}


//鼠标在密码文本框时
function pwdFocus(){
	var pwd = document.getElementById("password");
	var pwdId = document.getElementById("password_prompt");
	pwdId.innerHTML="";
	}


//鼠标离开密码文本时
function pwdBlur(){
	var pwd = document.getElementById("password");
	var pwdId = document.getElementById("password_prompt");
	pwdId.innerHTML="";
	
	if(pwd.value==""){
		pwdId.innerHTML="密码不能为空";
		return false;
		}
	if(pwd.value.length <6 || pwd.value.length >16){
		pwdId.innerHTML="密码不正确,请重新输入";
		return false;		
		}
	pwdId.innerHTML="";
	return true;
	}


//提交表单时验证表单的正确性
function checkForm(username,userpwd){
    //alert('!!!');
    if(username.value == ""){
    	alert("用户名不能为空！");
    	return false;
    }
    if(userpwd.value == ""){
    	alert("密码不能为空！");
    	return false;
    }
    
    document.getElementById('form1').action="/login";
	document.getElementById('form1').submit();
	
}
