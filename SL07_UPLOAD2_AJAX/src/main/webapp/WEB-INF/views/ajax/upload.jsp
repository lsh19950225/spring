<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="shortcut icon" type="image/x-icon" href="/resources/images/SiSt.ico">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/resources/cdn-main/example.css">
<script src="/resources/cdn-main/example.js"></script>
<style>
 span.material-symbols-outlined{
    vertical-align: text-bottom;
 }  
</style>
</head>
<body>
<header>
  <h1 class="main"><a href="#" style="position: absolute;top:30px;">KenIk HOme</a></h1>
  <ul>  
    <li><a href="#">로그인</a></li>
    <li><a href="#">회원가입</a></li>
  </ul>
</header>
<div>
  <xmp class="code"> 
  	/ajax/upload.jsp
  </xmp>
  
  <form action="" method="post" enctype="multipart/form-data">
    <div><input type="text" name="output" value="hello world!"></div> 
    
    <div>
       <input type="file" name="attach" multiple="multiple">
       <button id="btnUpload" type="button">ajax upload</button> 
       <script>
       	$(function () {
       		$("#btnUpload").on("click", function(event){
       			var formData = new FormData(); // 문법이다. : 이 객체를 생성하고 첨부파일을 담아서 보낸다.
       			var inputFile = $(":file[name=attach]");
       			
       			var files = inputFile[0].files;
       			console.log(files);
       			for (var i = 0; i < files.length; i++) {
					formData.append( "attachList",files[i] );
				} //  for
       			
       			
       			$.ajax({
       				// /ajax/uploadAjax : 이걸로 줘도 문제 없다.
       				url:'uploadAjax'
       				, processData: false // 이거랑
       	            , contentType: false // 이 2개는 무조건 줘야된다. / 첨부파일을 업로드할 경우에 필요한 속성이다.
       	            , type:'post' // 이렇게 준다고 정의함
       	            , data:formData // 첨부파일들
       	            , success:function (result) {
       	            	alert("ajax uploaded");
       	            }
       			});
       			
       			
       		});
       	});
       </script>
    </div>
    
    <div><input type="text" name="writer" value="admin"></div>
    <div><input type="text" name="email" value="admin@naver.com"></div>
    
    <div><input type="submit"></div>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">      
  </form>
  
</div> 
</body>
</html>







 

