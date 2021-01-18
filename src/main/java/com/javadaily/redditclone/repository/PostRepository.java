package com.javadaily.redditclone.repository;

import com.javadaily.redditclone.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostRepository extends JpaRepository<Post,Long> {
}
