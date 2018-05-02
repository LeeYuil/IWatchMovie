package net.admin.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.movie.db.MovieBean;
import net.movie.db.MovieDAO;

public class MovieSearchAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
		
		String keyword=request.getParameter("keyword");
		int sel1=Integer.parseInt(request.getParameter("sel1"));
		
		MovieDAO mdao= new MovieDAO();
		int count = mdao.getMovieSearchCount(keyword, sel1);
		List<MovieBean> movieList = mdao.searchMovie(keyword, sel1);
		
		int pageSize=10;

		String pageNum=request.getParameter("pageNum");
		System.out.println(pageNum);
		if(pageNum==null){
			pageNum="1";
		}

		int currentPage=Integer.parseInt(pageNum);
		int startRow=(currentPage-1)*pageSize+1;
		int endRow= currentPage *  pageSize;
		int pageCount=count/pageSize + (count%pageSize==0?0:1);
		int pageBlock=10;
		int startPage=((currentPage-1)/pageBlock)*pageBlock+1 ;

		int endPage=startPage+pageBlock-1;
		if(pageCount < endPage){
			endPage=pageCount;
		}
		
		
		
		request.setAttribute("movieList", movieList);
		request.setAttribute("count", count);
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
