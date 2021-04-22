<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC 게시판</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/qna_board_modify.css">
<script type="text/javascript">
	function modifyboard() {
		modifyform.submit();
	}
</script>
</head>
<body>
	<%-- ${article } --%>
	<!-- 게시판 등록 -->
	<section id="writeForm">
		<h2>게시판글 수정</h2>
		<form action="boardModifyPro.do" method="post" name="modifyform">
			<input type="hidden" name="board_num" value="${article.board_num}" />
			<input type="hidden" name="page" value="${page}" />
			<table>
				<tr>
					<td class="td_left"><label for="board_name">글쓴이</label></td>
					<td class="td_right"><input type="text" name="board_name"
						id="board_name" value="${article.board_name}" /></td>
				</tr>
				<tr>
					<td class="td_left"><label for="board_pass">비밀번호</label></td>
					<td class="td_right"><input type="password" name="board_pass"
						id="board_pass" /></td>
				</tr>
				<tr>
					<td class="td_left"><label for="board_subject">제 목</label></td>
					<td class="td_right"><input type="text" name="board_subject"
						id="board_subject" value="${article.board_subject}" /></td>
				</tr>
				<tr>
					<td class="td_left"><label for="board_content">내 용</label></td>
					<td class="td_right"><textarea name="board_content"
							id="board_content" cols="40" rows="15"> ${article.board_content}
					</textarea></td>
				</tr>
			</table>
			<section id="commandCell">
				<a href="javascript:modifyboard()">[수정]</a>&nbsp;&nbsp; <a
					href="javascript:history.go(-1)">[뒤로]</a>
			</section>
		</form>
	</section>
</body>
</html>