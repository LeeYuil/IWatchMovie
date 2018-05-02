package net.admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.news.db.NewsBean;
import net.news.db.NewsDAO;
import oracle.net.aso.f;

public class NewsContent implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("NoticeContent execute()");
		
		int news_code = Integer.parseInt(request.getParameter("news_code"));
		
		NewsDAO newsDAO = new NewsDAO();
		NewsBean newsBean = new NewsBean();
		newsBean = newsDAO.getNewsContent(news_code);
		
		request.setAttribute("newsBean", newsBean);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./home/home.jsp?CMD=../admin/admin.jsp?CMD2=./news_content.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
