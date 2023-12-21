<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 보기</title>
<link rel="stylesheet" href="/resources/css/board.css">

<style>
.field {
         width: 90%;
         border:none;
         border-bottom: 2px solid gray;
         padding:10px;
         outline : none;
         color: #636e72;
         height: 25px;
         background: none;
     }
.input_content{
  width: 90%;
  height: 300px;
  padding: 10px;
  box-sizing: border-box;
  border: solid #adadad;
  font-size: 16px;
  resize: both;
  
}     
     


</style>
</head>
<body>

	<div class="main">
	<img src="/resources/images/logo.jpg" id="topBanner"><br>
<h1 style="text-align:center;">회원 정보 보기</h1>
<br>
<div class="boardView">
<div style="width:70%;height:auto;margin:auto;padding:20px;border:none;">
<img src="/profile/${memberInfo.stored_filename}" style="display:block;width:80%;height:auto;margin:auto">
</div>
<div class ="field"> 아이디: ${memberInfo.userid} </div>
<div class ="field"> 이름: ${memberInfo.username} </div>
<div class ="field"> 성별 : ${memberInfo.gender} </div>
<div class ="field"> 취미 :${memberInfo.hobby} </div>
<div class ="field"> 전화번호: ${memberInfo.telno}  </div>
<div class ="field"> 이메일: ${memberInfo.email}  </div>
<div class ="field"> 별명: ${memberInfo.userid} </div>
<div class ="field"> 최종 로그인 날짜: ${memberInfo.lastlogindate}</div>
<div class ="field"> 최종 패스워드 변경일: ${memberInfo.lastlogoutdate} </div>
<div class ="field"> 회원 권한: 일반유저 </div>
<c:if test="${memberInfo.role == 'MASTER'}">
<div class="itmes">회원 권한 : 관리자 </div>
</c:if>
 <div class ="input_content"><pre>내용 :${memberInfo.description} </pre> </div>
</div>
<br>
<div class="bottom_menu">
<a href = "/board/list?page=1">처음으로</a> &nbsp;&nbsp;
 <a href = "/member/signup">기본정보 변경</a> &nbsp;&nbsp;
 <a href = "/member/memberPasswordModify">패스워드 변경</a> &nbsp;&nbsp;
 <a href = "javascript:logout()">회원탈퇴</a> &nbsp;&nbsp;
  </div>   






  
</div>
</body>
</html>