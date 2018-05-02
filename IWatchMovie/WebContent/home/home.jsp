<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	response.setCharacterEncoding("utf-8");
	
	String CMD = request.getParameter("CMD");
	if(CMD == null)
	{
		CMD = "home_content.jsp";
	}
%> 
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
    <link rel="stylesheet" href="./assets/css/IWatchMovie.css">
    <link rel="stylesheet" href="./assets/css/front.css">
    <script src="./assets/js/jquery-1.11.2.min.js"></script>
    <script src="./assets/js/bootstrap.min.js"></script>
    <script src="./assets/js/owl.carousel.min.js"></script>
    <script src="./assets/js/contact.js"></script>
    <script src="./assets/js/jquery.flexslider.js"></script>
	<script src="./assets/js/script.js"></script>
	<title>I Watch Movie</title>
</head>
<body>

	<!-- 메인 메뉴  -->
	<header class="top">
		<jsp:include page="../include/header.jsp"/>
	</header>

	<div class="clear"></div>
	
	<div id="tableContainer">
	    <div id="tableRow">
			<!-- 페이지마다 적용될 내용 -->
			<jsp:include page="<%=CMD %>"></jsp:include>
		</div>
	</div>
	
	<div class="clear"></div>
	
	<!-- footer -->
	<jsp:include page="../include/footer.jsp"></jsp:include>

</body>
</html>