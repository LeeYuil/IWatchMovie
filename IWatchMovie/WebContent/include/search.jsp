<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<section class="tour section-wrapper container">
	
	<h2 class="section-title">Find a Movie</h2>
		
	<p class="section-subtitle">
		Please Input Keyword
	</p>
	
	<div class="row">
		<form action="./MovieSearchAction.mo" method="post" id="sr1" name="sr1">	
			<div class="col-md-3 col-sm-6">				
				<div class="form-dropdown">
				
					<label for="sel1">
						Select list (select one):
					</label> 
					
					<select	class="form-control border-radius" id="sel1" name="sel1">
						<option value="0">장르를 선택하세요</option>
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
					
				</div>
			</div>
		
			<div class="col-md-3 col-sm-6">
				<div class="input-group">
					<input type="text" class="form-control border-radius border-right" placeholder="영화 제목을 입력하세요." id="keyword" name="keyword"/> 
					<span class="input-group-addon border-radius custom-addon"> </span>
				</div>
			</div>
		
			<div class="col-md-3 col-sm-6"> 
				<div class="btn btn-default border-radius custom-button" onclick="document.sr1.submit()"> 
					검색
				</div> 
			</div> 
		</form>
	</div>
	
</section>	
	
</body>
</html>