package com.example.blog.controllers;

import com.example.blog.models.Post;
import com.example.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomePageController {
    private final PostRepository postRepository;
    @Autowired
    public HomePageController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping
    public String homepage(){
        return "index";
    }

    @ModelAttribute("posts")
    public List<Post> post(){
        List<Post> allPosts = postRepository.findAllOrderByPostIdDesc();
        return allPosts;
    }
}
