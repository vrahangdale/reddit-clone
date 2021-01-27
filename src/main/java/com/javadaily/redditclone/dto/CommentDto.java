package com.javadaily.redditclone.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@AllArgsConstructor
@Builder
@Data
public class CommentDto {

    private Long id;
    private String text;
    private Long postId;
    private String userName;
    private Instant createdDate;

}
