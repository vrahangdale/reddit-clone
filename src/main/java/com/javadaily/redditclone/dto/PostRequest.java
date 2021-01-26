package com.javadaily.redditclone.dto;

import com.javadaily.redditclone.model.Subreddit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostRequest {

    public Long postId;
    public String postName;
    public String description;
    public String subredditName;
    public String url;

}
