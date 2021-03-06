<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container">

	<form>

		<input type=hidden id="id"  value="${principal.user.id}">
		<div class="form-group">
			<label for="email">User Name:</label>
			<input type="text" value="${principal.user.username}" class="form-control" placeholder="Enter your name" id="username" readonly>
		</div>

		<div class="form-group">
			<label for="password">Password</label>
			<input type="password" class="form-control" placeholder="Enter 1st password" id="password1">
			<label for="password">Password 재입력</label>
			<input type="password" class="form-control" placeholder="Enter 2nd password" id="password2">
		</div>

		<div class="form-group">
			<label for="email">Email address:</label>
			<input type="email" value="${principal.user.email}" class="form-control" placeholder="Enter email" id="email">
		</div>

	</form>
	
	<button id="btn-update" class="btn btn-primary">회원변경</button>

</div>

<!-- js경로는 static경로로 자동 지정되어 찾아간다. -->
<script src="/js/user.js"></script>

<%@ include file="../layout/footer.jsp"%>