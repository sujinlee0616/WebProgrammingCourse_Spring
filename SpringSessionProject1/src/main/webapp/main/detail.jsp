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
<!-- React -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/react/0.14.0/react.js"></script> 
<script src="https://cdnjs.cloudflare.com/ajax/libs/react/0.14.0/react-dom.js"></script> 
<script src="https://cdnjs.cloudflare.com/ajax/libs/babel-core/5.8.23/browser.min.js"></script> 
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script> 
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>
	<div class="container" id="root"></div>
	<script type="text/babel">
		// ====================== [Detail]: 아래의 3개 클래스를 조립★★★  ======================
		// - Detail: list.jsp에서의 App과 같은 역할. 
		class Detail extends React.Component{
			render(){
				return (
					<div className="row">
						<h1 className="text-center">영화상세보기</h1>
					</div>
				)
				// 참고) return 뒤 시작괄호'('는 반드시 return의 바로 '옆'에 붙어야함! 아랫줄에 붙으면 안 됨 
			}
		}


		// ====================== [ReactDOM render] : Detail을 render ======================
		// - div #root에다가 detail 리턴값 포함시킨다.
		ReactDOM.render(<Detail/>, document.getElementById('root'));
	</script>

</body>
</html>






