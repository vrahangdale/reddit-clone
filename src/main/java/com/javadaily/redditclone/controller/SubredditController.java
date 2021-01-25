package com.javadaily.redditclone.controller;

import com.javadaily.redditclone.dto.SubredditDto;
import com.javadaily.redditclone.service.SubredditService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/api/subreddit")
public class SubredditController {

    private final SubredditService subredditService; // i am using the constructor injection so not using autowired here

    @PostMapping
    public ResponseEntity<SubredditDto> createSubreddit(@RequestBody SubredditDto subredditDto){

        // ResponseEntity helps in making the response more understandable for the api caller
        return ResponseEntity.status(HttpStatus.CREATED).body(subredditService.save(subredditDto));

    }

    @GetMapping
    public ResponseEntity<List<SubredditDto>> getAllSubreddit(){
        return ResponseEntity.status(HttpStatus.OK).body(subredditService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubredditDto> getSubreddit(@PathVariable Long id){

        return ResponseEntity.status(HttpStatus.OK).body(subredditService.get(id));
    }
}
