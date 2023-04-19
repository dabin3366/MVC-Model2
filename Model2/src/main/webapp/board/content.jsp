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

<!-- 게시판 -->
<article>
<h1>Content(본문)</h1>
<table id="notice">

	<tr>
		<td>글번호</td>
		<td>${dto.bno }</td>
	    <td>조회수</td>
		<td>${dto.readcount }</td>
    </tr>
    <tr>
		<td>글쓴이</td>
		<td>${dto.name }</td>
		<td>작성일</td>
	    <td>${dto.date }</td>
    </tr>
    <tr>
		<td>제목</td>
		<td colspan="3">${dto.subject }</td>
    </tr>
    <tr>
		<td>첨부파일</td>
		<td colspan="3">${dto.file }</td>
    </tr>
     <tr>
		<td>내용</td>
		<td colspan="3">${dto.content}</td>
    </tr>
    <tr>
		<td>내용</td>
		<%-- <td colspan="3">${dto.content }</td> --%>
		<td colspan="3">
		<textarea rows="5" cols="20" >${dto.content}</textarea>
		</td>
    </tr>
    <tr>
		<td colspan="4">
			<!-- 로그인아이디(세션), 글쓴이 이름이 같을때 -->
			<c:if test="${id.equals(dto.name) }" >
				<input type="button" value="수정하기" class="btn" 
					   onclick="location.href='./BoardUpdate.bo?bno=${dto.bno }&pageNum=${pageNum}';">
					   
				<input type="button" value="삭제하기" class="btn" 
					   onclick="location.href='./BoardDeleteAction.bo?bno=${dto.bno}&pageNum=${pageNum }';">
			</c:if>
				   
			<input type="button" value="답글쓰기" class="btn" 
				   onclick="location.href='./BoardReWrite.bo?bno=${dto.bno}&pageNum=${pageNum}&re_ref=${dto.re_ref }&re_lev=${dto.re_lev }&re_seq=${dto.re_seq }';">
			
			<input type="button" value="목록으로" class="btn" 
				   onclick="location.href='./BoardList.bo?pageNum=${pageNum}';">
		</td>
    </tr>
  
</table>
	<div id="table_search">
	</div>
	<div class="clear"></div>
	<div id="page_control">
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