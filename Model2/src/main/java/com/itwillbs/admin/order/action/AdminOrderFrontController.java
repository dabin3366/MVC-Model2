package com.itwillbs.admin.order.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.ao")
public class AdminOrderFrontController extends HttpServlet {
	
	protected void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" C  : doProcess 호출! ");
		
		/////////////////////// 1. 가상주소 계산 //////////////////////////
		System.out.println("\n\n 1. 가상주소 계산 - 시작 ");
		String requestURI = request.getRequestURI();
		System.out.println(" C : requestURI - "+requestURI);
		String ctxPath = request.getContextPath();
		System.out.println(" C : ctxPath - "+ctxPath);
		String command = requestURI.substring(ctxPath.length());
		System.out.println(" C : command - "+command);
		System.out.println("\n 1. 가상주소 계산 - 끝 ");
		
		/////////////////////// 1. 가상주소 계산 //////////////////////////

		/////////////////////// 2. 가상주소 매핑 //////////////////////////
		System.out.println("\n\n 2. 가상주소 매핑 - 시작 ");
		Action action = null;
		ActionForward forward = null;
		
		if(command.equals("/AdminOrderList.ao")) {
			System.out.println(" C : /AdminOrderList.ao 호출 ");
			// 패턴 3  AdminOrderListAction() 객체
			
			action = new AdminOrderListAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}		
		}
		else if(command.equals("/AdminOrderDetail.ao")) {
			System.out.println(" C : /AdminOrderDetail.ao 호출 ");
			// 패턴3
			// AdminOrderDetailAction
			action = new AdminOrderDetailAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/AdminOrderUpdate.ao")) {
			System.out.println(" C : /AdminOrderUpdate.ao 호출 ");
			// 패턴2
			// AdminOrderUpdateAction()
			action = new AdminOrderUpdateAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		
		System.out.println("\n 2. 가상주소 매핑 - 끝 ");
		/////////////////////// 2. 가상주소 매핑 //////////////////////////

		/////////////////////// 3. 가상주소 이동 //////////////////////////
		System.out.println("\n\n 3. 가상주소 이동 - 시작 ");
		
		if(forward != null) {  // 이동정보가 있을때
			if(forward.isRedirect()) { // true
				System.out.println(" C : 방식 - "+forward.isRedirect()+", 주소 - "+forward.getPath());
				response.sendRedirect(forward.getPath());					
			}else {//false
				System.out.println(" C : 방식 - "+forward.isRedirect()+", 주소 - "+forward.getPath());
				RequestDispatcher dis =
						request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
			}
		}
		
		
		
		System.out.println("\n 3. 가상주소 이동 - 끝 ");
		/////////////////////// 3. 가상주소 이동 //////////////////////////
	}
	
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" C  : doGET 호출! ");
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" C  : doPOST 호출! ");
		doProcess(request, response);
	}
	
	

}
