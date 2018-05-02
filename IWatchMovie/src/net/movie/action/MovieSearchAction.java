package net.movie.action;

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
		
		MovieDAO mdao=new MovieDAO();
		List<MovieBean> glist=mdao.searchMovie(keyword, sel1);
		
		request.setAttribute("glist", glist);
		
		ActionForward forward=new ActionForward();
		forward.setPath("./home/home.jsp?CMD=../movie/movieSearch.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
