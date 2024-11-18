<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
  	list.jsp
  </xmp>
  
  <table>
  	<caption style="text-align: right;">
  		<a href="/board/register">글쓰기</a>
  	</caption>
  	<thead>
         <tr>
           <th>#번호</th>
           <th>제목</th>
           <th>작성자</th>
           <th>작성일</th>
           <th>수정일</th>        
         </tr>
      </thead>
      <tbody>
      	<c:choose>
      		<c:when test="${ empty list }">
      			<tr>
      				<td colspan="5">no board</td>
      			</tr>
      		</c:when>
      		<c:otherwise>
      			<c:forEach items="${ list }" var="board">
      				<tr>
      					<td><c:out value="${ board.bno }" /></td>
      					<%-- <td><a href="/board/get?bno=${ board.bno }"><c:out value="${ board.title }" /></a></td> --%>
      					<%-- <td><a href="/board/get/${ board.bno }"><c:out value="${ board.title }" /></a></td> --%>
      					<td><a class="move" href="${ board.bno }"><c:out value="${ board.title }" /></a></td>
      					<td><c:out value="${ board.writer }" /></td>
      					<td><fmt:formatDate value="${ board.regdate }" pattern="yyyy-MM-dd" /></td>
      					<td><fmt:formatDate value="${ board.updatedate }" pattern="yyyy-MM-dd" /></td>
      				</tr>
      			</c:forEach>
      		</c:otherwise>
      	</c:choose>
      </tbody>
      <tfoot>
      	<tr>
      		<td colspan="5">
      			<div class="center">
      				<div class="pagination">
      					<c:if test="${pageMaker.prev}">
      						<a href="${ pageMaker.startPage - 1 }">&laquo;</a>
      					</c:if>
      					<c:forEach begin="${ pageMaker.startPage }" end="${ pageMaker.endPage }" step="1" var="num">
      						<a href="${ num }" class='${ num eq pageMaker.criteria.pageNum ? "active" : "" }'>${ num }</a>
      					</c:forEach>
      					<c:if test="${pageMaker.next}">
      						<a href="${ pageMaker.endPage + 1 }">&raquo;</a>
      					</c:if>
      				</div><!-- pagination -->
      			</div><!-- center -->
      		</td>
      	</tr>
      </tfoot>
  </table>
  
  <form id="actionForm" action="/board/list" method="get">
  	<input type="hidden" name="pageNum" value="${pageMaker.criteria.pageNum}">
  	<input type="hidden" name="amount" value="${pageMaker.criteria.amount}">
  	<!-- 검색조건, 검색어 -->
  </form>
  
  <script>
  	// rttr.addFlashAttribute("result", board.getBno());
  	$(function (){
  		var result = '<c:out value="${result}" />';
  		checkModal(result);
  		
  		// 뒤로가기 처리 막겠다.
  		history.replaceState({},null,null);
  		
  		function checkModal(result){
  			if (result === " || history.state ") {
				return ;
			} // if
  			if ( parseInt(result) > 0 ) {
				alert( `\${result} 번이 등록되었습니다.` );
				return ;
			} // if
			if (result === 'SUCCESS') {
				alert("삭제 완료");
				return ;
			} // if
  		} // checkModal
  		
  		// 1) 페이징 블럭에서 번호를 클릭할 때 이동한다.
  		$(".pagination a").on("click",function(event){
  			event.preventDefault();
  			let pageNum = $(this).attr("href");
  			
  			actionForm
					.find(":hidden[name=pageNum]")
						.val(pageNum)
						.end() // 끝내고 부모로 다시 돌아간다.
					.submit();
  		});
  		
  		// 2) 제목을 클릭해서 상세보기 페이지로 이동할 때 + pageNum, amount
  		//		상세보기에 있는 목록으로 이동하는 버튼 클릭할 때 그 해당 페이지로 이동
  		var actionForm = $("#actionForm");
  		
  		$("a.move").on("click",function(event){
  			event.preventDefault(); // 기본 링크 막기
  			let bno = $(this).attr("href");
  			actionForm
  					.attr("action","/board/get")
  					.append(`<input type="hidden" name="bno" value="\${bno}">`)
  					.submit();
  		}); // click
  	}); // document.ready
  </script>
  
</div> 
</body>
</html>







 

