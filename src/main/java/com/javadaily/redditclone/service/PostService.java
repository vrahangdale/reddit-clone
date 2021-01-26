package com.javadaily.redditclone.service;

import com.javadaily.redditclone.dto.PostRequest;
import com.javadaily.redditclone.dto.PostResponse;
import com.javadaily.redditclone.exceptions.PostNotFoundException;
import com.javadaily.redditclone.exceptions.SpringRedditException;
import com.javadaily.redditclone.exceptions.SubredditNotFoundException;
import com.javadaily.redditclone.mapper.PostMapper;
import com.javadaily.redditclone.model.Post;
import com.javadaily.redditclone.model.Subreddit;
import com.javadaily.redditclone.model.User;
import com.javadaily.redditclone.repository.PostRepository;
import com.javadaily.redditclone.repository.SubredditRepository;
import com.javadaily.redditclone.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    private UserRepository userRepository;

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

    @Transactional(readOnly = true)
    public List<PostResponse> getAll() {
        return postRepository.findAll()
                .stream()
                .map(postmapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getAllPostBySubreddit(Long id) {
        Subreddit subreddit = subredditRepository.findById(id).orElseThrow(()-> new SubredditNotFoundException("No Subreddit with id "+ id));


         return postRepository.findAllBySubreddit(subreddit)
                 .stream()
                 .map(postmapper::mapToDto)
                 .collect(Collectors.toList());

    }

    @Transactional(readOnly = true)
    public List<PostResponse> getAllPostByUser(String name) {


        // get the user from data base nad then return not the logged in user
        // the logged in user will be used under the profile of a user
        //User user = authService.getCurrentUser();
        User user = userRepository.findByUsername(name).orElseThrow(()-> new UsernameNotFoundException("No User with Username "+ name));

        return postRepository.findByUser(user).stream()
                .map(postmapper::mapToDto)
                .collect(Collectors.toList());

    }
}
