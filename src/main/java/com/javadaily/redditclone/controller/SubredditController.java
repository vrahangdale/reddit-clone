package com.javadaily.redditclone.controller;

import com.javadaily.redditclone.dto.SubredditDto;
import com.javadaily.redditclone.service.SubredditService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("/api/subreddit")
public class SubredditController {

    private final SubredditService subredditService; // i am using the constructor injection so not using autowired here

    @PostMapping
    public ResponseEntity<SubredditDto> createSubreddit(@RequestBody SubredditDto subredditDto){

        return ResponseEntity.status(HttpStatus.CREATED).body(subredditService.save(subredditDto));

    }


    @GetMapping
    public void getAllSubreddit(){
        // TODO: 2021-01-23 implement the  get all subreddit feature
        System.out.println("I am called from subreddit get all");

    }
}
