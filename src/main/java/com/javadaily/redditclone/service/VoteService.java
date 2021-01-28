package com.javadaily.redditclone.service;

import com.javadaily.redditclone.dto.VoteDto;
import com.javadaily.redditclone.exceptions.PostNotFoundException;
import com.javadaily.redditclone.exceptions.SpringRedditException;
import com.javadaily.redditclone.model.Post;
import com.javadaily.redditclone.model.User;
import com.javadaily.redditclone.model.Vote;
import com.javadaily.redditclone.model.VoteType;
import com.javadaily.redditclone.repository.PostRepository;
import com.javadaily.redditclone.repository.VoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class VoteService {

    private PostRepository postRepository;
    private VoteRepository voteRepository;
    private AuthService authService;

    public void vote(VoteDto voteDto) {

       User user = authService.getCurrentUser();
       // get the post for which the vote is for
      Post post =  postRepository.findById(voteDto.getPostId()).orElseThrow(()-> new PostNotFoundException("No Post with id " + voteDto.getPostId() ));

      Vote vote = Vote.builder().voteType(voteDto.getVoteType()).post(post).user(user).build();

      // doing this so that we can only upvote and downvote a post by a single user
        Optional<Vote> voteByPostAndUser = voteRepository.findTopByPostAndUserOrderByVoteIdDesc(post, authService.getCurrentUser());
        if (voteByPostAndUser.isPresent() &&
                voteByPostAndUser.get().getVoteType()
                        .equals(voteDto.getVoteType())) {
            throw new SpringRedditException("You have already "
                    + voteDto.getVoteType() + "'d for this post");
        }

      //find the type of vote and increment or decrement the vote on post
        if(vote.getVoteType().equals(VoteType.UPVOTE) ){
           post.setVoteCount(post.getVoteCount() + 1);
        }else{
            post.setVoteCount(post.getVoteCount() - 1);
        }

        voteRepository.save(vote);
        postRepository.save(post);


    }
}
