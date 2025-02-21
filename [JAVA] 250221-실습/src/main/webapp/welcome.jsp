<%@ page import="org.example.booksearchweb.model.MemberVO" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.booksearchweb.model.BoardVO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>환영합니다</title>
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
<%
  MemberVO member = (MemberVO) session.getAttribute("member");
  if(member == null) {
    out.println("로그인 정보를 찾을 수 없습니다.");
  } else {
%>
<h1><%= member.getName() %> 님 환영합니다</h1>
<%--<a href="bookSearch.html">도서 검색</a>--%>
<a href="boardView">게시판 보기</a>
<a href="logout">로그아웃</a>

<% } %>


</body>
</html>
