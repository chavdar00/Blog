package com.example.blog.dto;

public class CreateCommentDTO {
    private String content;
    private Integer userId;
    private Integer postId;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
       this.content= content;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }
}
