<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>로그인 페이지</title>
    <style>
        body { font-family: Arial, sans-serif; }
        .login-container {
            width: 300px;
            margin: 100px auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        .login-container h2 { text-align: center; }
        .login-container label { display: block; margin-top: 10px; }
        .login-container input[type="text"],
        .login-container input[type="password"] {
            width: 100%;
            padding: 8px;
            margin-top: 5px;
            box-sizing: border-box;
        }
        .login-container input[type="submit"] {
            width: 100%;
            padding: 10px;
            margin-top: 15px;
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
        }
        .login-container input[type="submit"]:hover { background-color: #45a049; }
    </style>
</head>
<body>
<%
    String error = (String)request.getAttribute("error");
    if(error == null){
%>
<div class="login-container">
    <h2>로그인</h2>
    <form action="login" method="post">
        <label for="id">아이디</label>
        <input type="text" id="id" name="id" required>

        <label for="password">비밀번호</label>
        <input type="password" id="password" name="password" required>

        <input type="submit" value="로그인">
    </form>
</div>
<%
} else {
%>
<script type="text/javascript">
    alert("<%= error %>");
    window.location.href = "login.jsp";
</script>
<%
    }
%>
</body>
</html>
