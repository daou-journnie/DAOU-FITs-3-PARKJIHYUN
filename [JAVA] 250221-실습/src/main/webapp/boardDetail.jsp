<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="org.example.booksearchweb.model.BoardVO" %>
<%@ page import="org.example.booksearchweb.model.MemberVO" %>
<%@ page import="org.example.booksearchweb.model.CommentVO" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

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
  // 세션에서 로그인된 회원 정보를 가져옴
  MemberVO member = (MemberVO) session.getAttribute("member");
  String userId = "";
  if(member != null) {
    userId = member.getId();
  }

  BoardVO board = (BoardVO) request.getAttribute("board");
  List<CommentVO> commentList = (List<CommentVO>) request.getAttribute("commentList");
  boolean isAuthor = board.getUserId().equals(userId);
  boolean isLikedByCurrentUser = (boolean) request.getAttribute("isLikedByCurrentUser");
  if(board == null){
%>
<p>게시글을 찾을 수 없습니다.</p>
<%
} else {
%>
<!-- 게시글 상세보기 (기존 코드 위/아래에 추가) -->
<div class="board-detail">
  <h2><%= board.getTitle() %></h2>
  <div class="info">
    작성자: <%= board.getWriter() %> | 등록일: <%= board.getRegDate() %> | 수정일: <%= board.getModDate() %>
  </div>
  <div class="content">
    <%= board.getContent() %>
  </div>

  <!-- 좋아요 섹션 -->
  <div class="like-section">
    <script>
      console.log(<%= isLikedByCurrentUser %>)

    </script>
    <!-- 본인 게시글이면 좋아요 버튼 숨김 -->
    <%
      if(!isAuthor) { %>
    <button id="likeBtn" data-board-id="<%= board.getId() %>">
      <% if(isLikedByCurrentUser){ %>
      좋아요 취소
      <% } else { %>
      좋아요
      <% } %>
    </button>
    <% } %>
    <span id="likeCount">좋아요 수: <%= board.getLikeCount() %></span>
  </div>

<%--  <!-- 댓글 섹션 -->--%>
<%--  <div class="commentList">--%>
<%--    <h3>댓글</h3>--%>
<%--    <%--%>
<%--      for(int i = 0; i < commentList.size(); i++) {--%>
<%--        CommentVO comment = commentList.get(i);--%>
<%--    %>--%>
<%--    <div class="commentList">--%>
<%--      <p><strong><%= comment.getWriter() %></strong> - <%= comment.getRegDate() %></p>--%>
<%--      <p><%= comment.getContent() %></p>--%>
<%--    </div>--%>
<%--    <%--%>
<%--      }--%>
<%--    %>--%>
    <!-- 댓글 등록 폼 -->
<%--    <div class="comment-write-form">--%>
<%--    <form action="commentWrite" method="post">--%>
<%--      <input type="hidden" name="boardId" value="<%= board.getId() %>" />--%>
<%--      <textarea name="content" rows="3" cols="50" placeholder="댓글을 입력하세요"></textarea><br/>--%>
<%--      <input type="hidden" name="userId" value="<%= userId %>" />--%>
<%--      <input type="submit" value="작성">--%>
<%--    </form>--%>
<%--  </div>--%>

    <!-- 댓글 목록 영역 -->
    <div id="commentList">
      <% for(CommentVO comment : commentList) { %>
      <div class="comment" id="comment-<%= comment.getId() %>">
        <p><strong><%= comment.getWriter() %></strong> - <%= comment.getRegDate() %></p>
        <p><%= comment.getContent() %></p>
        <%-- 만약 현재 로그인한 사용자와 댓글 작성자가 같다면 삭제 버튼 표시 --%>
        <%
          System.out.println(comment.getUserId() + " " + userId);
          if((comment.getUserId()).equals(userId)) {
        %>
        <button class="deleteComment" data-comment-id="<%= comment.getId() %>">삭제</button>
        <% } %>
      </div>
      <% } %>

    </div>

    <!-- 댓글 등록 폼 부분 -->
    <div class="comment-write-form">
      <input type="hidden" id="commentBoardId" value="<%= board.getId() %>" />
      <textarea id="commentContent" name="content" rows="3" cols="50" placeholder="댓글을 입력하세요"></textarea><br/>
      <input type="hidden" id="commentUserId" value="<%= userId %>" />
      <button id="commentSubmit">작성</button>
    </div>



    <script>
        $(document).ready(function(){
            // 댓글 등록 이벤트 처리
            $("#commentSubmit").on("click", function(){
                var boardId = $("#commentBoardId").val();
                var userId = $("#commentUserId").val();
                var content = $("#commentContent").val();
                console.log("댓글 등록 요청:", boardId, userId, content);

                $.ajax({
                    url: "commentWrite",
                    type: "POST",
                    data: {
                        boardId: boardId,
                      userId: userId,
                        content: content
                    },
                    dataType: "json",
                    success: function(response) {
                        console.log(response.userId, userId);
                        // 새 댓글 HTML 생성 (서버에서 받은 JSON에 id, writer, content, regDate 포함)
                        var newCommentHtml = '<div class="comment" id="comment-' + response.id + '">' +
                            '<p><strong>' + response.writer + '</strong> - ' + response.regDate + '</p>' +
                            '<p>' + response.content + '</p>';
                        // 현재 로그인한 사용자와 댓글 작성자가 같으면 삭제 버튼 추가
                        if(response.userId === '<%= userId %>'){
                            newCommentHtml += '<button class="deleteComment" data-comment-id="' + response.id + '">삭제</button>';
                        }
                        newCommentHtml += '</div>';
                        $("#commentList").append(newCommentHtml);
                        // 댓글 입력창 초기화
                        $("#commentContent").val('');
                    },
                    error: function(xhr, status, error) {
                        alert("댓글 작성 중 오류 발생: " + error);
                    }
                });
            });

            // 댓글 삭제 이벤트 위임 처리: 새로 추가된 댓글에도 적용됨.
            $("#commentList").on("click", ".deleteComment", function(){
                var commentId = $(this).data("comment-id");
                $.ajax({
                    url: "commentDelete",
                    type: "POST",
                    data: { commentId: commentId },
                    dataType: "json",
                    success: function(response) {
                        if(response.success) {
                            $("#comment-" + commentId).remove();
                        } else {
                            alert("댓글 삭제에 실패했습니다.");
                        }
                    },
                    error: function(xhr, status, error) {
                        alert("댓글 삭제 중 오류가 발생했습니다: " + error);
                    }
                });
            });

          $("#likeBtn").on("click", function(){
            var boardId = $(this).data("board-id");
            // 현재 버튼의 텍스트를 확인해서, 좋아요 추가인지 취소인지 판단
            var isLiked = ($(this).text().trim() === "좋아요 취소");
            $.ajax({
              url: "boardLike",  // 좋아요 처리를 위한 서블릿 URL
              type: "POST",
              xhrFields: { withCredentials: true },

              data: {
                boardId: boardId,
                action: isLiked ? "unlike" : "like"
              },
              dataType: "json",
              success: function(response) {
                // 응답으로 업데이트된 좋아요 수와 상태를 받는다고 가정
                $("#likeCount").text("좋아요 수: " + response.likeCount);
                if(response.liked) {
                  $("#likeBtn").text("좋아요 취소");
                } else {
                  $("#likeBtn").text("좋아요");
                }
              },
              error: function(xhr, status, error) {
                alert("좋아요 처리 중 오류 발생: " + error);
              }
            });
          });
        });


    </script>




    <br>
  <div class="actions">
    <a href="boardView">목록보기</a>
    <!-- ...기존 삭제, 수정 링크... -->
    <%
      if(board.getUserId().equals(userId)){
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
