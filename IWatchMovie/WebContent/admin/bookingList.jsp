<%@page import="net.booking.db.BookingBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>I Watch Movie</title>
</head>
<body>

<div id="scheduleList">
	<h3> 예매 관리 </h3>
	
<% 
	//한글처리
	request.setCharacterEncoding("utf-8");

 	int count = (int) request.getAttribute("count");
 	String pageNum = (String) request.getAttribute("pageNum");
 	int currentPage = (int) request.getAttribute("currentPage");
 	int startRow = (int) request.getAttribute("startRow");
 	int endRow = (int) request.getAttribute("endRow");
 	int pageSize = (int) request.getAttribute("pageSize");

%>
	
	<table border="1">
		<tr>
			<td>예매 번호</td>
			<td>예매자 ID</td>
			<td>영화</td>
			<td>예매한 시간</td>
		</tr>
<%
			ArrayList<BookingBean> bookingList = new ArrayList<BookingBean>();
			bookingList = (ArrayList) request.getAttribute("bookingList");
			
			for(int i=0;i<bookingList.size();i++)
			{
%>
		<tr>
			<td>
				<a href="./BookingContent.ad?book_code=<%=bookingList.get(i).getBook_code() %>">
					<%=bookingList.get(i).getBook_code() %>
				</a>
			</td>
			<td><%=bookingList.get(i).getMember_id() %></td>
			<td><%=bookingList.get(i).getMov_title() %></td>
			<td><%=bookingList.get(i).getBook_time() %></td>
		</tr>
<%
			}
%>
	</table>
	
<%
	if(count != 0)
	{
		int pageCount =  count / pageSize +  (count % pageSize == 0 ? 0 : 1) ;
		int pageBlock = 10;
		int startPage = ((currentPage-1) / pageBlock) * pageBlock + 1 ;
		int endPage = startPage + pageBlock -1;
		
		if(pageCount < endPage)
		{
			endPage = pageCount;
		}

		if(startPage > pageBlock)
		{
%>
			<a href="./BookingList.ad?pageNum=<%=startPage - pageBlock %>">[이전]</a>
<%
		}
			
		for(int i=startPage;i<=endPage;i++)
		{
%>
			<a href="./BookingList.ad?pageNum=<%=i %>">[<%=i %>]</a>
<%
		}
		
		if(endPage < pageCount)
		{
%>
			<a href="./BookingList.ad?pageNum=<%=startPage + pageBlock %>">[다음]</a>
<%
		}
	}
%>

	
</div>
</body>
</html>