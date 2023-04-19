package com.itwillbs.goods.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.goods.db.GoodsDAO;

public class GoodsListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : GoodsListAction_execute 호출 !");
		
		// 전달정보 저장 
		String item = request.getParameter("item");
		
		if(item == null) {
			item = "all";
		}
		// 디비 객체 DAO 생성 - 상품리스트 조회
		GoodsDAO dao = new GoodsDAO();
		
		// request 영역에 저장
		//request.setAttribute("goodsList", dao.getGoodsList()); 
		request.setAttribute("goodsList", dao.getGoodsList(item)); 
		
		// 페이지 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./goods/goods_list.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
