<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>UPDATE</title>
</head>
<body>
<h1> Member Update</h1>
	
	<form action="memberUpdate" method="post">
	<p>ID : <input type="text" name="id" readonly="readonly" value="${member.id}"></p>
	<p>PW : <input type="password" name="pw" value="${up.pw}"></p>
	<p>NAME : <input type="text" name="name" value="${up.name}"></p>
	<p>EMAIL : <input type="text" name="email" value="${up.email}"></p>
	<p>PHONE : <input type="text" name="phone" value="${up.phone}"></p>
	<p>AGE : <input type="text" name="age" value="${up.age}"></p>
	<p>JOB : <input type="text" name="job" value="${up.job}"></p>
	<p><button>Update</button></p>
	</form>

</body>
</html>