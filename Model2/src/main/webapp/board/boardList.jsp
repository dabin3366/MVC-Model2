<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="./css/default.css" rel="stylesheet" type="text/css">
<link href="./css/subpage.css" rel="stylesheet" type="text/css">
<!--[if lt IE 9]>
<script src="http://ie7-js.googlecode.com/svn/version/2.1(beta4)/IE9.js" type="text/javascript"></script>
<script src="http://ie7-js.googlecode.com/svn/version/2.1(beta4)/ie7-squish.js" type="text/javascript"></script>
<script src="http://html5shim.googlecode.com/svn/trunk/html5.js" type="text/javascript"></script>
<![endif]-->
<!--[if IE 6]>
 <script src="../script/DD_belatedPNG_0.0.8a.js"></script>
 <script>
   /* EXAMPLE */
   DD_belatedPNG.fix('#wrap');
   DD_belatedPNG.fix('#main_img');   

 </script>
 <![endif]-->
</head>
<body>
<div id="wrap">
<!-- 헤더들어가는 곳 -->
	<jsp:include page="../inc/top.jsp"/>
<!-- 헤더들어가는 곳 -->

<!-- 본문들어가는 곳 -->
<!-- 메인이미지 -->
<div id="sub_img_center"></div>
<!-- 메인이미지 -->

<!-- 왼쪽메뉴 -->
  
  <jsp:include page="../inc/leftMenu.jsp"/>
  
<!-- 왼쪽메뉴 -->

<%-- ${boardList } --%>
<!-- 게시판 -->
<article>
<h1>Notice</h1>
<table id="notice">
<tr><th class="tno">No.</th>
    <th class="ttitle">Title</th>
    <th class="twrite">Writer</th>
    <th class="tdate">Date</th>
    <th class="tread">Read</th></tr>
    
    <c:forEach var="dto" items="${boardList }">
		<tr>
			<td>${dto.bno }</td>
			<td class="left">
				<a href="./BoardContent.bo?bno=${dto.bno }&pageNum=${pageNum}">${dto.subject }</a>
			</td>
	    	<td>${dto.name }</td>
	    	<td>${dto.date }</td>
	    	<td>${dto.readcount }</td>
	    </tr>
    </c:forEach>
    
</table>

<div id="table_search">
	<form action="./BoardSearchAction.bo" method="get">
	<input type="text" name="search" class="input_box">
	<input type="submit" value="search" class="btn" >
	</form>
</div>
	
<div class="clear"></div>
<div id="page_control">
	<c:if test="${startPage>pageBlock }" >
		<a href="./BoardList.bo?pageNum=${startPage-pageBlock }">Prev</a>
	</c:if>
	
	<c:forEach var="i" begin="${startPage }" end="${endPage }" step="1">
		<a href="./BoardList.bo?pageNum=${i }">${i}</a>
	</c:forEach>
	<c:if test="${endPage < pageCount }">
		<a href="./BoardList.bo?pageNum=${startPage+pageBlock }">Next</a>
	</c:if>
	
</div>
</article>
<!-- 게시판 -->
<!-- 본문들어가는 곳 -->
<div class="clear"></div>
<!-- 푸터들어가는 곳 -->
<footer>
<hr>
<div id="copy">All contents Copyright 2011 FunWeb 2011 FunWeb 
Inc. all rights reserved<br>
Contact mail:funweb@funwebbiz.com Tel +82 64 123 4315
Fax +82 64 123 4321</div>
<div id="social"><img src="./images/facebook.gif" width="33" 
height="33" alt="Facebook">
<img src="./images/twitter.gif" width="34" height="34"
alt="Twitter"></div>
</footer>
<!-- 푸터들어가는 곳 -->
</div>
</body>
</html>