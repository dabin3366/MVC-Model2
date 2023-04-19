package com.itwillbs.order.action;

import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.basket.db.BasketDAO;
import com.itwillbs.member.db.MemberDAO;
import com.itwillbs.member.db.MemberDTO;

public class OrderStartAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println(" M : OrderStartAction_execute() ");
		
		// 세션값 제어(로그인정보)
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		if(id == null) {
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;			
		}
		
		// 장바구니 객체정보 + 상품 객체정보
		BasketDAO bkdao = new BasketDAO();
		Vector totalList = bkdao.getBasketList(id);
		
		// 1) 장바구니정보 (구매상품,옵션,수량....)
		List basketList = (List)totalList.get(0);
		// 2) 상품정보 (상품명,가격,이미지....)
		List goodsList = (List)totalList.get(1);		
		
		// 3) 사용자 정보 (필요시 입력)
		MemberDAO mdao = new MemberDAO();
		MemberDTO mdto = mdao.getMemberInfo(id);

		// request객체에 저장
		request.setAttribute("basketList",basketList);
		request.setAttribute("goodsList",goodsList);
		request.setAttribute("mdto", mdto);
		
		// 페이지로 이동
		forward.setPath("./order/goods_buy.jsp");
		forward.setRedirect(false);		
		
		return forward;
	}

}
