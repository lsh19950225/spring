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
  	modify.jsp
  </xmp>
  
  <form action="/board/modify" method="post">
     <table>  
       <tbody>
         <tr>
           <th>글번호</th>
           <td><input type="text" name="bno" class="full"  readonly="readonly"  value="${ boardVO.bno }"></td>        
         </tr> 
         <tr>
           <th>제목</th>
           <td><input type="text" name="title" class="full"  value="${ boardVO.title }"></td>        
         </tr> 
         <tr>
           <th>내용</th>
           <td><textarea  name="content" class="full" ><c:out value="${ boardVO.content }"></c:out></textarea></td>        
         </tr> 
         <tr>
           <th>작성자</th>
           <td><input type="text" name="writer" class="short" readonly="readonly" value="${ boardVO.writer }"></td>        
         </tr>  
       </tbody> 
       <tfoot>
         <tr>
           <td colspan="2">
             <button type="button"  data-oper="modify" class="edit">Modify</button>
             <button type="button" data-oper="list"  class="list">List</button>
           </td>
         </tr>
       </tfoot>
     </table>
     
     <input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }">
     
     <input type="hidden" name="pageNum" value="${ criteria.pageNum }">
     <input type="hidden" name="amount" value="${ criteria.amount }">
     
  </form>    
  
  <script>
  	$(function(){
  		var formObj = $("form");
  		$("tfoot button").on("click", function(){
  			let operation = $(this).data("oper");
  			if( operation === 'modify' ) { // 수정한 게시글 내용 처리
  				formObj.submit(); // post
  			} else if ( operation === 'list' ) {
  				// location.href = "/board/list"
  				let pageNumTag = $(":hidden[name='pageNum']").clone();
  				let amountTag = $(":hidden[name='amount']").clone();
  				
  				formObj
  					.attr({
  						"action":"/board/list",
  						"method":"get"
  					})
  					.empty()
  					.append(pageNumTag)
  					.append(amountTag)
  					.submit();
  			} // if
  		}); // click
  	}); // document.ready
  </script>
  
</div> 
</body>
</html>







 

