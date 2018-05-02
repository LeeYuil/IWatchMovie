<%@page import="net.member.db.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<script type="text/javascript">
		function warning() {
			alert("로그인이 필요한 서비스입니다.");
			history.back();
		}
	</script>
</head>
<body>

	<nav class="navbar navbar-default navbar-fixed-top">
	
	<%
		MemberDAO memberDAO = new MemberDAO();
		String member_id = (String) session.getAttribute("member_id");
		if(member_id == null)
		{
	%>
			<div id="login">
				<form action="./MemberLoginAction.me" method="post">
					<table style="color:black;" align="center" id="t1">
						<tr>
							<td>아이디 : </td>
							<td><input type="text" name="member_id"></td>
							<td>비밀번호 : </td>
							<td><input type="password" name="member_pass"></td>
							<td>
								<input type="submit" value="로그인">
								<input type="button" value="회원가입" onclick="location.href='./MemberJoin.me'">
							</td>
						</tr>
					</table>		
				</form>
			
	<%
		} else
		{
	%>
	
			<span style="color: black;"><%=memberDAO.getName(member_id) %>님 반갑습니다.</span>
			<a href="./MemberLogoutAction.me">로그아웃</a>	
	<%
		}
	%>
		</div>
		
		<div class="container">
		
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="./Home.ho" title="HOME"><i class="ion-paper-airplane"></i> euro <span>travel</span></a>
			</div> <!-- /.navbar-header -->
	
		    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-right">
					<li class="active"><a href="./Home.ho">홈</a></li>
					<li><a href="./Movie.mo">영화</a></li>
					<li><a href="./News.ne">소식 & 이벤트</a></li>
<%
				int man_grade = memberDAO.getMemberGrade(member_id);
				
				if(man_grade == -1) {
%>
					<li><a href="javascript:warning();">마이페이지</a></li>	
<%					
				}
				
				else if(man_grade == 1)
				{
%>
					<li><a href="./Member.me">마이페이지</a></li>				
<%
				} else if (man_grade == 2)
				{
%>
					<li><a href="./Admin.ad">마이페이지</a></li>
<%
				}
%>
				</ul> 
		    </div>
		    
	  	</div>
	</nav>
	
</body>
</html>