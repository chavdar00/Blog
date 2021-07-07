package com.example.blog.repository;

import com.example.blog.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query(value = "select p from Post as p order by p.postId desc")
    List<Post> findAllOrderByPostIdDesc();

    @Query(value = "select u.userName from Post as p join p.user as u where p.postId = :postId")
    String getPostOwnerByPostId(Integer postId);

    @Query(value = "select p from Post as p where p.user.userName= :username")
    List<Post> getPostsByUserName(String username);
}
