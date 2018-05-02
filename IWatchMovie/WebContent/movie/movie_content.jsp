<%@page import="net.movie.db.MovieBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	request.setCharacterEncoding("utf-8");

	MovieBean movieBean = (MovieBean) request.getAttribute("movieBean");
%>

	<table border="1" style="width: 800px; height: 700px; color: black;">
		<tr>
			<td>
				<img src="./upload/<%=movieBean.getMov_img() %>" style="width: 300px; height: 400px;">
			</td>
			<td>
				<h3><%=movieBean.getMov_title() %></h3> <br><br>
				개요 &nbsp; <%=movieBean.getGenre_name() %> | <%=movieBean.getMov_time() %>분 | <%=movieBean.getMov_stday().substring(0,11) %> 개봉 <br><br>
				감독 &nbsp; <%=movieBean.getMov_dir() %> <br><br>
				출연 &nbsp; <%=movieBean.getMov_act() %> <br><br>
				등급 &nbsp; 
<%
				switch(movieBean.getMov_level())
				{
					case "1" : 
						%> 전체 관람가 <%
						break;
					case "2" : 
						%> 12세 관람가 <%
						break;
					case "3" : 
						%> 15세 관람가 <%
						break;
					case "4" : 
						%> 청소년 관람불가 <%
						break;
						
				}				
%>					
				<br><br><br>
				
				<input type="button" value="예매하기" onclick="location.href='./DateSchedule.bo?mov_code=<%=movieBean.getMov_code()%>'">
				
			</td>
		</tr>
		<tr>
			<td colspan="2">
				줄거리 <br><br>
				
				<%=movieBean.getMov_info() %>
			</td>
		</tr>
	</table>
	
</body>
</html>