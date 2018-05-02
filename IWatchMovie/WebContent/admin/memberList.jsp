<%@page import="net.member.db.MemberBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width" , initial-scale="1">
	<link rel="stylesheet" href="../css/bootstrap.css">
	<title>회원 목록</title>
	<script type="text/javascript">

	function delete_member(member_id){
		if (confirm("해당 회원과 관련된 모든 정보가 삭제 됩니다. 정말 탈퇴처리하시겠습니까?") == true) { 
			location.href="./MemberDelete.ad?member_id=" + member_id;
		} else {
			return;
		}
	}

	</script>
</head>
<body>

	<%
		List memberList = (List) request.getAttribute("memberList");
	%>

	<div class="container">
		<div class=row>

			<table class="table table-striped"
				style="text-align: center; border: 1px solid #dddddd">
				<thead>
					<tr>
						<th colspan="7"
							style="background-color: #eeeeee; text-align: center;">회원정보목록</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>아이디</td>
						<td>비밀번호</td>
						<td>이름</td>
						<td>전화번호</td>
						<td>선호장르 코드</td>
						<td>회원 등급</td>
						<td>탈퇴 처리</td>
					</tr>
					<%
						for (int i = 0; i < memberList.size(); i++) {
							MemberBean mb = (MemberBean) memberList.get(i);
					%>
					<tr>
						<td><%=mb.getMember_id()%></td>
						<td><%=mb.getMember_pass()%></td>
						<td><%=mb.getMember_name()%></td>
						<td><%=mb.getMember_tel()%></td>
						<td><%=mb.getGenre_code()%></td>
						<td>
							<%if(mb.getMan_grade().equals("1")) {%>일반회원<%}
							else if(mb.getMan_grade().equals("2")) {%>관리자<%}%>
						</td>
						<td>
							 <input type="button" value="탈퇴처리" onclick="delete_member('<%=mb.getMember_id()%>')" class="btn btn-primary" />
						</td>
					</tr>
					<%
						}
					%>
				</tbody>
			</table>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.1.1.min.js">
		
	</script>
	<script src="js/bootstrap.js">
		
	</script>
</body>
</html>
