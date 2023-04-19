package com.itwillbs.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.board.db.BoardDAO;
import com.itwillbs.board.db.BoardDTO;

public class BoardContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : BoardContentAction_execute 호출 ! ");
		
		// 전달정보 저장
		int bno = Integer.parseInt(request.getParameter("bno"));
		String pageNum = request.getParameter("pageNum");
		// BoardDAO() 객체 생성
		BoardDAO dao = new BoardDAO();
		// 조회수 1증가 메서드 호출
		dao.updateReadCount(bno);
		// 게시판글 1개를 가져오는 메서드(특정글)
		BoardDTO dto = dao.getBoard(bno);
		
		// 엔터키 -> br태그 변경
		String tmp = dto.getContent().replace("\r\n", "<br>");
		dto.setContent(tmp);
		
		// 게시판에 정보 저장(request 영역)
		request.setAttribute("dto",dto);
		request.setAttribute("pageNum",pageNum);
		// 페이지 이동(출력 페이지)
		ActionForward forward = new ActionForward();
		forward.setPath("./board/content.jsp");
		forward.setRedirect(false);
		// ./board/content.jsp
		
		return forward;
	}

}
