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
%>
	<form action="./NewsInsertAction.ad">
		
		<table border="1">
			<tr>
				<td>게시글 분류</td>
				<td>
					<select name="news_group">
							 <option value="1" >공지사항</option>
							 <option value="2" >진행중인 이벤트</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>게시글 제목</td>
				<td><input type="text" name="news_title"></td>
			</tr>
			<tr>
				<td>게시글 내용</td>
				<td><input type="text" name="news_info"></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="확인" >
					<input type="button" value="취소" onclick="location.href='./News.ad'">
				</td>
			</tr>
		</table>
		
	</form>
</body>
</html>