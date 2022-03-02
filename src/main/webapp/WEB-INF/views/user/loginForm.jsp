<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container">

	<form action="/auth/loginProc" method="POST">
		<div class="form-group">
			<label for="text">User Name:</label>
			<input type="text" name="username" class="form-control" placeholder="Enter your name" id="username">
		</div>
		<div class="form-group">
			<label for="password">Password:</label>
			<input type="password" name="password" class="form-control" placeholder="Enter password" id="password">
		</div>
		<button id="btn-login" class="btn btn-primary">로그인</button>
		<%-- <a href="https://kauth.kakao.com/oauth/authorize?client_id=${KEY}&redirect_uri=${CALLBACK}&response_type=code"><img height=38 src="/image/kakao_login_button.png"></a> --%>
	</form>

</div>

<%@ include file="../layout/footer.jsp"%>