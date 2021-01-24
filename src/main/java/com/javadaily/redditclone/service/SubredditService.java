package com.javadaily.redditclone.service;

import com.javadaily.redditclone.dto.SubredditDto;
import com.javadaily.redditclone.model.Post;
import com.javadaily.redditclone.model.Subreddit;
import com.javadaily.redditclone.repository.SubredditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service

public class SubredditService {

    @Autowired
    private SubredditRepository subredditRepository;

    private List<Post> posts;

    @Transactional
    public SubredditDto save(SubredditDto subredditDto) {

        Subreddit subreddit = subredditRepository.save(mapSubredditDto(subredditDto));

         subredditDto.setId(subreddit.getId());

         return subredditDto;

    }

    private Subreddit mapSubredditDto(SubredditDto subredditDto) {
        return Subreddit.builder().name(subredditDto.getName())
                .description(subredditDto.getDescription())
                .build();
    }
}
