<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container">

		<button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
		<c:if test="${board.user.id == principal.user.id}">
			<a href="/board/${board.id}/updateForm" class="btn btn-warning">수정</a>
			<button class="btn btn-danger" id="btn-delete">삭제</button>
		</c:if>
		<br/><br/>
		
		<%-- <input type="hidden" id="id" value="${board.id}"> --%>
		<div>
			글 번호 : <span id="id">${board.id}</span>
			<br/>
			글 작성자 : <span>${board.user.username}</span>
		</div>
		<br/>
		
		<div>
			<h3>${board.title}</h3>
		</div>
		
		<hr/>
		
		<div>
			<div> ${board.content} </div>
		</div>
		
		<hr/>

</div>

<!-- js경로는 static경로로 자동 지정되어 찾아간다. -->
<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>

