package net.admin.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.admin.db.AdminBean;
import net.admin.db.AdminDAO;

public class ScheduleList implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("WatchSchedule execute()");
		
		AdminDAO adminDAO = new AdminDAO();
		int count = adminDAO.getScheduleCount();
				
		int pageSize = 10;
		
		String pageNum = (String) request.getParameter("pageNum");
		if(pageNum == null)
		{
			pageNum = "1";
		}
		
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage-1) * pageSize +1;
		int endRow = currentPage * pageSize;
		
		ArrayList<AdminBean> scheduleList = new ArrayList<AdminBean>();

		scheduleList = adminDAO.getScheduleList(startRow,endRow);
		
		request.setAttribute("scheduleList", scheduleList);
		request.setAttribute("count", count);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("startRow", startRow);
		request.setAttribute("endRow", endRow);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./home/home.jsp?CMD=../admin/admin.jsp?CMD2=./scheduleList.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
