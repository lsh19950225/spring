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
  	emp.jsp
  </xmp>
  
  <form action="/scott/dept" method="post">
    <table id="tbl-emp">
      <caption></caption>
      <thead>
      <tr>
        <th></th>
        <th>Dno</th>
        <th>Dname</th>
        <th>Eno</th>
        <th>Ename</th>
        <th>Job</th>
        <th>Hiredate</th>
        <th>Sal</th>
        <th>Gr</th>
      </tr>
    </thead>
      <tbody>
          <c:forEach items="${ list }" var="dto">
         <tr>
           <td><input type="checkbox" value="${ dto.empno }" name="empno"></td>
           <td>${ dto.deptno }</td> 
           <td>${ dto.dname }</td> 
           <td>${ dto.empno }</td>
           <td>${ dto.ename }</td>
           <td>${ dto.job }</td>
           <td><fmt:formatDate value="${ dto.hiredate }" pattern="yyyy-MM-dd" /></td>
           <td>${ dto.sal }</td>
           <td>${ dto.grade }</td>
         </tr>
      </c:forEach>
      </tbody>
      <tfoot>
      <tr>
        <td colspan="9">
          <button id="home" class="home">HOme</button>
        </td>
      </tr>
    </tfoot>
    </table>
  </form>
  
</div> 
</body>
</html>







 

