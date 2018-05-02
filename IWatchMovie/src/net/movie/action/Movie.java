package net.movie.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.movie.db.MovieBean;
import net.movie.db.MovieDAO;

public class Movie implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Movie execute()");
		
		MovieDAO mdao=new MovieDAO();
		List<MovieBean> movieList=mdao.getMovieList();
		
		request.setAttribute("movieList", movieList);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./home/home.jsp?CMD=../movie/movie.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
