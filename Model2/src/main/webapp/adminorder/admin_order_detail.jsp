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
	<h1>주문 상세정보 - 수정</h1>
	
	<!-- 주문의 공통정보를 사용 -->
	<c:set var="oDTO" value="${aoDetailList[0] }"/>
	
	
	<h2>주문번호(DB) : ${aoDetailList[0].o_trade_num }</h2>
	<h2>주문번호(param) : ${param.trade_num }</h2>
	<table id="notice">
		<tr>
		    <th class="ttitle">상품명</th>
		    <th class="ttitle">크기</th>
		    <th class="ttitle">색상</th>
		    <th class="ttitle">수량</th>
		    <th class="ttitle">가격</th>
		</tr>
		
		
		<%-- <c:if test="${ basketList.size() != 0 }">  --%>
		<c:if test="${ !empty aoDetailList }">
		<!-- el표현식 연산자 empty : 값이 null,size가 0일때 true -->
		
		<%-- <c:forEach var="dto" items="${basketList }"> --%>
		<c:forEach var="dto" items="${aoDetailList }">		
			<tr>
				<td class="left">${dto.o_g_name }</td>
				<td class="left">${dto.o_g_size }</td>
				<td class="left">${dto.o_g_color }</td>
				<td class="left">${dto.o_g_amount }</td>
				<td class="left">${dto.o_sum_money }</td>
		    </tr>
	    </c:forEach>
	    
	    </c:if>
	    
	    <tr>
	    	<td colspan="6"> <h2>주문자 정보</h2> </td>
	    </tr>
	    <tr>
	    	<td colspan="6">
	    	   이름 :  ${oDTO.o_m_id }<br>
	    	   이메일 :  <br>
	    	   ..... 
 			</td>
	    </tr>
	    <tr>
	    	<td colspan="6"> <h2>배송지 정보</h2> </td>
	    </tr>
	    <tr>
	    	<td colspan="6"> 
	    	 받는사람 : <input type="text" name="o_r_name" value="${oDTO.o_r_name }"> <br>
	    	 연락처 : <input type="text" name="o_r_phone" value="${oDTO.o_r_phone }"> <br>
	    	 배송지주소 : <input type="text" name="o_r_addr1"> <br>
	    	 상세주소 : <input type="text" name="o_r_addr2"> <br>
	    	 기타 요구사항 : <input type="text" name="o_r_msg"> <br>	    	
	    	</td>
	    </tr>
	    <tr>
	    	<td colspan="6"> <h2>결제 정보</h2> </td>
	    </tr>
	    <tr>
	    	<td colspan="6"> 
	    		<input type="radio" name="o_trade_type"  value="신용카드">신용카드
	    		<input type="radio" name="o_trade_type" value="체크카드">체크카드
	    		<input type="radio" name="o_trade_type" value="카카오페이">카카오페이
	    		<input type="radio" name="o_trade_type" value="네이버페이">네이버페이
	    		<input type="radio" name="o_trade_type" value="무통장입금">무통장입금<br>
	    		입금자명(무통장) : <input type="text" name="o_trade_payer" value="${mdto.name }">   	
	    	</td>
	    </tr>
	</table>
	
	<form action="./AdminOrderUpdate.ao" method="post">
		<input type="hidden" name="trade_num" value="${oDTO.o_trade_num }">
			<hr>
	   		<select name="status">
	   			<option>주문상태 변경</option>
	   			<option value="0"
	   			<c:if test="${oDTO.o_status == 0 }"> selected</c:if>
	   			>대기중</option>
	   			<option value="1"
	   			<c:if test="${oDTO.o_status == 1 }"> selected</c:if>
	   			>발송준비</option>
	   			<option value="2"
	   			<c:if test="${oDTO.o_status == 2 }"> selected</c:if>
	   			>발송완료</option>
	   			<option value="3"
	   			<c:if test="${oDTO.o_status == 3 }"> selected</c:if>
	   			>배송중</option>
	   			<option value="4"
	   			<c:if test="${oDTO.o_status == 4 }"> selected</c:if>
	   			>배송완료</option>
	   			<option value="5"
	   			<c:if test="${oDTO.o_status == 5 }"> selected</c:if>
	   			>주문취소</option>
	   		</select>
	   		 	
	   		<hr>
	   		
	   		운송장번호 : <input type="text" name="trans_num" value="${oDTO.o_trans_num }"> 
	   		<hr>
	   		<input type="submit" value="주문상태 수정">
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



