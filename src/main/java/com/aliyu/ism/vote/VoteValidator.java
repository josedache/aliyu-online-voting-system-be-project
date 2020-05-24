package com.aliyu.ism.vote;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class VoteValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Vote.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "id.null");
        ValidationUtils.rejectIfEmpty(errors, "candidate", "candidate.null");
        ValidationUtils.rejectIfEmpty(errors, "voter", "voter.null");

    }
}
