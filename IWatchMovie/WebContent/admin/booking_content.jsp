<%@page import="net.booking.db.BookingBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>I Watch Movie</title>
</head>
<body>
<%
	request.setCharacterEncoding("utf-8");

	BookingBean bookingBean = (BookingBean) request.getAttribute("bookingBean");
%>
	<table border="1">
		<tr rowspan="2">
			<h3>상세 예매 내역</h3>
		</tr>
		<tr>
			<td>예매 번호</td>
			<td><%=bookingBean.getBook_code() %></td>
		</tr>
		<tr>
			<td>예매자 ID</td>
			<td><%=bookingBean.getMember_id() %></td>
		</tr>
		<tr>
			<td>예매한 날짜(시간)</td>
			<td><%=bookingBean.getBook_time() %></td>
		</tr>
		<tr>
			<td>예매 영화</td>
			<td><%=bookingBean.getMov_title() %></td>
		</tr>
		<tr>
			<td>상영 날짜</td>
			<td><%=bookingBean.getWat_date().substring(0,11) %></td>
		</tr>
		<tr>
			<td>상영 시작 시간</td>
			<td><%=bookingBean.getWat_sttime() %></td>
		</tr>
		<tr>
			<td>상영관</td>
			<td><%=bookingBean.getHal_name() %></td>
		</tr>
		<tr>
			<td>예매 좌석</td>
			<td><%=bookingBean.getSeat_name() %></td>
		</tr>
		<tr>
			<td colspan="4">
				<input type="button" value="목록으로" onclick="location.href='./BookingList.ad'">
			</td>
		</tr>
	</table>
	
</body>
</html>