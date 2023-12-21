<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<style>

        body { font-family: "나눔고딕", "맑은고딕";}
        h1 { font-family: "HY견고딕"; color: rgb(81, 144, 160);}
        a:link { color : black} 
        a:visited { color : black}
        a:hover { color: red }
        a {text-decoration: none; cursor:hand;}
        .main {
            text-align: center;
        }

        .topBanner {
            margin-top : 10px;
            margin-bottom : 10px;
            max-width: 500px;
            height: auto;
            display : block;
            margin: auto;

        }
        .login {
        
        width:50%;
        height: auto;
        padding: 20px, 20px;
        background-color:#FFFFFF;
        text-align: center;
        border: 5px solid gray;
        border-radius: 30px;
        margin:auto;
        
        }
        .userid, .password {
        
        width:80%;
        height: 25px;
        border: none;
        border-bottom: 2px solid #adadad;
        outline: none;
        color: #636e72;
        font-size: 16px;
         background: none;
         margin-top: 20px;
   
        }
        
        .bottomText {
        text-align: center;
        font-size: 20px;
        }
        
        .btn_login {
            position:relative;
            left: 40%;
            transform: translateX(-50%);
            margin-bottom: 40px;
            width: 80%;
            height: 40px;
             background: linear-gradient(125deg,#81ecec,#6c5ce7,#81ecec);
             background-position:left;
             background-size:200%
             color:white;
             font-weight:bold;
             border:none;
             cursor:pointer;
             transition: 0.4s;
             display:inline;
        }
        
</style>
<script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.2/rollups/aes.js"></script>
<script>
   window.onload = async () => {
      //쿠키 가져오기
      const getCookie = (name) => {
          
    	   const cookies = document.cookie.split(`; `).map((el)=>el.split('='));
    	   let getItem = [];
    	   
    	for(let i=0; i<cookies.length; i++){
    		if(cookies[i][0] === name) {
    			getItem.push(cookies[i][1]);
    			break;
    		}
    	}   
    	   
      
      
      if(getItem.length > 0) {
    	  return decodeURIComponent(getItem[0]);
      }
     
      }
   
   let userid = getCookie('userid');
   let password = getCookie('password');
   let authkey = getCookie('authkey');
   
   //userid 쿠키가 존재하면
   if(userid !== undefined){
	document.querySelector('#rememberUserid').checked = true;
	document.querySelector('#userid').value = userid;
   } else {
	   document.querySelector('#rememberUserid').checked = false;
   }
   
   // 패스워드 쿠키가 존재하면
   if(password !== undefined){
	document.querySelector('#rememberPassword').checked = true;
	// Base64로 인코딩 된 password 디코딩
	const decrypt = CryptoJS.enc.Base64.parse(password);
	const hashData = decrypt.toString(CryptoJS.enc.Utf8);
	password = hashData;
	
	document.querySelector('#password').value = password;
   } else {
	   document.querySelector('#rememberPassword').checked = false;
   }
   
   
   //자동 로그인 처리
   if(authkey !== undefined) {
	   let formData = new FormData();
	   formData.append("authkey",authkey);
	   
	   await fetch('/member/login?autologin=PASS', {
		 method: 'POST',
		 body: formData
	   }).then((response)=>response.json())
	   .then((data) => {
		   if(data.message === 'GOOD') {
			  document.location.href='/board/list?page=1'; 
		   } else {
			   alert('시스템 장애로 자동 로그인이 실패 했습니다.');
		   }
	   }).catch((error)=> {
		   console.log("error = " + error);
	   })
   }
   
   
   }

   const loginCheck = async () =>{
      
      const userid = document.querySelector('#userid');
      const password = document.querySelector('#password');
      const authkey = '12345';
      
      if(userid.value === ''){
         alert("아이디를 입력하세요");
         userid.focus();
         return false;
      }
      if(password.value === ''){
         alert("비밀번호를 입력하세요");
         userid.focus();
         return false;
      }
      
      //document.loginForm.action='/member/login';
      //document.loginForm.submit();
      let formData = new FormData();//id에 loginFrom을 넣고 FormData(id)를 하면 됨
      formData.append("userid",userid.value);
      formData.append("password",password.value); // form문이 필요없음 
      
      await fetch('/member/login?autologin=NEW',{
         method:"POST",
         body:formData
      }).then((response) => response.json())
      .then((data) => {
         if(data.message === 'GOOD'){
            cookieManage(userid.value,password.value,data.authkey);
            document.location.href='/board/list?page=1';   
         }
         else if(data.message === 'ID_NOT_FOUND'){
            msg.innerHTML="존재하지 않는 아이디 입니다.";
         }
         else if(data.message === 'PASSWORD_NOT_FOUND'){
            msg.innerHTML="잘못된 비밀번호 입니다."
         }
         else{
            alert("시스템 장애로 로그인이 실패하였습니다.");
         }   
      }).catch((error) => {
         console.log("error = "+error);
      })
   }
   
   //이메일 체크관리
   const checkRememberUserid = () => {
      if(document.querySelector('#rememberUserid').checked){
         document.querySelector('#rememberMe').checked = false;
      }   
   }
   //패스워드 체크관리
   const checkRememberPassword = () => {
      if(document.querySelector('#rememberPassword').checked){
         document.querySelector('#rememberMe').checked = false;
      }   
   }
   //자동로그인
   const checkRememberMe = () => {
      if(document.querySelector('#rememberMe').checked){
         document.querySelector('#rememberUserid').checked = false;
         document.querySelector('#rememberPassword').checked = false;
      }   
   }
   
   //쿠키관리
   const cookieManage = (userid,password,authkey) => {
      
      //userid가 체크되었을 경우
      if(rememberUserid.checked){
         document.cookie = 'userid='+userid+';path=/;expires=Sun, 31, Dec 2023 23:59:59 GMT';
      }
      else{
         document.cookie = 'userid='+userid+';path=/;max-age=0';
      }
      //password가 체크되었을 경우
      if(rememberPassword.checked){
         //Base64(양방향 복호화 알고리즘)로 패스워드 인코딩
         const key = CryptoJS.enc.Utf8.parse(password);
         const base64 = CryptoJS.enc.Base64.stringify(key);
         password = base64;
         document.cookie = 'password='+password+'; path=/; expries=Sun,30,Dec 2023 23:59:59 GMT';
      }
      else{
         document.cookie = 'password='+password+';path=/;max-age=0';
      }
      
      //자동저장이 체크되었을 경우
      if(rememberMe.checked){
         document.cookie = 'authkey='+authkey+'; path=/; expires=Sun, 31, Dec 2023 23:59:59 GMT';
      }
      else{
         document.cookie = 'authkey='+authkey+'; path=/; max-age=0';
      }
   }
   
   
   const press = () => {
       if(event.keyCode == 13) loginCheck(); // 13 = enter
    }
</script>

</head>
<body>

<%
String userid = (String)session.getAttribute("userid");

//권한부여
if(userid != null){
   response.sendRedirect("/board/list?page=1");
}
%>

<div class="main">

<div class="topBanner">
<img class="topBanner" src="/resources/images/logo.jpg" title="서울기술교육센터">
</div>

<div class="login">
<h1>로그인</h1>
<form name="loginForm" method="post">
   <input type="text" name="userid" class="userid" id="userid" placeholder="아이디를 입력하세요">
   <input type="password" name="password" class="password" id="password" placeholder="패스워드를 입력하세요" onkeydown="press()">
   <p id="msg" style="color:red; text-align:center"></p>
   <br>
   <input type="checkbox" id="rememberUserid" onclick="checkRememberUserid()"> 아이디기억
   <input type="checkbox" id="rememberPassword" onclick="checkRememberPassword()"> 패스워드기억
   <input type="checkbox" id="rememberMe" onclick="checkRememberMe()"> 자동 로그인
   <br><br>
   <input type="button" id="btn_login" class="btn_login" value="로그인" onclick="loginCheck()">
</form>

<div class="bottomText">사용자가 아니면 ▶ <a href="/member/signup">여기</a>를 눌러 등록을 해주세요.<br><br></div>
<div>[<a href="/member/searchID">아이디 찾기</a> | <a href="/member/searchPassword">패스워드 찾기</a>]<br><br></div>


</div>

</div>


</body>
</html>