package com.itwillbs.admin.goods.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.admin.goods.db.AdminGoodsDAO;
import com.itwillbs.admin.goods.db.GoodsDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class AdminGoodsAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : AdminGoodsAddAction_execute 호출 ");
		// 상품업로드(upload 폴더, 크기 옵션...)
		
		// 파일업로드 접근경로
		ServletContext ctx = request.getServletContext();
		String realPath = ctx.getRealPath("/upload");
		//System.out.println(realPath);
		
		int maxSize = 10 * 1024 * 1024; // 10MB
		
		MultipartRequest multi = new MultipartRequest(
										request,
										realPath,
										maxSize,
										"UTF-8",
										new DefaultFileRenamePolicy()
										);
		System.out.println(" M : 첨부파일 업로드 성공!");
		
		// 나머지 정보들 저장(DTO 객체)
		GoodsDTO dto = new GoodsDTO();
		dto.setCategory(multi.getParameter("category"));
		dto.setName(multi.getParameter("name"));
		dto.setPrice(Integer.parseInt(multi.getParameter("price")));
		dto.setColor(multi.getParameter("color"));
		dto.setSize(multi.getParameter("size"));
		dto.setAmount(Integer.parseInt(multi.getParameter("amount")));
		dto.setContent(multi.getParameter("content"));
		
		String img = multi.getFilesystemName("file1")+","+
				     multi.getFilesystemName("file2")+","+
				     multi.getFilesystemName("file3")+","+
				     multi.getFilesystemName("file4");
		
		dto.setImage(img);
		// 디비 연결 - 상품등록 메서드
		AdminGoodsDAO dao = new AdminGoodsDAO();
		dao.insertGoods(dto);
		
		// 페이지 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./AdminGoodsList.ag");
		forward.setRedirect(true);
		
		return forward;
	}

}
