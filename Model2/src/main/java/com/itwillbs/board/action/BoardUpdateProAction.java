package com.itwillbs.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.board.db.BoardDAO;
import com.itwillbs.board.db.BoardDTO;

public class BoardUpdateProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : BoardUpdateProAction_execute 호출 !");
		
		// 한글처리 -> 생략
		// 전달된 정보 저장
		String pageNum = request.getParameter("pageNum");
		
		BoardDTO dto = new BoardDTO();
		dto.setBno(Integer.parseInt(request.getParameter("bno")));
		dto.setName(request.getParameter("name"));
		dto.setSubject(request.getParameter("subject"));
		dto.setContent(request.getParameter("content"));
		dto.setPass(request.getParameter("pass"));
		// BoardDAO 객체 생성 - 글 수정 메서드 호출
		BoardDAO dao = new BoardDAO();
		int result = dao.updateBoard(dto);
		// 수정 처리 결과에 따른 페이지 이동(JS)
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		System.out.println(" M : JS 사용페이지 이동 (컨트롤러 이동 X)");
		if(result == 0 ) {
			out.write("<script>");
			out.write("alert('비밀번호 오류 !');");
			out.write("history.back();");
			out.write("</script>");
			out.close();
			return null;
		}
		if(result == -1 ) {
			out.write("<script>");
			out.write("alert('게시판 글 없음!');");
			out.write("history.back();");
			out.write("</script>");
			out.close();
			return null;
		}
		// result == 1
		out.write("<script>");
		out.write("alert('수정 완료!');");
		out.write("location.href='./BoardList.bo?pageNum="+pageNum+"';");
		out.write("</script>");
		out.close();
		return null;
	}

}
