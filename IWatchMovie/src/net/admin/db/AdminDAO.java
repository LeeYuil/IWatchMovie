package net.admin.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import net.member.db.MemberBean;

public class AdminDAO {

	Connection con=null;
	String sql="";
	PreparedStatement pstmt=null;
	ResultSet rs=null;

	private Connection getConnection() throws Exception{		
		Context init=new InitialContext();
		DataSource ds=(DataSource)init.lookup("java:comp/env/jdbc/orcl");
		con=ds.getConnection();
		return con;
	}
	
	// 회원 목록 가져오기
	public List getMemberList()
	{
		List memberlist=new ArrayList();
		
		try {
			//1단계 드라이버로더
			//2단계 디비연결
			con=getConnection();
			
			//3단계 sql
			sql="select * from member";
			pstmt=con.prepareStatement(sql);
			
			//4단계 결과저장 <= 실행 
			rs=pstmt.executeQuery();
			
			//5단계 첫행(다음행)이동 데이터 있으면
			//  MemberBean mb 객체 생성
			//  mb id 변수 저장 set메서드 호출  rs 에 문자형 "id"열 가져와서 저장
			// memberList 한칸 저장  .add(자바빈주소)
			while(rs.next()){
				MemberBean mb=new MemberBean();
				mb.setMember_id(rs.getString("member_id"));
				mb.setMember_pass(rs.getString("member_pass"));
				mb.setMember_name(rs.getString("member_name"));
				mb.setMember_tel(rs.getString("member_tel"));
				mb.setGenre_code(rs.getInt("genre_code"));
				mb.setMan_grade(rs.getString("man_grade"));
				memberlist.add(mb);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//닫기
			if(rs!=null)
				try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)
				try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)
				try{con.close();}catch(SQLException ex){}
		}
		
		return memberlist;
	}
	
	public String getGenreName(int genre_code)
	{
		sql = "select genre_name from genre where genre_code = ?";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				return rs.getString("genre_name");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)try{con.close();}catch(SQLException ex){}
		}
		
		return "";
	}
	
	public void memberDelete(String member_id)
	{
		try {
			//1,2 디비연결
			con=getConnection();
			
			//3 sql id에 해당하는 회원 탈퇴 처리
			sql="delete from member where member_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, member_id);
			//4 실행
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)try{con.close();}catch(SQLException ex){}
		}
	}

	
	// 등록된 상영일정 전체 개수 반환
	public int getScheduleCount()
	{
		Connection con = null;
		int count = 0;
		sql = "select count(*) from watch_schedule";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next())
				count = rs.getInt(1);
			
			return count;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (rs!= null)
				try {rs.close();} catch (SQLException ex) {}
			if (pstmt != null)
				try {pstmt.close();} catch (SQLException ex) {}
			if(con!= null)
				try {con.close();} catch (SQLException ex) {}
		}
		return count;
	}
	
	// 상영일정 목록 (페이징 기법 사용)
	public ArrayList<AdminBean> getScheduleList(int startRow,int endRow)
	{
		ArrayList<AdminBean> scheduleList = new ArrayList<AdminBean>();		
		sql = "select  * "
				+ "from    (select a.*,rownum rnum "
				+ "			from    (select		w.wat_code,h.hal_name,m.mov_title,w.wat_date,w.wat_sttime,w.mov_sel_pri"
				+ "					from        watch_schedule w,hall h,movie m"
				+ "					where       h.hal_code=w.hal_code"
				+ "					and         w.mov_code=m.mov_code"
				+ "					order by    w.wat_code desc) a"
				+ "			where rownum <= ?)"
				+ "where   rnum > ?";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, endRow);
			pstmt.setInt(2, startRow-1);
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				AdminBean adminBean = new AdminBean();
				adminBean.setWat_code(rs.getInt("wat_code"));
				adminBean.setHal_name(rs.getString("hal_name"));
				adminBean.setMov_title(rs.getString("mov_title"));
				adminBean.setWat_date(rs.getString("wat_date"));
				adminBean.setWat_sttime(rs.getString("wat_sttime"));
				adminBean.setMov_sel_pri(rs.getInt("mov_sel_pri"));
				scheduleList.add(adminBean);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (rs!= null)
				try {rs.close();} catch (SQLException ex) {}
			if (pstmt != null)
				try {pstmt.close();} catch (SQLException ex) {}
			if(con!= null)
				try {con.close();} catch (SQLException ex) {}
		}
	
		return scheduleList;
	}
	
	// 등록된 영화제목과 일치하는지 확인
	public int checkMovie(String mov_title)
	{
		sql = "select * from movie where mov_title=?";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mov_title);
			rs = pstmt.executeQuery();
			if(rs.next())
			{
				return 1;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (rs!= null)
				try {rs.close();} catch (SQLException ex) {}
			if (pstmt != null)
				try {pstmt.close();} catch (SQLException ex) {}
			if(con!= null)
				try {con.close();} catch (SQLException ex) {}
		}
		
		return -1;
	}
	
	// 영화제목에 따른 영화코드 받아오기 
	public int getMov_code(String mov_title)
	{
		sql = "select mov_code from movie where mov_title=?";
		int mov_code = 0;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mov_title);
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				mov_code = rs.getInt("mov_code");
				return mov_code;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (rs!= null)
				try {rs.close();} catch (SQLException ex) {}
			if (pstmt != null)
				try {pstmt.close();} catch (SQLException ex) {}
			if(con!= null)
				try {con.close();} catch (SQLException ex) {}
		}
		
		return mov_code;
	}
	
	// 상영일정 등록
	public void insertSchedule(AdminBean adminBean)
	{
		sql = "insert into watch_schedule (wat_code,hal_code,mov_code,wat_date,wat_sttime,mov_sel_pri) "
				+ "values (wat_seq.nextval,?,?,?,?,?)";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, adminBean.getHal_code());
			pstmt.setInt(2, adminBean.getMov_code());
			pstmt.setString(3, adminBean.getWat_date());
			pstmt.setString(4, adminBean.getWat_sttime());
			pstmt.setInt(5, adminBean.getMov_sel_pri());
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				try {pstmt.close();} catch (SQLException ex) {}
			if(con!= null)
				try {con.close();} catch (SQLException ex) {}
		}
	}
	
	// 상영일정 삭제
	public void deleteSchedule(int wat_code)
	{
		sql = "delete from watch_schedule where wat_code=?";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, wat_code);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				try {pstmt.close();} catch (SQLException ex) {}
			if(con!= null)
				try {con.close();} catch (SQLException ex) {}
		}
	}
	
	// 일반 리뷰
	public List getReviewList()
	{
		List reviewList=new ArrayList();
		sql = "select r.rev_code, b.member_id, r.rev_title, r.rev_date "
				+ "from review r join booking b "
				+ "on r.book_code = b.book_code "
				+ "where rev_best=1 "
				+ "order by rev_code desc";
		
		try {
			con=getConnection();
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				AdminBean adminBean=new AdminBean();
				adminBean.setRev_code(rs.getInt("rev_code"));
				adminBean.setMember_id(rs.getString("member_id"));
				adminBean.setRev_title(rs.getString("rev_title"));
				adminBean.setRev_date(rs.getDate("rev_date"));
				reviewList.add(adminBean);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs!=null)
				try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)
				try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)
				try{con.close();}catch(SQLException ex){}
		}
		return reviewList;
	}
	
	// 리뷰 내용
	public AdminBean getReview(int rev_code)
	{
		sql="select r.rev_code, b.member_id, r.rev_title, r.rev_info, r.rev_date "
				+ "from review r join booking b "
				+ "on r.book_code = b.book_code "
				+ "where rev_code=?";
		try {
			con = getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, rev_code);
			rs=pstmt.executeQuery();
			
			if(rs.next())
			{
				AdminBean adminBean = new AdminBean();
				adminBean.setRev_code(rs.getInt("rev_code"));
				adminBean.setMember_id(rs.getString("member_id"));
				adminBean.setRev_title(rs.getString("rev_title"));
				adminBean.setRev_info(rs.getString("rev_info"));
				adminBean.setRev_date(rs.getDate("rev_date"));
				return adminBean;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs!=null)
				try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)
				try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)
				try{con.close();}catch(SQLException ex){}
		}
		return null;
	}
	
	public void updateReviewBest(int rev_code)
	{
		sql = "update review set rev_best=2 where rev_code=?";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, rev_code);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs!=null)
				try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)
				try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)
				try{con.close();}catch(SQLException ex){}
		}
	}
	
	public void deleteReview(int rev_code)
	{
		sql = "delete from review where rev_code=?";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, rev_code);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs!=null)
				try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)
				try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)
				try{con.close();}catch(SQLException ex){}
		}
	}
	
	public List getReviewBestList()
	{
		List reviewBestList = new ArrayList();
		sql="select r.rev_code, b.member_id, r.rev_title, r.rev_info, r.rev_date "
				+ "from review r join booking b "
				+ "on r.book_code = b.book_code "
				+ "where r.rev_best = 2 "
				+ "order by rev_code desc";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				AdminBean adminBean = new AdminBean();
				adminBean.setRev_code(rs.getInt("rev_code"));
				adminBean.setMember_id(rs.getString("member_id"));
				adminBean.setRev_title(rs.getString("rev_title"));
				adminBean.setRev_info(rs.getString("rev_info"));
				adminBean.setRev_date(rs.getDate("rev_date"));
				reviewBestList.add(adminBean);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs!=null)
				try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)
				try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)
				try{con.close();}catch(SQLException ex){}
		}
		return reviewBestList;
	}
	
	public int getRevBest(int rev_code)
	{
		int rev_best = 0;
		sql = "select rev_best from review where rev_code=?";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, rev_code);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				rev_best = rs.getInt("rev_best");
				return rev_best;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}  finally {
			if(rs!=null)
				try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)
				try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)
				try{con.close();}catch(SQLException ex){}
		}
		
		return rev_best;
	}
	
	public void insertSeatStatus() 
	{
		sql = "select max(wat_code) from watch_schedule";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				sql = "insert into seat_status_watch_schedule "
						+ "select	w.wat_code,s.seat_code,'n' "
						+ "from 	watch_schedule w,hall h,seat s "
						+ "where 	w.hal_code=h.hal_code "
						+ "and 		h.hal_code=s.hal_code "
						+ "and 		w.wat_code=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, rs.getInt("max(wat_code)"));
				pstmt.executeUpdate();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if(rs!=null)
				try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)
				try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)
				try{con.close();}catch(SQLException ex){}
		}
	}
}






