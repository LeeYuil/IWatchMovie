<%@page import="net.member.db.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width" , initial-scale="1">
<link rel="stylesheet" href="../css/bootstrap.css">
<title>I Watch Movie</title>
</head>
<body>

	<form method="post" action="./MemberDeleteAction.me">
		<table border="1">
			<tr>
				<td colspan="2">
					<h3>È¸¿ø Å»Åð</h3>
				</td>
			</tr>
			<tr>
				<td>¾ÆÀÌµð : </td>
				<td><input type="text" name="member_id" ></td>
			</tr>
			<tr>
				<td>ºñ¹Ð¹øÈ£ : </td>
				<td><input type="password" name="member_pass" ></td>
			</tr>
			<tr>
				<td><input type="submit" value="È¸¿ø Å»Åð"></td>
			</tr>
		</table>
	</form>

</body>
</html>