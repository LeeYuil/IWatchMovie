package net.home.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class HomeFrontController extends HttpServlet{
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
		
		if(command.equals("/Home.ho"))
		{
			forward = new ActionForward();
			forward.setPath("./home/home.jsp");
			forward.setRedirect(false);
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