<%@page import="net.news.db.NewsBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>I Watch Movie</title>
	<script type="text/javascript">
		function delete_news(news_code) {
			if(confirm("해당 '소식 & 이벤트'를 삭제하시겠습니까?") == true) {
				location.href="./NewsDelete.ad?news_code=" + news_code;
			} else {
				return;
			}
		}
	</script>
</head>
<body>
<%
	request.setCharacterEncoding("utf-8");

	NewsBean newsBean = (NewsBean) request.getAttribute("newsBean");
%>
	<table border="1">
		<tr>
			<td>관리 번호</td>
			<td><%=newsBean.getNews_code() %></td>
			<td>게시글 분류</td>
			<td>
<%
				if(newsBean.getNews_group() == 1)
				{
%>
					공지사항
<%		
				} else if(newsBean.getNews_group() == 2)
				{
%>
					진행중인 이벤트
<%
				}
%>
			</td>
		</tr>
		<tr>
			<td>작성자</td>
			<td><%=newsBean.getMember_name() %></td>
			<td>작성(수정) 날짜</td>
			<td><%=newsBean.getNews_date() %></td>
		</tr>
		<tr>
			<td colspan="4"><%=newsBean.getNews_title() %></td>
		</tr>
		<tr>
			<td colspan="4"><%=newsBean.getNews_info() %></td>
		</tr>
		<tr>
			<td colspan="4">
				<input type="button" value="수정" onclick="location.href='./NewsUpdate.ad?news_code=<%=newsBean.getNews_code() %>'">
				<input type="button" value="삭제" onclick="delete_news(<%=newsBean.getNews_code()%>)">
				<input type="button" value="목록으로" onclick="location.href='./News.ad'">
			</td>
		</tr>
	</table>
	
</body>
</html>