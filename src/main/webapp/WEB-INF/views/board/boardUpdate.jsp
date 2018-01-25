<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="../resources/SE2/js/HuskyEZCreator.js" charset="utf-8"></script>
<title>UPDATE</title>
<script type="text/javascript">
$(function() {
	//전역변수
	var obj = [];
	//스마트에디터 프레임생성
	nhn.husky.EZCreator.createInIFrame({
		oAppRef : obj,
		elPlaceHolder : "contents",
		sSkinURI : "../resources/SE2/SmartEditor2Skin.html",
		htParams : {
			// 툴바 사용 여부
			bUseToolbar : true,
			// 입력창 크기 조절바 사용 여부
			bUseVerticalResizer : true,
			// 모드 탭(Editor | HTML | TEXT) 사용 여부
			bUseModeChanger : true,
		}
	});
	//전송버튼
	$("#save").click(function() {
		//id가 smarteditor인 textarea에 에디터에서 대입
		obj.getById["contents"].exec("UPDATE_CONTENTS_FIELD", []);
		//폼 submit
		$("#frm").submit();
	});
	
	var i = ${fn:length(view.files)}; // fn 태그 함수
	
	$(".del").click(function() {
			var del = $(this);
			var fnum = $(this).attr("title");
			var fname = $(this).attr("id");
			var check = confirm("삭제시 복구 불가능");
			if(check){
				 $.ajax({
					 url:"../file/fileDelete",
				 	 type:"GET",
				 	 data:{fnum:fnum, fname:fname}, //앞 fnum - 파라미터 / 뒤 fnum - 위에 선언해준 변수명
				 	 success : function(data){
				 		 if(data.trim() == 1){
				 			$(del).prev().remove();
				 			$(del).remove();
				 			i--;
				 		 }
				 	 }
				 });
				 alert("삭제 되었습니다")
			}else{
				alert("취소 되었습니다")
			}
	});
	

	$("#btn").click(function() {
		if (i < 5) {
			var ex = $("#ex").html();
			$("#result").append(ex);
			i++;
		} else {
			alert("그만해! 5개까지야")
		}
	});

	$("#result").on("click", ".x", function() {
		$(this).prev().remove();
		$(this).remove();
		i--;
	});
	

	/* var i=0;
	$("#btn").click(function() {
		if(i<5){
			$("#result").append('<input type="file" name="file"><span class="x">X</span>');
			
		i++;
		}else {
			alert("5개만 가능");
		}
	}); */
});
</script>
<style type="text/css">
#ex {
	display: none;
}

.x, .del {
	cursor: pointer;
}

.x:hover {
	color: red;
}
.del:hover {
	color: red;
}
</style>

</head>
<body>
	<h1>${board}Update</h1>

	<form id="frm" action="noticeUpdate" method="post" enctype="multipart/form-data">
		<p><input type="hidden" name="num" value="${view.num}"></p>
		<p>Writer : <input type="text" value="${view.writer}" disabled="disabled"></p>
		<p>TITLE : <input type="text" name="title" value="${view.title}"></p>
		<p>CONTENTS : <textarea rows="" cols="" name="contents" id="contents">${view.contents }</textarea></p>
		
		<c:forEach items="${view.files}" var="file">
			<p><input type="text" readonly="readonly" value="${file.oname}"> <span class="del" title="${file.fnum}" id="${file.fname}"> X </span></p>
		</c:forEach>
		
		<p><input type="button" value="FileAdd" id="btn"></p>
		<div id="result"></div>
		<input type="button" value="UP" id="save">
	</form>

	<div id="ex">
		<p><input type="file" name="file"><span class="x">X</span><p>
	</div>

</body>
</html>