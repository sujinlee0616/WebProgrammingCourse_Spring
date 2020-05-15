<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<!--JSP =======> MovieController 			 =========>   DAO (오라클, 몽고DB, ...)
	find.do		 @PostMapping("find.do")	<=========	  - DB를 다른걸 써도 DAO만 바뀔 뿐 다른데는 많이 바뀌지 않음 
				  사용자 요청값을 받아서 DAO 연결		통신
				  결과값을 JSP로 전송
				   		┃
				   		↓
				   	   JSP	
  -->
	<div class="container">
		<div class="row">
			<h1 class="text-center">검색결과</h1>
			<table class="table">
				<tr>
					<td>
						<a href="list.do" class="btn btn-sm btn-danger">목록</a>
					</td>
				</tr>
			</table>
			<c:forEach var="vo" items="${list }">
			<div class="col-md-4">
				<div class="thumbnail">
					<a href="detail.do?mno=${vo.mno }"> 
						<img src="${vo.poster }" alt="Lights" style="width: 100%">
						<div class="caption">
							<p>${vo.title }</p>
						</div>
					</a>
				</div>
			</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>





