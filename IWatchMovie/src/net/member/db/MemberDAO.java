package net.member.db;

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


public class MemberDAO {
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
	
	public int idCheck(String member_id,String member_pass)
	{
		String sql = "select * from member where member_id=?";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member_id);
			rs = pstmt.executeQuery();
			
			// rs 첫행이동 데이터 있으면 아이디 중복 check=1 / 없으면 아이디 중복아님 check = 0
			if(rs.next())
			{
				if(member_pass.equals(rs.getString("member_pass")))
				{
					return 1;
				}
				else
				{
					return 0;
				}
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
	
	public String getName(String member_id)
	{
		String member_name = null;
		String sql = "select member_name from member where member_id=?";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member_id);
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				member_name = rs.getString(1);
				return member_name;
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
		
		return null;
	}
	
	public void updateMember(MemberBean memberBean,String member_id)
	{
		sql = "update member set member_pass=?, member_name=?,member_tel=?,genre_code=? where member_id=?";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberBean.getMember_pass());
			pstmt.setString(2, memberBean.getMember_name());
			pstmt.setString(3, memberBean.getMember_tel());
			pstmt.setInt(4, memberBean.getGenre_code());
			pstmt.setString(5, member_id);
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
	
	public int deleteMember(String member_id,String member_pass)
	{
		int check=-1;
		
		try{
			//1단계 드라이버 로더
			//2단계 디비연결
			con= getConnection()
					;
			//3단계 sql 조건 id에 해당하는 회원정보 가져오기
			sql="select * from member where member_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, member_id);
			//4단계 rs = 실행결과저장 
			rs=pstmt.executeQuery();
			
			//5단계 rs첫행이동 데이터 있으면 아이디있음
			//           비밀번호비교 맞으면 check=1
			//           3단계 update name수정  조건 id이면  4단계 실행
			//                     틀리면 check=0
			//             데이터 없으면 아이디없음 check=-1
			if(rs.next())
			{
				if(member_pass.equals(rs.getString("member_pass")))
				{
					check=1;
					//3 delete
					sql="delete from member where member_id=?";
					pstmt=con.prepareStatement(sql);
					pstmt.setString(1, member_id);
					//4 실행
					pstmt.executeUpdate();
				} else {
					//틀리면 check=0
					check=0;
				}
				
			}else
			{
				//데이터 없으면 아이디없음 check=-1
				check=-1;
			}
			
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)try{con.close();}catch(SQLException ex){}
		}
		
		return check;
	}
	
	public MemberBean getMember(String member_id)
	{
		String sql = "select * from member where member_id=?";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member_id);
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				MemberBean memberBean = new MemberBean();
				memberBean.setMember_id(rs.getString("member_id"));
				memberBean.setMember_pass(rs.getString("member_pass"));
				memberBean.setMember_name(rs.getString("member_name"));
				memberBean.setMember_tel(rs.getString("member_tel"));
				memberBean.setGenre_code(rs.getInt("genre_code"));
				memberBean.setMan_grade(rs.getString("man_grade"));
				return memberBean;
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
		
		return null;
	}

	public int getMemberGrade(String member_id)
	{
		sql = "select man_grade from member where member_id=?";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member_id);
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				if(rs.getInt("man_grade") == 1)
				{
					return 1;
				} else if (rs.getInt("man_grade") == 2)
				{
					return 2;
				}
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

}
