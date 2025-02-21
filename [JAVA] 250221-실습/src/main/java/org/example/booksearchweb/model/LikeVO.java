package org.example.booksearchweb.model;

public class LikeVO {
    private int boardId;
    private String userId;

    public LikeVO(int boardId, String userId) {
        this.boardId = boardId;
        this.userId = userId;
    }
    // getter, setter 생략

    public int getBoardId() {
        return boardId;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
