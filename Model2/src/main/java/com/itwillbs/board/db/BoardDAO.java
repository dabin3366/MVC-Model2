package com.itwillbs.board.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {
	// itwill_board 테이블에 관한 모든 디비작업 처리
	
	
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
	
	// 글쓰기 - insertBoard(dto)
	public void insertBoard(BoardDTO dto) throws Exception {
		int bno = 0;
		
		// 1.2 디비연결
		con = getCon();
		// 3. SQL 작성(글번호 계산) & pstmt 객체
		String sql = "select max(bno) from itwill_board";
		PreparedStatement pstmt = con.prepareStatement(sql);
		// 4. SQL실행
		ResultSet rs = pstmt.executeQuery();
		// 5. 데이터 처리
		if(rs.next()) {
			//bno = rs.getInt("max(bno)")+1;
			bno = rs.getInt(1)+1;
		}
	//	else {
	//		bno = 1;
	//		System.out.println("else");
	//	}
		System.out.println(" bno : "+ bno);
		// 3.  SQL작성(insert) & pstmt 객체
		sql = "insert into itwill_board (bno,name,pass,subject,content,readcount,"
				+ "re_ref,re_lev,re_seq,date,ip,file) "
				+ " values(?,?,?,?,?,?,?,?,?,now(),?,?)";
		
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, bno);
		pstmt.setString(2, dto.getName());
		pstmt.setString(3, dto.getPass());
		pstmt.setString(4, dto.getSubject());
		pstmt.setString(5, dto.getContent());
		
		pstmt.setInt(6, 0); // 새 글의 조회는 항상 0
		pstmt.setInt(7, bno); // ref(일반글의 경우, 글번호 = ref값 동일)
		pstmt.setInt(8, 0); // lev (일반글은 항상 0)
		pstmt.setInt(9, 0); // seq (일반글은 항상 0)
		pstmt.setString(10, dto.getIp());
		pstmt.setString(11, dto.getFile());
		
		// 4. SQL 실행
		pstmt.executeUpdate();
		
		System.out.println(" DAO : 글쓰기 완료! ");
		
		rs.close();
		pstmt.close();
		con.close();
	}
	// 글쓰기 - insertBoard(dto)
	
	// 글 전체 목록 - getBoardListAll()
	public List getBoardListAll() {
		
		List boardList = new ArrayList();
		// ArrayList boardList2 = new ArrayList();
		
		//  디비 연결 정보
		// 1. 드라이버 로드
		// 2. 디비 연결
		try {
			con =getCon();
			// 3. sql 작성 & pstmt객체
			sql = "select * from itwill_board";
			pstmt = con.prepareStatement(sql);
			// 4. sql 실행
			rs = pstmt.executeQuery();
			// 5. 데이터 처리
			// rs(DB) -> BoardDTO -> list
			while(rs.next()) {
				// rs -> DTO
				BoardDTO dto = new BoardDTO();
				
				dto.setBno(rs.getInt("bno"));
				dto.setContent(rs.getString("content"));
				dto.setDate(rs.getDate("date"));
				dto.setFile(rs.getString("File"));
				dto.setIp(rs.getString("ip"));
				dto.setName(rs.getString("name"));
				dto.setPass(rs.getString("pass"));
				dto.setRe_lev(rs.getInt("re_lev"));
				dto.setRe_ref(rs.getInt("re_ref"));
				dto.setRe_seq(rs.getInt("re_seq"));
				dto.setReadcount(rs.getInt("readcount"));
				dto.setSubject(rs.getString("subject"));
				
				// dto -> list
				boardList.add(dto);
				
			} // while
			
			System.out.println(" DAO : 게시판 글정보 모두 저장완료 ");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		return boardList;
	}
	// 글 전체 목록 - getBoardListAll()
	
	// 글 전체 목록 - getBoardListPage()
	
	public List getBoardListPage(int startRow,int pageSize) {
		
		List boardList = new ArrayList();
		// ArrayList boardList2 = new ArrayList();
		
		//  디비 연결 정보
		// 1. 드라이버 로드
		// 2. 디비 연결
		try {
			con =getCon();
			// 3. sql 작성 & pstmt객체
			// 정렬-re_ref 내림차순, re_seq 오름차순
			// limit 시작위치,개수
			sql = "select * from itwill_board "
					+ "order by re_ref desc, re_seq asc "
					+ "limit ?,?";
			pstmt = con.prepareStatement(sql);
			
			// ???
			
			pstmt.setInt(1, startRow-1);
			pstmt.setInt(2, pageSize);
			
			// 4. sql 실행
			rs = pstmt.executeQuery();
			// 5. 데이터 처리
			// rs(DB) -> BoardDTO -> list
			while(rs.next()) {
				// rs -> DTO
				BoardDTO dto = new BoardDTO();
				
				dto.setBno(rs.getInt("bno"));
				dto.setContent(rs.getString("content"));
				dto.setDate(rs.getDate("date"));
				dto.setFile(rs.getString("File"));
				dto.setIp(rs.getString("ip"));
				dto.setName(rs.getString("name"));
				dto.setPass(rs.getString("pass"));
				dto.setRe_lev(rs.getInt("re_lev"));
				dto.setRe_ref(rs.getInt("re_ref"));
				dto.setRe_seq(rs.getInt("re_seq"));
				dto.setReadcount(rs.getInt("readcount"));
				dto.setSubject(rs.getString("subject"));
				
				// dto -> list
				boardList.add(dto);
				
			} // while
			
			System.out.println(" DAO : 게시판 글정보 모두 저장완료 ");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			// 자원해제-DB연결정보 해제
			// 사용했던 자원의 역순 해제 (안전)
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// 글 전체 목록 - getBoardListPage()
	
		
		return boardList;
	}
	
	// 글 전체 개수 조회
	/**
	 * 글 전체 개수를 조회하는 메서드
	 * @return 글 전체 개수
	 * 
	 */
	public int getBoardCount() {
		int cnt = 0;
		// 1. 드라이버로드
		// 2. 디비연결
		try {
			con = getCon();
			// 3. sql작성& pstmt 객체
			sql = "select count(*) from itwill_board";
			pstmt = con.prepareStatement(sql);
			// 4. sql 실행
			rs = pstmt.executeQuery();
			// 5. 데이터 처리
			if(rs.next()) {
				cnt = rs.getInt(1);
			}
			System.out.println("DAO : 글 전체 개수 :"+cnt);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeDB();
		}
	
		return cnt;
	}
	// 조회수 1증가 - updateReadCount(bno)
	public void updateReadCount(int bno) {
		// 1.2. 디비연결
		try {
			con = getCon();
			// 3. sql작성& pstmt 객체
			sql = "update itwill_board set readcount = readcount+1 where bno=? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bno);
			// 4. sql 실행
			pstmt.executeUpdate();
			
			System.out.println("DAO : 글 조회수 1증가완료!");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
	}
	
	// 조회수 1증가 - updateReadCount(bno)
	
	// 특정 글정보를 가져오는 메서드 - getBoard(bno)
	public BoardDTO getBoard(int bno) {
		BoardDTO dto = null;
		//BoardDTO dto = new BoardDTO();
		
		try {
			// 디비연결
			con = getCon();
			// 3. sql작성& pstmt 객체
			sql="select * from itwill_board where bno=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bno);
			// 4. sql 실행
			rs = pstmt.executeQuery();
			if(rs.next()) {
				// DB데이터 -> BoardDTO 저장
				dto = new BoardDTO();
				dto.setBno(rs.getInt("bno"));
				dto.setContent(rs.getString("content"));
				dto.setDate(rs.getDate("date"));
				dto.setFile(rs.getString("file"));
				dto.setIp(rs.getString("ip"));
				dto.setName(rs.getString("name"));
				dto.setPass(rs.getString("pass"));
				dto.setRe_lev(rs.getInt("re_lev"));
				dto.setRe_ref(rs.getInt("re_ref"));
				dto.setRe_seq(rs.getInt("re_seq"));
				dto.setReadcount(rs.getInt("readcount"));
				dto.setSubject(rs.getString("subject"));
			}
			System.out.println("DAO : 글정보 저장완료!");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
		return dto;
	}
	// 특정 글정보를 가져오는 메서드 - getBoard(bno)
	
	
	// updateBoard(dto) - 글 수정
	public int updateBoard(BoardDTO dto) {
		int result = -1; // 수정처리의 결과 저장
		//        (-1 : 에러,글이없는경우,0 : 글은 있으나 비밀번호 오류,1 : 정상수정)
		try {
			// 1.2. 디비연결
			con = getCon();
			// sql 작성(select) & pstmt 객체
			sql = "select pass from itwill_board where bno=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getBno());
			// sql 실행
			rs = pstmt.executeQuery();
			// 5. 데이터 처리 (update)
			if(rs.next()) {
				// 게시판 글 잇음
				if(dto.getPass().equals(rs.getString("pass"))) {
					// 게시판 비밀번호 O
					// 게시판 정보 수정
					//3. sql 작성(update) & pstmt 객체
					// 이름, 제목, 내용 변경
					sql ="update itwill_board set name=?,subject=?,content=? where bno=? and pass=?";
					pstmt = con.prepareStatement(sql);
					
					pstmt.setString(1, dto.getName());
					pstmt.setString(2, dto.getSubject());
					pstmt.setString(3, dto.getContent());
					pstmt.setInt(4, dto.getBno());
					pstmt.setString(5, dto.getPass());
					// 4. sql 실행
					result = pstmt.executeUpdate();			
					
					//result = 1;
				}else {
					// 게시판 비밀번호 X
					result = 0;
				}
			}else {
				// 게시판 글 없음
				// return -1; // 리턴구문은 가능하면 1개만 사용 
				result = -1;
			}
			
			System.out.println("DAO : 게시판 수정 완료 "+result);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
		return result;
	}
	// updateBoard(dto) - 글 수정
	
	
	// deleteBoard(dto) - 글 삭제
	public int deleteBoard(BoardDTO dto) {
		int result = -1;
		try {
			// 1.2. 디비연결
			con = getCon();
			// sql 작성(select) & pstmt 객체
			sql = "select pass from itwill_board where bno=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getBno());
			// sql 실행
			rs = pstmt.executeQuery();
			// 5. 데이터 처리 (delete)
			if(rs.next()) {
				// 게시판 글 잇음
				if(dto.getPass().equals(rs.getString("pass"))) {
					// 게시판 비밀번호 O
					// 게시판 정보 수정
					//3. sql 작성(update) & pstmt 객체
					// 이름, 제목, 내용 변경
					sql ="delete from itwill_board where bno=?";
					pstmt = con.prepareStatement(sql);
					
					pstmt.setInt(1, dto.getBno());
					// 4. sql 실행
					result = pstmt.executeUpdate();			
					//result = 1;
				}else {
					// 게시판 비밀번호 X
					result = 0;
				}
			}else {
				// 게시판 글 없음
				// return -1; // 리턴구문은 가능하면 1개만 사용 
				result = -1;
			}
			
			System.out.println("DAO : 게시판 글 삭제완료 "+result);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
		return result;
	}
	
	// deleteBoard(dto) - 글 삭제
	
	// deleteBoard(bno) - 글 삭제
		public int deleteBoard(int bno) {
			int result = 0;
			//1.2 디비연결
			try {
				con = getCon();
				sql = "delete from itwill_board where bno=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, bno);
				result = pstmt.executeUpdate(); // result(0,1)
				
				System.out.println(" DAO : 글 삭제 완료!");
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				closeDB();
			}
			return result;
		}
		
		// deleteBoard(bno) - 글 삭제
	
	// 답글쓰기 메서드 reWriteBoard(dto)
	public void reWriteBoard(BoardDTO dto) {
		// 1.2. 디비연결
		int bno = 0;
		try {
			con = getCon();
			// 3. sql 작성 & pstmt 객체
			//////////////////////////////////////////////////////////////////////
			// 글번호 (bno) 계산
			sql="select max(bno) from itwill_board";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				bno = rs.getInt(1)+1;
			}
			//////////////////////////////////////////////////////////////////////
			// 답글 순서 재배치(re_seq - update) : 같은 그룹에 있으면서 기존의 순서값보다 큰값이 있을경우
			sql="update itwill_board set re_seq=re_seq+1 where re_ref=? and re_seq>?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getRe_ref());
			pstmt.setInt(2, dto.getRe_seq());
			
			int result = pstmt.executeUpdate();
			
			if(result > 0) {
				System.out.println("DAO : 답글 순서 재배치 완료! ");
			}
			//////////////////////////////////////////////////////////////////////
			// 글정보 저장 (insert)
			sql = "insert into itwill_board(bno,name,pass,subject,content,readcount,re_ref,re_lev,re_seq,date,ip,file) "
					+ "values(?,?,?,?,?,?,?,?,?,now(),?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bno);
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getPass());
			pstmt.setString(4, dto.getSubject());
			pstmt.setString(5, dto.getContent());
			pstmt.setInt(6, 0);   // readcount 초기값 : 0
			////////////////답글 처리///////////////////////
			pstmt.setInt(7, dto.getRe_ref());  // 답글 re_ref == 원글 re_ref
			pstmt.setInt(8, dto.getRe_lev()+1);  // 답글 re_lev == 원글 re_lev + 1
			pstmt.setInt(9, dto.getRe_seq()+1);  // 답글 re_seq == 원글 re_seq + 1
			////////////////답글 처리///////////////////////
			pstmt.setString(10, dto.getIp());
			pstmt.setString(11, dto.getFile());
			//////////////////////////////////////////////////////////////////////
			// 4. sql 실행
			pstmt.executeUpdate();
			System.out.println("DAO : 답글작성 완료! ");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
		
	}

	// 답글쓰기 메서드 reWriteBoard(dto)
	
	// 검색 처리 메서드 - searchBoardList(search,startRow,pageSize)
	public List<BoardDTO> searchBoardList(String search,int startRow,int pageSize){
		
		List<BoardDTO> searchList = new ArrayList<>();
		try {
			// 디비연결
			con = getCon();
			// sql 작성 & pstmt 객체
			// 검색결과를 조회(like), 정렬 - re_ref 내림차순(최신글 위로)/ re_seq 오름차순
			// limit 사이즈만큼 처리
			sql = "select * from itwill_board "
					+ " where subject like ? "
					+ " order by re_ref desc, re_seq asc limit ?,? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+search+"%");
			pstmt.setInt(2, startRow-1);
			pstmt.setInt(3, pageSize);
			rs = pstmt.executeQuery();
			// 데이터 처리
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setBno(rs.getInt("bno"));
				dto.setContent(rs.getString("content"));
				dto.setDate(rs.getDate("date"));
				dto.setFile(rs.getString("file"));
				dto.setIp(rs.getString("ip"));
				dto.setName(rs.getString("name"));
				dto.setPass(rs.getString("pass"));
				dto.setRe_lev(rs.getInt("re_lev"));
				dto.setRe_ref(rs.getInt("re_ref"));
				dto.setRe_seq(rs.getInt("re_seq"));
				dto.setReadcount(rs.getInt("readcount"));
				dto.setSubject(rs.getString("subject"));
				
				searchList.add(dto);
			}
			System.out.println(" DAO : 검색결과 저장완료!");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
		
		
		
		return searchList;
	}
	
	
	
	
	// 검색 처리 메서드 - searchBoardList(search,startRow,pageSize)
	
}// DAO class
