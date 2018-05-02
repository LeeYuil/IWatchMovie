<%@page import="net.news.db.NewsBean"%>
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
	<h3> 소식 관리 </h3>
	
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
			<td>관리 번호</td>
			<td>게시글 분류</td>
			<td>제목</td>
			<td>작성자</td>
			<td>작성 날짜</td>
		</tr>
<%
			ArrayList<NewsBean> newsList = new ArrayList<NewsBean>();
			newsList = (ArrayList) request.getAttribute("newsList");
			
			for(int i=0;i<newsList.size();i++)
			{
%>
		<tr>
			<td><%=newsList.get(i).getNews_code() %></td>
			<td>
<%
				if(newsList.get(i).getNews_group() == 1)
				{
%>
					공지사항
<%		
				} else if(newsList.get(i).getNews_group() == 2)
				{
%>
					진행중인 이벤트
<%
				}
%>
			</td>
			<td><a href="./NewsContent.ad?news_code=<%=newsList.get(i).getNews_code()%>"><%=newsList.get(i).getNews_title() %></a></td>
			<td><%=newsList.get(i).getMember_name() %></td>
			<td><%=newsList.get(i).getNews_date().substring(0,11) %></td>
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
			<a href="./News.ne?pageNum=<%=startPage - pageBlock %>">[이전]</a>
<%
		}
			
		for(int i=startPage;i<=endPage;i++)
		{
%>
			<a href="./News.ne?pageNum=<%=i %>">[<%=i %>]</a>
<%
		}
		
		if(endPage < pageCount)
		{
%>
			<a href="./News.ne?pageNum=<%=startPage + pageBlock %>">[다음]</a>
<%
		}
	}
%>

	<input type="button" value="소식 등록" onclick="location.href='./NewsInsert.ad'">
	
</div>
</body>
</html>