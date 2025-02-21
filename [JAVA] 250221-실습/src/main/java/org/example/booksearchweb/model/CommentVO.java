package org.example.booksearchweb.model;

import java.util.Date;

public class CommentVO {
    private Integer id;       // 게시글 고유 번호
    private Integer boardId;     // 게시글
    private String userId;
    private String content;   //  내용
    private String writer;    // 작성자
    private Date regDate;     // 등록일자
    private Date modDate;     // 수정일자

    // 기본 생성자
    public CommentVO() {}

    public CommentVO(Integer boardId, String userId, String content) {
        this.boardId = boardId;
        this.userId = userId;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBoardId() {
        return boardId;
    }

    public void setBoardId(Integer boardId) {
        this.boardId = boardId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public Date getModDate() {
        return modDate;
    }

    public void setModDate(Date modDate) {
        this.modDate = modDate;
    }

    @Override
    public String toString() {
        return "CommentVO{" +
                "id=" + id +
                ", boardId=" + boardId +
                ", userId=" + userId +
                ", content='" + content + '\'' +
                ", writer='" + writer + '\'' +
                ", regDate=" + regDate +
                ", modDate=" + modDate +
                '}';
    }
}
