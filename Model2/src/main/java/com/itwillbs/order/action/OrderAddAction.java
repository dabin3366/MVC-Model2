package com.itwillbs.order.action;

import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.basket.db.BasketDAO;
import com.itwillbs.goods.db.GoodsDAO;
import com.itwillbs.order.db.OrderDAO;
import com.itwillbs.order.db.OrderDTO;

public class OrderAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 세션정보 제어
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		if(id == null) {
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}
		
		// 한글처리 (필터)
		// 전달정보 저장 (DTO)
		OrderDTO orDTO = new OrderDTO();
		// id, 받는사람,연락처,주소,상세주소,요구사항
		// 결제 정보,결제자
		
		orDTO.setO_m_id(id);
		orDTO.setO_r_name(request.getParameter("o_r_name"));
		orDTO.setO_r_phone(request.getParameter("o_r_phone"));
		orDTO.setO_r_addr1(request.getParameter("o_r_addr1"));
		orDTO.setO_r_addr2(request.getParameter("o_r_addr2"));
		orDTO.setO_r_msg(request.getParameter("o_r_msg"));
		orDTO.setO_trade_type(request.getParameter("o_trade_type"));
		orDTO.setO_trade_payer(request.getParameter("o_trade_payer"));
		
		// DB에 있는 정보도 추가적으로 저장
		// 구매한 상품정보,장바구니정보...
		
		// 장바구니 DB객체
		BasketDAO bkDAO = new BasketDAO();
		Vector totalList = bkDAO.getBasketList(id);
		List basketList = (List)totalList.get(0);
		List goodsList = (List)totalList.get(1);
		
		// 주문정보 저장 (DAO)
		OrderDAO orDAO = new OrderDAO();
		orDAO.addOrder(orDTO,basketList,goodsList);
		System.out.println(" M : 주문정보 저장! ");
		
		// 메일, 문자, push - 알림 (구현형태만)
		// => 시간이 필요한 작업 (Thread 개념)
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				for(long i=0;i<1000000000000000000L;i++);
				System.out.println(" M : 메일 전송 완료! ");
			}
		}).start();
		
		// 상품의 전체 수량 변경
		GoodsDAO gDAO = new GoodsDAO();
		gDAO.updateAmount(basketList);
		System.out.println(" M : 상품 수량변경! ");
		
		// 장바구니 비우기 
		bkDAO.deleteBasket(id);
		System.out.println(" M : 장바구니 제거 완료! ");
		
		
		// 페이지 이동		
		forward.setPath("./OrderList.or");
		forward.setRedirect(true);
		
		return forward;
	}

}
