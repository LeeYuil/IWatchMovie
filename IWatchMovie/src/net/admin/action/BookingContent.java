package net.admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.booking.db.BookingBean;
import net.booking.db.BookingDAO;
import net.news.db.NewsBean;
import net.news.db.NewsDAO;

public class BookingContent implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("BookingContent execute()");
		
		String book_code = (String) request.getParameter("book_code");
		
		BookingDAO bookingDAO = new BookingDAO();
		BookingBean bookingBean = new BookingBean();
		bookingBean = bookingDAO.getBookingContent(book_code);
		
		request.setAttribute("bookingBean", bookingBean);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./home/home.jsp?CMD=../admin/admin.jsp?CMD2=./booking_content.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
