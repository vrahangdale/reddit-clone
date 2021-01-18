package com.javadaily.redditclone.repository;

import com.javadaily.redditclone.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerficationTokenRepository extends JpaRepository<VerificationToken, Long> {
}
