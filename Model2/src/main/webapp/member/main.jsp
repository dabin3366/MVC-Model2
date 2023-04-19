<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1> main.jsp </h1>
	
	
	
	<h2> 메인 페이지(model2) </h2>
	<!-- 메인페이지는 로그인 상태일때만 사용가능 
		 로그인정보가 없을경우 로그인페이지로 이동
	-->
	
	<!-- ㅇㅇㅇ님 환영합니다! -->
	<%
		String id = (String) session.getAttribute("id");
		
		   if(id == null){
			   response.sendRedirect("./MemberLogin.me");
		   }
	%>
	
	<h3><%=session.getAttribute("id") %>님 환영합니다</h3>
	<h3>${sessionScope.id }님 환영합니다</h3>
	
		
	<input type="button" value="로그아웃" onclick="location.href='./MemberLogoutAction.me';">	
	
	<hr>
	<h2> 일반회원 메뉴 </h2>
	<h3> <a href="./MemberInfo.me">회원정보 조회</a></h3>
	
	<h3> <a href="./MemberUpdate.me">회원정보 수정</a></h3>
	
	<h3> <a href="./MemberDelete.me">회원정보 삭제</a></h3>
	
	<h3> <a href="./BoardList.bo">아이티윌 게시판</a></h3>
	
	<hr>
	<hr>
	
	<!-- 관리자 메뉴는 admin일때만 조회 가능 -->
	<%
		// if(id != null && id.equals("admin")){}
		if(id != null){
			if(id.equals("admin")){
	
	%>
	
	<h2> 관리자 메뉴 </h2>
	<h3> <a href="list.jsp">회원목록(List) </a></h3>
	<%
			}
		}
	%>
	
	
	
	
	
	
	
		
	
	
	
	
</body>
</html>