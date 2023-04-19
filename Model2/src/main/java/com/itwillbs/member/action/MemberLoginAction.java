package com.itwillbs.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.member.db.MemberDAO;
import com.itwillbs.member.db.MemberDTO;

public class MemberLoginAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : MemberLoginAction_execute() 호출 ");
		
		// 로그인 체크
		// 한글처리
		request.setCharacterEncoding("UTF-8");
		// 전달된 파라미터 저장(id,pw)
//		String id = request.getParameter("id"); 
//		String pw = request.getParameter("pw");
		
		MemberDTO dto = new MemberDTO();
		dto.setId(request.getParameter("id"));
		dto.setPw(request.getParameter("pw"));
//		System.out.println(" M : "+dto);
		
		// DAO 객체 - 로그인 체크() 호출
		MemberDAO dao = new MemberDAO();
		int result = dao.loginMember(dto);
		
		// 호출결과에 따른 페이지 이동(JS사용)
		if(result == 0) { // 비밀번호 오류
			System.out.println(" M : JS-페이지 이동(컨트롤러 X) ");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			out.write("<script>");
			out.write("  alert('비밀번호 오류!'); ");
			out.write("  history.back(); ");
			out.write("</script>");
			out.close();
			return null;     // 자바스크립트로만 이동, 컨트롤러에서는 이동 X
		}
		if(result == -1) { // 회원정보 없음
			System.out.println(" M : JS-페이지 이동(컨트롤러 X) ");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			out.write("<script>");
			out.write("  alert('회원정보 없음!'); ");
			out.write("  history.back(); ");
			out.write("</script>");
			out.close();
			return null;     // 자바스크립트로만 이동, 컨트롤러에서는 이동 X
		}
		// result == 1  // 로그인 성공
		
		// 로그인 성공 => 로그인정보 저장(세션객체)
		HttpSession session = request.getSession();
		session.setAttribute("id", dto.getId());
		
		// main페이지 이동티켓
		ActionForward forward = new ActionForward();
		forward.setPath("./Main.me");
		forward.setRedirect(true);
		
		return forward;
	}
	
}
