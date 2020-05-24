package com.aliyu.ism.vote;

import com.aliyu.ism.user.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

    List<Vote> findVoteByCandidate_Post(Post post);
}
