package com.itwillbs.board.action;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.board.db.BoardDAO;
import com.itwillbs.board.db.BoardDTO;

public class BoardSearchAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : BoardSearchAction_execute() 호출 !");

		// 전달정보 저장
		String search = request.getParameter("search");
		System.out.println(" M : search:" + search);
		// BoardDAO - 검색처리()
		BoardDAO dao = new BoardDAO();

		// 게시판 전체글 개수
		int cnt = dao.getBoardCount();
		// -> 검색어 해당 글 개수 변경
		
		// 페이징처리 1
		// 한페이지에 출력할 글의 개수
		int pageSize = 5;

		// 현페이지가 몇페이지 인가 판단
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null) {
			pageNum = "1";
		}

		// 페이지 시작행 번호 계산
		// 1 11 21 31 41 .....
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;

		// 페이지 끝행 번호 계산
		// 10 20 30 .....
		int endRow = currentPage * pageSize;
		//////////////////////////////////////////////////////////////////////////
		List<BoardDTO> searchList = dao.searchBoardList(search,startRow,pageSize);
		// 페이징 처리 2 - 페이지 하단부 페이지 이동링크

		// 전체 필요한 페이지 수
		// ex) 글 : 120, 페이지당 10개씩 -> 페이지수 : 12
		// ex) 글 : 127, 페이지당 10개씩 -> 페이지수 : 13

		int pageCount = cnt / pageSize + (cnt % pageSize == 0 ? 0 : 1);

		// 한 화면에 출력할 페이지 블럭의 개수
		int pageBlock = 3;

		// 페이지블럭 시작번호
		// ex) 1~10 => 1 , 11~20 => 11, 21~30 => 21
		int startPage = ((currentPage - 1) / pageBlock) * pageBlock + 1;

		// 페이지블럭 끝번호
		int endPage = startPage + pageBlock - 1;
		if (endPage > pageCount) {
			endPage = pageCount;
		}
		////////////////////////////////////////////////////////////////////////
		request.setAttribute("boardList", searchList);
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("cnt", cnt);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		
		
		ActionForward forward = new ActionForward();
		forward.setPath("./board/boardList.jsp");
		forward.setRedirect(false);

		return forward;
	}

}
