<%@ page language = "java" contentType = "text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset = "UTF-8">
    <title>게시물 등록</title>
    <link rel="stylesheet" href="/resources/css/board.css" type="text/css">
 

<style>
 .fileuploadForm {
        margin: 5px;
        padding: 5px 5px 2px 30px;
        text-align: left;
        width: 90%;
        
        }
        
        .fileZone {
        border: solid #adadad;
        background-color: #a0a0a0;
        width: 97%;
        height:80px;
        color: white;
        text-align: center;
        vertical-align: middle;
        padding: 5px;
        font-size: 120%;
        
        }
        
        .fileUploadList {
        border: solid #adadad;
        width: 97%;
        height: auto;
        padding: 5px;
        font-size: 120%;
        
        
        }
        
        
        .filename {
        
        display:inline-block;
        vertical-align:top;
        width: 50%
        
        }
        
        .filesize {
        display:inline-block;
        vertical-align:top;
        color: #30693D;
        margin-left:10px;
        margin-right:5px;
       
        }
        
        .btn_del {
        display:inline-block;
        width: 15%;
        cursor: pointer;
        vertical-align: top;
        }
        
        .statusbar {
        border-bottom: solid 1px #92AAB0;
        min-height: 25px;
        width: 96%;
        padding: 10px 10px 10px 10px;
        vertical-align: top;
        }
        
        .statusbar:nth-child(odd) {
        	background: #EBEFF0;
        }
</style>

 <script>
 
 window.onload = () => {
 	const fileZone = document.querySelector('#fileZone');
 	const inputFile = document.querySelector('#inputFile');
 	
 	// fileZone 을 클릭하면 발생하는 이벤트 처리
 	fileZone.addEventListener('click', (e) => {
 		
 		inputFile.click(e)
 	});
 	
 //파일탐색창을 열어 선택한 파일 정보를 files에 할당
 inputFile.addEventListener('change', (e) => {
 	const files = e.target.files;
 	fileCheck(files);
 });
 
 /* 마우스 이벤트 종류
 dragstart: 사용자가 객체를 드래그 할려고 시작할때 발생함.
 drag : 대상 객체를 드래그 하면서 마우스를 움직일때 발생함.
 dragenter(*) : 마우스가 대상 객체의 위로 진입할때 발생함
  dragover(*) : 드래그 하면서 마우스가 대상 객체의 영역 위에 자리 잡고 있을때 발생
 drop(*) : 드래그가 끝나서 드래그 하던 객체를 장소에 놓았을 때 발생
 dragleave : 드래그가 끝나서 마우스가 대상 객체의 위에서 벗어날때 발생함
 dragend:  대상객체를 드래그하다가 마우스 버튼을 놓는 순간 발생함
 */
 	
 
 //fileZone으로 dragenter 이벤트 발생 시 처리하는 이벤트 핸들러
 fileZone.addEventListener('dragenter',(e)=> {
 	e.stopPropagation(); // 상위 엘레멘트들로의 이벤트 전파를 중단
 	e.preventDefault(); // 웹브라우저의 고유 동작을 중단
 	fileZone.style.border = 'solid 2px #0B85A1';
 })
 
 //filzeZone으로 dragover 이벤트 발생시 처리하는 이벤트 핸들러
 fileZone.addEventListener('dragover',(e)=> {
 	e.stopPropagation(); // 상위 엘레멘트들로의 이벤트 전파를 중단
 	e.preventDefault(); // 웹브라우저의 고유 동작을 중단
 	
 })
 //filzeZone으로 drop 이벤트 발생시 처리하는 이벤트 핸들러
 
 fileZone.addEventListener('drop',(e)=> {
 	e.stopPropagation(); // 상위 엘레멘트들로의 이벤트 전파를 중단
 	e.preventDefault(); // 웹브라우저의 고유 동작을 중단
 	const files = e.dataTransfer.files;
 	fileCheck(files);
 	
 })
 }
 var uploadCountLimit = 5; //업로드 가능한 파일 개수
  var fileCount = 0; // 한 번 선택해서 가져오는 파일의 개수 
  var fileNum = 0; // 파일 고유 넘버
    var content_files = new Array();
  var rowCount = 0;
  
  const fileCheck =(files) => {
 	 let filesArr = Object.values(files); // 객체에서 value에 해당되는 값을 뽑아서 배열로 반환 
 	 
 	 if(fileCount + filesArr.length > uploadCountLimit ) {
 		 alert('파일은 최대 ' + uploadCountLimit + '개까지 업로드 할 수 있습니다.');
 		 return;
 	 }else {
 		 fileCount += filesArr.length; 
 	 }
 	 filesArr.forEach((file)=> {
 		 
 		 //FileReader 객체 : 웹애플리케이션 비동기적으로 웹브라우저에서 파일을 읽을때 사용하며,
 		 // <input type= "file">태그를 이용하여 사용자가 선택한 파일들로부터 반환된 FileList 객체나
 		 // Drag & Drop 이벤트로 반환된 DataTransfer 객체로부터 데이터를 얻음
 		 var reader = new FileReader();
 		 reader.readAsDataURL(file); // 파일 읽기
 		 
 		 // reader.readAsDataURL(file) 실행으로 파일 읽기가 성공적으로 수행되고 난 후
 		 // read.onload 이벤트 핸들러를 통해 read.onload 이벤트 핸들러 내의 콜백 함수가 비동기적으로 실행됨
 	     reader.onload = (e) => {
 		 	content_files.push(file);
 		 	if(file.size > 1073741824) {
 		 		alert('파일 사이즈는 1GB를 초과할 수 없습니다.');
 		 		return;
 		 		}
 		 	
 		 	rowCount++;
 		 	var row = "odd";
 		 	if(rowCount %2 ==0) row="even";
 		 	
 		 	
 		 	// 동적으로 div HTML 요소 생성
 		 	let statusbar = document.createElement('div'); 
 		 	statusbar.setAttribute('class','statusbar ' + row) // <div class='statusbar odd'><div>
 		 	statusbar.setAttribute('id','file' + fileNum); // <div class='statusbar odd' id='file0'><div>
 		 	
 		 	// statusbar element내의 하위 element인 <div class='filename'>파일이름</div>를 생성
 		 	let filename = "<div class='filename'>" + file.name + "</div>";
 		 	
 		   // statusbar element내의 하위 element인 <div class='filesize'>파일사이즈</div>를 생성
 		 	let sizeStr = "";
 		    let sizeKB = file.size/1024;
 		    if(parseInt(sizeKB) > 1024) {
 		    	var sizeMB = sizeKB/1024;
 		       sizeStr = sizeMB.toFixed(2) + "MB";
 		    } else {
 		    	sizeStr = sizeKB.toFixed(2) + "KB";
 		    }
 		    let size = "<div class='filesize'>" + sizeStr + "</div>";
 		    
 		    // statusbar element 내의 하위 element인 삭제 버튼 생성
 		    let btn_delete = "<div class='btn_del'><input type='button' value='삭제' onclick=fileDelete('file" + fileNum + "')></div>";
 		 	
 		    statusbar.innerHTML = filename + size + btn_delete;
 		    
 		    fileUploadList.appendChild(statusbar);
 		    
 		    fileNum++;
 		    
 		 	};
 	 });
 	 
 	 inputFile.value = '';
 	 
 	 
  }
  
  
  
  
  
  

  const fileDelete = (fileNum) => {
 	 var no = fileNum.replace(/[^0-9]/g,"")   // file0에서 숫자 0의 앞 부분을 지우고 남은 숫자 0을 반환
 	 content_files[no].is_delete = true;
 	 document.querySelector('#' + fileNum).remove();
 	 fileCount --;
 	 
  }
 
 
 
 
     const modifyForm = async () => {
     
         let title = document.querySelector('#title');
         let content = document.querySelector('#content');


         // 필수값 등록시 값 입력 여부 확인
      
         if(title.value === '') {
             alert('제목을 입력하세요');
             title.focus();
             return false;
         }
         if(content.value === '') {
             alert('내용을 입력하세요!!!');
             content.focus();
             return false;

         }
         
       //  document.ModifyForm.action = '/board/modify';
       //  document.ModifyForm.submit();

       
     let formData = new FormData(ModifyForm);
  let uploadURL = "";
    
  if(content_files.length !=0) {
  	// 첨부파일이 있는 경우
  	uploadURL = "/board/fileUpload?kind=U";	
  	for(let i = 0; i< content_files.length; i++) {
  		if(!content_files[i].is_delete) {
  			formData.append("sendToFileList", content_files[i]);
  		}
  	}
  } else {
  	uploadURL = "/board/modify";
  }
  
  
  await fetch(uploadURL, {
  	method: 'POST',
      body: formData
  }).then((response)=> response.json())
  .then((data)=> {
  	   if(data.message === 'GOOD') {
  		   alert("게시물이 수정되었습니다.");
  		   document.location.href='/board/view?seqno=' + seqno.value + '&page= ' + page.value + '&keyword=' + keyword.value;
  	   }
  }).catch((error)=> {
  	alert("시스템 장애로 게시물 수정이 실패했습니다.");
  console.log("error" + error);	
  })

     }
 </script>

</head>




   


<body>

 <div class = "main">
    <img src="/resources/images/logo.jpg" id="topBanner"><br>
    <h1>게시물 수정</h1>
    <br>
    <div id = "formZone"> 
        <form class = "ModifyForm" name = "ModifyForm" method="post">

            <input type = "text" class = "input_field" name = "writer" value = "${view.writer}" disabled>
            <input type = "text" id ="title" class = "input_field" name = "title" value = "${view.title}">
            <input type = "hidden" id="seqno" name = "seqno" value = "${view.seqno}">
            <input type = "hidden" id="page" name = "page" value = "${page}">
            <input type = "hidden" id="keyword" name = "keyword" value = "${keyword}">
            <input type = "hidden" name = "writer" value = "${username}">
              <input type = "hidden" name = "userid" value = "${userid}">
            <br><br>
            <textarea id = "content"  cols="100" rows = "500" class="input_content" name="content">${view.content}</textarea>
            <div style="width:80%;margin:auto">
        <c:forEach items="${fileInfoView}" var="file"> 
        
             <p style="text-align:left">
               
                삭제 : <input type="checkbox" name="deleteFileList" value="${file.fileseqno}">
                ${file.org_filename}&nbsp;(${file.filesize})
            
             </p>
        
        </c:forEach>
            </div>
            
             <div class="fileuploadForm">
            <input type="file" id="inputFile" name="uploadFile" style="display:none" multiple>
            <div class="fileZone" id="fileZone">파일첨부를 하기 위해서는 클릭하거나 여기로 파일을 드래그 하세요.<br>
                   첨부 파일은 최대 5개까지 등록이 가능합니다 .
            </div>
              <div class="fileUploadList" id="fileUploadList">
              
              </div>
            </div>
            
            <input type = "button" class = "btn_write" value="수정" onclick = "modifyForm()">
            <input type = "button" class = "btn_cancel" value="취소" onclick = "history.back()">

        </form>

    </div>
    <br><br>
 </div>   

</body>

<%

String userid = (String)session.getAttribute("userid");
String username = (String)session.getAttribute("username");
String role = (String)session.getAttribute("role");
if(userid ==null) response.sendRedirect("/");

%>


</html>