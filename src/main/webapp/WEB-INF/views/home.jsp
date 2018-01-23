<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>Home</title>
</head>
<body>
	<h1>Hello world!</h1>

	<P>The time on the server is ${serverTime}.</P>

	<a href="./notice/noticeList">Notice</a>
	<a href="./qna/qnaList">QnA</a>
	<br>
	<br>
	<br>
	
	<c:if test="${empty member}">
	<a href="./member/memberJoin">Join</a>
	<a href="./member/memberLogin">Login</a>
	</c:if>
	
	<c:if test="${not empty member}">
	<a href="./member/memberMyPage?id=${member.id}">MyPage</a>
	<a href="./member/memberLogOut">LogOut</a>
	</c:if>
</body>
</html>
