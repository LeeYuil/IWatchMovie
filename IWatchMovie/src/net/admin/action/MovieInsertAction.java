package net.admin.action;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import net.movie.db.MovieBean;
import net.movie.db.MovieDAO;


public class MovieInsertAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("MovieInsertAction execute()");
		
		request.setCharacterEncoding("utf-8");
		
		String varPath=request.getRealPath("/upload");
		
		MultipartRequest multi=new MultipartRequest(request, varPath, 10*1024*1024, "utf-8",new DefaultFileRenamePolicy());
		
		String file1=multi.getFilesystemName("mov_img");
		
		MovieBean mb=new MovieBean();
		MovieDAO mdao=new MovieDAO();
		
		mb.setMov_title(multi.getParameter("mov_title"));
		mb.setMov_dir(multi.getParameter("mov_dir"));
		mb.setMov_act(multi.getParameter("mov_act"));
		mb.setGenre_code(Integer.parseInt(multi.getParameter("genre_code")));
		mb.setMov_info(multi.getParameter("mov_info"));
		mb.setMov_stday(multi.getParameter("mov_stday"));
		mb.setMov_time(Integer.parseInt(multi.getParameter("mov_time")));
		mb.setMov_sel_pri(Integer.parseInt(multi.getParameter("mov_sel_pri")));
		mb.setMov_level(multi.getParameter("mov_level"));	
		mb.setMov_img(file1);
		mdao.insertMovie(mb);
		
		ActionForward forward=new ActionForward();
		forward.setPath("./MovieList.ad");
		forward.setRedirect(true);
		return forward;

	}

}
