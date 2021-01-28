package com.javadaily.redditclone.repository;

import com.javadaily.redditclone.model.Post;
import com.javadaily.redditclone.model.User;
import com.javadaily.redditclone.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote,Long> {
    // finding the user by post and order the result by vote id desc
    // i.e getting the recent vote by a particular user for a post
    Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, User currentUser);
}
