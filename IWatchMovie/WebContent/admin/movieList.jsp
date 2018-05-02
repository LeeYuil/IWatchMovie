<%@page import="net.movie.db.MovieBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale = 1.0, maximum-scale=1.0, user-scalable=no" />
<title>I Watch Movie</title>
<script type="text/javascript">
		function detailOpen(i){
			var chdtl=document.getElementById("movdetail"+i).style.visibility;
			var chstr=document.getElementById("movstory"+i).style.visibility;
			alert(chdtl);
			if(document.getElementById("movdetail"+i).style.visibility=='hidden'){
				document.getElementById("movdetail"+i).style.visibility='visible';
				document.getElementById("movstory"+i).style.visibility='visible';
			}else{
				document.getElementById("movdetail"+i).style.visibility='hidden';
				document.getElementById("movstory"+i).style.visibility='hidden';
			}
		}
		
		function delete_movie(mov_code) {
			if(confirm("해당 영화를 삭제하시겠습니까?") == true) {
				location.href="./MovieDeleteAction.ad?mov_code=" + mov_code;
			} else {
				return;
			}
		}
	</script>
</head>
<body>

<%
		//  count , pageNum, boardList, pageCount, pageBlock
		//  startPage, endPage 가져오기
		int count = ((Integer) request.getAttribute("count")).intValue();
		String pageNum = (String) request.getAttribute("pageNum");
		List<MovieBean> movieList = (List<MovieBean>) request.getAttribute("movieList");
		int pageCount = ((Integer) request.getAttribute("pageCount")).intValue();
		int pageBlock = ((Integer) request.getAttribute("pageBlock")).intValue();
		int startPage = ((Integer) request.getAttribute("startPage")).intValue();
		int endPage = ((Integer) request.getAttribute("endPage")).intValue();
%>
	
	<form action="./MovieSearchAction.ad" method="post">
		<select class="form-control border-radius" id="sel1" name="sel1">
			<option value="0">장르를 선택해주세요</option>
			<option value="1">액션</option>
			<option value="2">스릴러</option>
			<option value="3">드라마</option>
			<option value="4">멜로/로맨스</option>
			<option value="5">범죄</option>
			<option value="6">어드벤처</option>
			<option value="7">SF</option>
			<option value="8">성인물</option>
			<option value="9">가족</option>
			<option value="10">공포/호러</option>
			<option value="11">다큐멘터리</option>
			<option value="12">코미디</option>
			<option value="13">애니메이션</option>
		</select> 
		<input type="text" id="keyword" name="keyword"> 
		<input type="submit" value="검색">
	</form>

	<table border="1">
		<tr>
			<td>제목</td>
			<td>감독</td>
			<td>비고</td>
		</tr>
<%
			for (int i = 0; i < movieList.size(); i++) {
				MovieBean bb = (MovieBean) movieList.get(i);

				String ttr="";

				switch (bb.getMov_level()) {
					case "1":
						ttr="전체관람가";
						break;
					case "2":
						ttr="12세관람가";
						break;
					case "3":
						ttr="15세관람가";
						break;
					case "4":
						ttr="성인관람가";
						break;
				}
%>
		<tr>
			<td><%=bb.getMov_title()%></td>
			<td><%=bb.getMov_dir()%></td>
			<td><input type="button" value="상세보기"
				onclick="detailOpen(<%=i%>)"></td>
		</tr>
		<tr id="movdetail<%=i%>" style="visibility: hidden;">
			<td><%=bb.getMov_sel_pri()%>원</td>
			<td><%=bb.getMov_time()%>분</td>
			<td><%=ttr%></td>
		</tr>
		<tr id="movstory<%=i%>" style="visibility: hidden;">
			<td colspan="2"><%=bb.getMov_info()%></td>
			<td>
				<input type="button" value="수정하기" onclick="location.href='MovieUpdate.ad?mov_code=<%=bb.getMov_code()%>'">
				<input type="button" value="삭제하기" onclick="delete_movie('<%=bb.getMov_code()%>')">
			</td>
		</tr>
<%
			}
%>
	</table>
	
<%
		if (count != 0) {
			if (startPage > pageBlock) {
%>
				<a href="./MovieList.ad?pageNum=<%=startPage - pageBlock%>">[이전]</a>
<%
			}
			for (int j = startPage; j <= endPage; j++) {
%>
				<a href="./MovieList.ad?pageNum=<%=j%>">[<%=j%>]</a>
<%
			}
			if (endPage < pageCount) {
%>
				<a href="./MovieList.ad?pageNum=<%=startPage+pageBlock%>">[다음]</a>
<%
			}

		}
%>

		<input type="button" value="영화 등록" onclick="location.href='./MovieInsert.ad'">

</body>
</html>