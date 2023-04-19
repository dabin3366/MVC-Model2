package com.itwillbs.basket.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.basket.db.BasketDAO;
import com.itwillbs.basket.db.BasketDTO;

public class BasketAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : BasketAddAction_execute 호출 ! ");
		
		// 개인영역 -> 로그인 
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		ActionForward forward = new ActionForward();
		if(id == null) {
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}
		
		// 전달된 정보 저장(구매수량,크기,색상,상품번호)
		// BasketDTO 객체생성
		BasketDTO dto = new BasketDTO();
		dto.setB_g_num(Integer.parseInt(request.getParameter("gno")));
		dto.setB_g_amount(Integer.parseInt(request.getParameter("amount")));
		dto.setB_g_color(request.getParameter("color"));
		dto.setB_g_size(request.getParameter("size"));
		dto.setB_m_id(id);
		System.out.println(" M : "+dto);
		
		// BasketDAO 객체
		BasketDAO dao = new BasketDAO();
		// 기존에 동일 상품이(+옵션) 있는지 없는지 체크
		int result = dao.checkGoods(dto);
		// 장바구니 저장(기존의 상품이 없을떄)
		if(result != 1) {
			dao.basketAdd(dto);
		}
		
		forward.setPath("./BasketList.ba");
		forward.setRedirect(true);
		
		return forward;
	}

}
