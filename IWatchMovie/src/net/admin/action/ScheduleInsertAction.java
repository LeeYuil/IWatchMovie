package net.admin.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.admin.db.AdminBean;
import net.admin.db.AdminDAO;

public class ScheduleInsertAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("ScheduleInsertAction execute()");
		
		request.setCharacterEncoding("utf-8");
		
		int hal_code = Integer.parseInt(request.getParameter("hal_code"));
		String mov_title = (String) request.getParameter("mov_title");
		String wat_date = (String) request.getParameter("wat_date");
		String wat_sttime = (String) request.getParameter("wat_sttime");
		int mov_sel_pri = Integer.parseInt(request.getParameter("mov_sel_pri"));
		
		AdminDAO adminDAO = new AdminDAO();
		int mov_code = adminDAO.getMov_code(mov_title);
		
		AdminBean adminBean = new AdminBean();
		adminBean.setHal_code(hal_code);
		adminBean.setMov_code(mov_code);
		adminBean.setWat_date(wat_date);
		adminBean.setWat_sttime(wat_sttime);
		adminBean.setMov_sel_pri(mov_sel_pri);
		
		int result = adminDAO.checkMovie(mov_title);
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		if(result == 1)
		{
			adminDAO.insertSchedule(adminBean);
			out.println("<script>");
			out.println("alert('상영일정이 등록 되었습니다.')");
			out.println("location.href='./ScheduleList.ad'");
			out.println("</script>");
		} else
		{
			out.println("<script>");
			out.println("alert('정확한 영화명을 입력하세요. : "+mov_title+"')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		
		
		return null;
	}
	
}
