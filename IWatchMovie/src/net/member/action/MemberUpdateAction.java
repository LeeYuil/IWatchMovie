package net.member.action;

import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.MemberBean;
import net.member.db.MemberDAO;

public class MemberUpdateAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("MemberUpdateAction execute()");
		
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		String member_id = (String) session.getAttribute("member_id");
		
		MemberBean memberBean = new MemberBean();
		memberBean.setMember_id(member_id);
		memberBean.setMember_pass((String)request.getParameter("member_pass"));
		memberBean.setMember_name((String)request.getParameter("member_name"));
		memberBean.setMember_tel((String)request.getParameter("member_tel"));
		memberBean.setGenre_code(Integer.parseInt(request.getParameter("genre_code")));
		
		MemberDAO memberDAO = new MemberDAO();
		memberDAO.updateMember(memberBean,member_id);
		
		response.setContentType("text/html;charset=UTF-8"); 
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('수정되었습니다.');");
		out.println("location.href='./MemberUpdate.me'");
		out.println("</script>");
		out.close();
		
		
		return null;
	}
	
}