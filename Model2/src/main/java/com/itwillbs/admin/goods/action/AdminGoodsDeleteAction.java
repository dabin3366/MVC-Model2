package com.itwillbs.admin.goods.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.admin.goods.db.AdminGoodsDAO;
import com.itwillbs.admin.goods.db.GoodsDTO;

public class AdminGoodsDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : AdminGoodsDeleteAction_execute 호출 !");
		
		// 전달된 정보 gno
		int gno = Integer.parseInt(request.getParameter("gno"));
		
		// DAO 객체 생성 - 상품정보 삭제 메서드
		AdminGoodsDAO dao = new AdminGoodsDAO();
		dao.deleteGoods(gno);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./AdminGoodsList.ag");
		forward.setRedirect(true);
		
		return forward;
	}

}
