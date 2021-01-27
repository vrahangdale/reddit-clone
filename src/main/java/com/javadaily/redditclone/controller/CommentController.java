package com.javadaily.redditclone.controller;

import com.javadaily.redditclone.dto.CommentDto;
import com.javadaily.redditclone.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {

    private CommentService commentService;

    @PostMapping
    public ResponseEntity<Void> createComment(@RequestBody CommentDto commentDto){
        commentService.save(commentDto);
       return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/by-post/{postId}")
    public  List<CommentDto> getAllCommentByPostId(@PathVariable Long postId){
        List<CommentDto> commentDtoList = commentService.getAllCommentByPost(postId);

        return commentDtoList;
    }

    @GetMapping("/by-user/{username}")
    public List<CommentDto> getAllCommentByUser(@PathVariable String username){

        return commentService.getAllCommentsByUser(username);
    }


}
