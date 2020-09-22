<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/includeFile.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 조회</title>
<style type="text/css">
#c {
	text-align: left;
}

a {
	margin-left: 5px;
	line-height: 25px;
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
</style>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.7.6/handlebars.min.js"></script>
<script id="repliesTemp" type="text/x-handlebars-template">
	{{#each .}}
	<table border="1">
		<tr>
			<th>번호</th>
			<td>{{rnum}}</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>{{writer}}</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>{{content}}</td>
		</tr>
		<tr>
			<th>작성일자</th>
			<td>{{regdate}}</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<button class="btnReplyModify" value="{{rnum}}">수정</button>
				<button class="btnReplyDelete" value="{{rnum}}">삭제</button>
			</td>
		</tr>
	</table> <br> 
	{{/each}}

	{{#each .}}
		<li>
			<div>
				번호: {{rnum}} <br>
				작성자: {{writer}} <br>
				내용: <span id="rnum{{rnum}}">{{content}}</span> <br>
				작성일자: {{regdate}} <br>
				<button class="btnReplyModify" value="{{rnum}}">수정</button>
				<button class="btnReplySave" value='{{rnum}}' hidden>저장</button>
				<button class="btnReplyCancel" value='{{rnum}}' hidden>취소</button>
				<button class="btnReplyDelete" value="{{rnum}}">삭제</button>
			</div>
		</li>
	{{/each}}
	
</script>
<script type="text/javascript">
	$(function() {
		$('#modify').on('click', function(e) {
			e.preventDefault();
			var bnum = $('#bnum').val();
			$(location).attr('href', '${path}/board/modify?bnum=' + bnum);
		});
		$('#file').on('click', function() {
			var filename = $('#filename').val();
			$(location).attr('href', '${path}/board/downloads?filename=' + filename);
		});
		$('#main').on('click', function() {
			$(location).attr('href', '${path}/board/list');
		});
		
		// 댓글
		$('#btnReplyAdd').on('click', function () {
			var bnum = $('#bnum').val();
			var replyWriter = $('#replyWriter').val();
			var replyContent = $('#replyContent').val();
			// alert(replyContent);
			$.ajax({
				url : '${path}/reply/',
				type : 'post',
				contentType : 'application/json',
				data : JSON.stringify({	// json 문자열 표기법으로 변환
					bnum:bnum,
					writer:replyWriter,
					content:replyContent
				}),
				dataType : 'text',
				success : function (result) {
					alert(result);
				},
				error : function (result) {
					alert("error");
					console.log(result);
				}
			});
		});
		
		// 댓글 조회
		$('#btnReplyList').on('click', function () {
			replyList();
		});
		
		// 댓글 리스트 생성, 화면 출력
		function replyDisplay(data) {
			var source = $('#repliesTemp').html();
			var template = Handlebars.compile(source);
			$('#replies').html(template(data));
		}
		
		function replyList() {
			var bnum = $('#bnum').val();
			$.ajax({
				url : '${path}/reply/' + bnum,	// restfull
				type : 'get',
				dataType : 'json',	// 결과 값의 형태
				success : function (result) {
					alert("성공");
					console.log(result);
					replyDisplay(result);
				},
				error : function (result) {
					alert("error");
					console.log(result);
				}
			});
		}

		function afterSave(rnum) {
			var html = '<span id="rnum'+ rnum + '">'+ $('#rnum'+rnum).val() +'</span>';
			$('#rnum'+rnum).replaceWith(html);
			// 버튼 컨트롤
			$('.btnReplySave[value=' + rnum + ']').hide(); // 저장버튼 감추기
			$('.btnReplyCancel[value=' + rnum + ']').hide(); // 취소버튼 감추기
			$('.btnReplyModify[value=' + rnum + ']').show(); // 수정버튼 보이기
		}
		
		// 댓글 수정 저장
		$('#replies').on('click', '.btnReplySave', function () {
			var rnum = $(this).val();
			var replyContent = $('#rnum'+rnum).val();
			$.ajax({
				url : '${path}/reply/' + rnum,
				type : 'put',
				contentType : 'application/json',
				data : JSON.stringify({	// json 문자열 표기법으로 변환
					content:replyContent
				}),
				dataType : 'text',
				success : function (result) {
					alert(result);
					afterSave(rnum);
					replyList(result);
				},
				error : function (result) {
					alert("error");
					console.log(result);
				}
			});
		});
		
		// 댓글 수정
		$('#replies').on('click', '.btnReplyModify', function () {
			var rnum = $(this).val();
			var html = '<textarea id="rnum' + rnum + '">' + $('#rnum'+rnum).html() + '</textarea>';
			$('#rnum'+rnum).replaceWith(html);
			// 버튼 컨트롤
			$('.btnReplySave[value=' + rnum + ']').show(); // 저장버튼 보이기
			$('.btnReplyCancel[value=' + rnum + ']').show(); // 취소버튼 보이기
			$('.btnReplyModify[value=' + rnum + ']').hide(); // 수정버튼 감추기
			 
		});
		
		// 댓글 취소
		$('#replies').on('click', '.btnReplyCancel',function() {
			var rnum = $(this).val();
			var html = '<span id="rnum' + rnum + '">'+ $('#rnum'+rnum).html() +'</span>'; // 수정 할 수 있게 변경
			$('#rnum'+rnum).replaceWith(html); //기존의 html을 바꾼다
			// 버튼 컨트롤
			$('.btnReplySave[value=' + rnum + ']').hide(); // 저장버튼 감추기
			$('.btnReplyCancel[value=' + rnum + ']').hide(); // 취소버튼 감추기
			$('.btnReplyModify[value=' + rnum + ']').show(); // 수정버튼 보이기
		});
		
		// 댓글 삭제
		$('#replies').on('click', '.btnReplyDelete', function() {
			var rnum = $(this).val();
			var bnum = $('#bnum').val();
			$.ajax({
				url : '${path}/reply/' + rnum + "?bnum=" + bnum, // restfull
				type : 'delete',
				dataType : 'text', // 결과 값의 형태
				success : function(result) {
					console.log(result);
					replyList();
				},
				error : function(result) {
					alert("error");
					console.log(result);
				}
			});
		});
	});
</script>
</head>
<body>
	<input type="hidden" id="bnum" value="${board.bnum}">
	<table border="1">
		<tr>
			<th>작성자</th>
			<td>${board.writer}</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>${board.email}</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>${board.subject}</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>${board.content}</td>
		</tr>
		<tr>
			<th>작성일자</th>
			<td><fmt:formatDate pattern="yyyy.MM.dd. hh:mm:ss" value="${board.regdate}" /></td>
		</tr>
		<tr>
			<th>최종 수정일</th>
			<td><fmt:formatDate pattern="yyyy.MM.dd. hh:mm:ss" value="${board.updatee}" /></td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td id="c"><c:forEach var="file" items="${flist}">
					<input type="hidden" id="filename" value="${file.filename}">
					<a id="file" style="margin-bottom: 20px;">${file.filename}</a>
					<br>
				</c:forEach></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<button id="modify">수정</button>
				<button type="button" id="main">메인</button>
			</td>
		</tr>
	</table>

	<hr>
	<h2>댓글</h2>
	<div>
		<label>작성자</label> <input type="text" id="replyWriter"> <br>
		<label>내용</label>
		<textarea id="replyContent" rows="2" cols="20"></textarea>
		<button id="btnReplyAdd">댓글추가</button>
	</div>

	<h2>댓글 목록</h2>
	<button id="btnReplyList">댓글 조회</button>
	<div id="replies"></div>
</body>
</html>