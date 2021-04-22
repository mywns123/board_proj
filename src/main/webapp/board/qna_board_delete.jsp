<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC 게시판</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/qna_board_delete.css">
</head>
<body>
	<section>
		<form name="deleteForm"
			action="boardDeletePro.do?board_num=${board_num}" method="post">
			<input type="hidden" name="page" value="${page}" />
			<fieldset id="passForm">
				<legend>게시글 삭제</legend>
				<table id="btn">
					<tr>
						<td><label>글 비밀번호 : </label></td>
						<td><input name="board_pass" type="password"></td>
					</tr>
					<tr id="btn1">
						<td><input type="submit" value="삭제">&nbsp;&nbsp; <input
							type="button" value="돌아가기" onclick="javascript:history.go(-1)" /></td>
					</tr>
				</table>
			</fieldset>
		</form>
	</section>
</body>
</html>