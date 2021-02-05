package com.javadaily.redditclone.mapper;

import com.javadaily.redditclone.dto.CommentDto;
import com.javadaily.redditclone.dto.CommentDto.CommentDtoBuilder;
import com.javadaily.redditclone.model.Comment;
import com.javadaily.redditclone.model.Post;
import com.javadaily.redditclone.model.User;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-02-05T11:57:13-0800",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 11.0.3 (JetBrains s.r.o)"
)
@Component
public class CommentMapperImpl implements CommentMapper {

    @Override
    public Comment map(CommentDto commentDto, User user, Post post) {
        if ( commentDto == null && user == null && post == null ) {
            return null;
        }

        Comment comment = new Comment();

        if ( commentDto != null ) {
            comment.setText( commentDto.getText() );
        }
        if ( user != null ) {
            comment.setUser( user );
        }
        if ( post != null ) {
            comment.setPost( post );
        }
        comment.setCreatedDate( java.time.Instant.now() );

        return comment;
    }

    @Override
    public CommentDto mapToDto(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        CommentDtoBuilder commentDto = CommentDto.builder();

        commentDto.id( comment.getId() );
        commentDto.text( comment.getText() );
        commentDto.createdDate( comment.getCreatedDate() );

        commentDto.postId( comment.getPost().getPostId() );
        commentDto.userName( comment.getUser().getUsername() );

        return commentDto.build();
    }
}
