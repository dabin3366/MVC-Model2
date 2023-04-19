package com.itwillbs.admin.order.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.itwillbs.order.db.OrderDTO;


public class AdminOrderDAO {
	// 공통 변수 선언
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "";

	// 디비연결 메서드
	private Connection getCon() throws Exception {
		// 드라이버로드 + 디비연결 => 커넥션풀

		// JNDI(Java Naming and Directory Interface) API 사용
		// -> 특정 자원(파일/데이터)에 접근하기위한 기술을 제공한다
		// Context 객체 생성
		Context initCTX = new InitialContext();
		// context.xml 파일에 있는 리소스 정보를 불러오기
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
	
	// getAdminOrderList()
	public List getAdminOrderList() {
		List adminOrderList = new ArrayList();				
		
		try {
			con = getCon();
			
			sql = "select o_trade_num,o_g_name,o_g_amount,o_g_size,"
					+ "o_g_color,sum(o_sum_money) as o_sum_money,"
					+ "o_trans_num,o_date,o_status,o_trade_type,o_m_id "
					+ "from itwill_order "
					+ "group by o_trade_num order by o_trade_num desc";
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				OrderDTO dto = new OrderDTO();
				dto.setO_trade_num(rs.getString("o_trade_num"));
				dto.setO_g_name(rs.getString("o_g_name"));
				dto.setO_g_amount(rs.getInt(3));
				dto.setO_g_size(rs.getString(4));
				dto.setO_g_color(rs.getString(5));
				dto.setO_sum_money(rs.getInt(6));
				dto.setO_trans_num(rs.getString(7));
				dto.setO_date(rs.getDate(8));
				dto.setO_status(rs.getInt(9));
				dto.setO_trade_type(rs.getString(10));
				dto.setO_m_id(rs.getString(11));
				
				adminOrderList.add(dto);
			}//while
			
			System.out.println(" DAO : 주문 정보 저장완료");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return adminOrderList;
	}	
	// getAdminOrderList()
	
	// getAdminOrderDetail(trade_num)
	public List getAdminOrderDetail(String trade_num) {
		List adminOrderDetailList = new ArrayList();
		try {
			con = getCon();
			sql="select * from itwill_order where o_trade_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, trade_num);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				OrderDTO dto = new OrderDTO();
				dto.setO_date(rs.getDate("o_date"));
				dto.setO_g_name(rs.getString("o_g_name"));
				dto.setO_g_amount(rs.getInt("o_g_amount"));
				dto.setO_g_size(rs.getString("o_g_size"));
				dto.setO_g_color(rs.getString("o_g_color"));
				dto.setO_trade_num(rs.getString("o_trade_num"));
				dto.setO_trans_num(rs.getString("o_trans_num"));
				dto.setO_sum_money(rs.getInt("o_sum_money"));
				dto.setO_status(rs.getInt("o_status"));
				dto.setO_trade_type(rs.getString("o_trade_type"));
				
				dto.setO_m_id(rs.getString("o_m_id"));
				dto.setO_r_name(rs.getString("o_r_name"));
				dto.setO_r_phone(rs.getString("o_r_phone"));
				
				adminOrderDetailList.add(dto);
			}
			System.out.println(" DAO : 주문상세페이지 정보 저장완료 ! (관리자) ");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
		
		return adminOrderDetailList;
	}
	
	// getAdminOrderDetail(trade_num)
	
	// updateOrder(dto)
	public void updateOrder(OrderDTO dto) {
		try {
			con = getCon();
			sql="update itwill_order set o_status=?,o_trans_num=? where o_trade_num=?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, dto.getO_status());
			pstmt.setString(2, dto.getO_trans_num());
			pstmt.setString(3, dto.getO_trade_num());
			
			pstmt.executeUpdate();
			
			System.out.println(" DAO : 주문상태,운송장번호 수정완료 ");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
	}
	// updateOrder(dto)
	
	
}
