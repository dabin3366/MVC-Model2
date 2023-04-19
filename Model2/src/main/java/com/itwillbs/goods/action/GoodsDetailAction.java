package com.itwillbs.goods.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.admin.goods.db.GoodsDTO;
import com.itwillbs.goods.db.GoodsDAO;

public class GoodsDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : GoodsDetailAction_execute 호출 ! ");
		// 전달 정보 저장 gno
		int gno = Integer.parseInt(request.getParameter("gno"));
		// DAO - 특정 상품의 정보를 가져오기
		GoodsDAO dao = new GoodsDAO();
		
		// request 영역에 저장
		request.setAttribute("dto",dao.getGoods(gno));
		// 페이지 이동 & 출력(./goods/goods_detail.jsp)
		ActionForward forward = new ActionForward();
		forward.setPath("./goods/goods_detail.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
