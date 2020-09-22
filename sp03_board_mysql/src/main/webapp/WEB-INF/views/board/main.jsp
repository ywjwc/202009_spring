<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/includeFile.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인</title>
<script type="text/javascript">
	$(function () {
		$('#list').on('click', function () {
			$(location).attr('href', '${path}/board/list');
		});
	});
</script>
</head>
<body>
	<button id="list">게시판</button>
	<div>
		<img alt="메인" src="${path}/resources/img/pig.png" width="300px">
		<img alt="메인" src="${path}/img/pig.png" width="300px">
		<img alt="메인" src="${path}/localimg/pig.png" width="300px">
		<img alt="메인" src="/images/pig.png" width="300px">
	</div>
</body>
</html>