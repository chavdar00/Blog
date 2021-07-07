package com.example.blog.controllers;

import com.example.blog.dto.EditUserDTO;
import com.example.blog.models.Post;
import com.example.blog.models.UserDetails;
import com.example.blog.repository.PostRepository;
import com.example.blog.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserRepository userRepository;
    private  final PostRepository postRepository;

    public UserController(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @GetMapping("/{id}")
    public String showUserPage(@PathVariable int id, Principal principal, Model model){
        UserDetails userDetails;
        userDetails = userRepository.getById(id);
        List<Post> userPosts = postRepository.getPostsByUserName(userDetails.getUserName());
        if(userDetails.getUserName().equals(principal.getName())){
            model.addAttribute("Flag",true);
        }else model.addAttribute("Flag",false);
        model.addAttribute("user",userDetails);
        model.addAttribute("userPosts",userPosts);
        return "user-page";
    }
    @GetMapping
    public String showCurrentUserPage( Principal principal, Model model)
    {
        UserDetails userDetails = userRepository.getUserDetailsByUserName(principal.getName());
        List<Post> userPosts = postRepository.getPostsByUserName(userDetails.getUserName());
        model.addAttribute("Flag",true);
        model.addAttribute("user",userDetails);
        model.addAttribute("userPosts",userPosts);
        return "user-page";
    }
    @GetMapping("/edit")
    public String loadUserDetails(Principal principal,Model model){
        UserDetails userDetails = userRepository.getUserDetailsByUserName(principal.getName());
        model.addAttribute("user",userDetails);
        return "edit-user";
    }
    @PostMapping("/edit")
    public String editUserPage(Principal principal, EditUserDTO editUserDTO){
        UserDetails userDetails = userRepository.getUserDetailsByUserName(principal.getName());
        userDetails.setEmail(editUserDTO.getEmail());
        userDetails.setFirstName(editUserDTO.getFirstName());
        userDetails.setLastName(editUserDTO.getLastName());
        userRepository.save(userDetails);
        return "redirect:/user/"+userDetails.getId();
    }
}
