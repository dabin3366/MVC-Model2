<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
<div id="login"><a href="member/login.html">login</a> | <a href="member/join.html">join</a></div>
<div class="clear"></div>
<!-- 로고들어가는 곳 -->
<div id="logo"><img src="images/logo.gif" width="265" height="62" alt="Fun Web"></div>
<!-- 로고들어가는 곳 -->
<nav id="top_menu">
<ul>
	<li><a href="./Main.me">HOME</a></li>
	<li><a href="./GoodsList.go">쇼핑몰(사용자)</a></li>
	<li><a href="./BoardList.bo">게시판</a></li>
	
	<c:if test="${id != null }">
		<li><a href="./BasketList.ba">장바구니</a></li>
	</c:if>
	
	<c:if test="${id != null && id.equals('admin') }">
		<li><a href="./AdminGoodsList.ag">아이티윌 쇼핑몰(관리자)</a></li>
	</c:if>	
</ul>
</nav>
</header>