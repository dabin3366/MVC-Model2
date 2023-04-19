package com.itwillbs.basket.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.itwillbs.admin.goods.db.GoodsDTO;

public class BasketDAO {

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

	// checkGoods(dto)
	public int checkGoods(BasketDTO dto) {
		int result = 0;

		try {
			// 디비연결
			con = getCon();
			// sql - 기존의 상품정보가 있는지 체크 (update)
			sql = "select * from itwill_basket where " + " b_m_id=? and b_g_num=? and b_g_size=? and b_g_color=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getB_m_id());
			pstmt.setInt(2, dto.getB_g_num());
			pstmt.setString(3, dto.getB_g_size());
			pstmt.setString(4, dto.getB_g_color());
			// sql실행
			rs = pstmt.executeQuery();
			// 데이터 처리
			if (rs.next()) { // 기존에 장바구니에 동일상품등록

				// sql - 기존상품 개수만 update
				sql = "update itwill_basket set b_g_amount=b_g_amount+? where "
						+ " b_m_id=? and b_g_num=? and b_g_size=? and b_g_color=?";
				pstmt = con.prepareStatement(sql);

				pstmt.setInt(1, dto.getB_g_amount());
				pstmt.setString(2, dto.getB_m_id());
				pstmt.setInt(3, dto.getB_g_num());
				pstmt.setString(4, dto.getB_g_size());
				pstmt.setString(5, dto.getB_g_color());
				// sql 실행
				result = pstmt.executeUpdate();
			}
			System.out.println(" DAO : 장바구니 체크 완료 ! " + result);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return result;
	}
	// checkGoods(dto)

	// basketAdd(dto)
	public void basketAdd(BasketDTO dto) {
		int b_num = 0;
		try {
			// 디비연결
			con = getCon();
			// sql
			sql = "select max(b_num) from itwill_basket";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				b_num = rs.getInt(1) + 1;
			}
			// sql - insert
			sql = "insert into itwill_basket " + " values (?,?,?,?,?,?,now())";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, b_num);
			pstmt.setString(2, dto.getB_m_id());
			pstmt.setInt(3, dto.getB_g_num());
			pstmt.setInt(4, dto.getB_g_amount());
			pstmt.setString(5, dto.getB_g_size());
			pstmt.setString(6, dto.getB_g_color());
			pstmt.executeUpdate();
			System.out.println(" DAO : 장바구니 저장!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	}
	// basketAdd(dto)

	// basketDAO - getBasketList(id)
	// List = 장바구니(리스트) + 상품정보(리스트)
	public Vector getBasketList(String id) {
		// 장바구니정보 + 상품정보
		Vector totalList = new Vector();
		// 장바구니 정보만 저장
		List basketList = new ArrayList();
		// 상품 정보만 저장
		List goodsList = new ArrayList();
		try {
			con = getCon();
			// 장바구니 리스트 저장
			sql = "select * from itwill_basket where b_m_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			// 데이터 처리
			while(rs.next()) {
				// rs(장바구니 리스트) -> DTO -> basketList
				BasketDTO dto = new BasketDTO();
				
				dto.setB_num(rs.getInt("b_num"));
				dto.setB_m_id(rs.getString("b_m_id"));
				dto.setB_g_num(rs.getInt("b_g_num"));
				dto.setB_g_amount(rs.getInt(4));
				dto.setB_g_size(rs.getString(5));
				dto.setB_g_color(rs.getString(6));
				dto.setB_date(rs.getDate(7));
				
				// DTO -> List
				basketList.add(dto);
				//////////////////////////////////////////////////////////////////////////////////////////
				// 장바구니에 저장된 상품의 상품정보를 저장
				// 3. sql
				sql = "select * from itwill_goods where gno=?";
				PreparedStatement pstmt2 = con.prepareStatement(sql);
				
				pstmt2.setInt(1, dto.getB_g_num());
				// sql실행
				ResultSet rs2 = pstmt2.executeQuery();
				if(rs2.next()) {
					// 상품정보 저장(상품명, 가격,이미지...)
					GoodsDTO gdto = new GoodsDTO();
					gdto.setName(rs2.getString("name"));
					gdto.setPrice(rs2.getInt("price"));
					gdto.setImage(rs2.getString("image"));
					gdto.setGno(rs2.getInt("gno"));
					
					goodsList.add(gdto);
					
				}// if
				//////////////////////////////////////////////////////////////////////////////////////////
			} // while
			
			// basketList -> vector
			totalList.add(basketList);
			// goodsList -> vector
			totalList.add(goodsList);
			System.out.println(" DAO : 저장된 상품저장");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return totalList;
	}
	// basketDAO - getBasketList(id)
	
	
	// basketDelete(dto)
	public void deleteBasket(int b_num) {
		try {
			con = getCon();
			sql = "delete from itwill_basket where b_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, b_num);
			pstmt.executeUpdate();
			System.out.println(" DAO : 장바구니 삭제");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
	}
	// basketDelete(dto)
	
	// basketDelete(id)
		public void deleteBasket(String id) {
			try {
				con = getCon();
				sql = "delete from itwill_basket where b_m_id=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.executeUpdate();
				System.out.println(" DAO : 장바구니 삭제 완료!");
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				closeDB();
			}
		}
		// basketDelete(id)
	

}
