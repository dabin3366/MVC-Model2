package com.itwillbs.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.member.db.MemberDAO;
import com.itwillbs.member.db.MemberDTO;

public class MemberUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : MemberUpdateAction_execute() 호출 ");
		// 세션제어
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		ActionForward forward = new ActionForward();
		if(id == null) {
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}
		// DB- 특정 회원정보 조회
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = dao.getMemberInfo(id);
		// request영역에 저장
		request.setAttribute("dto", dto);
		//request.setAttribute("dto", dao.getMemberInfo(id));
		// 페이지 이동 티켓
		forward.setPath("./member/updateForm.jsp");
		forward.setRedirect(false);
		
		
		return forward;
	}

}
