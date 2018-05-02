<%@page import="net.movie.db.MovieBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="noIE" lang="en-US">
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<meta name="viewport" content="width=device-width, initial-scale = 1.0, maximum-scale=1.0, user-scalable=no"/>
	<link rel="stylesheet" href="./assets/css/bootstrap.min.css">
	<link rel="stylesheet" href="./assets/css/ionicons.min.css">
	<link rel="stylesheet" href="./assets/css/owl.carousel.css">
	<link rel="stylesheet" href="./assets/css/owl.theme.css">
	<link rel="stylesheet" href="./assets/css/flexslider.css" type="text/css">
    <link rel="stylesheet" href="./assets/css/main.css">
    <script src="./assets/js/jquery-1.11.2.min.js"></script>
    <script src="./assets/js/bootstrap.min.js"></script>
    <script src="./assets/js/owl.carousel.min.js"></script>
    <script src="./assets/js/contact.js"></script>
    <script src="./assets/js/jquery.flexslider.js"></script>
	<script src="./assets/js/script.js"></script>
	<title>I Watch Movie</title>
</head>
<body>

	<jsp:include page="../include/search.jsp"></jsp:include>
	
	<div id="show_mov" style="margin: 0 0 0 0; position: absolute; display: none; background-color: rgba(50, 50, 50, 0.7); z-index: 1000; text-align: center;">
	
		<input type="text" id="show_mov_code" style="visibility: hidden;"><br />
		
		<img src="" id="show_mov_img" style="margin: 10px auto; display: block; max-width: 100%; cursor:pointer;" onclick="shutMov()">
		
		<div style="margin:0px auto; width:60%; text-align:center;">
			<h1 id="show_mov_title"></h1>
			<h2 id="show_mov_dir"></h2>
			<h2 id="show_mov_act"></h2>
			<h2 id="show_mov_time"></h2>
			<h3 id="show_mov_info"></h3>
			
			<input type="button" value="수정" onclick="updateMove()"> 
			<input type="button" value="삭제" onclick="deleteMove()">
		</div>

	</div> <br />
	
<%
		request.setCharacterEncoding("utf-8");
		List<MovieBean> glist = (List<MovieBean>)request.getAttribute("glist");
%>

	<div id="list_mov" style="margin: 5%; width: 90%; height: auto; box-sizing: border-box; text-align: center; display: block;">
	
		<h1>검색결과</h1>
		
<%
		for (int i = 0; i < glist.size(); i++) 
		{
			MovieBean nmb = new MovieBean();
			nmb = glist.get(i);
%>
			<div style="position: relative; margin: 1%; width: 20%; height: auto; vertical-align: middle; box-sizing: border-box; display: inline-block; background-color: rgba(25, 25, 25, 1)">
				<h1><%=nmb.getMov_title()%></h1>
				
				<input type="hidden" id="list_mov_code<%=i%>" value="<%=nmb.getMov_code()%>"> 
				<input type="hidden" id="list_mov_title<%=i%>" value="<%=nmb.getMov_title()%>">
				<input type="hidden" id="list_mov_dir<%=i%>" value="<%=nmb.getMov_dir()%>"> 
				<input type="hidden" id="list_mov_act<%=i%>" value="<%=nmb.getMov_act()%>"> 
				<input type="hidden" id="list_mov_info<%=i%>" value="<%=nmb.getMov_info()%>"> 
				<input type="hidden" id="list_mov_time<%=i%>" value="<%=nmb.getMov_time()%>">
<%
				if (nmb.getMov_img() != null) 
				{
%>
					<img src="./upload/<%=nmb.getMov_img()%>" style="display: block; max-width: 100%; cursor: pointer;" onclick="location.href='./MovieContent.mo?mov_code=<%=nmb.getMov_code() %>'"><br />
<%				
				} else 
				{ 
					%>
					<input type="button" value="수정" onclick="updateSolMove(<%=nmb.getMov_code()%>)"> 
					<input type="button" value="삭제" onclick="deleteSolMove(<%=nmb.getMov_code()%>)">
<%
				}
%>
				<br />
			</div>
<%
		} 
%>
	</div>
	
</body>
</html>