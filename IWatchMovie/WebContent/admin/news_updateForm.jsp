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
	<form action="./NewsUpdateAction.ad">
	
		<input type="hidden" name="news_code" value="<%=newsBean.getNews_code() %>" />
	
		<table border="1">
			<tr>
				<td>관리 번호</td>
				<td><%=newsBean.getNews_code() %></td>
				<td>게시글 분류</td>
				<td>
					<select name="news_group">
							 <option value="1" <%if(newsBean.getNews_group()==1){ %> selected <%}%>>공지사항</option>
							 <option value="2" <%if(newsBean.getNews_group()==2){ %> selected <%}%>>진행중인 이벤트</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>작성자</td>
				<td><%=newsBean.getMember_name() %></td>
				<td>작성(수정) 날짜</td>
				<td><input type="date" name="news_date" value="<%=newsBean.getNews_date() %>"></td>
			</tr>
			<tr>
				<td colspan="4"><input type="text" name="news_title" value="<%=newsBean.getNews_title() %>"></td>
			</tr>
			<tr>
				<td colspan="4"><input type="text" name="news_info" value="<%=newsBean.getNews_info() %>"></td>
			</tr>
			<tr>
				<td colspan="4">
					<input type="submit" value="확인" >
					<input type="button" value="취소" onclick="location.href='./News.ad'">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>