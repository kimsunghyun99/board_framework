<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
<link rel="stylesheet" href="/resources/css/board.css" type="text/css">
<style>



</style>



<script>
 
 const IDSearchCheck = async () => {
	if(username.value == '') {
			alert("이름을 입력하세요."); 
			username.focus(); 
			return false;
		}
	if(telno.value == '') {
			alert("전화번호를 입력하세요."); 
			telno.focus();
			return false;
		}
	
	
	let formData = new FormData(IDsearchForm);
	
	 await fetch('/member/searchID', {
		  	method: 'POST',
		      body: formData    
		  }).then((response)=> response.json())
		  .then((data)=> {
		  	   if(data.message !== 'ID_NOT_FOUND') {
		  		   let result = "<br><br><h1>아이디 : " + data.userid + "</h1>";
		  		   IDSearchResult.innerHTML = result;
		  		 }else  {
		  			 alert("해당 조건에 맞는 아이디가 존재하지 않습니다.");
		  		 }			 
		  }).catch((error)=> {
		  console.log(error);	
		  })
	
}


const press = () => {
	if(event.keyCode == 13) {
		IDSearchCheck();
	}
}




</script>



</head>


<body>






<div class="main">

<div>
<img src="/resources/images/logo.jpg" id="topBanner">
</div>


<div class="ModifyForm">
<form name="IDsearchForm" class="IDsearchForm" id="IDsearchForm" method="post">
<h1>아이디 찾기</h1>
<input type="text" id="username" name="username" class="input_field" placeholder="이름을 입력하세요.">
<input type="text" id="telno" name="telno" class="input_field" onkeydown="press(this.form)" placeholder="전화번호를 입력하세요">
<input type="button" class="btn_write" value="아이디 찾기" onclick="IDSearchCheck()">
 <input type="button" class="btn_cancel" value="홈으로" onclick="javascript:location.href='/'">
 </form>
 </div>
 <div class="IDSearchResult" id="IDSearchResult"></div>
 
 


</div>





</body>
</html>