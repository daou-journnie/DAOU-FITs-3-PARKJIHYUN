<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="org.example.booksearchweb.model.BoardVO" %>
<%@ page import="org.example.booksearchweb.model.MemberVO" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>게시글 상세보기</title>
  <style>
    body { font-family: Arial, sans-serif; margin: 20px; }
    .board-detail { max-width: 800px; margin: auto; }
    .board-detail h2 { border-bottom: 1px solid #ddd; padding-bottom: 10px; }
    .board-detail .info { font-size: 0.9em; color: #555; margin-bottom: 20px; }
    .board-detail .content { white-space: pre-wrap; margin-bottom: 20px; }
    .actions a { text-decoration: none; padding: 8px 12px; background: #f2f2f2; border: 1px solid #ddd; color: #333; margin-right: 10px; }
    .actions a:hover { background: #e2e2e2; }
  </style>
</head>
<body>
<%
  BoardVO board = (BoardVO) request.getAttribute("board");
  if(board == null){
%>
<p>게시글을 찾을 수 없습니다.</p>
<%
} else {
%>
<div class="board-detail">
  <h2><%= board.getTitle() %></h2>
  <div class="info">
    작성자: <%= board.getWriter() %> | 등록일: <%= board.getRegDate() %> | 수정일: <%= board.getModDate() %>
  </div>
  <div class="content">
    <%= board.getContent() %>
  </div>
  <div class="actions">
    <a href="boardList">목록보기</a>
    <%
      MemberVO member = (MemberVO) session.getAttribute("member");
      if(board.getWriter().equals(member.getName())){
    %>
    <a href="boardDelete?id=<%= board.getId() %>">삭제</a>
    <a href="boardEdit?id=<%= board.getId() %>">수정</a>
    <%
      }
    %>
  </div>
</div>
<%
  }
%>
</body>
</html>
