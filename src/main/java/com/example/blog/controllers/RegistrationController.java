package com.example.blog.controllers;

import com.example.blog.dto.UserRegistrationDTO;
import com.example.blog.models.UserDetails;
import com.example.blog.repository.RoleRepository;
import com.example.blog.repository.UserRepository;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    public final UserRepository userRepository;
    public final UserDetailsManager userDetailsManager;
    public final RoleRepository roleRepository;

    @Autowired
    public RegistrationController(UserRepository userRepository, UserDetailsManager userDetailsManager, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.userDetailsManager = userDetailsManager;
        this.roleRepository = roleRepository;
    }

    @GetMapping
    public String showRegistrationPage(Model model) {
        model.addAttribute("user", new UserRegistrationDTO());
        return "register";
    }

    @PostMapping
    public String registerUser(UserRegistrationDTO user,
                               Model model,
                               BindingResult bindingResult) {
        //check for invalid input

        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "Username/Password/First name/Last name can't be empty ant Username/Password/First name/Last name should be between 2 and 25 symbols:(");
            return "register";
        }

        if (userDetailsManager.userExists(user.getUserName())) {
            model.addAttribute("user", user);
            model.addAttribute("error", "User with the same username already exists!");
            return "register";
        }

        if (userRepository.getUserDetailsByEmail(user.getEmail()) != null){
            model.addAttribute("user", user);
            model.addAttribute("error", "User with the same email already exists!");
            return "register";
        }

        if (!user.getPassword().equals(user.getConfirmPassword())) {
            model.addAttribute("user", user);
            model.addAttribute("error", "Password confirmation doesn't match password!");
            return "register";
        }
        //create user
        UserDetails userDetails = new UserDetails();
        userDetails.setFirstName(user.getFirstName());
        userDetails.setLastName(user.getLastName());
        userDetails.setEmail(user.getEmail());
        userDetails.setUserName(user.getUserName());
        userDetails.setUserPassword(user.getPassword());
        userDetails.setActive(true);
        userDetails.setRole(roleRepository.getById(2));

        //create spring security user
        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("USER_USER");
        org.springframework.security.core.userdetails.User newUser =
                new org.springframework.security.core.userdetails.User(
                        user.getUserName(), "{noop}" + user.getPassword(), authorities);

        userDetailsManager.createUser((newUser));
        userRepository.save(userDetails);

        return "login";
    }
}
