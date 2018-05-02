package net.admin.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.movie.db.MovieDAO;


public class MovieList implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("MovieList execute()");
		
		MovieDAO movieDAO = new MovieDAO();

		int count = movieDAO.getMovieCount();
		int pageSize=10;

		String pageNum=request.getParameter("pageNum");
		System.out.println(pageNum);
		if(pageNum==null){
			pageNum="1";
		}

		int currentPage=Integer.parseInt(pageNum);
		int startRow=(currentPage-1)*pageSize+1;

		int endRow= currentPage *  pageSize;
		List movieList = null;
		if(count!=0){
			movieList = movieDAO.getMovieList(startRow, endRow);
		}
		
		int pageCount=count/pageSize + (count%pageSize==0?0:1);
		int pageBlock=10;
		int startPage=((currentPage-1)/pageBlock)*pageBlock+1 ;

		int endPage=startPage+pageBlock-1;
		if(pageCount < endPage){
			endPage=pageCount;
		}

		request.setAttribute("count", count);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("movieList", movieList);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		
		ActionForward forward=new ActionForward();
		forward.setPath("./home/home.jsp?CMD=../admin/admin.jsp?CMD2=./movieList.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
