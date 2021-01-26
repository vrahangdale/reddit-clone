package com.javadaily.redditclone.mapper;


import com.javadaily.redditclone.dto.PostRequest;
import com.javadaily.redditclone.dto.PostResponse;
import com.javadaily.redditclone.model.Post;
import com.javadaily.redditclone.model.Subreddit;
import com.javadaily.redditclone.model.User;
import com.javadaily.redditclone.repository.CommentRepository;
import com.javadaily.redditclone.repository.VoteRepository;
import com.javadaily.redditclone.service.AuthService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;


@Mapper(componentModel = "spring")
public abstract class PostMapper {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private AuthService authService;

    public abstract PostRequest mapToPostDto(Post post);

    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "description", source = "postRequest.description")
    @Mapping(target = "subreddit", source = "subreddit")
    //@Mapping(target = "voteCount", constant = "0")
    @Mapping(target = "user", source = "user")
    public abstract Post map(PostRequest postRequest, Subreddit subreddit, User user);

    @Mapping(target = "id", source = "postId")
    @Mapping(target = "subredditName", source = "subreddit.name")
    @Mapping(target = "userName", source = "user.username")
    /*@Mapping(target = "commentCount", expression = "java(commentCount(post))")
    @Mapping(target = "duration", expression = "java(getDuration(post))")
    @Mapping(target = "upVote", expression = "java(isPostUpVoted(post))")
    @Mapping(target = "downVote", expression = "java(isPostDownVoted(post))")*/
    public abstract PostResponse mapToDto(Post post);

}
