package com.example.blog.models;

import javax.persistence.*;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Integer commentId;
    @ManyToOne()
    @JoinColumn(name="post_id")
    private Post post;
    @ManyToOne()
    @JoinColumn(name="user_id")
    private UserDetails user;
    @Column(name = "comment_content")
    private String content;

    public Comment(Integer commentId, Post post, UserDetails user, String content) {
        this.commentId = commentId;
        this.post = post;
        this.user = user;
        this.content = content;
    }

    public Comment() {
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public UserDetails getUser() {
        return user;
    }

    public void setUser(UserDetails user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
