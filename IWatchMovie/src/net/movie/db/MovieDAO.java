package net.movie.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MovieDAO {
	
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
	
	public String getGenreName(int genre_code)
	{
		sql = "select genre_name from genre where genre_code=?";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, genre_code);
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				String genre_name = rs.getString("genre_name");
				return genre_name;
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
	
	public int getMovieCount() 
	{
		int count=0;
		try {
			//1,2 디비연결
			con=getConnection();
			//3 sql board모든 데이터 가져와서 개수 카운트
			sql="select count(*) from movie";
			pstmt=con.prepareStatement(sql);
			//4 rs <= 실행
			rs=pstmt.executeQuery();
			//5 rs 첫행이동 데이터 있으면 count=가져온개수 저장
			if(rs.next()){
				count=rs.getInt(1); //1열 데이터 가져오기
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
		return count;
	}
	
	public int getMovieSearchCount(String keyword, int sel1) 
	{
		int count=0;
		try {
			//1,2 디비연결
			con=getConnection();
			//3 sql board모든 데이터 가져와서 개수 카운트
			
			if(keyword=="")
			{
				sql = "select count(*) from movie where genre_code=? order by mov_code desc";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, sel1);
			} else if(sel1==0)
			{
				sql = "select count(*) from movie where mov_title like ? order by mov_code desc";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+keyword+"%");
			} else
			{
				sql = "select count(*) from movie where mov_dir like ? and genre_code=? order by mov_code desc";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+keyword+"%");
				pstmt.setInt(2, sel1);
			}	
			
			rs=pstmt.executeQuery();

			if(rs.next()){
				count=rs.getInt(1); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(rs!=null)
				try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)
				try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)
				try{con.close();}catch(SQLException ex){}
		}
		return count;
	}
	
	public List<MovieBean> getMovieList(int startRow, int pageSize)
	{
		List<MovieBean> movieList = new ArrayList<MovieBean>();
		
		try {
			con=getConnection();
			sql = "select 	tex.*, gen.genre_name "
				+ "from		(select rownum as rownumber, a.* "
				+ "			from	(select * "
				+ "				 	from movie "
				+ "					order by mov_code desc) a) tex, genre gen "
				+ "where	tex.genre_code=gen.genre_code "
				+ "and		rownumber>=? "
				+ "and		rownumber<=?";
			
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, pageSize);
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				MovieBean mb=new MovieBean();
				mb.setMov_code(rs.getInt("mov_code"));
				mb.setMov_title(rs.getString("mov_title"));
				mb.setMov_dir(rs.getString("mov_dir"));
				mb.setMov_act(rs.getString("mov_act"));
				mb.setGenre_code(rs.getInt("genre_code"));
				mb.setMov_info(rs.getString("mov_info"));
				mb.setMov_stday(rs.getString("mov_stday"));
				mb.setMov_time(rs.getInt("mov_time"));
				mb.setMov_level(rs.getString("mov_level"));
				mb.setMov_img(rs.getString("mov_img"));
				mb.setGenre_name(rs.getString("genre_name"));
				movieList.add(mb);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null)
				try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)
				try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)
				try{con.close();}catch(SQLException ex){}
		}
		
		return movieList;
	}
	
	public void insertMovie(MovieBean mb)
	{
		try {
			con=getConnection();
			sql="insert into movie (mov_code,mov_title,mov_dir,mov_act,genre_code,mov_info,mov_stday,mov_time,mov_level,mov_img) "
				+ "values(mov_seq.nextval,?,?,?,?,?,?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, mb.getMov_title());
			pstmt.setString(2, mb.getMov_dir());
			pstmt.setString(3,  mb.getMov_act());
			pstmt.setInt(4, mb.getGenre_code());
			pstmt.setString(5, mb.getMov_info());
			pstmt.setString(6, mb.getMov_stday());
			pstmt.setInt(7, mb.getMov_time());
			pstmt.setString(9, mb.getMov_level());
			pstmt.setString(10, mb.getMov_img());
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null){try{pstmt.close();}catch(Exception e){}}
			if(con!=null){try{con.close();}catch(Exception e){}}
		}
	}
	
	public List<MovieBean> searchMovie(String keyword, int sel1)
	{
		List<MovieBean> movieList=new ArrayList<MovieBean>();

		try{
			con=getConnection();
			
			if(keyword=="")
			{
				sql = "select * from movie where genre_code=? order by mov_code desc";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, sel1);
			} else if(sel1==0)
			{
				sql = "select * from movie where mov_title like ? order by mov_code desc";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+keyword+"%");
			} else
			{
				sql = "select * from movie where mov_dir like ? and genre_code=? order by mov_code desc";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+keyword+"%");
				pstmt.setInt(2, sel1);
			}	
			
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				MovieBean mb=new MovieBean();
				mb.setMov_code(rs.getInt("mov_code"));
				mb.setMov_title(rs.getString("mov_title"));
				mb.setMov_dir(rs.getString("mov_dir"));
				mb.setMov_act(rs.getString("mov_act"));
				mb.setGenre_code(rs.getInt("genre_code"));
				mb.setMov_info(rs.getString("mov_info"));
				mb.setMov_stday(rs.getString("mov_stday"));
				mb.setMov_time(rs.getInt("mov_time"));
				mb.setMov_level(rs.getString("mov_level"));
				mb.setMov_img(rs.getString("mov_img"));
				
				movieList.add(mb);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(rs!=null){try{rs.close();}catch(Exception e){}}
			if(pstmt!=null){try{pstmt.close();}catch(Exception e){}}
			if(con!=null){try{con.close();}catch(Exception e){}}
		}
		
		return movieList;
	}
	
	public MovieBean getMovie(int mov_code) 
	{
		MovieBean mb = new MovieBean();

		try{
			con = getConnection();
			sql = "select * from movie where mov_code=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mov_code);
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				mb.setMov_code(rs.getInt("mov_code"));
				mb.setMov_title(rs.getString("mov_title"));
				mb.setMov_dir(rs.getString("mov_dir"));
				mb.setMov_act(rs.getString("mov_act"));
				mb.setGenre_code(rs.getInt("genre_code"));
				mb.setMov_info(rs.getString("mov_info"));
				mb.setMov_stday(rs.getString("mov_stday"));
				mb.setMov_time(rs.getInt("mov_time"));
				mb.setMov_level(rs.getString("mov_level"));
				mb.setMov_img(rs.getString("mov_img"));
				mb.setGenre_name(getGenreName(rs.getInt("genre_code")));
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(rs!=null){try{rs.close();}catch(Exception e){}}
			if(pstmt!=null){try{pstmt.close();}catch(Exception e){}}
			if(con!=null){try{con.close();}catch(Exception e){}}
		}
		
		return mb;
	}
	
	public List<MovieBean> getMovieList()
	{
		List<MovieBean> movieList = new ArrayList<MovieBean>();
		sql = "select * from movie";
		
		try{
			con=getConnection();
			pstmt = con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				MovieBean mb=new MovieBean();
				mb.setMov_code(rs.getInt("mov_code"));
				mb.setMov_title(rs.getString("mov_title"));
				mb.setMov_dir(rs.getString("mov_dir"));
				mb.setMov_act(rs.getString("mov_act"));
				mb.setGenre_code(rs.getInt("genre_code"));
				mb.setMov_info(rs.getString("mov_info"));
				mb.setMov_stday(rs.getString("mov_stday"));
				mb.setMov_time(rs.getInt("mov_time"));
				mb.setMov_level(rs.getString("mov_level"));
				mb.setMov_img(rs.getString("mov_img"));
				
				movieList.add(mb);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs!=null){try{rs.close();}catch(Exception e){}}
			if(pstmt!=null){try{pstmt.close();}catch(Exception e){}}
			if(con!=null){try{con.close();}catch(Exception e){}}
		}
		
		return movieList;
	}
	
	public void updateMovie(MovieBean mb)
	{
		try{
			con=getConnection();
			String sql="select mov_code from movie where mov_code=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, mb.getMov_code());
			rs=pstmt.executeQuery();
			
			if(rs.next())
			{
				sql="update movie set mov_title=?, mov_dir=?, mov_act=?, genre_code=?, mov_info=?, mov_stday=?, mov_time=?, mov_level=?, mov_img=? "
						+ "where mov_code=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, mb.getMov_title());
				pstmt.setString(2, mb.getMov_dir());
				pstmt.setString(3,  mb.getMov_act());
				pstmt.setInt(4, mb.getGenre_code());
				pstmt.setString(5, mb.getMov_info());
				pstmt.setString(6, mb.getMov_stday());
				pstmt.setInt(7, mb.getMov_time());
				pstmt.setString(8, mb.getMov_level());
				pstmt.setString(9, mb.getMov_img());
				pstmt.setInt(10, mb.getMov_code());
				pstmt.executeUpdate();
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null){try{pstmt.close();}catch(Exception e){}}
			if(con!=null){try{con.close();}catch(Exception e){}}
		}
	}
	
	public int deleteMovie(int mov_code)
	{
		int check=0;
		
		try{
			con=getConnection();
			String sql="select mov_code from movie where mov_code=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, mov_code);
			rs=pstmt.executeQuery();
			
			if(rs.next())
			{
				check=1;
				sql="delete from movie where mov_code=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, mov_code);
				pstmt.executeUpdate();
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(rs!=null){try{rs.close();}catch(Exception e){}}
			if(pstmt!=null){try{pstmt.close();}catch(Exception e){}}
			if(con!=null){try{con.close();}catch(Exception e){}}
		}
		
		return check;
	}
	
}
