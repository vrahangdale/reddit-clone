package com.javadaily.redditclone.repository;

import com.javadaily.redditclone.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
