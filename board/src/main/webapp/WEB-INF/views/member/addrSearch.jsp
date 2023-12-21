<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주소 검색</title>
<link rel="stylesheet" href="/resources/css/board.css" type="text/css">
<script>


 const addrCheck = (zipcode,addr1,addr2,addr3) =>{
		 
		 window.opener.document.getElementById('zipcode').value = zipcode;
		 window.opener.document.getElementById('addr1').value = addr1 + addr2 + addr3;
		 window.close();
 }


</script>
</head>


<body>

<div class="main">
	<h1>주소 검색</h1>
    <table class="InfoTable">
      <tr>
      <th> 우편번호</th>
      <th> 주소</th>
      <th> 선택</th>
      </tr>
	<tbody>
	<c:forEach items="${list}" var="list">
	    <tr>
	        <td>${list.zipcode}</td>  
	        <td style="text-align:left">${list.province}${list.road}${list.building}<br>${list.oldaddr}</td> 
	        <td><input type="button" value="선택" onclick="addrCheck('${list.zipcode}','${list.province}','${list.road}','${list.building}')"></td>
	    </tr>
	    </c:forEach>
	</tbody>
    </table>
    <div>${pageList}</div>
</div>
</body>
</html>