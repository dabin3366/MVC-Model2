package com.itwillbs.admin.goods.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class AdminGoodsDAO {
	// 공통 변수 선언
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		
		
		
		// 디비연결 메서드
		private Connection getCon() throws Exception{
			// 드라이버로드 + 디비연결 => 커넥션풀
			// JNDI(Java Naming and Directory Interface) API
			// -> 특정 자원(파일/데이터)에 접근하기위한 기술을 제공한다
			// Context 객체 생성
			Context initCTX = new InitialContext();
			// content.xml 파일에 있는 리소스 정보를 불러오기
			DataSource ds 
				= (DataSource)initCTX.lookup("java:comp/env/jdbc/model2");
			// 디비연결
			con = ds.getConnection();
			
			System.out.println(" DAO : 디비연결 성공! "+con);
			
			return con;
		}
		// 디비연결 메서드
		
		// 자원해제
		public void closeDB() {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// 자원해제
		
		// 상품 등록 메서드
		
		public void insertGoods(GoodsDTO dto) {
			int gno = 0;
			
			// 1.2 디비연결
			try {
				con = getCon();
				
				// 3. sql 작성
				sql = "select max(gno) from itwill_goods";
				pstmt = con.prepareStatement(sql);
				// 4. sql실행
				rs = pstmt.executeQuery();
				// 5.데이터 처리
				if(rs.next()) {
					gno = rs.getInt(1) +1 ;
				}
				// 3. sql 작성 (상품등록)
				sql = "insert into itwill_goods(gno,category,name,content,size,color,amount,price,image,best,date) "
						+ " values(?,?,?,?,?,?,?,?,?,?,now())";
				pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, gno);
				pstmt.setString(2, dto.getCategory());
				pstmt.setString(3, dto.getName());
				pstmt.setString(4, dto.getContent());
				pstmt.setString(5, dto.getSize());
				pstmt.setString(6, dto.getColor());
				pstmt.setInt(7, dto.getAmount());
				pstmt.setInt(8, dto.getPrice());
				pstmt.setString(9, dto.getImage());
				pstmt.setInt(10, 0); // 0 - 일반상품,  1 - 인기상품
				
				pstmt.executeUpdate();
				System.out.println(" DAO : 상품등록 완료!");
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				closeDB();
			}
		}
		// 상품 등록 메서드
		
		// 상품 목록 조회 메서드
		public List<GoodsDTO> adminGoodsList(){
			List<GoodsDTO> aGoodsList = new ArrayList<>();
			try {
				// 1.2 디비 연결
				con = getCon();
				// 3. sql 작성
				sql = "select * from itwill_goods";
				pstmt = con.prepareStatement(sql);
				// 4. sql 실행
				rs = pstmt.executeQuery();
				// 5. 데이터 처리 (rs -> DTO -> List)
				while(rs.next()) {
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
					
					aGoodsList.add(dto);
				}
				System.out.println(" DAO : 상품목록 조회 성공! ");
				System.out.println(" DAO : "+aGoodsList.size());
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				closeDB();
			}
			return aGoodsList;
		}
		// 상품 목록 조회 메서드
		
		// 특정 상품정보를 조회 - getAdminGoods(gno)
		public GoodsDTO getAdminGoods(int gno) {
			GoodsDTO dto = null;
			try {
				con = getCon();
				sql = "select * from itwill_goods where gno =?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, gno);
				rs = pstmt.executeQuery();
				if(rs.next()) {
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
				System.out.println(" DAO : 상품 조회 성공!");
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				closeDB();
			}
			
			return dto;
		}
		// 특정 상품정보를 조회 - getAdminGoods(gno)
		
		// 상품 수정 메서드 - updateGoods()
		public void updateGoods(GoodsDTO dto) {
			
			try {
				con = getCon();
				sql = "update itwill_goods set category=?,name=?,price=?,color=?,size=?,amount=?,content=?,best=? "
						+ "where gno=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, dto.getCategory());
				pstmt.setString(2, dto.getName());
				pstmt.setInt(3, dto.getPrice());
				pstmt.setString(4, dto.getColor());
				pstmt.setString(5, dto.getSize());
				pstmt.setInt(6, dto.getAmount());
				pstmt.setString(7, dto.getContent());
				pstmt.setInt(8, dto.getBest());
				pstmt.setInt(9, dto.getGno());
				pstmt.executeUpdate();
				
				System.out.println(" DAO : 상품정보 수정완료!");
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				closeDB();
			}
		}
		// 상품 수정 메서드
		
		// 상품 삭제 메서드
		
		public void deleteGoods(int gno) {
			try {
				con = getCon();
				sql = "delete from itwill_goods where gno=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, gno);
				pstmt.executeUpdate();
				
				System.out.println(" DAO : 상품 삭제 완료 !");
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				closeDB();
			}
		}
		// 상품 삭제 메서드
		
} 
