<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="org.example.booksearchweb.model.MemberVO" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 작성</title>
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
<div class="form-container">
    <h1>게시글 작성</h1>
    <%
        // 세션에서 로그인된 회원 정보를 가져옴
        MemberVO member = (MemberVO) session.getAttribute("member");
        String userId = "";
        if(member != null) {
            userId = member.getId();
        }
    %>
    <form action="boardWrite" method="post">
        <label for="title">제목</label>
        <input type="text" id="title" name="title" required>

        <label for="content">내용</label>
        <textarea id="content" name="content" rows="10" required></textarea>

        <%-- 작성자 정보를 읽기 전용 혹은 hidden으로 전달 --%>
<%--        <label for="writer">작성자</label>--%>
<%--        <input type="text" id="writer" name="writer" value="<%= writer %>" readonly>--%>
        <%-- 또는 hidden field로 전달--%>
        <input type="hidden" name="userId" value="<%= userId %>">


        <input type="submit" value="작성">
    </form>
</div>
</body>
</html>
