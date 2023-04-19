package com.itwillbs.board.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




public class BoardFrontController extends HttpServlet{

	
	protected void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" C : doProcess() 호출");
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
		
		if(command.equals("/BoardWrite.bo")) {
			System.out.println(" C : /BoardWrite.bo 호출");
			System.out.println(" C : db사용 x view o");
			// 패턴1
			forward = new ActionForward();
			forward.setPath("./board/writeForm.jsp");
			forward.setRedirect(false);
		}
		else if(command.equals("/BoardWriteAction.bo")) {
			System.out.println(" C : /BoardWriteAction.bo 호출");
			System.out.println(" C : DB사용 o view 페이지 이동");
			// 패턴2
			action = new BoardWriteAction();
			
			 try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/BoardList.bo")) {
			System.out.println(" C : /BoardList.bo 호출 ");
			System.out.println(" C : DB사용 O view O");
			// 패턴3
			// BoardListAction() 생성
			action = new BoardListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/BoardContent.bo")) {
			System.out.println(" C : /BoardContent.bo 호출 ");
			System.out.println(" C : DB사용 O , view 출력");
			// 패턴3
			// BoardContentAction() 생성
			action = new BoardContentAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/BoardUpdate.bo")) {
			System.out.println(" C : /BoardUpdate.bo 호출! ");
			System.out.println(" C : DB사용 O view 출력");
			// 패턴3
			// BoardUpdateAction() 생성
			action = new BoardUpdateAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/BoardUpdatePro.bo")) {
			System.out.println(" C : /BoardUpdatePro.bo 호출 ");
			System.out.println(" C : DB사용 O view 이동");
			// 패턴2
			// BoardUpdateProAction() 객체 생성
			action = new BoardUpdateProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/BoardDeleteAction.bo")){
			System.out.println(" C : /BoardDeleteAction.bo 호출");
			System.out.println("DB사용 O view 이동");
			// 패턴2
			// BoardDeleteAction 객체 생성
			action = new BoardDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/BoardReWrite.bo")) {
			System.out.println(" C : /BoardReWrite.bo 호출");
			System.out.println(" C : DB사용 X view 출력");
			// 패턴1
			forward = new ActionForward();
			forward.setPath("./board/reWriteForm.jsp");
			forward.setRedirect(false);
			
		}
		else if(command.equals("/BoardReWriteAction.bo")) {
			System.out.println(" C : /BoardReWriteAction 호출 ! ");
			System.out.println(" C : DB사용 o view 이동");
			// 패턴2
			// BoardReWriteAction 객체 생성
			action = new BoardReWriteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/BoardSearchAction.bo")) {
			System.out.println(" C : /BoardSearchAction.bo 호출 !");
			System.out.println(" C : DB사용 o view 출력");
			// 패턴3
			// BoardSearchAction() 생성
			action = new BoardSearchAction();
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
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" C : doGet() 호출");
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" C : doPost() 호출");
		doProcess(request, response);
	}
	
}
