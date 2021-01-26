package com.javadaily.redditclone.service;

import com.javadaily.redditclone.dto.PostRequest;
import com.javadaily.redditclone.dto.PostResponse;
import com.javadaily.redditclone.exceptions.PostNotFoundException;
import com.javadaily.redditclone.exceptions.SpringRedditException;
import com.javadaily.redditclone.mapper.PostMapper;
import com.javadaily.redditclone.model.Post;
import com.javadaily.redditclone.model.Subreddit;
import com.javadaily.redditclone.model.User;
import com.javadaily.redditclone.repository.PostRepository;
import com.javadaily.redditclone.repository.SubredditRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostService {

    @Autowired
    private PostRepository postRepository;
    private SubredditRepository subredditRepository;
    private PostMapper postmapper;
    private AuthService authService;
    private PostMapper postMapper;

    public PostResponse save(PostRequest postRequest) {
        Subreddit subreddit = subredditRepository.findByName(postRequest.getSubredditName()).orElseThrow(
                ()-> new SpringRedditException("No Subreddit with name " +
                        postRequest.getSubredditName() +" found"));

        // getting the user from SecurityContext
        User user = authService.getCurrentUser();
        // map the postRequest to post
       Post post = postRepository.save(postmapper.map(postRequest,subreddit,user));

       return postMapper.mapToDto(post);

    }

    @Transactional(readOnly = true)
    public PostResponse getPost(Long id) {

            Post post = postRepository.findById(id).orElseThrow(()-> new PostNotFoundException("Post Not found with id " + id.toString()));
            return postMapper.mapToDto(post);
    }


    public List<PostResponse> getAll() {
        return postRepository.findAll().stream().map(postmapper::mapToDto).collect(Collectors.toList());
    }
}
