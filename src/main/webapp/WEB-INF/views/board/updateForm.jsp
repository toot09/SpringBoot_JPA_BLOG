<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container">

	
	<form>
		<input type="hidden" id="id" value="${board.id}">
		<div class="form-group">
			<input value="${board.title}" type="text" name="username" class="form-control" placeholder="Enter Title" id="title">
		</div>

		<div class="form-group">
			<!-- summernote라는 이름을 찾아야지만 참조할 수 있기 때문에 클래스 네임추가  -->
			<textarea class="form-control summernote" rows="5" id="content">${board.content}</textarea>
		</div>
	</form>
	<!-- form action이 아닌 JSON으로 던져주기 위해 form태그 밖으로 뺀다  -->
	<button id="btn-update" class="btn btn-primary">수정완료</button>

</div>

<!-- summernode -->
 <script>
      $('.summernote').summernote({
        tabsize: 2,
        height: 300
      });
</script>

<!-- js경로는 static경로로 자동 지정되어 찾아간다. -->
<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>

