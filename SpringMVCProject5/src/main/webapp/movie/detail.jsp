<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.row {
   margin: 0px auto;
   width:800px;
}
h1 {
  text-align: center;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<h1 class="text-center">&lt;${vo.title }&gt; 상세보기</h1>
			<table class="table">
				<tr>
					<td width="30%" rowspan="5" class="text-center">
						<img alt="" src="${vo.poster }" width="100%">
					</td>
					<td width="70%">${vo.director }</td>
				</tr>
				<tr>
					<td width="70%">${vo.actor }</td>
				</tr>
				<tr>
					<td width="70%">${vo.genre }</td>
				</tr>
				<tr>
					<td width="70%">${vo.grade }</td>
				</tr>
				
				<tr>
					<td colspan="2">
						${vo.story }
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>