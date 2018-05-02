package net.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import net.member.db.MemberBean;
import net.member.db.MemberDAO;

public class MemberDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("MemberDeleteAction execute()");


		request.setCharacterEncoding("UTF-8");
		String member_id=request.getParameter("member_id");
		String member_pass=request.getParameter("member_pass");

		//id, pass파라미터 가져오기	
		MemberDAO mdao = new MemberDAO();
		MemberBean mb = new MemberBean();
		mb.setMember_id(request.getParameter("member_id"));
		mb.setMember_pass(request.getParameter("member_pass"));
		
		int check = mdao.deleteMember(member_id, member_pass);
		//check==1아이디, 비밀번호 일치 로그인인증
				//세션값생성"id", id main.jsp 이동
				//check==0 "비밀번호틀림" 뒤로이동
				//check==-1 "아이디없음" 뒤로이동
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out=response.getWriter();
		
		if(check==1)
		{
			HttpSession session = request.getSession();
			session.invalidate();
			out.println("<script>");
			out.println("alert('회원 탈퇴 완료');");
			out.println("location.href='./Home.ho';");
			out.println("</script>");
			out.close();
			return null;
		} else if(check==0) {
			out.println("<script>");
			out.println("alert('비밀번호 틀림');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
		 	return null;
		} else
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
