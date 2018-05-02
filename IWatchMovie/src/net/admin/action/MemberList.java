package net.admin.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.admin.db.AdminDAO;


public class MemberList implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("MemberList execute PJ()");
		
		// MemberDAO mdao 객체생성
		AdminDAO adminDAO = new AdminDAO();

		List memberList = adminDAO.getMemberList();
		
		// request <= 자바빈 mb 저장
		// request.setAttribute("이름", 값)
		request.setAttribute("memberList", memberList);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./home/home.jsp?CMD=../admin/admin.jsp?CMD2=./memberList.jsp");
		forward.setRedirect(false);
		return forward;
	}

	
}
