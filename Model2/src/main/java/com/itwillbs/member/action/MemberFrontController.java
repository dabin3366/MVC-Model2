package com.itwillbs.member.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Member컨트롤러 : 회원정보관련된 처리만 수행(서블릿)

public class MemberFrontController extends HttpServlet{

	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" C : MemberFrontController-doProcess() 호출 ");
		
//		System.out.println(request.getRequestURI()); 
//		System.out.println(request.getRequestURL()); 
		//////////////////////1. 가상주소 계산/////////////////////////////
		System.out.println("\n\n 1. 가상주소 계산 - 시작 ");
		String requestURI = request.getRequestURI();
		System.out.println(" C : requestURI - "+requestURI);
		String ctxPath = request.getContextPath();
		System.out.println(" C : ctxPath - "+ctxPath);
		String command = requestURI.substring(ctxPath.length());
		System.out.println(" C : command - "+command);
		System.out.println("\n 1. 가상주소 계산 - 끝 ");
		//////////////////////1. 가상주소 계산/////////////////////////////
		
		//////////////////////2. 가상주소 매핑/////////////////////////////
		System.out.println(" \n\n 2. 가상주소 매핑 - 시작 ");
		Action action = null;
		ActionForward forward = null;
		
		if(command.equals("/MemberJoin.me")) {
			System.out.println(" C : /MemberJoin.me 호출!");
			System.out.println(" C : DB동작 X, view O");
			// 패턴1
			forward = new ActionForward();
			forward.setPath("./member/insertForm.jsp");
			forward.setRedirect(false);
		}else if(command.equals("/MemberJoinAction.me")) {
			System.out.println(" C : /MemberJoinAction.me 호출! ");
			System.out.println(" C : DB동작 O,view 이동");
			// 패턴2
			// 객체 생성 MemberJoinAction
			//MemberJoinAction m = new MemberJoinAction();
			 action = new MemberJoinAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(command.equals("/MemberLogin.me")) {
			System.out.println(" C : /MemberLogin.me 호출");
			System.out.println(" C : DB사용X, view O");
			// 패턴1
			forward = new ActionForward();
			forward.setPath("./member/loginForm.jsp");
			forward.setRedirect(false);
		}else if(command.equals("/MemberLoginAction.me")) {
			System.out.println(" C : /MemberLoginAction.me 호출");
			System.out.println(" C : DB사용O, view O");
			//패턴2
			// MemberLoginAction 객체 생성
			action = new MemberLoginAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/Main.me")) {
			System.out.println(" C : /Main.me 호출 ");
			System.out.println(" C : DB사용 X, view O");
			// 패턴1
			forward = new ActionForward();
//			forward.setPath("./member/main.jsp");
			forward.setPath("./main/main.jsp");
			forward.setRedirect(false);
		}
		else if(command.equals("/MemberLogoutAction.me")) {
			System.out.println(" C : /MemberLogoutAction.me 호출 ");
			System.out.println(" C : DB사용 X(작업처리O), view 이동 ");
			// 패턴2
			// MemberLogoutAction 객체 생성
			action = new MemberLogoutAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/MemberInfo.me")) {
			System.out.println(" C : /MemberInfo.me 호출 ");
			System.out.println(" C : DB사용 O, view O");
			// 패턴3
			// MemberInfoAction 객체 생성
			action = new MemberInfoAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		else if(command.equals("/MemberUpdate.me")) {
			System.out.println(" C : /MemberUpdate.me 호출");
			System.out.println(" C : DB사용 O, view O");
			// 패턴3
			action = new MemberUpdateAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/MemberUpdateProAction.me")) {
			System.out.println(" C : /MemberUpdateProAciton.me 호출 ");
			System.out.println(" C : DB사용 O, view 페이지이동 ");
			// 패턴2
			// MemberUpdateProAction 객체 생성
			action = new MemberUpdateProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/MemberDelete.me")) {
			System.out.println(" C : /MemberDelete.me 호출");
			System.out.println(" C : DB사용 O, view O");
			action = new MemberDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		System.out.println(" \n 2. 가상주소 매핑 - 끝 ");
		//////////////////////2. 가상주소 매핑/////////////////////////////
		
		//////////////////////3. 가상주소 이동/////////////////////////////
		System.out.println(" \n\n 3. 가상주소 이동 - 시작 ");
		if(forward != null) { // 이동정보가 있을때
			
			if(forward.isRedirect()) { // true
				System.out.println(" C : 방식 - "+forward.isRedirect()+", 주소 - "+forward.getPath());
				response.sendRedirect(forward.getPath());
			}else { // false
				System.out.println(" C : 방식 - "+forward.isRedirect()+", 주소 - "+forward.getPath());
				RequestDispatcher dis =
					request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
			}
		}
		
		System.out.println(" \n 3. 가상주소 이동 - 끝 ");
		//////////////////////3. 가상주소 이동/////////////////////////////
		
		
	}
	
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" C : MemberFrontController-doGet() 호출 ");
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" C : MemberFrontController-doPost() 호출 ");
		doProcess(request, response);
	}
	
}
