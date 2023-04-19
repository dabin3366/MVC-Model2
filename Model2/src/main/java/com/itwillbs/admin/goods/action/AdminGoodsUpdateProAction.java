package com.itwillbs.admin.goods.action;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.admin.goods.db.AdminGoodsDAO;
import com.itwillbs.admin.goods.db.GoodsDTO;

public class AdminGoodsUpdateProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : AdminGoodsUpdateProAction_execute 호출 ! ");
		// 한글 처리
		
		// [첨부파일 수정]
		// 수정할 파일을 업로드
		// 기존의 파일, 수정할 파일 이름을 각각 저장
		
		// 기존파일 -> 파일 삭제
		File f = new File("경로+이름");
		f.delete();
		// 수정파일 -> 정보 수정
		// 전달된 정보 저장
		GoodsDTO dto = new GoodsDTO();
		dto.setGno(Integer.parseInt(request.getParameter("gno")));
		dto.setCategory(request.getParameter("category"));
		dto.setName(request.getParameter("name"));
		dto.setPrice(Integer.parseInt(request.getParameter("price")));
		dto.setColor(request.getParameter("color"));
		dto.setSize(request.getParameter("size"));
		dto.setAmount(Integer.parseInt(request.getParameter("amount")));
		dto.setContent(request.getParameter("content"));
		dto.setBest(Integer.parseInt(request.getParameter("best")));
		
		// DAO - 정보 수정 메서드
		AdminGoodsDAO dao = new AdminGoodsDAO();
		dao.updateGoods(dto);
		// 페이지 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./AdminGoodsList.ag");
		forward.setRedirect(true);
		
		return forward;
	}

}
