<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1> ./member/updateForm.jsp </h1>
	
	
	<h2> ITWILL 회원정보 수정 </h2>
	
	<fieldset>
		<form action="./MemberUpdateProAction.me" method="post" name="fr" onsubmit="return sendSubmit();">
			아이디 : <input type="text" name="id" value="${dto.id }" readonly> <br>
			비밀번호 : <input type="password" name="pw" value="${dto.pw }"> <br>					
			이름 : <input type="text" name="name" value="${dto.name }"> <br>
			나이 : <input type="text" name="age" value="${dto.age }"> <br>
			성별 : <input type="radio" name="gender" value="남"
			    <c:if test="${dto.gender == '남' }"> 
			    checked
			    </c:if>
				   >남
			       <input type="radio" name="gender" value="여"
			    <c:if test="${dto.gender.equals('여') }">
			    	checked
			    </c:if>
			       >여 <br> 
			이메일 : <input type="email" name="email" value="${dto.email }"> <br>
			<hr>
			<input type="submit" value="회원수정">
		</form>
	</fieldset>
	
	<script type="text/javascript">
		function sendSubmit(){
			if(document.fr.pw.value == ""){
				alert("비밀번호를 입력하세요 !");
				document.fr.pw.focus();
				return false;
			}
		
		/////
			alert(" 유효성 체크 완료! 페이지 이동(정보전송) ");	
		}
	</script>
	
</body>
</html>