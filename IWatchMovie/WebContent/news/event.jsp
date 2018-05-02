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
	<h3> 진행중인 이벤트 </h3>
	
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
<%
			ArrayList<NewsBean> eventList = new ArrayList<NewsBean>();
			eventList = (ArrayList) request.getAttribute("eventList");
			
			for(int i=0;i<eventList.size();i++)
			{
%>
		<tr>
			<td><a href="./EventContent.ne?news_code=<%=eventList.get(i).getNews_code()%>"><%=eventList.get(i).getNews_title() %></a></td>
			<td><%=eventList.get(i).getNews_date().substring(0,11) %></td>
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
			<a href="./Notice.ne?pageNum=<%=startPage - pageBlock %>">[이전]</a>
<%
		}
			
		for(int i=startPage;i<=endPage;i++)
		{
%>
			<a href="./Notice.ne?pageNum=<%=i %>">[<%=i %>]</a>
<%
		}
		
		if(endPage < pageCount)
		{
%>
			<a href="./Notice.ne?pageNum=<%=startPage + pageBlock %>">[다음]</a>
<%
		}
	}
%>

	
</div>
</body>
</html>