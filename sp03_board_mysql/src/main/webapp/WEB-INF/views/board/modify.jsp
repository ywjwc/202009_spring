<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/includeFile.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 수정</title>
<style type="text/css">
#c {
	text-align: left;
}
</style>
<script type="text/javascript">
	$(function () {
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
		$('#upd').on('click', function (e) {
			e.preventDefault();	// 객체 기본 기능 소멸
			$('#updateForm').attr('action', '${path}/board/modify');
			$('#updateForm').attr('method', 'post');
 			$('#updateForm').attr('enctype', 'multipart/form-data');
			$('#updateForm').submit();
		});		
		$('#del').on('click', function(e) {
			e.preventDefault();
			var bnum = $('#bnum').val();
			$(location).attr('href', '${path}/board/remove?bnum=' + bnum);
		});
		$('#main').on('click', function() {
			$(location).attr('href', '${path}/board/list');
		});
	});	
</script>
</head>
<body>
	<form id="updateForm">
	<input type="hidden" id="bnum" value="${board.bnum}">
		<table border="1">
			<tr>
				<th>작성자</th>
				<td><input type="text" name="writer" size="39" value="${board.writer}" readonly="readonly"></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><input type="email" name="email" size="39" value="${board.email}"></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="subject" size="39" value="${board.subject}"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="7" cols="41" name="content"	>${board.content}</textarea></td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td>
					<div id = "fileGroup">
						<c:forEach var="file" items="${flist}">
							<div>
								<input type="hidden" name="fnum" value="${file.fnum}"> 
								${file.filename}	
								<button class="btnFileDelete">삭제</button>
							</div>
						</c:forEach>
						<div>
							<input type="file" name="bfiles">	
							<button class="btnFileDelete">삭제</button>		
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<button id="upd">수정</button>
					<button id="del">삭제</button>
					<button type="button" id="main">메인</button>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>