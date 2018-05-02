<%@page import="net.booking.db.BookingBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");

	ArrayList<BookingBean> timeList = (ArrayList) request.getAttribute("timeList");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>I Watch Movie</title>
	<style type="text/css">
		div#date_main
	 	{
	 		width: 670px;
	 		float: right;
	 	}
	 	
	 	div#date_content { border: 1px solid black;
			width: 100px;
			height: 80px;
			font-size: 20px;
			text-align: center;
	 		float: left;
	 	}
	</style>
</head>
<body>	

	<div id="date_main">
<%
		for(int i=0;i<timeList.size();i++)
		{
%>	
			<div id="date_content">
				<%=timeList.get(i).getHal_name() %> <br>
				<a href="./SeatList.bo?hal_code=<%=timeList.get(i).getHal_code() %>">
					<%=timeList.get(i).getWat_sttime() %>
				</a>
			</div>
<%
		}
%>
	</div>
	
</body>
</html>