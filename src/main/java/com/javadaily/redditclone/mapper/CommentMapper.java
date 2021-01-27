package com.javadaily.redditclone.mapper;

import com.javadaily.redditclone.dto.CommentDto;
import com.javadaily.redditclone.model.Comment;
import com.javadaily.redditclone.model.Post;
import com.javadaily.redditclone.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "text", source = "commentDto.text")
    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "post", source = "post")
    @Mapping(target = "user", source = "user")
    public Comment map(CommentDto commentDto, User user, Post post);

    @Mapping(target = "postId", expression = "java(comment.getPost().getPostId())")
    @Mapping(target = "userName", expression = "java(comment.getUser().getUsername())")
    public CommentDto mapToDto(Comment comment);
}
