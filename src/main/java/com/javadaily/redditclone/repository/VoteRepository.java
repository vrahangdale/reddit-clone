package com.javadaily.redditclone.repository;

import com.javadaily.redditclone.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote,Long> {
}
