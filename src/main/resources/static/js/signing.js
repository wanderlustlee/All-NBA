// JavaScript Document

/*鼠标在昵称文本框中时*/
function nickNameFocus(){
	var nickName=document.getElementById("nickName");
	var nickNameId=document.getElementById("nickName_prompt");
	//email.className="register_input register_input_Focus";
	nickNameId.innerHTML="";
	 //promptId.className="register_prompt";
	}

/*鼠标离开昵称文本框时*/
function nickNameBlur(){
	var nickName=document.getElementById("nickName");
	var nickNameId=document.getElementById("nickName_prompt");
	nickNameId.innerHTML="";	
     if(nickName.value==""){
	    nickNameId.innerHTML="昵称不能为空，请输入您的昵称";
		//nickNameId.className="register_prompt_error";
		//nickName.className="register_input register_input_Blur";
	return false;
	  }
	  
	  return true;
}



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
		emailId.innerHTML="邮箱不能为空!";
		//promptId.className="register_prompt_error";
		//email.className="register_input register_input_Blur";
		return false;
		 }
	if(reg.test(email.value)==false){
	    emailId.innerHTML="邮箱格式不正确，请重新输入";
		//promptId.className="register_prompt_error";
		//email.className="register_input register_input_Blur";
	    return false;
	  }   
	   
	 // promptId.className="register_prompt_ok";
	 //email.className="register_input";
	 return true;
}

/*鼠标在密保问题文本框中时*/
function questionFocus(){
	var question=document.getElementById("question");
	var questionId=document.getElementById("question_prompt");
	//email.className="register_input register_input_Focus";
	questionId.innerHTML="";
	 //promptId.className="register_prompt";
	}

/*鼠标离开密保问题文本框时*/
function questionBlur(){
	var question=document.getElementById("question");
	var questionId=document.getElementById("question_prompt");
	questionId.innerHTML="";
     if(question.value==""){
    	 questionId.innerHTML="密保不能为空";
		return false;
	  }
     return true;
}
/*鼠标在密保答案文本框中时*/
function questionFocus(){
	var answer=document.getElementById("answer");
	var answerId=document.getElementById("answer_prompt");
	//email.className="register_input register_input_Focus";
	answerId.innerHTML="";
	 //promptId.className="register_prompt";
	}

/*鼠标离开密保答案文本框时*/
function answerBlur(){
	var answer=document.getElementById("answer");
	var answerId=document.getElementById("answer_prompt");
	answerId.innerHTML="";
     if(answer.value==""){
    	 answerId.innerHTML="密保不能为空";
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
		pwdId.innerHTML="密码不能为空，请输入密码";
		return false;
		}
	if(pwd.value.length <6 || pwd.value.length >16){
		pwdId.innerHTML="密码长度为6-16";
		return false;		
		}
	pwdId.innerHTML="";
	return true;
	}
	
//鼠标放在在此输入密码时
function repwdFocus(){
	var repwd = document.getElementById("repassword");
	var repwdId = document.getElementById("repassword_prompt");
	repwdId.innerHTML="";
	}
	
//鼠标离开再次输入密码时
function repwdBlur(){
	var pwd = document.getElementById("password");
	var repwd = document.getElementById("repassword");
	var repwdId = document.getElementById("repassword_prompt");
	if(repwd.value==""){
		repwdId.innerHTML="密码不能为空，请重新输入密码";
		return false;
		}
	if(repwd.value != pwd.value){
		repwdId.innerHTML="两次密码输入不一致，请重新输入";
		return false;		
		}
	repwdId.innerHTML="";
	return true;
	}

//提交表单时验证表单的正确性
function checkForm(){
	var flagNickName=nickNameBlur();
    var flagPwd=pwdBlur();
	var flagRepwd=repwdBlur();
    var flagEmail=emailBlur();
	
	nickNameBlur();
	pwdBlur();
	repwdBlur();
	emailBlur();
	if(flagNickName==true &&flagPwd==true &&flagRepwd==true &&flagEmail==true){
		
    	document.getElementById('form1').action="/userregister";
    	document.getElementById('form1').submit();
    } else{
    	return false;
    }
}

