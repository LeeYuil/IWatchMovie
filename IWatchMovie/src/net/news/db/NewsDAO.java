package net.news.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import net.admin.db.AdminBean;
import net.member.db.MemberDAO;

public class NewsDAO {
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
	
	public int getNewsCount()
	{
		Connection con = null;
		int count = 0;
		sql = "select count(*) from news";
		
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
	
	public ArrayList<NewsBean> getNewsList(int startRow,int endRow)
	{
		ArrayList<NewsBean> noticeList = new ArrayList<NewsBean>();		
		sql = "select  * "
				+ "from    (select a.*,rownum rnum "
				+ "			from    (select		* "
				+ "					from        news "
				+ "					order by    news_code desc) a"
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
				NewsBean newsBean = new NewsBean();
				newsBean.setNews_code(rs.getInt("news_code"));
				newsBean.setMember_id(rs.getString("member_id"));
				newsBean.setNews_title(rs.getString("news_title"));
				newsBean.setNews_info(rs.getString("news_info"));
				newsBean.setNews_date(rs.getString("news_date"));
				newsBean.setNews_group(rs.getInt("news_group"));
				MemberDAO memberDAO = new MemberDAO();
				newsBean.setMember_name(memberDAO.getName(rs.getString(("member_id"))));
				noticeList.add(newsBean);
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
	
		return noticeList;
	}
	
	public ArrayList<NewsBean> getNoticeList(int startRow,int endRow)
	{
		ArrayList<NewsBean> noticeList = new ArrayList<NewsBean>();		
		sql = "select  * "
				+ "from    (select a.*,rownum rnum "
				+ "			from    (select		* "
				+ "					from        news "
				+ "					where       news_group = 1 "
				+ "					order by    news_code desc) a"
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
				NewsBean newsBean = new NewsBean();
				newsBean.setNews_code(rs.getInt("news_code"));
				newsBean.setMember_id(rs.getString("member_id"));
				newsBean.setNews_title(rs.getString("news_title"));
				newsBean.setNews_info(rs.getString("news_info"));
				newsBean.setNews_date(rs.getString("news_date"));
				newsBean.setNews_group(rs.getInt("news_group"));
				MemberDAO memberDAO = new MemberDAO();
				newsBean.setMember_name(memberDAO.getName(rs.getString(("member_id"))));
				noticeList.add(newsBean);
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
	
		return noticeList;
	}
	
	public ArrayList<NewsBean> getEventList(int startRow,int endRow)
	{
		ArrayList<NewsBean> evnetList = new ArrayList<NewsBean>();		
		sql = "select  * "
				+ "from    (select a.*,rownum rnum "
				+ "			from    (select		* "
				+ "					from        news "
				+ "					where       news_group = 2 "
				+ "					order by    news_code desc) a"
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
				NewsBean newsBean = new NewsBean();
				newsBean.setNews_code(rs.getInt("news_code"));
				newsBean.setMember_id(rs.getString("member_id"));
				newsBean.setNews_title(rs.getString("news_title"));
				newsBean.setNews_info(rs.getString("news_info"));
				newsBean.setNews_date(rs.getString("news_date"));
				newsBean.setNews_group(rs.getInt("news_group"));
				MemberDAO memberDAO = new MemberDAO();
				newsBean.setMember_name(memberDAO.getName(rs.getString(("member_id"))));
				evnetList.add(newsBean);
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
	
		return evnetList;
	}
	
	public NewsBean getNewsContent(int news_code)
	{
		sql = "select * from news where news_code=?";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, news_code);
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				NewsBean newsBean = new NewsBean();
				newsBean.setNews_code(rs.getInt("news_code"));
				newsBean.setMember_id(rs.getString("member_id"));
				newsBean.setNews_title(rs.getString("news_title"));
				newsBean.setNews_info(rs.getString("news_info"));
				newsBean.setNews_date(rs.getString("news_date"));
				newsBean.setNews_group(rs.getInt("news_group"));
				MemberDAO memberDAO = new MemberDAO();
				newsBean.setMember_name(memberDAO.getName(rs.getString(("member_id"))));
				
				return newsBean;
			}
		} catch (Exception e) {
			// TODO: handle exception
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
	
	public void insertNews(NewsBean newsBean)
	{
		sql = "insert into news values (news_seq.nextval,?,?,?,sysdate,?)";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, newsBean.getMember_id());
			pstmt.setString(2, newsBean.getNews_title());
			pstmt.setString(3, newsBean.getNews_info());
			pstmt.setInt(4, newsBean.getNews_group());
			pstmt.executeUpdate();
			
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
	}
	
	public void updateNews(NewsBean newsBean)
	{
		sql = "update news set news_group=?,news_date=?,news_title=?,news_info=? where news_code=?";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, newsBean.getNews_group());
			pstmt.setString(2, newsBean.getNews_date());
			pstmt.setString(3, newsBean.getNews_title());
			pstmt.setString(4, newsBean.getNews_info());
			pstmt.setInt(5, newsBean.getNews_code());
			pstmt.executeUpdate();
			
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
	}
	
	public void deleteNews(int news_code)
	{
		sql = "delete from news where news_code=?";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, news_code);
			pstmt.executeUpdate();
			
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
	}
}
