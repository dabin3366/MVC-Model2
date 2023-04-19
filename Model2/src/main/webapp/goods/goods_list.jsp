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
<article id="goodslist">
	<h1>상품 목록</h1>
	<table id="notice">
		<tr>
		    <th class="ttitle" >
		    <a href="./GoodsList.go">전체(ALL)</a>
		    </th>
		    <th class="ttitle" >
		    <a href="./GoodsList.go?item=best">인기상품</a>
		    </th>
		    <th class="ttitle" >
		    <a href="./GoodsList.go?item=outwear">아웃웨어</a>
		    </th>
		    <th class="ttitle" >
		    <a href="./GoodsList.go?item=fulldress">정장</a>
		    </th>
		    <th class="ttitle" >
		    <a href="./GoodsList.go?item=Tshirts">티셔츠</a>
		    </th>
		    <th class="ttitle" >
		    <a href="./GoodsList.go?item=shirts">셔츠</a>
		    </th>
		    <th class="ttitle" >
		    <a href="./GoodsList.go?item=pants">바지</a>
		    </th>
		    <th class="ttitle" >
		    <a href="./GoodsList.go?item=shoes">신발</a>
		    </th>
		</tr>
		<!-- 상품을 가로로 8개씩만 출력하기 -->
		
		<!-- 상품 출력에 필요한 정보 -->
		<c:set var="size" value="${goodsList.size() }"/> <!-- 상품의  개수 -->
		<c:set var="col" value="8"/> <!-- 열 -->
		<c:set var="row" value="${Math.ceil(size/col) }"/> <!-- 행의 개수  1.625 -> 2 -->
		<c:set var="num" value="0"/>
		<c:forEach begin="1" end="${row }" step="1">
			<tr>
				<c:forEach var="i" begin="1" end="${col }" step="1">
					<c:if test="${num < size}">
						<c:set var="dto" value="${goodsList[num] }"/>
							<td>
							  <img src="./upload/${dto.image.split(',')[0] }" width="110" > <br>
							<h5>
							<a href="./GoodsDetailAction.go?gno=${dto.gno }">${dto.name } </a>
							</h5> <br>
							<fmt:formatNumber value="${dto.price }"/>원 <br>
							</td>
					
							<c:set var="num" value="${num+1 }"/>
					</c:if>
				</c:forEach>
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
<div id="social"><img src="./images/facebook.gif" width="33" 
height="33" alt="Facebook">
<img src="./images/twitter.gif" width="34" height="34"
alt="Twitter"></div>
</footer>
<!-- 푸터 들어가는 곳 -->
</div>
</body>
</html>



    