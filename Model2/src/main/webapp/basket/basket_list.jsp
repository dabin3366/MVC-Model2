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

<!-- 왼쪽메뉴 -->
<!-- 내용 -->
<article>
	<h1>${sessionScope.id }님의 장바구니 정보</h1>
	<table id="notice">
		<tr>
		    <th class="ttitle" >번호</th>
		    <th class="ttitle" >이미지</th>
		    <th class="ttitle" >상품명</th>
		    <th class="ttitle" >가격</th>
		    <th class="ttitle" >수량</th>
		    <th class="ttitle" >크기</th>
		    <th class="ttitle" >색상</th>
		    <th class="ttitle" >취소</th>
		</tr>
		
		<%-- <c:forEach var="dto" items="${basketList }"> --%>
		<%-- <c:if test="${basketList.size() != 0 }"> --%>
		<!-- el표현식 연산자 empty : 값이 null,size가 0일때 true -->
		<c:if test="${ !empty basketList }">
			<c:forEach var="i" begin="0" end="${basketList.size()-1 }" step="1">
				<c:set var="dto" value="${basketList[i] }"/>
				<c:set var="goods" value="${goodsList[i] }" />
				<tr>
					<td class="left">${dto.b_num }</td>
					<td class="left">
						<img src="./upload/${goods.image.split(',')[0] }" width="50">
					</td>
					<td class="left">${goods.name }</td>
					<td class="left">${goods.price }</td>
					<td class="left">${dto.b_g_amount}</td>
					<td class="left">${dto.b_g_size}</td>
					<td class="left">${dto.b_g_color}</td>
					<td class="left">
						<a href="./BasketDeleteAction.ba?b_num=${dto.b_num }">취소</a>
					</td>
				</tr>
			</c:forEach>
		</c:if>
	</table>
	
	<h3><a href="./OrderStart.or">[구매하기]</a></h3>
	<h3><a href="./GoodsList.go">[계속쇼핑하기]</a></h3>
	
	
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
<div id="social"><img src="./images/facebook.gif" width="33" 
height="33" alt="Facebook">
<img src="./images/twitter.gif" width="34" height="34"
alt="Twitter"></div>
</footer>
<!-- 푸터 들어가는 곳 -->
</div>
</body>
</html>



    