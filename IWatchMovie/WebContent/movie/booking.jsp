<%@page import="net.movie.db.MovieBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	
	MovieBean movieBean = (MovieBean) request.getAttribute("movieBean");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<style type="text/css">

	</style>
</head>
<body>

	<table border="1" style="width: 1000px; height: 700px; color: black; text-align: center;">
		<tr>
			<td>
				<img src="./upload/<%=movieBean.getMov_img() %>" style="width: 200px; height: 300px;">
			</td>
			<td>
				<h3><%=movieBean.getMov_title() %></h3> <br><br><br>
				<input type="button" onclick="location.href='./Movie.mo'" value="영화 목록으로">
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<h2>날짜 선택</h2>
<%
			String CMD2 = request.getParameter("CMD2");
			if(CMD2 == null)
			{
%>				
<%
			} else
			{
%>
				<jsp:include page="<%=CMD2 %>"></jsp:include>
<%
			}
%>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<h2>시간 선택</h2>
<%
			String CMD3 = request.getParameter("CMD3");
			if(CMD3 == null)
			{
%>				
<%
			} else
			{
%>
				<jsp:include page="<%=CMD3 %>"></jsp:include>
<%
			}
%>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<h2>좌석 선택</h2>
<%
			String CMD4 = request.getParameter("CMD4");
			if(CMD4 == null)
			{
%>				
<%
			} else
			{
%>
				<jsp:include page="<%=CMD4 %>"></jsp:include>
<%
			}
%>
			</td>
		</tr>
	</table>

	

	
	
</body>
</html>