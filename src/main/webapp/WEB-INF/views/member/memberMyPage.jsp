<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MY PAGE</title>
<style type="text/css">
#poto{
	width: 200px;
	height: 200px;
}
</style>
</head>
<body>
	<h1>My Page</h1>

	<!-- Login한 상태이기 때문에 session으로 Member를 가져올수 있다 / 따로 Attribute를 가져올 필요가 없음-->
	<p>ID : ${member.id}</p>
	<p>NAME : ${member.name}</p>
	<p>EMAIL : ${member.email}</p>
	<p>PHONE : ${member.phone}</p>
	<p>AGE : ${member.age}</p>
	<p>JOB : ${member.job}</p>
	<p>POTO</p>
	<p><img  id="poto" src="../resources/upload/${member.fname}"></p>
	<br>
	<p>
		<a href="./memberUpdate?id=${member.id}">UPDATE</a> 
		<a href="./memberDelete">DELETE</a>
	</p>

</body>
</html>