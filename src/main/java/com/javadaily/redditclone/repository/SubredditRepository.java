package com.javadaily.redditclone.repository;

import com.javadaily.redditclone.model.Subreddit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubredditRepository extends JpaRepository<Subreddit,Long> {
}
