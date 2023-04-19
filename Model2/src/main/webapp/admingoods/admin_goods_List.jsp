<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
<!-- 헤더가 들어가는 곳 -->
	<jsp:include page="../inc/top.jsp"/>
<!-- 헤더가 들어가는 곳 -->

<!-- 본문 들어가는 곳 -->
<!-- 서브페이지 메인이미지 -->
<div id="sub_img"></div>
<!-- 서브페이지 메인이미지 -->
<!-- 왼쪽메뉴 -->
<nav id="sub_menu">
<ul>
<li><a href="./AdminGoodsAdd.ag"> 상 품 등 록(관리자) </a></li>
<li><a href="./AdminOrderList.ao"> 주 문 목 록 (관리자) </a></li>
<li><a href="#">Newsroom</a></li>
<li><a href="#">Public Policy</a></li>
</ul>
</nav>
<!-- 왼쪽메뉴 -->
<!-- 내용 -->
<article>
	<h1>관리자 상품 목록</h1>
	
	
	<table id="notice">
		<tr>
		    <th class="ttitle">번호</th>
		    <th class="ttitle">카테고리</th>
		    <th class="ttitle">이미지</th>
		    <th class="ttitle">상품명</th>
		    <th class="ttitle">가격</th>
		    <th class="ttitle">수량</th>
		    <th class="ttitle">등록일자</th>
		    <th class="ttitle">기타</th>
		</tr>
		
		<c:forEach var="dto" items="${aGoodsList }">
			<tr>
				<td class="left">${dto.gno }</td>
				<td class="left">${dto.category }</td>
				<td class="left">
				  <img src="./upload/${dto.image.split(',')[0] }" 
				       width="150" height="150" >
				</td>
				<td class="left">${dto.name }</td>
				<td class="left">
				    <fmt:formatNumber value="${dto.price }" />원
				</td>
				<td class="left">${dto.amount }</td>
				<td class="left">${dto.date }</td>
				<td class="left">
				 <a href="./AdminGoodsUpdate.ag?gno=${dto.gno }">수정</a>
				/
				 <a href="./AdminGoodsDeleteAction.ag?gno=${dto.gno }">삭제</a>
				</td>
		    </tr>
	    </c:forEach>
	    
	</table>
	
	
</article>
<!-- 내용 -->
<!-- 본문 들어가는 곳 -->
<div class="clear"></div>
<!-- 푸터 들어가는 곳 -->
<footer>
<hr>
<div id="copy">All contents Copyright 2011 FunWeb 2011 FunWeb 
Inc. all rights reserved<br>
Contact mail:funweb@funwebbiz.com Tel +82 64 123 4315
Fax +82 64 123 4321</div>
<div id="social"><img src="../images/facebook.gif" width="33" 
height="33" alt="Facebook">
<img src="../images/twitter.gif" width="34" height="34"
alt="Twitter"></div>
</footer>
<!-- 푸터 들어가는 곳 -->
</div>
</body>
</html>



