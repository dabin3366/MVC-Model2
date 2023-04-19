package com.itwillbs.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.board.db.BoardDAO;
import com.itwillbs.board.db.BoardDTO;

public class BoardUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : BoardUpdateAction_execute 호출 ! ");
		
		// 전달정보(파라미터) 저장
		int bno = Integer.parseInt(request.getParameter("bno"));
		String pageNum = request.getParameter("pageNum");
		// BoardDAO() 객채 생성
		BoardDAO dao = new BoardDAO();
		// 특정 글 정보를 가져오는 메서드
		/* BoardDTO dto = dao.getBoard(bno); */
		// request 영역에 저장
		request.setAttribute("dto", dao.getBoard(bno));
		request.setAttribute("pageNum", pageNum);
		// 페이지 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./board/updateForm.jsp");
		forward.setRedirect(false);
		// ./board/updateForm.jsp
		
		return forward;
	}

}
