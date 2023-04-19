package com.itwillbs.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.member.db.MemberDAO;
import com.itwillbs.member.db.MemberDTO;

public class MemberUpdateProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : MemberUpdateProAction_execute() 호출! ");
		// 세션 처리
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		if(id == null) {
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}
		// 한글처리
		request.setCharacterEncoding("UTF-8");
		
		// 전달된 정보 저장(수정할 정보)
		MemberDTO dto = new MemberDTO();
		dto.setId(id);
		dto.setPw(request.getParameter("pw"));
		dto.setName(request.getParameter("name"));
		dto.setEmail(request.getParameter("email"));
		dto.setGender(request.getParameter("gender"));
		dto.setAge(Integer.parseInt(request.getParameter("age")));
		
		// DAO 객체 - 회원정보 수정()
		MemberDAO dao = new MemberDAO();
		int result = dao.updateMember(dto);
		
		// 페이지 이동(JS)
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		if(result == 0) {
			out.println("<script>");
			out.println("alert('비밀번호 오류');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;
		}
		if(result == -1) {
			out.println("<script>");
			out.println("alert('아이디없음 오류');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;
		}
		
		// result == 1
		out.println("<script>");
		out.println("alert('수정 완료');");
		out.println("location.href='./Main.me';");
		out.println("</script>");
		out.close();
		
		return null;
	}

}
