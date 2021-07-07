package com.example.blog.models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Integer postId;
    @Column(name = "post_name")
    private String title;
    @Column(name = "post_content")
    private String content;
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private UserDetails user;
    @Column(name = "uploadat")
    private String uploadAt;

    public Post(Integer postId, String title, String content, UserDetails user, String uploadAt) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.user = user;
        this.uploadAt = uploadAt;
    }

    public String getUploadAt() {
        return uploadAt;
    }

    public void setUploadAt(String uploadAt) {
        this.uploadAt = uploadAt;
    }

    public Post(Integer postId, String title, String content, UserDetails user) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.user = user;
    }

    public Post() {
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
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

    public UserDetails getUser() {
        return user;
    }

    public void setUser(UserDetails user) {
        this.user = user;
    }
}
