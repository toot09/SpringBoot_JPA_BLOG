let index = {
	init: function() {
		$("#btn-save").on("click", () => {
			this.save();
		});
		$("#btn-delete").on("click", () => {
			this.deleteById();
		});
		$("#btn-update").on("click", () => {
			this.updateById();
		});
	},
	save: function() {
		let data = {
			title: $("#title").val(),
			content: $("#content").val(),
		};
		// ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청 
		// ajax 호출시 default는 비동기 호출 
		$.ajax({
			type: "POST",
			url: "/api/board",
			data: JSON.stringify(data),	// 자바스크립트 OBJECT -> JSON String
			contentType: "application/json; charset=utf-8", // body 데이터가 어떤 타입인지(MIME)
			dataType: "json" // 응답왔을때 기본적으로 대부분 문자열인데 json같다? 그러면 javascript object로 변환해준다.  
		}).done(function(resp) {
			alert("글이 등록 되었습니다.");
			console.log(resp);
			location.href = "/";
		}).fail(function(err) {
			alert(JSON.stringify(err));
		});
	},
	
	deleteById: function() {
		var id = $("#id").text();
		$.ajax({
			type: "DELETE",
			url: "/api/board/"+id,
		}).done(function(resp) {
			alert("글이 삭제 되었습니다.");
			console.log(resp);
			location.href = "/";
		}).fail(function(err) {
			alert(JSON.stringify(err));
		});
	},
	
	updateById: function() {
		let data = {
			title: $("#title").val(),
			content: $("#content").val(),
			id: $("#id").val(),
		};
		$.ajax({
			type: "PUT",
			url: "/api/board",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"  
		}).done(function(resp) {
			alert("글이 수정 되었습니다.");
			console.log(resp);
			location.href = "/board/"+data.id;
		}).fail(function(err) {
			alert(JSON.stringify(err));
		});
	},
}

index.init();