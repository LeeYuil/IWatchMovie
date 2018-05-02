package net.admin.action;



import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.admin.db.AdminDAO;

public class ScheduleDelete implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("ScheduleDelete execute()");
		
		int wat_code = Integer.parseInt(request.getParameter("wat_code"));
		
		AdminDAO adminDAO = new AdminDAO();
		adminDAO.deleteSchedule(wat_code);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./ScheduleList.ad");
		forward.setRedirect(true);
		return forward;
	}

}
