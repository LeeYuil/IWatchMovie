package net.movie.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.movie.db.MovieBean;
import net.movie.db.MovieDAO;


public class MovieContent implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("MovieContent execute()");
		
		request.setCharacterEncoding("utf-8");
		
		int mov_code = Integer.parseInt(request.getParameter("mov_code"));
		
		MovieDAO movieDAO = new MovieDAO();
		MovieBean movieBean = movieDAO.getMovie(mov_code);
		
		request.setAttribute("movieBean", movieBean);
		
		ActionForward forward=new ActionForward();
		forward.setPath("./home/home.jsp?CMD=../movie/movie_content.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
