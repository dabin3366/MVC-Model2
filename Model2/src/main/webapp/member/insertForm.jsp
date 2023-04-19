<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1> ./member/insertForm.jsp </h1>
	
	
	<h2> ITWILL 회원가입 </h2>
	
	<fieldset>
		<form action="./MemberJoinAction.me" method="post" name="fr" onsubmit="sendSubmit();">
			아이디 : <input type="text" name="id"> <br>
			비밀번호 : <input type="password" name="pw"> <br>					
			이름 : <input type="text" name="name"> <br>
			나이 : <input type="text" name="age"> <br>
			성별 : <input type="radio" name="gender" value="남">남
			       <input type="radio" name="gender" value="여">여 <br>
			이메일 : <input type="email" name="email"> <br>
			<hr>
			<input type="submit" value="회원가입">
		</form>
	</fieldset>
	
	<script type="text/javascript">
		function sendSubmit(){
			if(document.fr.id.value == ""){
				alert("아이디를 입력하세요 !");
				document.fr.id.focus();
				return false;
			}
		
		/////
			alert(" 유효성 체크 완료! 페이지 이동(정보전송) ");	
		}
	</script>
	
</body>
</html>