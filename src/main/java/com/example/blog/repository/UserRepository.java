package com.example.blog.repository;

import com.example.blog.models.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserDetails, Integer> {

    public UserDetails getUserDetailsByUserName(String userName);

    public UserDetails getUserDetailsByEmail(String userEmail);

}
