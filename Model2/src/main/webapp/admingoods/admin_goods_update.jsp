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
	<h1>관리자 상품 수정 페이지</h1>
	
	<form action="./AdminGoodsUpdateProAction.ag" method="post" >
	   <input type="hidden" name="gno" value="${dto.gno }">
	<table id="notice">
		<tr>
		    <th colspan="2" class="ttitle">상품수정</th>
		</tr>
		<tr>
			<td class="left">카테고리</td>
			<td class="left">
			  <select name="category">
			  		<option value="outwear"
			  		   <c:if test="${dto.category.equals('outwear') }">
			  		  	 selected
			  		   </c:if>
			  		>아웃웨어</option>
			  		<option value="fulldress" 
			  		<c:if test="${dto.category.equals('fulldress') }">
			  		  	 selected
			  		   </c:if>
			  		>정장</option>
			  		<option value="Tshirts" 
			  			<c:if test="${dto.category.equals('Tshirts') }">
			  		  	 selected
			  		   </c:if>
			  		>티셔츠</option>
			  		<option value="shirts" 
			  			<c:if test="${dto.category.equals('shirts') }">
			  		  	 selected
			  		   </c:if>
			  		>셔츠</option>
			  		<option value="pants"
			  			<c:if test="${dto.category.equals('pants') }">
			  		  	 selected
			  		   </c:if>
			  		>바지</option>
			  		<option value="shoes"
			  			<c:if test="${dto.category.equals('shoes') }">
			  		  	 selected
			  		   </c:if>
			  		>신발</option>
			  </select>
			</td>
	    </tr>
		<tr>
			<td class="left">상품명</td>
			<td class="left"><input type="text" name="name" value="${dto.name }"></td>
	    </tr>
		<tr>
			<td class="left">판매가격</td>
			<td class="left"><input type="text" name="price" value="${dto.price }"></td>
	    </tr>
		<tr>
			<td class="left">색상 </td>
			<td class="left"><input type="text" name="color" value="${dto.color }"></td>
	    </tr>
		<tr>
			<td class="left">크기 </td>
			<td class="left"><input type="text" name="size" value="${dto.size }"></td>
	    </tr>
		<tr>
			<td class="left">수량 </td>
			<td class="left"><input type="text" name="amount" value="${dto.amount }"></td>
	    </tr>
		<tr>
			<td class="left">상품정보 </td>
			<td class="left"><textarea rows="10" cols="20" name="content">${dto.content }</textarea></td>
	    </tr>
	    
    	<tr>
			<td class="left">인기상품 </td>
			<td class="left">
				<input type="radio" name="best" value="1" 
				   <c:if test="${dto.best == 1 }">
				      checked
				   </c:if>
				>예
				<input type="radio" name="best" value="0" 
					<c:if test="${dto.best == 0 }">
				      checked
				    </c:if>
				>아니오
			</td>
	    </tr>
	    
	    
	    <tr>
	       <td colspan="2">
	       	  <input type="submit" value="상품수정하기" class="btn">
	       </td>
	    </tr>
	    
	</table>
	
	
	</form>
	
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



