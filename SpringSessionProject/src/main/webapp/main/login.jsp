<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.row{
	margin: 0px auto;
	width: 350px;
}
</style>
</head>
<body>
  <div class="container">
    <div class="row">
      <h2 class="text-center">Login</h2>
      <form method="post" action="login_ok.do">
	      <table class="table">
	        <tr>
	          <th width="30%" class="text-right danger">ID</th>
	          <td width="70%">
	            <input type="text" name="id" class="input-sm" size="15">
	          </td>
	        </tr>
	        <tr>
	          <th width="30%" class="text-right danger">Password</th>
	          <td width="70%">
	            <input type="password" name="pwd" class="input-sm" size="15">
	          </td>
	        </tr>
	        <tr>
	          <td colspan="2" class="text-center">
	            <button class="btn btn-sm btn-primary">로그인</button>
	            <input type="button" class="btn btn-sm btn-success" value="취소">
	          </td>
	        </tr>
	      </table>
      </form>
    </div>
  </div>
</body>
</html>