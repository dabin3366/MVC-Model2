package com.itwillbs.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.board.db.BoardDAO;
import com.itwillbs.board.db.BoardDTO;

public class BoardDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : BoardDeleteAction_execute() 호출 ");
		
		// 전달정보 저장
		int bno = Integer.parseInt(request.getParameter("bno"));
		String pageNum = request.getParameter("pageNum");
		// BoardDAO - 삭제 메서드 오버로딩
		BoardDAO dao = new BoardDAO();
		int result = dao.deleteBoard(bno);
		// 삭제 처리 결과에 따른 페이지 이동(JS)
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		if(result == 0) {
			out.write("<script>");
			out.write("alert('삭제 실패!');");
			out.write("history.back();");
			out.write("</script>");
			out.close();
			return null;
		}
		// result == 1
		out.write("<script>");
		out.write("alert('삭제 완료!');");
		out.write("location.href='./BoardList.bo?pageNum"+pageNum+"';");
		out.write("</script>");
		out.close();
		return null;
		
		// 삭제O / 삭제X
		
	}

}
