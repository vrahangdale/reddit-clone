package com.javadaily.redditclone.service;

import com.javadaily.redditclone.dto.CommentDto;
import com.javadaily.redditclone.exceptions.PostNotFoundException;
import com.javadaily.redditclone.mapper.CommentMapper;
import com.javadaily.redditclone.model.Post;
import com.javadaily.redditclone.model.User;
import com.javadaily.redditclone.repository.CommentRepository;
import com.javadaily.redditclone.repository.PostRepository;
import com.javadaily.redditclone.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private CommentMapper commentMapper;
    private AuthService authService;
    private UserRepository userRepository;


    public void save(CommentDto commentDto) {

        Post post  = postRepository.findById(commentDto.getPostId())
                .orElseThrow(()->new PostNotFoundException("No Post found with id " + commentDto.getPostId().toString()));

        commentRepository.save(commentMapper.map(commentDto, authService.getCurrentUser(), post));
    }

    public List<CommentDto> getAllCommentByPost(Long id) {

    Post post = postRepository.findById(id).orElseThrow(()-> new PostNotFoundException("No post found with id "+ id));

        return commentRepository.findAllByPost(post).stream()
                .map(commentMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public List<CommentDto> getAllCommentsByUser(String username) {

       User user = userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("No user with name " + username));

       return commentRepository.findAllByUser(user).stream()
               .map(commentMapper::mapToDto)
               .collect(Collectors.toList());
    }
}
