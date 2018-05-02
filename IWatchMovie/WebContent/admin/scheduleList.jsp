<%@page import="java.util.ArrayList"%>
<%@page import="net.admin.db.AdminBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<script type="text/javascript">

	function delete_schedule(wat_code){
		if (confirm("해당 상영일정을 삭제하시겠습니까?") == true) { 
			location.href="./ScheduleDelete.ad?wat_code=" + wat_code;
		} else {
			return;
		}
	}

	</script>

</head>
<body>

<div id="scheduleList">
	<h3> 상영일정 관리 </h3>
	
<% 
	//한글처리
	request.setCharacterEncoding("utf-8");

 	int count = (int) request.getAttribute("count");
 	String pageNum = (String) request.getAttribute("pageNum");
 	int currentPage = (int) request.getAttribute("currentPage");
 	int startRow = (int) request.getAttribute("startRow");
 	int endRow = (int) request.getAttribute("endRow");
 	int pageSize = (int) request.getAttribute("pageSize");
 	//AdminBean adminBean = (AdminBean) request.getAttribute("adminBean");

%>
	
	<table border="1">
		<tr>
			<td>관리번호</td>
			<td>상영관</td>
			<td>영화 제목</td>
			<td>날짜</td>
			<td>시작 시간</td>
			<td>예매 가격</td>
			<td>일정 삭제</td>
		</tr>
<%
			ArrayList<AdminBean> scheduleList = new ArrayList<AdminBean>();
			scheduleList = (ArrayList) request.getAttribute("scheduleList");
			
			for(int i=0;i<scheduleList.size();i++)
			{
%>
		<tr>
			<td><%=scheduleList.get(i).getWat_code() %></td>
			<td><%=scheduleList.get(i).getHal_name() %></td>
			<td><%=scheduleList.get(i).getMov_title() %></td>
			<td><%=scheduleList.get(i).getWat_date().substring(0,11) %></td>
			<td><%=scheduleList.get(i).getWat_sttime() %></td>
			<td><%=scheduleList.get(i).getMov_sel_pri() %></td>
			<td><input type="button" value="삭제" onclick="delete_schedule(<%=scheduleList.get(i).getWat_code()%>)"></td>
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
			<a href="./ScheduleList.ad?pageNum=<%=startPage - pageBlock %>">[이전]</a>
<%
		}
			
		for(int i=startPage;i<=endPage;i++)
		{
%>
			<a href="./ScheduleList.ad?pageNum=<%=i %>">[<%=i %>]</a>
<%
		}
		
		if(endPage < pageCount)
		{
%>
			<a href="./ScheduleList.ad?pageNum=<%=startPage + pageBlock %>">[다음]</a>
<%
		}
	}
%>

	<input type="button" value="상영일정 등록" onclick="location.href='./ScheduleInsert.ad'">
	
</div>
</body>
</html>