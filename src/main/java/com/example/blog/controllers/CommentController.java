package com.example.blog.controllers;

import com.example.blog.dto.CreateCommentDTO;
import com.example.blog.models.Comment;
import com.example.blog.models.Post;
import com.example.blog.repository.CommentRepository;
import com.example.blog.repository.PostRepository;
import com.example.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class CommentController {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    @Autowired
    public CommentController(UserRepository userRepository, PostRepository postRepository, CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @PostMapping("/comment")
    public String AddComment(CreateCommentDTO createCommentDTO,
                             Principal principal){
        Comment comment = new Comment();
        Post post = postRepository.getById(createCommentDTO.getPostId());
        comment.setContent(createCommentDTO.getContent());
        comment.setUser(userRepository.getUserDetailsByUserName(principal.getName()));
        comment.setPost(post);
        commentRepository.save(comment);

        return "redirect:/post/"+post.getPostId();
    }
}
