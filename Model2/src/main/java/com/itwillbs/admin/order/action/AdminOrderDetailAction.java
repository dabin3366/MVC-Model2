package com.itwillbs.admin.order.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.admin.order.db.AdminOrderDAO;

public class AdminOrderDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 관리자 - 세션제어
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		ActionForward forward = new ActionForward();
		if( id == null || !id.equals("admin") ) {
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;					
		}
		
		// 전달정보 저장 trade_num
		String trade_num = request.getParameter("trade_num");
		
		// AdminOrderDAO - 특정 주문정보만 조회
		AdminOrderDAO dao = new AdminOrderDAO();
		List aoDetailList = dao.getAdminOrderDetail(trade_num);
		// request 영역에 저장 
		request.setAttribute("aoDetailList", aoDetailList);
		
		forward.setPath("./adminorder/admin_order_detail.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
