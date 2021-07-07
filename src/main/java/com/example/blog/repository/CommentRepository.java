package com.example.blog.repository;

import com.example.blog.models.Comment;
import com.example.blog.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> getCommentsByPostOrderByCommentIdDesc(Post id);
    @Transactional
    @Modifying
    @Query(value = "delete from Comment c where c.post.postId = :id")
    void deleteCommentsByPostId(Integer id);
}
