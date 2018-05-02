package net.admin.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.booking.db.BookingBean;
import net.booking.db.BookingDAO;


public class BookingList implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("BookingList execute()");
		
		BookingDAO bookingDAO = new BookingDAO();
		int count = bookingDAO.getBookingCount();
				
		int pageSize = 10;
		
		String pageNum = (String) request.getParameter("pageNum");
		if(pageNum == null)
		{
			pageNum = "1";
		}
		
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage-1) * pageSize +1;
		int endRow = currentPage * pageSize;
		
		ArrayList<BookingBean> bookingList = new ArrayList<BookingBean>();

		bookingList = bookingDAO.getBookingList(startRow,endRow);
		
		request.setAttribute("bookingList", bookingList);
		request.setAttribute("count", count);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("startRow", startRow);
		request.setAttribute("endRow", endRow);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./home/home.jsp?CMD=../admin/admin.jsp?CMD2=./bookingList.jsp");
		forward.setRedirect(false);
		return forward;
	}
	
}
