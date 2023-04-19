package com.itwillbs.order.db;

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

import com.itwillbs.admin.goods.db.GoodsDTO;
import com.itwillbs.basket.db.BasketDTO;

public class OrderDAO {
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
	
	// addOrder(orDTO,basketList,goodsList)
	public void addOrder(OrderDTO orDTO,List basketList,List goodsList) {
		
		int o_num = 0; // 일련번호
		int trade_num = 0; // 주문번호 
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");		
		
		try {
			// 일련번호 계산하기 
			// 1.2 디비연결
			con = getCon();
			// 3. sql 작성 & pstmt
			sql = "select max(o_num) from itwill_order";
			pstmt = con.prepareStatement(sql);
			// 4. sql 실행
			rs = pstmt.executeQuery();
			// 5. 데이터 처리
			if(rs.next()) {
				o_num = rs.getInt(1) + 1;
			}
			System.out.println(" DAO : o_num = "+o_num);
			trade_num = o_num;
			System.out.println(" DAO : trade_num = "+trade_num);
			
			///////////////////////////////////////////////////////////////
			// 주문정보 저장
			
			for(int i=0;i<basketList.size();i++) {
				BasketDTO bkDTO = (BasketDTO)basketList.get(i);
				GoodsDTO gDTO = (GoodsDTO)goodsList.get(i);
				
				// 3.
				sql = "insert into itwill_order values(?,?,?,?,?,?,?,?,?,?,"
						+ "?,?,?,?,?,?,now(),?,now(),?)"; //? 20개 - 2 now()
				pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, o_num);
				pstmt.setString(2,  sdf.format(cal.getTime())+"-"+trade_num ); // ex)20230228-1
				pstmt.setInt(3, bkDTO.getB_g_num());
				pstmt.setString(4, gDTO.getName());
				pstmt.setInt(5, bkDTO.getB_g_amount());
				pstmt.setString(6, bkDTO.getB_g_size());
				pstmt.setString(7, bkDTO.getB_g_color());
				
				pstmt.setString(8, orDTO.getO_m_id());
				
				pstmt.setString(9, orDTO.getO_r_name());
				pstmt.setString(10, orDTO.getO_r_addr1());
				pstmt.setString(11, orDTO.getO_r_addr2());
				pstmt.setString(12, orDTO.getO_r_phone());
				pstmt.setString(13, orDTO.getO_r_msg());
				
				//summoney => 주문한 상품 금액 ( 1개당 가격 * 구매수량 )
				pstmt.setInt(14, gDTO.getPrice() * bkDTO.getB_g_amount());
				
				pstmt.setString(15, orDTO.getO_trade_type());
				pstmt.setString(16, orDTO.getO_trade_payer());
				
				pstmt.setString(17, "");// 운송장번호 trans_num
				
				// 주문상태 : 0-대기중, 1-발송준비,2-발송완료,3-배송중,4-배송완료,5-주문취소
				pstmt.setInt(18, 0); 
				
				// 4.
				pstmt.executeUpdate();
				// 주문 저장
				
				o_num++;  // 일련변호 증가
				
			}//for-끝
				
			System.out.println(" DAO : 주문정보 저장완료! ");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		
	}
	// addOrder(orDTO,basketList,goodsList)
	
	
	// getOrderList(id)
	public List getOrderList(String id) {
		List orderList = new ArrayList();				
		
		try {
			con = getCon();
			
			sql = "select o_trade_num,o_g_name,o_g_amount,o_g_size,"
					+ "o_g_color,sum(o_sum_money) as o_sum_money,"
					+ "o_trans_num,o_date,o_status,o_trade_type "
					+ "from itwill_order where o_m_id=? "
					+ "group by o_trade_num order by o_trade_num desc";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id);

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
				
				orderList.add(dto);
			}//while
			
			System.out.println(" DAO : 주문 정보 저장완료");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return orderList;
	}
	// getOrderList(id)
	
	
	// getOrderDetail(trade_num)
	public List getOrderDetail(String trade_num) {
		
		List detailList = new ArrayList();
		
		try {
			con = getCon();
			sql = "select * from itwill_order where o_trade_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, trade_num);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				// rs -> DTO -> List
				OrderDTO dto = new OrderDTO();
				
				dto.setO_date(rs.getDate("o_date"));
				dto.setO_g_amount(rs.getInt("o_g_amount"));
				dto.setO_g_color(rs.getString("o_g_color"));
				dto.setO_g_name(rs.getString("o_g_name"));
				dto.setO_g_size(rs.getString("o_g_size"));
				dto.setO_trade_num(rs.getString("o_trade_num"));
				dto.setO_trans_num(rs.getString("o_trans_num"));
				dto.setO_sum_money(rs.getInt("o_sum_money"));
				dto.setO_status(rs.getInt("o_status"));
				dto.setO_trade_type(rs.getString("o_trade_type"));
				
				detailList.add(dto);
			} //while
			
			System.out.println(" DAO  : 주문 상세페이지 정보 조회 ");			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return detailList;
	}	
	// getOrderDetail(trade_num)
	
	
	
	
	
	
	
	
}
