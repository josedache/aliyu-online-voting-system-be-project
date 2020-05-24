package com.aliyu.ism.vote;

import com.aliyu.ism.Utils;
import com.aliyu.ism.user.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/vote")
public class VoteController {

    @Autowired
    private VoteRepository voteRepository;

    @PostMapping
    public ResponseEntity<Vote> vote(
            @Validated @RequestBody Vote vote, BindingResult bindingResult
    ) {

        if (Utils.validationErrorCheck(bindingResult)) {
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity
                .ok(voteRepository.save(vote));
    }

    @GetMapping
    public ResponseEntity<List<Vote>> getAllVote() {
        return ResponseEntity.ok(voteRepository.findAll());
    }

    @GetMapping("{position}")
    public List<Vote> getVoteResults(@PathVariable Post position) {
        if (position == null) return Collections.emptyList();
        return voteRepository.findVoteByCandidate_Post(position);
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.addValidators(new VoteValidator());
    }

}
