package com.itwillbs.order.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.order.db.OrderDAO;

public class OrderDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println(" M : OrderDetailAction_execute() ");
		
		// 세션정보 처리 (생략)
		
		// 전달정보 저장 (trade_num)
		String trade_num = request.getParameter("trade_num");
		
		// OrderDAO - 주문번호에 해당하는 모든 주문정보 가져오기
		OrderDAO dao = new OrderDAO();
		
		// request 영역에 저장
		request.setAttribute("detailList", dao.getOrderDetail(trade_num));
		
		// 페이지 이동	
		ActionForward forward = new ActionForward();
		forward.setPath("./order/order_detail.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
