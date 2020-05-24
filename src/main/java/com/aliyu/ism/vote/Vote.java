package com.aliyu.ism.vote;

import com.aliyu.ism.user.User;

import javax.persistence.*;

@Entity(name = "vote")
public class Vote {
    private Long id;
    private User voter;
    private User candidate;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToOne
    public User getVoter() {
        return voter;
    }

    public void setVoter(User voter) {
        this.voter = voter;
    }

    @OneToOne
    public User getCandidate() {
        return candidate;
    }

    public void setCandidate(User candidate) {
        this.candidate = candidate;
    }
}
