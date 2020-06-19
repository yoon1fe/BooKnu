<%@page import="java.util.Enumeration"%>
<%@page import="com.dto.BoardDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	ArrayList<BoardDTO> boards = (ArrayList<BoardDTO>)request.getAttribute("boards");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
</head>
<body>
	<jsp:include page="menu.jsp" />

	<a href="write.jsp">글 쓰기</a>
	
	<table class="table table-striped table-bordered">
	<thead>
		<tr>
			<th>게시글 번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>추천수</th>
			<th>비추천수</th>
			<th>조회수</th>
			<th>작성 시각</th>
		<tr>
	</tr>
	
	<tbody>						
			<%
			for(BoardDTO bds : boards){
				%>
			<tr onClick="location.href='BoardContent?id=<%=bds.getBoard_number()%>'"  style='cursor: pointer'>
				<td><%=bds.getBoard_number() %></td>
				<td><%=bds.getTitle() %></td>
				<td><%=bds.getId() %></td>
				<td><%=bds.getLike() %></td>
				<td><%=bds.getDislike() %></td>
				<td><%=bds.getReadCount()%></td>
				<td><%=bds.toString() %></td>
			</tr>
				<%
			}
			%>
			
	</tbody>
	</table>

	<jsp:include page="footer.jsp" />
</body>
</html>