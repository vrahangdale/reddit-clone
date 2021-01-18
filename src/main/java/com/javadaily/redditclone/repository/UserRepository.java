package com.javadaily.redditclone.repository;

import com.javadaily.redditclone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
