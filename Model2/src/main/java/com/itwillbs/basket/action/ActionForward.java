package com.itwillbs.basket.action;

/**
 *  페이지 이동할때 필요한 정보를 저장하는 객체 (티켓)
 *  1) 페이지 이동주소
 *   
 *  2) 이동 방식 - true : sendRedirect() 이동
 *  	         - false : forward() 이동
 */

public class ActionForward {
	private String path;  // 이동할 페이지 주소
	private boolean isRedirect;  // 이동할 방식
	
	public ActionForward() {
		System.out.println("---------------------------");
		System.out.println(" 페이지 이동정보(티켓) 생성");
		System.out.println("---------------------------");
	}
	
	 
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public boolean isRedirect() {
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	 
}
