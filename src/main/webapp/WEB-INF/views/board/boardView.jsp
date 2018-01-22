<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>VIEW</title>
</head>
<body>
	<h1>${board} View</h1>

	<h2>Title : ${view.title}</h2>
	<h2>Contents : ${view.contents}</h2>
	
	<!-- 첨부파일 a 태그 사용 -->
	<c:forEach items="${view.files}" var="file">
		<a href="../file/fileDown?fname=${file.fname}&oname=${file.oname}">${file.oname}</a><br>
	</c:forEach>	
	
	<a href="${board}Update?num=${view.num}">UP</a>
	<a href="${board}Delete?num=${view.num}">DEL</a>
	
</body>
</html>