package com.example.blog.controllers;

import com.example.blog.dto.CreatePostDTO;
import com.example.blog.dto.EditPostDTO;
import com.example.blog.models.Comment;
import com.example.blog.models.Post;
import com.example.blog.repository.CommentRepository;
import com.example.blog.repository.PostRepository;
import com.example.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/post")
public class PostController {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Autowired
    public PostController(PostRepository postRepository, CommentRepository commentRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    @GetMapping()
    public String showPostPage() {
        return "post-page";
    }

    @GetMapping("/post-form")
    public String showCreatePostPage() {
        return "create-post";
    }

    @GetMapping("/{id}")
    public String showPostById(@PathVariable int id,
                               Model model,
                               Principal principal) {
        Post post = postRepository.getById(id);

        //todo make postDto
        //todo mapper
        //todo Post->PostDto
        String postOwnerName = postRepository.getPostOwnerByPostId(id);
        /*System.out.printf("--------------->" + postOwnerName + "----------------"+principal.getName());*/
        List<Comment> comments = commentRepository.getCommentsByPostOrderByCommentIdDesc(post);
        if(postOwnerName.equals(principal.getName())){
            model.addAttribute("Flag",true);
        }else model.addAttribute("Flag",false);
        model.addAttribute("post", post);
        model.addAttribute("comments", comments);
        return "post-page";
    }

    @PostMapping
    public String createPost(CreatePostDTO createPostDTO,
                             Principal principal,
                             Model model) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date today = new Date();
        String strDate = dateFormat.format(today);

        Post post = new Post();
        post.setTitle(createPostDTO.getTitle());
        post.setContent(createPostDTO.getContent());
        post.setUser(userRepository.getUserDetailsByUserName(principal.getName()));
        post.setUploadAt(strDate);
        postRepository.save(post);
        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public String editPost(@PathVariable int id, Model model) {
        Post post = postRepository.getById(id);
        model.addAttribute("post", post);
        return "edit-post";
    }
    @PostMapping("/edit")
    public String editPost(EditPostDTO editPostDTO){
        Post post = postRepository.getById(editPostDTO.getPostId());
        post.setTitle(editPostDTO.getTitle());
        post.setContent(editPostDTO.getContent());
        postRepository.save(post);
        return "redirect:/post/"+ post.getPostId();
    }
    @PostMapping("/{id}/delete")
    public String deleteOwnPost(@PathVariable int id)
    {
        commentRepository.deleteCommentsByPostId(id);
        postRepository.deleteById(id);
        return "redirect:/";
    }
}
