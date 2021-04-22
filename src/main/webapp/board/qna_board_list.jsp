<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC 게시판</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/qna_board_list.css">
</head>
<body>
	<%-- ${articleList}--%>
	<section id='list'>
		<h2>글 목록</h2>
		<h4>
			<a href="boardWriteForm.do">게시판 글쓰기</a>
		</h4>
		<table border="1">
			<tr id="th">
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>날짜</th>
				<th>조회수</th>
			</tr>
			<c:forEach var="board" items="${articleList}">
				<tr>
					<td>${board.board_num}</td>
					<td id="subject">
						<c:if test="${board.board_re_lev ne 0}">
							<c:forEach var="i" begin="1" end="${board.board_re_lev}" step="1">
								&nbsp;
							</c:forEach>
								&rarrhk;
						</c:if>
						<a href="boardDetail.do?board_num=${board.board_num}&page=${pageInfo.page}">
								${board.board_subject}</a>									
					</td>
					<td>${board.board_name}</td>
					<td>${board.board_date}</td>
					<%-- <td><fmt:formatDate value="${board.board_date}" type="date" pattern="yyyy년 MM월 dd일"/></td> --%>
					<td>${board.board_readcount}</td>
				</tr>
			</c:forEach>
		</table>
	</section>
	<c:choose>
		<c:when
			test="${ fn : length(articleList) != 0 && pageInfo.listCount > 0 }">
			<section id="pageList">
				<c:choose>
					<c:when test="${pageInfo.page <= 1 }">
						[이전]&nbsp;
					</c:when>
					<c:otherwise>
						<a href="boardList.do?page=${pageInfo.page -1}">[이전]</a>&nbsp;
					</c:otherwise>
				</c:choose>

				<c:forEach var="a" begin="1" end="${pageInfo.endPage}">
					<c:choose>
						<c:when test="${a == pageInfo.page}">
							<span>[${a}]</span>&nbsp;
						</c:when>
						<c:otherwise>
							<a href="boardList.do?page=${a}">[${a}]</a>&nbsp;
						</c:otherwise>
					</c:choose>
				</c:forEach>

				<c:choose>
					<c:when test="${pageInfo.page >= pageInfo.maxPage }">
						[다음]&nbsp;
					</c:when>
					<c:otherwise>
						<a href="boardList.do?page=${pageInfo.page +1}">[다음]</a>&nbsp;
					</c:otherwise>
				</c:choose>
			</section>
		</c:when>
		<c:otherwise>
			<section>등록된 글이 없습니다.</section>
		</c:otherwise>
	</c:choose>
	<%-- <section id="pageList">	
		<c:if test="${pageInfo.page <= 1}">
			[이전]&nbsp;	
		</c:if>
		<c:if test="${pageInfo.page > 1}">
			<a href="boardList.do?page=${pageInfo.page-1}">[이전]</a>&nbsp;	
		</c:if>
		
		<c:forEach var="a" begin="1" end="${pageInfo.endPage}">
			<c:if test="${a == pageInfo.page}">
				<span>[${a}]</span>	&nbsp;	
			</c:if>
			<c:if test="${a ne pageInfo.page}">
				<a href="boardList.do?page=${a}">[${a}]</a>		
			</c:if>	
		</c:forEach>
		
		<c:if test="${pageInfo.page >= pageInfo.maxPage}">
			[다음] &nbsp;	
		</c:if>
		<c:if test="${pageInfo.page < pageInfo.maxPage}">
			<a href="boardList.do?page=${pageInfo.page+1}">[다음]</a>&nbsp;	
		</c:if>				
	</section> --%>
</body>
</html>