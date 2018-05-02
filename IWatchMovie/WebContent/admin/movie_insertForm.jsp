<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<script type="text/javascript">
		function checking() {
			var codeval = document.fr;
	
			if (codeval.mov_title.value == "") {
				alert("영화제목은 필수입니다.");
				document.getElementById("mov_title").focus();
			} else if (codeval.mov_dir.value == "") {
				alert("영화감독은 필수입니다.");
				document.getElementById("mov_dir").focus();
			} else if (codeval.mov_act.value == "") {
				alert("영화배우는 필수입니다.");
				document.getElementById("mov_act").focus();
			} else if (codeval.genre_code.value == 0) {
				alert("장르 확인바랍니다");
				document.getElementById("genre_code").focus();
			} else if (codeval.mov_info.value == "") {
				alert("영화소개는 필수입니다.");
				document.getElementById("mov_info").focus();
			} else if (codeval.mov_stday.value == "") {
				alert("상영일자는 필수입니다.");
				document.getElementById("mov_stday").focus();
			} else if (codeval.mov_time.value == "") {
				alert("상영시간은 필수입니다.");
				document.getElementById("mov_time").focus();
			} else if (codeval.mov_sel_pri.value == "") {
				alert("판매가격은 필수입니다.");
				document.getElementById("mov_sel_pri").focus();
			} else if (codeval.mov_sel_pri.value == "") {
				alert("판매가격은 필수입니다.");
				document.getElementById("mov_sel_pri").focus();
			} else if (codeval.mov_level.value == 0) {
				alert("관람등급은 필수입니다.");
				document.getElementById("mov_level").focus();
			}
	
			codeval.submit();
		}
	</script>
</head>
<body>

	<h1>영화 등록</h1>
	
	<form action="./MovieInsertAction.ad" method="post" id="fr" name="fr" enctype="Multipart/form-data">
	
		<label for="mov_title">영화제목</label>
		<input type="text" id="mov_title" name="mov_title"><br> 
		
		<label for="mov_dir">영화감독</label>
		<input type="text" id="mov_dir" name="mov_dir"><br> 
		
		<label for="mov_act">영화배우</label>
		<input type="text" id="mov_act" name="mov_act"><br> 
		
		<label for="genre_code">장르선택</label>
		<select id="genre_code" name="genre_code">
			<option value="0">선택해주세요</option>
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
		</select><br> 
		
		<label for="mov_info">영화소개</label><br>
		<textarea cols="50" rows="30" id="mov_info" name="mov_info"></textarea><br> 
		
		<label for="mov_stday">상영개시</label>
		<input type="date" id="mov_stday" name="mov_stday"><br />
		
		<label for="mov_time">상영시간</label>
		<input type="text" id="mov_time" name="mov_time"><br> 
		
		<label for="mov_level">관람등급</label>
		<select id="mov_level" name="mov_level">
			<option value="0">선택해주세요</option>
			<option value="1">전체관람가</option>
			<option value="2">12세관람가</option>
			<option value="3">15세관람가</option>
			<option value="4">청소년 관람불가</option>
		</select><br> 
		
		<label for="mov_img" style="cursor: pointer;">영화포스터</label><br>
		<input type="file" id="mov_img" name="mov_img"><br>
		<img id="mov_img_pre" /> 
		
		<input type="button" value="등록" onclick="checking()">
		<input type="reset" value="재설정">
		<div id='preview'></div>
		
	</form>
	
</body>
</html>