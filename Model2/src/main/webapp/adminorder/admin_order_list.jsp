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
	<h1>관리자 - 모든 회원의 주문정보</h1>
	
	
	
	<table id="notice">
		<tr>
		    <th class="ttitle">주문번호</th>
		    <th class="ttitle">상품명</th>
		    <th class="ttitle">결제방법</th>
		    <th class="ttitle">주문금액</th>
		    <th class="ttitle">주문상태</th>
		    <th class="ttitle">주문일시</th>
		    <th class="ttitle">주문자</th>
		    <th class="ttitle">수정/삭제</th>
		</tr>
		
	 	<c:forEach var="dto" items="${adminOrderList }">	
			<tr>
			    <td>
			   		${dto.o_trade_num }
			    </td>
			    <td>${dto.o_g_name }</td>
			    <td>${dto.o_trade_type }</td>
			    <td>${dto.o_sum_money }</td>
			    <!-- 
			       주문상태 : 0 대기중, 1 발송준비, 2 발송완료
			       3 배송중, 4 배송완료,5 주문취소
			     -->
							     
			    <c:set var="status" value="" />
			    <c:choose>
			      <c:when test="${dto.o_status == 0 }">
			      	 <c:set var="status" value="대기중" />
			      </c:when>
			      <c:when test="${dto.o_status == 1 }">
			      	 <c:set var="status" value="발송준비" />
			      </c:when>
			      <c:when test="${dto.o_status == 2 }">
			      	 <c:set var="status" value="발송완료" />
			      </c:when>
			      <c:when test="${dto.o_status == 3 }">
			      	 <c:set var="status" value="배송중" />
			      </c:when>
			      <c:when test="${dto.o_status == 4 }">
			      	 <c:set var="status" value="배송완료" />
			      </c:when>
			      <c:when test="${dto.o_status == 5 }">
			      	 <c:set var="status" value="주문취소" />
			      </c:when>
			      <c:otherwise>
			      	 <c:set var="status" value="주문에러" />
			      </c:otherwise>			      
			    </c:choose> 
			     
			    <td>${status }</td>
			    
			    <td>${dto.o_date }</td>
			    <td>${dto.o_m_id }</td>
			    <td>
			   <a href="./AdminOrderDetail.ao?trade_num=${dto.o_trade_num }">수정</a>
			    /삭제
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



