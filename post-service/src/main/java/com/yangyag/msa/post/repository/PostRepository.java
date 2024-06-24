package com.yangyag.msa.post.repository;


import com.yangyag.msa.post.model.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostRepository extends JpaRepository<Post, Long> {
}
