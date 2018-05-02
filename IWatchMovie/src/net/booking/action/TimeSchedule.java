package net.booking.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.booking.db.BookingBean;
import net.booking.db.BookingDAO;
import net.movie.db.MovieBean;
import net.movie.db.MovieDAO;

public class TimeSchedule implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("TimeSchedule execute()");
		
		HttpSession session = request.getSession();
		int mov_code = (int) session.getAttribute("mov_code");
		String wat_date = (String) request.getParameter("wat_date");
		
		BookingDAO bookingDAO = new BookingDAO();
		ArrayList<BookingBean> timeList = new ArrayList<BookingBean>();
		timeList = bookingDAO.getTimeSchedule(mov_code,wat_date);
		
		MovieDAO movieDAO = new MovieDAO();
		MovieBean movieBean = movieDAO.getMovie(mov_code);
		
		session.setAttribute("wat_date", wat_date);
		request.setAttribute("movieBean", movieBean);
		request.setAttribute("timeList", timeList);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./home/home.jsp?CMD=../movie/booking.jsp?CMD3=./time_schedule.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
