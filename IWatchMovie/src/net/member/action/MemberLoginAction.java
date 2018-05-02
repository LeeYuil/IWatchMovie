package net.member.action;


import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.MemberDAO;

public class MemberLoginAction implements Action {

	// Alt + Shift + s -> v
	// 메소드 오버라이딩 : 부모의 함수 재정의
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("MemberLoginAction execute()");
		
		request.setCharacterEncoding("utf-8");
		
		String member_id = (String) request.getParameter("member_id");
		String member_pass = (String) request.getParameter("member_pass");
		
		MemberDAO memberDAO = new MemberDAO();

		HttpSession session = request.getSession();
		
		int check = memberDAO.idCheck(member_id,member_pass);

		response.setContentType("text/html;charset=UTF-8"); 
		PrintWriter out = response.getWriter();
		if( check == 1 )
		{	
			session.setAttribute("member_id", member_id);
			out.println("<script>");
			out.println("alert('로그인 되었습니다.');");
			out.println("location.href='./Home.ho'");
			out.println("</script>");
			out.close();
			return null;
		}
		else if( check == 0 )
		{
			out.println("<script>");
			out.println("alert('비밀번호 틀림');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;
		}
		else
		{
			out.println("<script>");
			out.println("alert('아이디 틀림');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;
		}
		
	}
	

}
