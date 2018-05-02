<%@page import="net.news.db.NewsBean"%>
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

	NewsBean newsBean = (NewsBean) request.getAttribute("newsBean");
%>
	<table border="1">
		<tr>
			<td>작성자</td>
			<td><%=newsBean.getMember_name() %></td>
			<td>작성 날짜</td>
			<td><%=newsBean.getNews_date() %></td>
		</tr>
		<tr>
			<!-- <td>공지 제목</td> -->
			<td colspan="4"><%=newsBean.getNews_title() %></td>
		</tr>
		<tr>
			<!-- <td>공지 내용</td> -->
			<td colspan="4"><%=newsBean.getNews_info() %></td>
		</tr>
		<tr>
			<td colspan="4">
				<input type="button" value="목록으로" onclick="location.href='./Notice.ne'">
			</td>
		</tr>
	</table>
	
</body>
</html>