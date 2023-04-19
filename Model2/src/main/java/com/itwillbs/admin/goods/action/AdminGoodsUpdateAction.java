package com.itwillbs.admin.goods.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.admin.goods.db.AdminGoodsDAO;
import com.itwillbs.admin.goods.db.GoodsDTO;

public class AdminGoodsUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : AdminGoodsUpdateAction_execute 호출 ! ");
		
		int gno = Integer.parseInt(request.getParameter("gno"));
		
		AdminGoodsDAO dao = new AdminGoodsDAO();
		GoodsDTO dto = dao.getAdminGoods(gno);
		
		request.setAttribute("dto", dto);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./admingoods/admin_goods_update.jsp");
		forward.setRedirect(false);
				
		
		return forward;
	}

}
