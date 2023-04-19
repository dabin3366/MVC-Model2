package com.itwillbs.member.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.member.db.MemberDAO;
import com.itwillbs.member.db.MemberDTO;

public class MemberJoinAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// pro 페이지에서 수행한 대부분의 동작을 처리
		System.out.println(" M : MemberJoinAction_execute() 호출! ");
		// 한글처리
		request.setCharacterEncoding("UTF-8");
		// 전달정보 저장(파라미터)
		
		// MemberDTO객체 생성 -> 정보 저장 (확인)
		MemberDTO dto = new MemberDTO();
		
		dto.setId(request.getParameter("id"));
		dto.setPw(request.getParameter("pw"));
		dto.setName(request.getParameter("name"));
		dto.setGender(request.getParameter("gender"));
		dto.setAge(Integer.parseInt(request.getParameter("age")));
		dto.setEmail(request.getParameter("email"));
		dto.setRegdate(new Timestamp(System.currentTimeMillis()));
		
		System.out.println(" M : "+dto.toString());
		// 디비에 저장
		MemberDAO dao = new MemberDAO();
		// 회원가입
		dao.insertMember(dto);
		
		// 로그인 페이지로 이동티켓 생성(직접이동x)
		ActionForward forward = new ActionForward();
		forward.setPath("./MemberLogin.me");
		forward.setRedirect(true);
		
		return forward;
	}
	
	
}
