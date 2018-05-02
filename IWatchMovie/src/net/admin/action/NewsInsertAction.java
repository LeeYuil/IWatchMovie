package net.admin.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.news.db.NewsBean;
import net.news.db.NewsDAO;

public class NewsInsertAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("NewInsertAction execute()");
		
		HttpSession session = request.getSession();
		String member_id = (String) session.getAttribute("member_id");
		
		int news_group = Integer.parseInt(request.getParameter("news_group"));
		String news_title = (String) request.getParameter("news_title");
		String news_info = (String) request.getParameter("news_info");
		
		NewsBean newsBean = new NewsBean();
		newsBean.setMember_id(member_id);
		newsBean.setNews_group(news_group);
		newsBean.setNews_title(news_title);
		newsBean.setNews_info(news_info);
		
		NewsDAO newsDAO = new NewsDAO();
		newsDAO.insertNews(newsBean);
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('해당 소식이 등록 되었습니다.')");
		out.println("location.href='./News.ad'");
		out.println("</script>");
		return null;
	}

}
