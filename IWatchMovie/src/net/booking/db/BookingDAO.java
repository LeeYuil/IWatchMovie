package net.booking.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import net.admin.db.AdminBean;
import net.member.db.MemberDAO;
import net.news.db.NewsBean;
import oracle.net.aso.p;

public class BookingDAO {
	
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
	
	public int getBookingCount()
	{
		int count = 0;
		sql = "select count(*) from booking";
		
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
	
	public ArrayList<BookingBean> getBookingList(int startRow,int endRow)
	{
		ArrayList<BookingBean> bookingList = new ArrayList<BookingBean>();		
		sql = "select  * "
				+ "from    (select	a.*,rownum rnum "
				+ "			from    (select	b.book_code,b.member_id,m.mov_title,b.book_time "
				+ "					from	booking b,watch_schedule w,movie m "
				+ "					where	b.wat_code=w.wat_code "
				+ "					and 	w.mov_code=m.mov_code) a"
				+ "			where	rownum <= ?)"
				+ "where   rnum > ?";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, endRow);
			pstmt.setInt(2, startRow-1);
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				BookingBean bookingBean = new BookingBean();
				bookingBean.setBook_code(rs.getString("book_code"));
				bookingBean.setMember_id(rs.getString("member_id"));
				bookingBean.setMov_title(rs.getString("mov_title"));
				bookingBean.setBook_time(rs.getString("book_time"));
				bookingList.add(bookingBean);
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
	
		return bookingList;
	}
	
	public BookingBean getBookingContent(String book_code)
	{
		sql =  "select	b.book_code,b.member_id,b.book_time,m.mov_title,w.wat_date,w.wat_sttime,h.hal_name,s.seat_name "
				+ "	from 	booking b,movie m,watch_schedule w,hall h,reserved_seat_status rss,seat s "
				+ "	where 	b.book_code=rss.book_code "
				+ "	and 	rss.seat_code=s.seat_code "
				+ "	and 	b.wat_code=w.wat_code "
				+ "	and		w.hal_code=h.hal_code "
				+ "	and 	w.mov_code=m.mov_code "
				+ "	and 	b.book_code=?";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, book_code);
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				BookingBean bookingBean = new BookingBean();
				bookingBean.setBook_code(rs.getString("book_code"));
				bookingBean.setMember_id(rs.getString("member_id"));
				bookingBean.setBook_time(rs.getString("book_time"));
				bookingBean.setMov_title(rs.getString("mov_title"));
				bookingBean.setWat_date(rs.getString("wat_date"));
				bookingBean.setWat_sttime(rs.getString("wat_sttime"));
				bookingBean.setHal_name(rs.getString("hal_name"));
				bookingBean.setSeat_name(rs.getString("seat_name"));
				return bookingBean;
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
	
	public ArrayList<BookingBean> getDateSchedule(int mov_code)
	{
		ArrayList<BookingBean> dateList = new ArrayList<BookingBean>();
		sql = "select distinct wat_date "
				+ "from watch_schedule "
				+ "where mov_code=? "
				+ "order by wat_date";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mov_code);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				BookingBean bookingBean = new BookingBean();
				bookingBean.setWat_date(rs.getString("wat_date"));
				dateList.add(bookingBean);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (rs!= null)
				try {rs.close();} catch (SQLException ex) {}
			if (pstmt != null)
				try {pstmt.close();} catch (SQLException ex) {}
			if (con!= null)
				try {con.close();} catch (SQLException ex) {}
		}
		
		return dateList;
	}
	
	public ArrayList<BookingBean> getTimeSchedule(int mov_code,String wat_date)
	{
		ArrayList<BookingBean> dateList = new ArrayList<BookingBean>();
		sql = "select h.hal_code,h.hal_name,w.wat_sttime "
				+ "from watch_schedule w,hall h "
				+ "where w.hal_code=h.hal_code "
				+ "and mov_code=? "
				+ "and wat_date=?";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mov_code);
			pstmt.setString(2, wat_date);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				BookingBean bookingBean = new BookingBean();
				bookingBean.setHal_code(rs.getInt("hal_code"));
				bookingBean.setHal_name(rs.getString("hal_name"));
				bookingBean.setWat_sttime(rs.getString("wat_sttime"));		
				dateList.add(bookingBean);
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
		
		return dateList;
	}
	
	public ArrayList<BookingBean> getSeatList(int hal_code)
	{
		ArrayList<BookingBean> seatList = new ArrayList<BookingBean>();
		sql = "select seat_code,seat_name from seat where hal_code=?";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, hal_code);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				BookingBean bookingBean = new BookingBean();
				bookingBean.setSeat_code(rs.getInt("seat_code"));
				bookingBean.setSeat_name(rs.getString("seat_name"));	
				seatList.add(bookingBean);
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
		
		return seatList;
	}
}






