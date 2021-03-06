package net.member.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 자바파일 => 서블릿파일 만듬(서블릿 상속)
public class MemberFrontController extends HttpServlet{
	//메서드 오버라이딩(상속한 부모의 메서드를 재정의)
	// alt shift s   v 
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MemberFrontController doProcess()");

		String requestURI=request.getRequestURI();
		System.out.println("URI주소 : "+requestURI);
	
		String contextPath=request.getContextPath();
		System.out.println("프로젝트 경로 : "+contextPath);
		System.out.println("프로젝트 길이 : "+contextPath.length());

		String command = requestURI.substring(contextPath.length());
		System.out.println("뽑아낸 가상주소 : "+command);

		ActionForward forward=null;
		Action action=null;
		
		if(command.equals("/Member.me"))
		{
			forward = new ActionForward();
			forward.setPath("./home/home.jsp?CMD=../member/member.jsp");
			forward.setRedirect(false);
		}
		else if(command.equals("/MemberUpdate.me"))
		{
			action = new MemberUpdate();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if(command.equals("/MemberUpdateAction.me"))
		{
			action = new MemberUpdateAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if(command.equals("/MemberLoginAction.me"))
		{
			action = new MemberLoginAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if(command.equals("/MemberLogoutAction.me"))
		{
			action = new MemberLogoutAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if(command.equals("/MemberDelete.me"))
		{
			forward = new ActionForward();
			forward.setPath("./home/home.jsp?CMD=../member/member.jsp?CMD2=./deleteForm.jsp");
			forward.setRedirect(false);
		} else if(command.equals("/MemberDeleteAction.me"))
		{
			action = new MemberDeleteAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}



		if(forward!=null){
			if(forward.isRedirect()){
				response.sendRedirect(forward.getPath());
			}else{

				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MemberFrontController doGet()");
		doProcess(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MemberFrontController doPost()");
		doProcess(request, response);
	}
}
