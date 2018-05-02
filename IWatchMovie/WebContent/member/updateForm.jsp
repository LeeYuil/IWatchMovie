<%@page import="net.member.db.MemberBean"%>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
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
	// 한글 처리
	request.setCharacterEncoding("utf-8");

	/* String member_id = (String) request.getAttribute("member_id");
	String member_pass = (String) request.getAttribute("member_pass");
	String member_name = (String) request.getAttribute("member_name");
	String member_tel = (String) request.getAttribute("member_tel");
	int genre_code = (int) request.getAttribute("genre_code"); */
	
	MemberBean memberBean = (MemberBean) request.getAttribute("memberBean");
%>
	
	<form action="./MemberUpdateAction.me" method="post">
	
		<table border="1">
			<tr>
				<td colspan="2">
					<h3>내 정보 수정</h3>
				</td>
			</tr>
			<tr>
				<td>아이디 : </td>
				<td><%=memberBean.getMember_id() %></td>
			</tr>
			<tr>
				<td>비밀번호 : </td>
				<td><input type="password" name="member_pass" value="<%=memberBean.getMember_pass() %>"></td>
			</tr>
			<tr>
				<td>이름 : </td>
				<td><input type="text" name="member_name" value="<%=memberBean.getMember_name() %>"></td>
			</tr>
			<tr>
				<td>연락처 : </td>
				<td><input type="tel" name="member_tel" value="<%=memberBean.getMember_tel() %>"></td>
			</tr>
			<tr>
				<td>선호장르 : </td>	<!-- 임의로 값 넣음 -->
				<td>
					<input type="radio" name="genre_code" value="1" <%if(memberBean.getGenre_code() == 1) { %>checked<% } %>> 액션
					<input type="radio" name="genre_code" value="2" <%if(memberBean.getGenre_code() == 2) { %>checked<% } %>> 스릴러
					<input type="radio" name="genre_code" value="3" <%if(memberBean.getGenre_code() == 3) { %>checked<% } %>> 드라마
					<input type="radio" name="genre_code" value="4" <%if(memberBean.getGenre_code() == 4) { %>checked<% } %>> 멜로 
					<input type="radio" name="genre_code" value="5" <%if(memberBean.getGenre_code() == 5) { %>checked<% } %>> 범죄 <br>
					<input type="radio" name="genre_code" value="6" <%if(memberBean.getGenre_code() == 6) { %>checked<% } %>> 어드벤처
					<input type="radio" name="genre_code" value="7" <%if(memberBean.getGenre_code() == 7) { %>checked<% } %>> SF
					<input type="radio" name="genre_code" value="8" <%if(memberBean.getGenre_code() == 8) { %>checked<% } %>> 성인물
					<input type="radio" name="genre_code" value="9" <%if(memberBean.getGenre_code() == 9) { %>checked<% } %>> 가족
					<input type="radio" name="genre_code" value="10" <%if(memberBean.getGenre_code() == 10) { %>checked<% } %>> 공포/호러 <br>
					<input type="radio" name="genre_code" value="11" <%if(memberBean.getGenre_code() == 11) { %>checked<% } %>> 다큐멘터리
					<input type="radio" name="genre_code" value="12" <%if(memberBean.getGenre_code() == 12) { %>checked<% } %>> 코미디
					<input type="radio" name="genre_code" value="13" <%if(memberBean.getGenre_code() == 13) { %>checked<% } %>> 애니메이션
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="수정" >
				</td>
			</tr>
		</table>
		
	</form>

</body>
</html>