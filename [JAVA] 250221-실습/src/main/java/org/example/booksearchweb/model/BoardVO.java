package org.example.booksearchweb.model;

import java.util.Date;

public class BoardVO {
    private Integer id;       // 게시글 고유 번호
    private String title;     // 게시글 제목
    private String content;   // 게시글 내용
    private String writer;
    private String userId;    // 작성자 (사용자 ID)
    private Integer likeCount;
    private Integer commentCount;
    private Integer viewCount;
    private Date regDate;     // 등록일자
    private Date modDate;     // 수정일자

    // 기본 생성자
    public BoardVO() {}

    public BoardVO(Integer id, String title, String content, String writer, String userId, Integer likeCount, Integer commentCount, Integer viewCount, Date regDate, Date modDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.userId = userId;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
        this.viewCount = viewCount;
        this.regDate = regDate;
        this.modDate = modDate;
    }

    public BoardVO(String title, String content, String userId) {
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.regDate = new Date();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
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
        return "BoardVO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", writer='" + writer + '\'' +
                ", userId='" + userId + '\'' +
                ", likeCount=" + likeCount +
                ", commentCount=" + commentCount +
                ", viewCount=" + viewCount +
                ", regDate=" + regDate +
                ", modDate=" + modDate +
                '}';
    }
}
