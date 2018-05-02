package net.news.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.member.db.MemberDAO;
import net.news.db.NewsBean;
import net.news.db.NewsDAO;


public class Notice implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Notice execute()");
		
		
		NewsDAO newsDAO = new NewsDAO();
		int count = newsDAO.getNewsCount();
				
		int pageSize = 10;
		
		String pageNum = (String) request.getParameter("pageNum");
		if(pageNum == null)
		{
			pageNum = "1";
		}
		
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage-1) * pageSize +1;
		int endRow = currentPage * pageSize;
		
		ArrayList<NewsBean> noticeList = new ArrayList<NewsBean>();

		noticeList = newsDAO.getNoticeList(startRow, endRow);
		request.setAttribute("noticeList", noticeList);
		request.setAttribute("count", count);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("startRow", startRow);
		request.setAttribute("endRow", endRow);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./home/home.jsp?CMD=../news/news.jsp?CMD2=./notice.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
