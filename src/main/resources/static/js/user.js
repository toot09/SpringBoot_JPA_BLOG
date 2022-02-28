let index = {
	init: function() {
		$("#btn-save").on("click", () => {
			this.save();
		});
		$("#btn-update").on("click", () => {
			this.update();
		});
	},
	save: function() {
		let data = {
			username: $("#username").val(),
			email: $("#email").val(),
			password: $("#password").val()
		};

		// ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청 
		// ajax 호출시 default는 비동기 호출 
		$.ajax({
			type: "POST",
			url: "/auth/joinProc",
			data: JSON.stringify(data),	// 자바스크립트 OBJECT -> JSON String
			contentType: "application/json; charset=utf-8", // body 데이터가 어떤 타입인지(MIME)
			dataType: "json" // 응답왔을때 기본적으로 대부분 문자열인데 json같다? 그러면 javascript object로 변환해준다.  
		}).done(function(resp) {
			alert("회원가입이 완료되었습니다.");
			console.log(resp);
			location.href = "/";
		}).fail(function(err) {
			alert(JSON.stringify(err));
		});
	},
	
	update: function() {
		
		if($("#password1").val() != $("#password2").val()) {
			alert("입력한 두 비밀번호가 일치하지 않습니다.");
			return;
		}
		
		let data = {
			id: $("#id").val(),
			username: $("#username").val(),
			email: $("#email").val(),
			password: $("#password1").val()
		};
		
		$.ajax({
			type: "PUT",
			url: "/auth/updateProc",
			data: JSON.stringify(data),	// 자바스크립트 OBJECT -> JSON String
			contentType: "application/json; charset=utf-8", // body 데이터가 어떤 타입인지(MIME)
			dataType: "json" // 응답왔을때 기본적으로 대부분 문자열인데 json같다? 그러면 javascript object로 변환해준다.  
		}).done(function(resp) {
			alert("회원 정보가 수정되었습니다.");
			console.log(resp);
			location.href = "/";
		}).fail(function(err) {
			alert(JSON.stringify(err));
		});
	},
}

index.init();