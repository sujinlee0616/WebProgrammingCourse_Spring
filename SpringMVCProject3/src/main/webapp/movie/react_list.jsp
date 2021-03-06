<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- React Script -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/react/0.14.0/react.js"></script> 
<script src="https://cdnjs.cloudflare.com/ajax/libs/react/0.14.0/react-dom.js"></script> 
<script src="https://cdnjs.cloudflare.com/ajax/libs/babel-core/5.8.23/browser.min.js"></script> 
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script> 
</head>
<body>
	<div id="root"></div>
	<script type="text/babel">
		class MovieList extends React.Component{ 
			render(){
				return (
					<div className="row">
						<h1 className="text-center">현재 상영영화</h1>
						<c:forEach var="vo" items="${list }">
						<div className="col-md-4">
							<div className="thumbnail">
								<a href="detail.do?mno=${vo.mno }"> 
									<img src="${vo.poster }" alt="Lights" style={{"width": "100%"}}/>
									<div className="caption">
										<p>${vo.title }</p>
									</div>
								</a>
							</div>
						</div>
						</c:forEach>
					</div>
				)
			}
		}
		ReactDOM.render(<MovieList />,document.getElementById('root'))
	</script>
	<!-- [문법]
	1. HTML 태그는 반드시 소문자로 사용
	2. 내부 스타일은 이렇게 써야
	3. empty 태그 반드시 닫아줘야 한다.
	   ex) <input ~~> (X)   <input ~~/> (O) 
	4. 클래스 이름은 class="이름"이 아니라 className="이름"으로 쓴다.-->
	  
</body>
</html>








