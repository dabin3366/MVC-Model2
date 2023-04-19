package com.itwillbs.basket.action;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.basket.db.BasketDAO;
import com.itwillbs.basket.db.BasketDTO;

public class BasketListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : BasketListAciton_execute 호출 ! ");
		
		// 세션 제어
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		if(id == null) {
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}
		// basketDAO - getBasketList(id)
		// => 장바구니 정보 + 상품정보(상품이름, 가격, 이미지.....)
		BasketDAO dao = new BasketDAO();
		Vector totalList = dao.getBasketList(id);
		
		System.out.println(" M : "+totalList);
		// request 영역에 저장 -> view 페이지에서 출력하기 쉬운형태로 전달
		//request.setAttribute("totalList", totalList);
		request.setAttribute("basketList", totalList.get(0));
		request.setAttribute("goodsList", totalList.get(1));
		// 페이지 이동(출력) ./basket/basket_list.jsp
		forward.setPath("./basket/basket_list.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
