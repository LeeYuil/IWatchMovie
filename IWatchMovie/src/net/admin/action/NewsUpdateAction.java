package net.admin.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.news.db.NewsBean;
import net.news.db.NewsDAO;

public class NewsUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("NewsUpdateAction execute()");
		
		int news_code = Integer.parseInt(request.getParameter("news_code"));
		int news_group = Integer.parseInt(request.getParameter("news_group"));
		String news_date = (String) request.getParameter("news_date");
		String news_title = (String) request.getParameter("news_title");
		String news_info = (String) request.getParameter("news_info");
		
		NewsBean newsBean = new NewsBean();
		newsBean.setNews_code(news_code);
		newsBean.setNews_group(news_group);
		newsBean.setNews_date(news_date);
		newsBean.setNews_title(news_title);
		newsBean.setNews_info(news_info);
		
		NewsDAO newsDAO = new NewsDAO();
		newsDAO.updateNews(newsBean);
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('수정되었습니다.')");
		out.println("location.href='./News.ad'");
		out.println("</script>");
		return null;
	}

}
