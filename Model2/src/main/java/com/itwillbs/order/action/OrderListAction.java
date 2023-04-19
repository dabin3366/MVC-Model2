package com.itwillbs.order.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.order.db.OrderDAO;

public class OrderListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : OrderListAction_execute 호출 ");
		
		// 세션제어
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		if(id == null) {
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;			
		}
		
		// OrderDAO 객체  - 주문목록 조회(id)
		OrderDAO dao = new OrderDAO();
		
		// 전달받은 정보를 view페이지로 전달 request영역
		request.setAttribute("orderList", dao.getOrderList(id));
		
		// 페이지로 이동    ./order/order_list.jsp 
		forward.setPath("./order/order_list.jsp");
		forward.setRedirect(false);		
		return forward;
	}

}
