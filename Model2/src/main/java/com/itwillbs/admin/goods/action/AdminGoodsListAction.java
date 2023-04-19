package com.itwillbs.admin.goods.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.admin.goods.db.AdminGoodsDAO;

public class AdminGoodsListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : AdminGoodsListAction_execute 호출 !");
		
		// DAO - 등록한 상품정보 조회(페이징x)
		AdminGoodsDAO dao = new AdminGoodsDAO();
		List aGoodsList = dao.adminGoodsList();
		
		
		// request 영역에 저장
		request.setAttribute("aGoodsList", aGoodsList);
		
		// 페이지 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./admingoods/admin_goods_List.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
