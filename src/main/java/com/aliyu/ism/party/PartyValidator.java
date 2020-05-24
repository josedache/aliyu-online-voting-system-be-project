package com.aliyu.ism.party;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class PartyValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Party.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "whitespace");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "abbreviation", "whitespace");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "whitespace");

        Party party = (Party) target;
    }
}
