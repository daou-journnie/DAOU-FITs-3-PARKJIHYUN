<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="org.example.booksearchweb.model.BoardVO" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 수정</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        .form-container { max-width: 600px; margin: auto; }
        input[type="text"], textarea {
            width: 100%;
            padding: 8px;
            margin: 6px 0;
            box-sizing: border-box;
        }
        input[type="submit"] {
            padding: 10px 20px;
            background-color: #4CAF50;
            border: none;
            color: white;
            cursor: pointer;
        }
        input[type="submit"]:hover { background-color: #45a049; }
    </style>
</head>
<body>
<%
    BoardVO board = (BoardVO) request.getAttribute("board");
    if(board == null) {
%>
<p>수정할 게시글을 찾을 수 없습니다.</p>
<%
} else {
%>
<div class="form-container">
    <h1>게시글 수정</h1>
    <form action="boardEdit" method="post">
        <!-- 게시글 ID는 hidden 필드로 전달 -->
        <input type="hidden" name="id" value="<%= board.getId() %>">
        <label for="title">제목</label>
        <input type="text" id="title" name="title" value="<%= board.getTitle() %>" required>

        <label for="content">내용</label>
        <textarea id="content" name="content" rows="10" required><%= board.getContent() %></textarea>

        <input type="submit" value="수정">
    </form>
</div>
<%
    }
%>
</body>
</html>
