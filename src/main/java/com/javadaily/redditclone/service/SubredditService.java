package com.javadaily.redditclone.service;

import com.javadaily.redditclone.dto.SubredditDto;
import com.javadaily.redditclone.exceptions.SpringRedditException;
import com.javadaily.redditclone.mapper.SubredditMapper;
import com.javadaily.redditclone.model.Post;
import com.javadaily.redditclone.model.Subreddit;
import com.javadaily.redditclone.repository.SubredditRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class SubredditService {

    // we are using the constructor injection using the  @AllArgsConstructor
    private SubredditRepository subredditRepository;
    private SubredditMapper subredditMapper;

    private List<Post> posts;

    @Transactional
    public SubredditDto save(SubredditDto subredditDto) {

        Subreddit subreddit = subredditRepository.save(subredditMapper.mapDtoToSubreddit(subredditDto));

         subredditDto.setId(subreddit.getId());

         return subredditDto;

    }
    // we don't need this as we are using the mapstruct
//    private Subreddit mapSubredditDto(SubredditDto subredditDto) {
//        return Subreddit.builder().name(subredditDto.getName())
//                .description(subredditDto.getDescription())
//                .build();
//    }

    @Transactional(readOnly = true)
    public List<SubredditDto> getAll() {
         return subredditRepository.findAll()
         .stream()
         .map(subredditMapper::mapToSubredditDto)
                .collect(Collectors.toList());
    }

    public SubredditDto get(Long id) {
        Subreddit subreddit = subredditRepository.findById(id).orElseThrow(()-> new SpringRedditException("No Subreddit found with id" + id));

        return subredditMapper.mapToSubredditDto(subreddit);
    }

//    private SubredditDto mapToDto(Subreddit subreddit) {
//            return SubredditDto.builder().name(subreddit.getName())
//                    .id(subreddit.getId())
//                    .numberOfPosts(subreddit.getPosts().size())
//                    .build();
//
//    }
}
