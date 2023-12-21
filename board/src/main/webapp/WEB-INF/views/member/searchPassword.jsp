<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>임시 패스워드 발급</title>
<link rel="stylesheet" href="/resources/css/board.css">



<script>

const pwSearchCheck = async () => {
	if(userid.value == "") {alert("아이디를 입력하세요."); userid.focus(); return false;}
	if(telno.value == '') {alert("전화번호를 입력하세요."); telno.foucs(); return false;}
	
	
	let formData = new FormData(pwSearchForm);
	
	 await fetch('/member/searchPassword', {
		  	method: 'POST',
		      body: formData
		      
		  }).then((response)=> response.json())
		  .then((data)=> {
		  	   if(data.status == 'GOOD') {
		  		   let result = "<br><br><h1>임시패스워드 : " + data.password + "</h1>";
		  		   TemporalPassword.innerHTML = result;
		  		   
		  		 }else   {
		  			 alert("해당 조건에 맞는 아이디가 존재하지 않습니다.");
		
		  		 }			 
		  }).catch((error)=> {
		  	
		  console.log(error);
		  });
	
}

const press = () => {
	if(event.keyCode == 13) {
		pwSearchCheck();
	}
}



</script>


</head>
<body>



<div class="main">

<div class="ModifyForm">
<form name="pwSearchForm" class="pwSearchForm" id="pwSearchForm" method="post">
    <h1>임시 패스워드 발급</h1>
    <div class="pwSearchFormDivision">
     <input type="text" name="userid" id="userid"  class="input_field" placeholder="아이디를 입력하세요">
    <input type="text" id="telno" name="telno" class="input_field" onkeydown="press(this.form)" placeholder="전화번호를 입력하세요">
<input type="button" id="btn_pwSearch" class="btn_write" value="임시 패스워드 발급" onclick="pwSearchCheck()">
 <input type="button" class="btn_cancel" value="홈으로" onclick="javascript:location.href='/'">
 </div>
</form>
<div class="TemporalPassword" id="TemporalPassword"></div>

</div>

</div>

</body>
</html>