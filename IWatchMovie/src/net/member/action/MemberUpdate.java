package net.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.MemberBean;
import net.member.db.MemberDAO;

public class MemberUpdate implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("MemberUpdate execute()");
		
		HttpSession session = request.getSession();
		String member_id = (String) session.getAttribute("member_id");

		MemberDAO memberDAO = new MemberDAO();
		MemberBean memberBean = memberDAO.getMember(member_id);
		
		request.setAttribute("memberBean", memberBean);
		
		// ./member/member_info.jsp 이동, 이동방식 forward()
		ActionForward forward = new ActionForward();
		forward.setPath("./home/home.jsp?CMD=../member/member.jsp?CMD2=./updateForm.jsp");
		forward.setRedirect(false);
		return forward;
	}
	
}