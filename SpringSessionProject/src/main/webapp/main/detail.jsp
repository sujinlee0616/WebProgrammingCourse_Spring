<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<!-- React Script -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/react/0.14.0/react.js"></script> 
<script src="https://cdnjs.cloudflare.com/ajax/libs/react/0.14.0/react-dom.js"></script> 
<script src="https://cdnjs.cloudflare.com/ajax/libs/babel-core/5.8.23/browser.min.js"></script> 
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script type="text/javascript">
$(function(){
	$('.spans').click(function(){
		$('.replys').hide();
		$('.replys_update').hide();
		var no=$(this).attr("data-no");
		$('#m'+no).show();
	})
	$('.span_update').click(function(){
		$('.replys').hide();
		$('.replys_update').hide();
		var no=$(this).attr("data-no");
		$('#u'+no).show();
	})
	
});
</script>
</head>
<body>
  <div class="container" id="root"></div>
  <script type="text/babel">
	//document.getElementById('root') == $('#root')
    class Detail extends React.Component{
		constructor(props){
			super(props);
			this.state={
				mno:${mno},
				detail:${json}
			}
		}
		/*componentDidMount()
		{
			axios.get("http://localhost/web/main/detail_data.do",{
					params:{
						mno:this.state.mno
					}
			}).then((result)=>{
				this.setState({detail:result.data});
				console.log(this.state.detail);
			});
		}*/
		render(){
		  return (
		  	<div className="row">
			  <h1 className="text-center">뮤직 상세보기(${sessionScope.id})</h1>
			  <table className="table">
				<tr>
				  <td colspan="2" className="text-center">
					<iframe src={"http://youtube.com/embed/"+this.state.detail.key}
						width="900" height="450"></iframe>
				  </td>
				</tr>
				<tr>
				  <td><b>노래명</b></td>
				  <td>{this.state.detail.title}</td>
				</tr>
				<tr>
				  <td><b>앨범</b></td>
				  <td>{this.state.detail.album}</td>
				</tr>
				<tr>
				  <td><b>가수명</b></td>
				  <td>{this.state.detail.singer}</td>
				</tr>
			  </table>
			  <table className="table">
				<tr>
				  <td className="text-right">
					<c:if test="${sessionScope.id!=null}">
					  <a href="logout.do" className="btn btn-sm btn-danger">로그아웃</a>
					</c:if>
					<a href="list.do" className="btn btn-sm btn-primary">목록</a>
				  </td>
				</tr>
			  </table>
			</div>
		  )
		}
	}
	ReactDOM.render(<Detail/>,document.getElementById('root'));
  </script>
  
  <c:if test="${sessionScope.id!=null }">
	  <div class="container">
	    <div class="row">
	      <h2 class="text-center">댓글</h2>
	      <table class="table">
	        <c:forEach var="rvo" items="${rList }">
	          <tr>
	            <td class="text-left">
	              <c:if test="${rvo.group_tab>0 }">
	                <c:forEach var="i" begin="1" end="${rvo.group_tab }">
	                  &nbsp;&nbsp;
	                </c:forEach>
	                <img src="icon_reply.gif">
	              </c:if>
	              <b style="color: blue;">◐${rvo.id }</b>(${rvo.dbday })
	            </td>
	            <td class="text-right">
	              <c:if test="${sessionScope.id==rvo.id }">
	                <span class="btn btn-xs btn-danger span_update" data-no="${rvo.no }">수정</span>
	                <a href="reply_delete.do?no=${rvo.no }&mno=${mno }" class="btn btn-xs btn-success">삭제</a>
	              </c:if>
	              <span class="btn btn-xs btn-primary spans" data-no="${rvo.no }">댓글</span>
	            </td>
	          </tr>
	          <tr>
	            <td colspan="2">
	              <c:if test="${rvo.group_tab>0 }">
	                <c:forEach var="i" begin="1" end="${rvo.group_tab }">
	                  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                </c:forEach>
	              </c:if>
	              ${rvo.msg }
	            </td>
	          </tr>
	          <tr id="m${rvo.no }" style="display: none" class="replys">
		          <td>
		            <form method="post" action="reply_reply_insert.do">
		              <textarea rows="3" cols="120" name="msg" style="float: left;"></textarea>
		              <input type="hidden" name="mno" value="${mno }">
		              <input type="hidden" name="pno" value="${rvo.no }">
		              <button class="btn btn-sm btn-success" style="float: left; height: 65px;">댓글쓰기</button>
		            </form>
		          </td>
	          </tr>
	          <tr id="u${rvo.no }" style="display: none" class="replys_update">
		          <td>
		            <form method="post" action="reply_update.do">
		              <textarea rows="3" cols="120" name="msg" style="float: left;">${rvo.msg }</textarea>
		              <input type="hidden" name="mno" value="${mno }">
		              <input type="hidden" name="no" value="${rvo.no }">
		              <button class="btn btn-sm btn-success" style="float: left; height: 65px;">수정하기</button>
		            </form>
		          </td>
	          </tr>
	        </c:forEach>
	      </table>
	      <table class="table">
	        <tr>
	          <td>
	            <form method="post" action="reply_insert.do">
	              <textarea rows="3" cols="150" name="msg" style="float: left;"></textarea>
	              <input type="hidden" name="mno" value="${mno }">
	              <button class="btn btn-sm btn-success" style="float: left; height: 65px;">댓글쓰기</button>
	            </form>
	          </td>
	        </tr>
	      </table>
	    </div>
	  </div>
  </c:if>
</body>
</html>