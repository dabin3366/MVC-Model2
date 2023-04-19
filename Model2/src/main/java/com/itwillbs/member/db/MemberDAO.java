package com.itwillbs.member.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;



public class MemberDAO {
	// 공통변수
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = "";
	
	// 공통기능
	// 1) 디비연결 - 커넥션풀
	private Connection getCon() throws Exception{
		
		// Context 객체(project) 생성
		Context initCTX = new InitialContext();
		// 디비정보 연동
		DataSource ds
		      = (DataSource)initCTX.lookup("java:comp/env/jdbc/model2");
		con = ds.getConnection();
		
		return con;
	}
	
	// 2) 자원해제
	public void closeDB() {
		try {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(con != null) con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//  회원가입 - insertMember(dto)
	public void insertMember(MemberDTO dto) {
		try {
			// 1,2 디비연결
			con = getCon();
			// 3 sql 작성 & pstmt 객체
			sql = "insert into itwill_member(id,pw,name,gender,age,email,regdate) "
					+ " values(?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			// ???
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPw());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getGender());
			pstmt.setInt(5, dto.getAge());
			pstmt.setString(6, dto.getEmail());
			pstmt.setTimestamp(7, dto.getRegdate());
			
			// 4 sql 실행
			pstmt.executeUpdate();
			
			System.out.println("DAO : 회원가입 성공! ");
			
		} catch (Exception e) {
			System.out.println("DAO : 회원가입 실패! ");
			e.printStackTrace();
		}finally {
			closeDB();
		}
	}
	//  회원가입 - insertMember(dto)
	// 로그인
	public int loginMember(MemberDTO dto) {
		int result = -1;
		
		try {
			// 1. 드라이버 로드
			// 2. 디비연결
			con = getCon();
			// 3. SQL 작성(select) & pstmt객체
			String sql = "select pw from itwill_member where id=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			// 4. SQL 실행
			rs = pstmt.executeQuery();
			// 5. 데이터처리
			if(rs.next()) {
				if(dto.getPw().equals(rs.getString("pw"))) {
					// 본인
					result = 1;
				}else {
					// 비밀번호 오류
					result = 0;
				}
			}else {
				// 비회원(아이디 정보없음)
				result = -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
		
		return result;
		
	}
	// 로그인
	
	// 회원정보 조회-시작
		public MemberDTO getMemberInfo(String id)  {
			MemberDTO dto = null;
			// 디비 -> 회원정보 가져오기
			try {
				// 1. 드라이버 로드
				// 2. 디비연결
				con = getCon();
				// 3. SQL작성(select) & pstmt 객체
				sql = "select id,name,gender,age,email,regdate from itwill_member where id=?";
				pstmt = con.prepareStatement(sql);
			
				pstmt.setString(1, id);
				// 4. SQL 실행
				rs = pstmt.executeQuery();
				// 5. 데이터 처리
				// 화면에 출력X -> 출력정보 저장 (리턴)
				if(rs.next()){
					dto = new MemberDTO();
					dto.setId(rs.getString("id"));
					dto.setName(rs.getString("name"));
					dto.setGender(rs.getString("gender"));
					dto.setAge(rs.getInt("age"));
					dto.setEmail(rs.getString("email"));
					dto.setRegdate(rs.getTimestamp("regdate"));
				}
				
				System.out.println(" DAO : 회원정보 조회 성공! ");
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				closeDB();
			}
			
			return dto;
		}
	// 회원정보 조회
		
	// 회원정보 수정
	public int updateMember(MemberDTO dto) {
	    // 1. 드라이버 로드
	    // 2. 디비 연결
		int result = -1;
		try {
			con = getCon();
			// 3. SQL 작성(select) & pstmt 객체
	    		sql = "select pw from itwill_member where id=?";
	    		pstmt = con.prepareStatement(sql);
	    		
	    		// 4. sql 실행
	    		pstmt.setString(1, dto.getId());
	    		rs = pstmt.executeQuery();
	    		if(rs.next()){
	    			
	    			if(dto.getPw().equals(rs.getString("pw"))) {
	    				//3 sql (수정)
	    				sql = "update itwill_member set name=?,age=?,email=?,gender=? where id=?";
	    				pstmt = con.prepareStatement(sql);
	    				
	    				pstmt.setString(1, dto.getName());
	    				pstmt.setInt(2, dto.getAge());;
	    				pstmt.setString(3, dto.getEmail());
	    				pstmt.setString(4, dto.getGender());
	    				pstmt.setString(5, dto.getId());
	    				
	    				result = pstmt.executeUpdate();
	    			}else {
	    				result = 0;
	    			}
	    		}else {
	    			result = -1;
	    		}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
		return result;
	}
	// 회원정보 수정
	
}
