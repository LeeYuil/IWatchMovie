package net.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MemberLogoutAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("MemberLogoutAction execute()");
		
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		session.invalidate();
		
		
		response.setContentType("text/html;charset=UTF-8"); 
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('로그아웃 되었습니다.');");
		out.println("location.href='./Home.ho'");
		out.println("</script>");
		out.close();
		
		
		return null;
	}
	
}
