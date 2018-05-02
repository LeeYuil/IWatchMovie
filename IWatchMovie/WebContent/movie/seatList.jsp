<%@page import="net.booking.db.BookingBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");

	ArrayList<BookingBean> seatList = (ArrayList) request.getAttribute("seatList");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>I Watch Movie</title>
	<style type="text/css">
		div#seat_main
	 	{
	 		width: 1000px;
	 		float: right;
	 	}
	 	
	 	div#seat_content { 
			width: 50px;
			height: 40px;
			font-size: 20px;
			text-align: center;
	 		float: left;
	 	}
	</style>
</head>
<body>	

	<div id="seat_main">
<%
		for(int i=0;i<seatList.size();i++)
		{
%>	
			<div id="seat_content">
				<input type="button" value="<%=seatList.get(i).getSeat_name() %>">
			</div>
<%
		}
%>
	</div>
	
</body>
</html>