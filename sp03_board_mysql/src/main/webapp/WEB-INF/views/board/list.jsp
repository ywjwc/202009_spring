<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/includeFile.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<style type="text/css">
table {
	margin-top: 5px;
	margin-bottom: 5px;
}

#th1 {
	width: 75px;
}

#th2 {
	width: 400px;
}

#th3 {
	width: 150px;
}

#th4 {
	width: 110px;
}

#th5 {
	width: 55px;
}

a:link {
	color: black;
	text-decoration: none;
}

a:visited {
	color: black;
	text-decoration: none;
}

a:hover {
	cursor: pointer;
	text-decoration: underline;
	color: black;
}

.reply {
	color: #ff0000;
}

#level {
	text-align: left;
}

#btn {
	background-color: red;
	text-decoration: none;
	font-size: 30px;
	color: white;
	display: inline-block;
	border-radius: 10px;
	transition: all 0.1s;
	text-shadow: 0px -2.5px rgba(0, 0, 0, 0.77);
	font-family: 'Lobster', cursive;
}

#btn:active {
	transform: translateY(1px);
}

#btn:hover {
	color: yellow;
}

.page {
	text-align: center;
	line-height: 50px;
}

.page a {
	display: inline-block;
	float: left;
	margin: 0 2px;
	border: 1px solid #42454c;
	width: 28px;
	height: 28px;
	line-height: 28px;
	background-color: #42454c;
	color: #ffffff;
	font-size: 13px;
	text-decoration: none;
	position: relative;
	vertical-align: top;
}

.page .prev {
	background: #f8f8f8 url('${path}/resources/img/page_prev.png') no-repeat center
		center;
	margin-right: 7px;
}

.page .next {
	background: #f8f8f8 url('${path}/resources/img/page_next.png') no-repeat center
		center;
	margin-left: 7px;
}

.page a.active:hover {
	background-color: #ffffff;
	color: #42454c;
}
</style>
<script type="text/javascript">
	// document 로딩 완료된 후 함수 실행
	$(function () {
		// 조회 버튼
		$('#btnSearch').on('click', function () {
			var findKey = $('#findKey').val();
			var findValue = $('#findValue').val();
			$(location).attr('href', '${path}/board/list?findKey=' + findKey + '&findValue=' + findValue);
		});
		// 등록 버튼
		$('#btnAdd').on('click', function () {
			$(location).attr('href', '${path}/board/insert');
		});
		$('#btnSessionDelete').on('click', function () {
			$(location).attr('href', '${path}/board/sessionDelete');
		});
		// 한건 조회
		$('a').on('click', function (e) {
			e.preventDefault(); // 객체 기본 기능 소멸
			var bnum = $(this).attr('href');
			$(location).attr('href', '${path}/board/detail?bnum=' + bnum);
		});
		$('.active').on('click', function (e) {
			e.preventDefault();
			var nowPage = $(this).attr('href');
			var findKey = $('#findKey').val();
			var findValue = $('#findValue').val();
			$(location).attr('href', '${path}/board/list?nowPage=' + nowPage + '&findKey=' + findKey + '&findValue=' + findValue);
		});
		$('.prev').on('click', function (e) {
			e.preventDefault();
			var nowPage = $(this).attr('href');
			var findKey = $('#findKey').val();
			var findValue = $('#findValue').val();
			$(location).attr('href', '${path}/board/list?nowPage=' + nowPage + '&findKey=' + findKey + '&findValue=' + findValue);
		});
		$('.next').on('click', function (e) {
			e.preventDefault();
			var nowPage = $(this).attr('href');
			var findKey = $('#findKey').val();
			var findValue = $('#findValue').val();
			$(location).attr('href', '${path}/board/list?nowPage=' + nowPage + '&findKey=' + findKey + '&findValue=' + findValue);
		});
	});
</script>
</head>
<body>
	<h2 style="display: inline;">리스트</h2>
	<input type=button value="새로고침" onclick="location.reload()">
	<input id="btnSessionDelete" type="button" value="삭제">
	<hr>
	<div>
		<select id="findKey">
			<option value="writer" <c:out value="${pdto.findKey == 'writer' ? 'selected' : ''}" />>작성자</option>
			<option value="subject" <c:out value="${pdto.findKey == 'subject' ? 'selected' : ''}" />>제목</option>
			<option value="content" <c:out value="${pdto.findKey == 'content' ? 'selected' : ''}" />>내용</option>
			<option value="sc" <c:out value="${pdto.findKey == 'sc' ? 'selected' : ''}" />>제목+내용</option>
		</select>
		<input type="text" id="findValue" value="${pdto.findValue}">
		<input type="button" id="btnSearch" value="조회">
		<input type="button" value="등록" id="btnAdd">
	</div>
	<table border="1">
		<tr>
			<th id="th1">글번호</th>
			<th id="th2">제목</th>
			<th id="th3">작성자</th>
			<th id="th4">작성일</th>
			<th id="th5">조회</th>			
		</tr>
		<c:forEach var="board" items="${list}">
			<tr>
				<td>${board.bnum}</td>
				<td><a href="${board.bnum}">${board.subject}</a>&nbsp;<strong class="reply"><c:if test="${board.replycnt > 0}">[${board.replycnt}]</c:if></strong></td>
				<td>${board.writer}</td>
				<td><fmt:formatDate pattern="yyyy.MM.dd." value="${board.regdate}" /></td>
				<td>${board.readcnt}</td>
			</tr>
		</c:forEach>
	</table>
	<!-- 페이징 처리 -->
	<!-- == eq, != ne, < lt, > gt, <= le, >= ge -->
	<div class="page">
		<!-- 이전 페이지 -->
		<c:if test="${pdto.startPage ne 1}">
			<a class="prev" href="${pdto.startPage-1}"></a>
		</c:if>
		<c:forEach var="i" begin="${pdto.startPage}" end="${pdto.endPage}">
			<a class="active" href="${i}">${i}</a>
		</c:forEach>
		<!-- 다음 페이지 -->
		<c:if test="${pdto.totPage gt pdto.endPage}">
			<a class="next" href="${pdto.endPage+1}"></a>
		</c:if>
	</div>
</body>
</html>