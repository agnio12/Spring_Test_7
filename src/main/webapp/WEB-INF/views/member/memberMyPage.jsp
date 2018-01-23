<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MY PAGE</title>
</head>
<body>
	<h1>My Page</h1>

	<p>ID : ${my.id}</p>
	<p>NAME : ${my.name}</p>
	<p>EMAIL : ${my.email}</p>
	<p>PHONE : ${my.phone}</p>
	<p>AGE : ${my.age}</p>
	<p>JOB : ${my.job}</p>
	<p>POTO : <img src="../resources/upload/${my.fname}"> </p>
	<br>
	<p>
		<a href="./memberUpdate?id=${my.id}">UPDATE</a> 
		<a href="./memberDelete">DELETE</a>
	</p>

</body>
</html>