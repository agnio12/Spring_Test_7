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
	$("#del").click(function() {
		var id = $(this).attr("title");
		$("#"+id).html('PHOTO : <input type="file" name="file" id="f">');
	});
	$("#btn").click(function() {
		var f = $("#f").val();
		if(f != ''){
			$("#frm").submit();
		}
	});
});
</script>
<style type="text/css">
#del{
	cursor: pointer;
}
</style>
</head>
<body>
<h1> Member Update</h1>
	
	<!-- Login한 상태이기 때문에 session으로 Member를 가져올수 있다 -->
	<form id="frm" action="memberUpdate" method="post" enctype="multipart/form-data">
	<p>ID : <input type="text" name="id" readonly="readonly" value="${member.id}"></p>
	<p>PW : <input type="password" name="pw" value="${member.pw}"></p>
	<p>NAME : <input type="text" name="name" value="${member.name}"></p>
	<p>EMAIL : <input type="text" name="email" value="${member.email}"></p>
	<p>PHONE : <input type="text" name="phone" value="${member.phone}"></p>
	<p>AGE : <input type="text" name="age" value="${member.age}"></p>
	<p>JOB : <input type="text" name="job" value="${member.job}"></p>
	<p id="photo">PHOTO : <span id="del" title="photo">${member.oname} X</span></p>
	<p><input type="button" value="UP" id="btn"></p>
	</form>

</body>
</html>