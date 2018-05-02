package net.booking.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.booking.db.BookingBean;
import net.booking.db.BookingDAO;
import net.movie.db.MovieBean;
import net.movie.db.MovieDAO;

public class SeatList implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("SeatList execute()");
		
		HttpSession session = request.getSession();
		int mov_code = (int) session.getAttribute("mov_code");
		String wat_date = (String) session.getAttribute("wat_date");
		int hal_code = Integer.parseInt(request.getParameter("hal_code"));
		
		BookingDAO bookingDAO = new BookingDAO();
		ArrayList<BookingBean> seatList = new ArrayList<BookingBean>();
		seatList = bookingDAO.getSeatList(hal_code);
		
		MovieDAO movieDAO = new MovieDAO();
		MovieBean movieBean = movieDAO.getMovie(mov_code);
		
		session.setAttribute("hal_code", hal_code);
		request.setAttribute("movieBean", movieBean);
		request.setAttribute("seatList", seatList);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./home/home.jsp?CMD=../movie/booking.jsp?CMD4=./seatList.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
