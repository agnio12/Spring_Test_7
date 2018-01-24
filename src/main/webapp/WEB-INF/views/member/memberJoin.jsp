<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JOIN</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("#id").keyup(function() {
			var id = $(this).val();
			$.ajax({
				url : "./memberIdCheck",
				type : "GET",
				data : {
					id : id
				},
				success : function(data) {
					if($.trim(data)  ==  "Possible ID"){
						$("#result").css('color', 'blue');
					}else{
						$("#result").css('color', 'red');
					}
					$("#result").html(data);
				}
			});
		});
		
	/* Post 방식
		var check=true;
		$("#id").keyup(function() {
			var data = $(this).val();
			$.post("memberIdCheck",{id:data},function(result){
				if(result.trim()==0){
					$("#result").html("이미 있는 ID");
				}else {
					$("#result").html("사용 가능 ID");
				}
			});
		}); */
		
	});
</script>
<style type="text/css">
</style>
</head>
<body>
	<h1> Member Join</h1>
	
	<form action="./memberJoin" method="post" enctype="multipart/form-data">
	<p>ID : <input type="text" name="id" id="id"></p>
	<div id="result"></div>
	<p>PW : <input type="password" name="pw"></p>
	<p>NAME : <input type="text" name="name"></p>
	<p>EMAIL : <input type="text" name="email"></p>
	<p>PHONE : <input type="text" name="phone"></p>
	<p>AGE : <input type="text" name="age"></p>
	<p>JOB : <input type="text" name="job"></p>
	<p>POTO : <input type="file" name="file"></p>
	<p><button>JOIN</button></p>
	</form>
</body>
</html>