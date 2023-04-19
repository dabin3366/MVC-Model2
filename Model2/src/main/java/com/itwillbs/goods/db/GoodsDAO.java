package com.itwillbs.goods.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.itwillbs.admin.goods.db.GoodsDTO;
import com.itwillbs.basket.db.BasketDTO;

public class GoodsDAO {

	// 공통 변수 선언
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "";

	// 디비연결 메서드
	private Connection getCon() throws Exception {
		// 드라이버로드 + 디비연결 => 커넥션풀
		// JNDI(Java Naming and Directory Interface) API
		// -> 특정 자원(파일/데이터)에 접근하기위한 기술을 제공한다
		// Context 객체 생성
		Context initCTX = new InitialContext();
		// content.xml 파일에 있는 리소스 정보를 불러오기
		DataSource ds = (DataSource) initCTX.lookup("java:comp/env/jdbc/model2");
		// 디비연결
		con = ds.getConnection();

		System.out.println(" DAO : 디비연결 성공! " + con);

		return con;
	}
	// 디비연결 메서드

	// 자원해제
	public void closeDB() {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// 자원해제

	// 상품정보 조회
	public List<GoodsDTO> getGoodsList() {
		List<GoodsDTO> goodsList = new ArrayList<>();
		try {
			// 1.2 디비 연결
			con = getCon();
			// 3. sql 작성
			sql = "select * from itwill_goods";
			pstmt = con.prepareStatement(sql);
			// 4. sql 실행
			rs = pstmt.executeQuery();
			// 5. 데이터 처리 (rs -> DTO -> List)
			while (rs.next()) {
				GoodsDTO dto = new GoodsDTO();
				dto.setAmount(rs.getInt("amount"));
				dto.setBest(rs.getInt("best"));
				dto.setCategory(rs.getString("category"));
				dto.setColor(rs.getString("color"));
				dto.setContent(rs.getString("content"));
				dto.setDate(rs.getDate("date"));
				dto.setGno(rs.getInt("gno"));
				dto.setImage(rs.getString("image"));
				dto.setName(rs.getString("name"));
				dto.setPrice(rs.getInt("price"));
				dto.setSize(rs.getString("size"));

				goodsList.add(dto);
			}
			System.out.println(" DAO : 상품목록 조회 성공! ");
			System.out.println(" DAO : " + goodsList.size());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return goodsList;
	}
	// 상품정보 조회

	public List<GoodsDTO> getGoodsList(String item) {
		List<GoodsDTO> goodsList = new ArrayList<>();
		StringBuffer SQL = new StringBuffer();

		try {
			// 1.2 디비 연결
			con = getCon();
			// 3. sql 작성 - item 정보에 따른 변경

//					sql = "select * from itwill_goods";
			SQL.append("select * from itwill_goods ");

			if (item.equals("all")) {
			} else if (item.equals("best")) {
				SQL.append("where best=? ");
			} else {
				SQL.append("where category=? ");
			}

			// pstmt = con.prepareStatement(SQL.toString());
			pstmt = con.prepareStatement(SQL + "");

			if (item.equals("all")) {
			} else if (item.equals("best")) {
				pstmt.setInt(1, 1);
			} else {
				pstmt.setString(1, item);
			}

			// 4. sql 실행
			rs = pstmt.executeQuery();
			// 5. 데이터 처리 (rs -> DTO -> List)
			while (rs.next()) {
				GoodsDTO dto = new GoodsDTO();
				dto.setAmount(rs.getInt("amount"));
				dto.setBest(rs.getInt("best"));
				dto.setCategory(rs.getString("category"));
				dto.setColor(rs.getString("color"));
				dto.setContent(rs.getString("content"));
				dto.setDate(rs.getDate("date"));
				dto.setGno(rs.getInt("gno"));
				dto.setImage(rs.getString("image"));
				dto.setName(rs.getString("name"));
				dto.setPrice(rs.getInt("price"));
				dto.setSize(rs.getString("size"));

				goodsList.add(dto);
			}
			System.out.println(" DAO : 상품목록 조회 성공! ");
			System.out.println(" DAO : " + goodsList.size());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return goodsList;
	}

	// 특정 상품을 조회
	public GoodsDTO getGoods(int gno) {
		GoodsDTO dto = null;
		try {
			con = getCon();
			sql = "select * from itwill_goods where gno =?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, gno);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto = new GoodsDTO();

				dto.setAmount(rs.getInt("amount"));
				dto.setBest(rs.getInt("best"));
				dto.setCategory(rs.getString("category"));
				dto.setColor(rs.getString("color"));
				dto.setContent(rs.getString("content"));
				dto.setDate(rs.getDate("date"));
				dto.setGno(rs.getInt("gno"));
				dto.setImage(rs.getString("image"));
				dto.setName(rs.getString("name"));
				dto.setPrice(rs.getInt("price"));
				dto.setSize(rs.getString("size"));
			}
			System.out.println(" DAO : 상품 정보 1개 조회성공!");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return dto;
	}
	// 특정 상품을 조회
	
	// updateAmount(basketList) - 주문 결제후 수량변경
	public void updateAmount(List basketList) {
		try {
			// 디비 연결
			con = getCon();
			// sql 
			for(int i=0;i<basketList.size();i++) {
				BasketDTO dto = (BasketDTO)basketList.get(i);
				// 구매 상품 내역모두 수량 변경
				sql="update itwill_goods set amount=amount-? where gno=?";
				pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, dto.getB_g_amount());
				pstmt.setInt(2, dto.getB_g_num());
				
				pstmt.executeUpdate();
			} // for
			System.out.println(" DAO : 구매후 상품 수량 변경!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	}
	
	
	// updateAmount(basketList) - 주문 결제후 수량변경

}
