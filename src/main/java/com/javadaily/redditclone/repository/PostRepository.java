package com.javadaily.redditclone.repository;

import com.javadaily.redditclone.model.Post;
import com.javadaily.redditclone.model.Subreddit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {

    public List<Post> findAllBySubreddit(Subreddit subreddit);
}
