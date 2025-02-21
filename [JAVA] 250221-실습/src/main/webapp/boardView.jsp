<%@ page import="org.example.booksearchweb.model.MemberVO" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.booksearchweb.model.BoardVO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시판</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: center;
        }
        th {
            background-color: #f2f2f2;
        }
        a {
            text-decoration: none;
            color: #333;
        }
        a:hover {
            text-decoration: underline;
        }
        .actions a { text-decoration: none; padding: 8px 12px; background: #f2f2f2; border: 1px solid #ddd; color: #333; margin-right: 10px; }
        .actions a:hover { background: #e2e2e2; }
    </style>
</head>
<body>
<h1></h1>

<%
    MemberVO member = (MemberVO) session.getAttribute("member");
    if(member == null) {
        %>
        <h1>로그인 정보를 찾을 수 없습니다.</h1>
<%
    } else {
%>
<h1>게시판</h1>
<%--<a href="bookSearch.html">도서 검색</a>--%>

<%-- 게시판 보여주기 --%>
<div class="actions">
    <a href="boardForm.jsp">글 작성</a>
</div> <br>

<%
    List<BoardVO> boardList = (List<BoardVO>) request.getAttribute("boardList");
    System.out.println("boardlist is.......");
    System.out.println(boardList);

    if(boardList == null || boardList.isEmpty()){
%>
<p>게시글이 없습니다.</p>
<%
} else {
%>
<table>
    <thead>
    <tr>
        <th>NO.</th>
        <th>제목</th>
        <th>작성자</th>
        <th>작성일</th>
        <th>댓글 수</th>
        <th>좋아요 수</th>
        <th>조회수</th>
    </tr>
    </thead>
    <tbody>
    <%

        for(int i = 0; i < boardList.size(); i++) {
            BoardVO board = boardList.get(i);
    %>
    <tr>
        <td><%= i+1 %></td>
        <td><a href="boardDetail?id=<%= board.getId() %>"><%= board.getTitle() %></a></td>
        <td><%= board.getWriter() %></td>
        <td><%= board.getRegDate() %></td>
        <td><%= board.getCommentCount() %></td>
        <td><%= board.getLikeCount() %></td>
        <td><%= board.getViewCount() %></td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>

<%-- 검색 폼 --%>
<div class="search-container">
    <form action="boardView" method="get">
        <input type="text" name="query" placeholder="제목 또는 내용 검색" value="${param.query}" />
        <input type="submit" value="검색">
    </form>
</div>
<%
    }
%>
<% } %>
</body>
</html>
