package com.javadaily.redditclone.controller;

import com.javadaily.redditclone.dto.PostRequest;
import com.javadaily.redditclone.dto.PostResponse;
import com.javadaily.redditclone.mapper.PostMapper;
import com.javadaily.redditclone.service.PostService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor
@Slf4j
public class PostController {

    private PostService postservice;


    @PostMapping
    public ResponseEntity<PostResponse> createPost(@RequestBody PostRequest postRequest){

        return ResponseEntity.status(HttpStatus.CREATED).body(postservice.save(postRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getPost(@PathVariable Long id){
        try {
            PostResponse post = postservice.getPost(id);
            return ResponseEntity.status(HttpStatus.OK).body(post);
        }catch (Exception e){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(PostResponse.builder().build());
        }

    }

    @GetMapping
    public List<PostResponse> getAllPosts(){
        return postservice.getAll();
    }
}
