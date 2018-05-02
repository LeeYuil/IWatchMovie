package net.admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.movie.db.MovieBean;
import net.movie.db.MovieDAO;

public class MovieUpdate implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		int mov_code=Integer.parseInt(request.getParameter("mov_code"));
		
		MovieDAO mdao=new MovieDAO();
		MovieBean mb=mdao.getMovie(mov_code);
		
		request.setAttribute("mb", mb);
		
		ActionForward forward=new ActionForward();
		forward.setPath("./home/home.jsp?CMD=../admin/admin.jsp?CMD2=./movie_updateForm.jsp");
		forward.setRedirect(false);
		return forward;
	}
	
}
