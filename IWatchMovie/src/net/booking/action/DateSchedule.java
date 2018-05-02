package net.booking.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.booking.db.BookingBean;
import net.booking.db.BookingDAO;
import net.movie.db.MovieBean;
import net.movie.db.MovieDAO;


public class DateSchedule implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("DateSchedule execute()");
		
		int mov_code = Integer.parseInt(request.getParameter("mov_code"));
		
		BookingDAO bookingDAO = new BookingDAO();
		ArrayList<BookingBean> dateList = new ArrayList<BookingBean>();
		dateList = bookingDAO.getDateSchedule(mov_code);
		
		MovieDAO movieDAO = new MovieDAO();
		MovieBean movieBean = movieDAO.getMovie(mov_code);
		
		HttpSession session = request.getSession();
		session.setAttribute("mov_code", mov_code);
		request.setAttribute("movieBean", movieBean);
		request.setAttribute("dateList", dateList);
		
		ActionForward forward=new ActionForward();
		forward.setPath("./home/home.jsp?CMD=../movie/booking.jsp?CMD2=./date_schedule.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
