<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글쓰기</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/qna_board_write.css">
</head>
<body>
	<!-- 게시판 등록 -->
	<section id="writerForm">
		<h2>게시판 글 등록</h2>
		<input id="back" type="button" value="돌아가기"
			onclick="javascript:history.go(-1)" />
		<form action="boardWritePrd.do" method="post"
			enctype="multipart/form-data" name="boardform">
			<table>
				<tr>
					<td class="td_left"><label for="BOARD_NAME">글쓴이</label></td>
					<td class="td_right"><input type="text" name="BOARD_NAME"
						id="BOARD_NAME" required /></td>
				</tr>
				<tr>
					<td class="td_left"><label for="BOARD_PASS">비밀번호</label></td>
					<td class="td_right"><input type="password" name="BOARD_PASS"
						id="BOARD_PASS" required /></td>
				</tr>
				<tr>
					<td class="td_left"><label for="BOARD_SUBJECT">제목</label></td>
					<td class="td_right"><input type="text" name="BOARD_SUBJECT"
						id="BOARD_SUBJECT" required /></td>
				</tr>
				<tr>
					<td class="td_left"><label for="BOARD_CONTENT">내용</label></td>
					<td class="td_right"><textarea name="BOARD_CONTENT"
							id="BOARD_CONTENT" required cols="40" rows="15"></textarea></td>
				</tr>
				<tr>
					<td class="td_left"><label for="BOARD_FILE">파일 첨부</label></td>
					<td class="td_right"><input type="file" name="BOARD_FILE"
						id="BOARD_FILE" required /></td>
				</tr>
			</table>
			<section id="commandCell">
				<input type="submit" value="등록">&nbsp;&nbsp; <input
					type="reset" value="다시쓰기">
			</section>
		</form>
	</section>
	<!-- 게시판 등록 -->
</body>
</html>