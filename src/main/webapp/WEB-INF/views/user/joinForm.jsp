<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container">

	<form action="/action_page.php">

		<div class="form-group">
			<label for="email">User Name:</label> <input type="text" class="form-control" placeholder="Enter your name" id="username">
		</div>

		<div class="form-group">
			<label for="email">Email address:</label> <input type="email" class="form-control" placeholder="Enter email" id="email">
		</div>

		<div class="form-group">
			<label for="password">Password:</label> <input type="password" class="form-control" placeholder="Enter password" id="password">
		</div>
	</form>
	
	<button id="btn-save" class="btn btn-primary">회원신청</button>

</div>

<!-- js경로는 static경로로 자동 지정되어 찾아간다. -->
<script src="/js/user.js"></script>

<%@ include file="../layout/footer.jsp"%>