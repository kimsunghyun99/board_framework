<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그아웃</title>
<link rel="stylesheet" href="/resources/css/board.css" type="text/css">
<style>
.LogoutView{
width:70%;
height:auto;
margin:auto;
padding:35px;
background-color:#FFFFFF;
text-align:left;
border:5px solid gray;
border-radius: 30px;
}

</style>
</head>
<body>



<br><br>
<div id="main">
<div id="LogoutView" class="LogoutView">

<h1>로그아웃 하였습니다. ${userid}(${username})님 안녕히 가세요</h1>
<h1>다시 로그인 하시려면 <a href="/">여기</a>를 클릭해주세요</h1>

</div>
</div>
</body>
</html>