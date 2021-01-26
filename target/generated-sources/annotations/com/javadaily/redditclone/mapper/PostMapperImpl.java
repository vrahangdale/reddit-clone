package com.javadaily.redditclone.mapper;

import com.javadaily.redditclone.dto.PostRequest;
import com.javadaily.redditclone.dto.PostRequest.PostRequestBuilder;
import com.javadaily.redditclone.dto.PostResponse;
import com.javadaily.redditclone.model.Post;
import com.javadaily.redditclone.model.Post.PostBuilder;
import com.javadaily.redditclone.model.Subreddit;
import com.javadaily.redditclone.model.User;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-01-25T16:48:58-0800",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 11.0.3 (JetBrains s.r.o)"
)
@Component
public class PostMapperImpl extends PostMapper {

    @Override
    public PostRequest mapToPostDto(Post post) {
        if ( post == null ) {
            return null;
        }

        PostRequestBuilder postRequest = PostRequest.builder();

        postRequest.postId( post.getPostId() );
        postRequest.postName( post.getPostName() );
        postRequest.description( post.getDescription() );
        postRequest.url( post.getUrl() );

        return postRequest.build();
    }

    @Override
    public Post map(PostRequest postRequest, Subreddit subreddit, User user) {
        if ( postRequest == null && subreddit == null && user == null ) {
            return null;
        }

        PostBuilder post = Post.builder();

        if ( postRequest != null ) {
            post.description( postRequest.getDescription() );
            post.postId( postRequest.getPostId() );
            post.postName( postRequest.getPostName() );
            post.url( postRequest.getUrl() );
        }
        if ( subreddit != null ) {
            post.subreddit( subreddit );
        }
        if ( user != null ) {
            post.user( user );
        }
        post.createdDate( java.time.Instant.now() );

        return post.build();
    }

    @Override
    public PostResponse mapToDto(Post post) {
        if ( post == null ) {
            return null;
        }

        PostResponse postResponse = new PostResponse();

        postResponse.setId( post.getPostId() );
        postResponse.setSubredditName( postSubredditName( post ) );
        postResponse.setUserName( postUserUsername( post ) );
        postResponse.setPostName( post.getPostName() );
        postResponse.setUrl( post.getUrl() );
        postResponse.setDescription( post.getDescription() );

        return postResponse;
    }

    private String postSubredditName(Post post) {
        if ( post == null ) {
            return null;
        }
        Subreddit subreddit = post.getSubreddit();
        if ( subreddit == null ) {
            return null;
        }
        String name = subreddit.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private String postUserUsername(Post post) {
        if ( post == null ) {
            return null;
        }
        User user = post.getUser();
        if ( user == null ) {
            return null;
        }
        String username = user.getUsername();
        if ( username == null ) {
            return null;
        }
        return username;
    }
}
