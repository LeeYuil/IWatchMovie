package net.admin.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 자바파일 => 서블릿파일 만듬(서블릿 상속)
public class AdminFrontController extends HttpServlet{
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
		
		if(command.equals("/Admin.ad"))
		{
			forward = new ActionForward();
			forward.setPath("./home/home.jsp?CMD=../admin/admin.jsp");
			forward.setRedirect(false);
		} else if(command.equals("/MemberList.ad"))
		{
			action = new MemberList();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if(command.equals("/MemberDelete.ad"))
		{
			action = new MemberDelete();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}else if(command.equals("/ScheduleList.ad"))
		{
			action = new ScheduleList();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if(command.equals("/ScheduleInsert.ad"))
		{
			forward = new ActionForward();
			forward.setPath("./home/home.jsp?CMD=../admin/admin.jsp?CMD2=./schedule_InsertForm.jsp");
			forward.setRedirect(false);
		} else if(command.equals("/ScheduleInsertAction.ad"))
		{
			action = new ScheduleInsertAction();	
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if(command.equals("/ScheduleDelete.ad"))
		{
			action = new ScheduleDelete();	
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if(command.equals("/News.ad"))
		{
			action = new News();	
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if(command.equals("/NewsContent.ad"))
		{
			action = new NewsContent();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if(command.equals("/NewsUpdate.ad"))
		{
			action = new NewsUpdate();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if(command.equals("/NewsUpdateAction.ad"))
		{
			action = new NewsUpdateAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if(command.equals("/NewsDelete.ad"))
		{
			action = new NewsDelete();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if(command.equals("/NewsInsert.ad"))
		{
			forward = new ActionForward();
			forward.setPath("./home/home.jsp?CMD=../admin/admin.jsp?CMD2=./news_InsertForm.jsp");
			forward.setRedirect(false);
		} else if(command.equals("/NewsInsertAction.ad"))
		{
			action = new NewsInsertAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}else if(command.equals("/BookingList.ad"))
		{
			action = new BookingList();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if(command.equals("/BookingContent.ad"))
		{
			action = new BookingContent();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if(command.equals("/MovieList.ad"))
		{
			action = new MovieList();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if(command.equals("/MovieInsert.ad"))
		{
			forward = new ActionForward();
			forward.setPath("./home/home.jsp?CMD=../admin/admin.jsp?CMD2=./movie_insertForm.jsp");
			forward.setRedirect(false);
		} else if(command.equals("/MovieInsertAction.ad"))
		{
			action = new MovieInsertAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if(command.equals("/MovieSearchAction.ad"))
		{
			action = new MovieSearchAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if(command.equals("/MovieUpdate.ad"))
		{
			action = new MovieUpdate();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if(command.equals("/MovieUpdateAction.ad"))
		{
			action = new MovieUpdateAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if(command.equals("/MovieDeleteAction.ad"))
		{
			action = new MovieDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if(command.equals("/ReviewGeneralList.ad"))
		{
			action = new ReviewGeneralList();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if(command.equals("/ReviewBestList.ad"))
		{
			action = new ReviewBestList();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if(command.equals("/ReviewContent.ad"))
		{
			action = new ReviewContent();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if(command.equals("/ReviewUpdateBest.ad"))
		{
			action = new ReviewUpdateBest();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if(command.equals("/ReviewDelete.ad"))
		{
			action = new ReviewDelete();
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
