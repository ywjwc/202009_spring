<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/includeFile.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 등록</title>
<style type="text/css">
table {
	border-spacing: 0;
	text-align: center;
}
</style>
<script type="text/javascript">
	$(function () {
		$('#add').on('click', function (e) {
			e.preventDefault();	// 객체 기본 기능 소멸
			$('#addForm').attr('action', '${path}/board/insert');
			$('#addForm').attr('method', 'post');
 			$('#addForm').attr('enctype', 'multipart/form-data');
			$('#addForm').submit();
		});
		$('#fileGroup').on('click', '.btnFileDelete', function (e) {
			e.preventDefault();
			$(this).parent().remove();
		});
		$('#fileGroup').on('change', 'div', function (e) {
			e.preventDefault();
			var appendHtml = '<div>' + 
							'<input type="file" name="bfiles">' + 
							'<button class="btnFileDelete">삭제</button>' + 
							'</div>';
			$('#fileGroup').append(appendHtml);
		});
		$('#main').on('click', function () {
			$(location).attr('href', '${path}/board/list');
		});
	});
</script>
</head>
<body>
	<form id="addForm">
		<table border="1">
			<tr>
				<th>작성자</th>
				<td><input type="text" name="writer" size="39"></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><input type="email" name="email" size="39"></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="subject" size="39"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="7" cols="41" name="content"></textarea></td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td><div id="fileGroup">
						<div>
							<input type="file" name="bfiles">
							<button class="btnFileDelete">삭제</button>
						</div>
					</div>
					<!-- <input type="file" name="bfiles" multiple="multiple"> -->
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<button id="add">등록</button>
					<button type="button" id="main">메인</button>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
