package com.example.demo.entity;

/**
 * @AUTHOR：wanderlustLee
 * @DATE:Create in 16:57 2018-06-29
 */
public class Comment {
    private int id;
    private String content;
    private String commentTime;
    private String commentUsername;
    private String commentDiaryID;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }

    public String getCommentUsername() {
        return commentUsername;
    }

    public void setCommentUsername(String commentUsername) {
        this.commentUsername = commentUsername;
    }

    public String getCommentDiaryID() {
        return commentDiaryID;
    }

    public void setCommentDiaryID(String commentDiaryID) {
        this.commentDiaryID = commentDiaryID;
    }
}
