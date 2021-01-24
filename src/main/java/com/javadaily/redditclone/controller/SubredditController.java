package com.javadaily.redditclone.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
@RequestMapping("/api/sudreddit")
public class SubredditController {

    @GetMapping("/all")
    public void getAllSubreddit(){

        System.out.println("I am called from subreddit");

    }
}
