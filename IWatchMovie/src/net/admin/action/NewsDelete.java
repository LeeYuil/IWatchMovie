package net.admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.news.db.NewsDAO;

public class NewsDelete implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("NewsDeleteAction execute()");
		
		int news_code = Integer.parseInt(request.getParameter("news_code"));
		
		NewsDAO newsDAO = new NewsDAO();
		newsDAO.deleteNews(news_code);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./News.ad");
		forward.setRedirect(true);
		return forward;
	}

}
