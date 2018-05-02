package net.admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.movie.db.MovieDAO;



public class MovieDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");

		int mov_code  = Integer.parseInt(request.getParameter("mov_code"));
		
		MovieDAO mdao=new MovieDAO();
		mdao.deleteMovie(mov_code);
		
		ActionForward forward=new ActionForward();
		forward.setPath("./MovieList.ad");
		forward.setRedirect(true);		
		return forward;
	}

}
