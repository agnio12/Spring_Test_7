<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title>UPDATE</title>
<script type="text/javascript">
	$(function() {
		$("#poto").click(function() {
			$("#poto").hide();
			$("#f").show();
		});
	});
</script>
<style type="text/css">
#poto{
	cursor: pointer;
}
#poto:hover{
	color: red;
}

#f{
	display: none;
}
</style>
</head>
<body>
<h1> Member Update</h1>
	
	<!-- Login한 상태이기 때문에 session으로 Member를 가져올수 있다 -->
	<form action="memberUpdate" method="post" enctype="multipart/form-data">
	<p>ID : <input type="text" name="id" readonly="readonly" value="${member.id}"></p>
	<p>PW : <input type="password" name="pw" value="${member.pw}"></p>
	<p>NAME : <input type="text" name="name" value="${member.name}"></p>
	<p>EMAIL : <input type="text" name="email" value="${member.email}"></p>
	<p>PHONE : <input type="text" name="phone" value="${member.phone}"></p>
	<p>AGE : <input type="text" name="age" value="${member.age}"></p>
	<p>JOB : <input type="text" name="job" value="${member.job}"></p>
	<p>POTO : <span id="poto">${member.oname} X</span> <input type="file" name="file" id="f"></p>
	<p><button>UP</button></p>
	</form>

</body>
</html>