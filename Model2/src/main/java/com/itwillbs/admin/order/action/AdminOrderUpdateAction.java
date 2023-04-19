package com.itwillbs.admin.order.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.admin.order.db.AdminOrderDAO;
import com.itwillbs.order.db.OrderDTO;

public class AdminOrderUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 관리자 - 세션제어
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		ActionForward forward = new ActionForward();
		if (id == null || !id.equals("admin")) {
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}
		// 전달정보 저장(주문번호,상태,운송장번호)
		OrderDTO dto = new OrderDTO();
		dto.setO_trade_num(request.getParameter("trade_num"));
		dto.setO_status(Integer.parseInt(request.getParameter("status")));
		dto.setO_trans_num(request.getParameter("trans_num"));
		// DAO - 메서드(DTO)
		AdminOrderDAO dao = new AdminOrderDAO();
		dao.updateOrder(dto);
		
		forward.setPath("./AdminOrderList.ao");
		forward.setRedirect(true);
		
		return forward;
	}

}
