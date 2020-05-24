package com.aliyu.ism.party;

import com.aliyu.ism.user.User;

import javax.persistence.*;
import java.util.List;

@Entity(name = "party")
public class Party {
    private Long id;
    private String abbreviation;
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
