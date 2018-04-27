/**
 * 
 */
function createRequest(url){
		
		http_request = false;
		if(window.XMLHttpRequest){
			http_request = new 	XMLHttpRequest();
		}else if(window.ActiveXObject){
			try{
				http_request = new ActiveXObject("Msxml2.XMLHTTP");
			}catch(e){
				try{
					http_request = new ActiveXObject("Microsoft.XMLHTTP");
				}catch(e){
					
				}
			}
		}
		if(!http_request){
			alert("不能创建XMLHttpRequest对象实例!");
			return false;
		}
		http_request.onreadystatechange = getResult;
		http_request.open("POST",url,true);
		
		http_request.send(null);
		
	}
	
	function getResult(){
		if(http_request.readyState == 4){
			if(http_request.status == 200){
				if(http_request.responseText.indexOf("用户名可用!") != -1 || http_request.responseText.indexOf("用户名不可用!") != -1){
				
				document.getElementById("toolTip1").innerHTML=http_request.responseText;
				document.getElementById("toolTip1").style.display="block";
				}
				if(http_request.responseText.indexOf("两次密码不一致!") != -1 || http_request.responseText.indexOf("两次密码一致!")!= -1){
				document.getElementById("toolTip2").innerHTML=http_request.responseText;
				document.getElementById("toolTip2").style.display="block";
				}
			}else{
				alert("您所请求的信息有错！ ");
			}
		}
	}