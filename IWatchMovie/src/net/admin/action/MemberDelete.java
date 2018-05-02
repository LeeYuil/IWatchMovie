package net.admin.action;


import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.admin.db.AdminDAO;
import net.member.db.MemberBean;

public class MemberDelete implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Execute AdminDelete");
		
		request.setCharacterEncoding("UTF-8");
		
		
		// MemberBean mb = new MemberBean();
		AdminDAO adminDAO = new AdminDAO();
		
		String member_id = (String) request.getParameter("member_id");
		// String member_pass = request.getParameter("member_pass");

		// mb.setMember_id(request.getParameter(member_id));
		// mb.setMember_pass(request.getParameter(member_pass));

		adminDAO.memberDelete(member_id);

		ActionForward forward = new ActionForward();
		forward.setPath("./MemberList.ad");
		forward.setRedirect(true);
		return forward;
	}

}
