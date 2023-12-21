<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 패스워드 변경</title>
<link rel="stylesheet" href="/resources/css/board.css">
<script src="/resources/js/getCookie.js"></script>
</head>
<body>


<script>



const passwordUpdate = async () =>  {
	
	const Pass = new_password.value;
	const Pass1 = new_password1.value;
	
	if(old_password.value == '') {
		alert('비밀번호를 입력하세요');
		old_password.focus();
		return false
	}
	if(Pass == '') {
		alert('암호를 입력하세요');
		new_password.focus();
		return false; 
	}
	if(Pass1 == '') {
		alert('암호를 다시 한 번 입력하세요');
		new_password1.focus();
		return false; 
	}
	if(Pass != Pass1) {
		alert('암호를 제대로 입력하세요');
		new_password1.focus();
		return false; 
	}



 let num = Pass.search(/[0-9]/g); 
			let eng = Pass.search(/[a-z]/ig); // i: 알파벳 대소문자 구분없이 검색
			let spe = Pass.search(/[`~!@#$%^&*|\\\'\";:\/?]/ig);        // 특수문자가 포함되어 있는지 검색
			if(Pass.length<8 || Pass.length > 20) {
				alert("암호는 8자리 ~ 20자 이내로 입력해 주세요.");
				return false;
			} else if(Pass.search(/\s/) != -1) {
				alert("암호는 공백 없이 입력해 주세요");
				return false;
			}  else if(num<0 || eng <0 || spe <0) {
				alert("암호는 영문,숫자,특수문자를 혼합하여 입력해 주세요.");
				return false;
			}




  let formData = new FormData();
  formData.append("old_password",old_password.value);
  formData.append("new_password",new_password.value);
    
  await fetch('/member/memberPasswordModify', {
  	method: 'POST',
      body: formData
  }).then((response)=> response.json())
  .then((data)=> {
  	   if(data.message === 'GOOD') {
  		   alert("패스워드가 변경되었습니다.")
  		   logout();
  		 }else if(data.message === 'PASSWORD_NOT_FOUND') {
  			 msg.innerHTML = '잘못된 패스워드를 입력했습니다.'
  		 } else  {
  			alert("시스템 장애로 게시물 수정이 실패했습니다.");
  		 }			 
  }).catch((error)=> {
  	
  console.log("error" + error);	
  });
  

}




  const logout = () => {
	  let authkey = getCookie('authkey');
	  let userid = getCookie('userid');
	  let password = getCookie('password');
	  if(authkey != undefined) 
		  document.cookie = 'authkey' + authkey + ";path=/;max-age=0";
	  if(userid != undefined) 
		  document.cookie = 'userid' + userid + ";path=/;max-age=0";
	  if(password != undefined) 
		  document.cookie = 'password' + password + ";path=/;max-age=0";
		  
		  document.location.href='/member/memberSessionOut';
		  
		  
  }
  
  
  

  </script>





<div>
<img src="/resources/images/logo.jpg" id="topBanner">

</div>
<div class="main">
<div class="ModifyForm">
<h1>회원 패스워드 변경</h1>
<form name="modifyForm" id="modifyForm" method="POST">
<input type="password" id="old_password" name="old_password" class="input_field" placeholder="기존 패스워드를 입력하세요">
<p id="msg" style="color:red; text-align:center;"></p>
<input type="password" id="new_password" name="new_password" class="input_field" placeholder="신규 패스워드를 입력하세요">
<p style="color:red;text-align:center;"> 8~20이내의 영문자, 숫자, 특수문자 조합으로 암호를 만들어 주세요 </p>
<input type="password" id="new_password1" name="new_password1" class="input_field" placeholder="신규 패스워드를 한번 더 입력하세요">
<input type="button" class="btn_write" value="패스워드 변경" onclick="passwordUpdate()">
 <input type="button" class="btn_cancel" value="취소" onclick="history.back()">
 
            




</form>
</div>
</div>


</body>
</html>