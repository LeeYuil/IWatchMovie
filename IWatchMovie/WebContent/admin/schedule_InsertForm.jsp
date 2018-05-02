<%@page import="net.admin.db.AdminDAO"%>
<%@page import="net.admin.db.AdminBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<style>
		table#t1 {
			text-align: center;
		}
	</style>
</head>
<body>
<%
	request.setCharacterEncoding("utf-8");


%>
<div id="scheduleInsert">
	<h1> 상영일정 등록 </h1>
	
	<form action="./ScheduleInsertAction.ad" method="post">
	
		<table border="1px" align="center" id="t1">
			<tr>
				<td colspan="2">
					<h3>상영 일정 등록</h3>
				</td>
			</tr>
			<tr>
				<td>상영관</td>
				<td>
					<select name="hal_code">
							 <option value="1">4DX관</option>
							 <option value="2">SCREENX관</option>
							 <option value="3">BUSINESS관</option>
							 <option value="4">04관</option>
							 <option value="5">ARTI관</option>
							 <option value="6">06관</option>
							 <option value="7">07관</option>
							 <option value="8">08관</option>
							 <option value="9">09관</option>
							 <option value="10">ART2관</option>
							 <option value="11">IMAX관</option>
							 <option value="12">12관</option>
					</select>

				</td>
			</tr>
			<tr>
				<td>영화</td>
				<td>
					<input type="text" name="mov_title" placeholder="영화제목을 입력하세요.">
				</td>
			</tr>
			<tr>
				<td>날짜 : </td>
				<td><input type="date" name="wat_date"></td>
			</tr>
			<tr>
				<td>상영 시작 시간 : </td>
				<td><input type="time" name="wat_sttime"></td>
			</tr>
			<tr>
				<td>예매 가격 : </td>
				<td><input type="number" name="mov_sel_pri"></td>
			</tr>
		</table>
		
		<input type="submit" value="등록">
	</form>
</div>
</body>
</html>