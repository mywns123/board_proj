<%@ page import="board_proj.ds.JndiDs"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
방가<br>
<%-- <%=JndiDs.getConnection() %><br>--%>
<a href="boardWriteForm.do">게시판 글쓰기</a><br>
<a href="boardList.do">목록보기</a><br>

</body>
</html>